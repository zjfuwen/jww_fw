layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var deptData = '';
	var size = 2;//每页出现的数据量
    page();

    //查询
    $(".search_btn").click(function(){
        var newArray = [];
        if($(".search_input").val() != ''){
            var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            page();
            layer.close(index);
        }else{
            layer.msg("请输入需要查询的内容");
        }
    })

	//添加部门
	//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
	$(window).one("resize",function(){
		$(".deptAdd_btn").click(function(){
			var index = layui.layer.open({
				title : "添加部门",
				type : 2,
				content : "deptAdd.html",
				success : function(layero, index){
					setTimeout(function(){
						layui.layer.tips('点击此处返回部门列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					},500)
				}
			})			
			layui.layer.full(index);
		})
	}).resize();

	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//是否禁用
	form.on('switch(isShow)', function(data){
		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
			layer.msg("状态修改成功！");
        },2000);
	})

	function page(current){
        current = current==null?1:current;
        var deptName = $(".search_input").val();
        if(deptName == ''){
            deptName = 'all';
        }
        $.ajax({
            url: "http://localhost:8089/dept/query/"+current+"/"+size+"/"+deptName,
            success: function(data){
                deptData = data;
                //执行加载数据的方法
                var dataHtml = '';
                if(deptData.code!=200){
                    dataHtml = '<tr><td colspan="8">查询异常</td></tr>';
                    return dataHtml;
                }
                currData = deptData.data.records;//.concat().splice(curr*nums-nums, nums);
                if(deptData.data.total != 0){
                    for(var i=0;i<currData.length;i++){
                        dataHtml += '<tr>'
                            +'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                            +'<td align="left">'+currData[i].deptName+'</td>'
                            +'<td>'+currData[i].sortNo+'</td>'
                            +'<td>'+currData[i].parentId+'</td>';
                        dataHtml += '<td><input type="checkbox" name="show" lay-skin="switch" lay-text="启用|禁用" lay-filter="isShow"'+currData[i].enable+'></td>'
                            +'<td>'
                            +  '<a class="layui-btn layui-btn-mini dept_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
                            +  '<a class="layui-btn layui-btn-danger layui-btn-mini dept_del" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
                            +'</td>'
                            +'</tr>';
                    }
                }else{
                    dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
                }
                $(".dept_content").html( dataHtml);

                //分页
                laypage({
                    cont : "page",
                    pages : deptData.data.pages,
                    curr: current || 1,
                    jump : function(obj,first){
                        if (!first) {
                            page(obj.curr);
                        }
                        $('.dept_list thead input[type="checkbox"]').prop("checked",false);
                        form.render();
                    }
                })
            },
            contentType: "application/json"
        });
	}
})
