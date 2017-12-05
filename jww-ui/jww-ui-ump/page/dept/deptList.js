layui.config({
	base : "../../js/"
}).use(['base','form','layer','jquery','laypage'],function(){
	var base = layui.base,
        form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var size = 10;//每页出现的数据量
    page();

    //查询
    $(".search_btn").click(function(){
        var newArray = [];
        var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        page();
        layer.close(index);
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
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="enable"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断部门是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="enable"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="enable"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

    //操作
    $("body").on("click",".dept_edit",function(){  //编辑
        layer.alert('您点击了文章编辑按钮，由于没时间做，后期会添加，敬请谅解。。。',{icon:6, title:'部门编辑'});
    })

	//是否禁用
	form.on('switch(isEnable)', function(data){
        var _this = $(this);
        var enable = _this.attr("is-enable");
        if(enable==1){
            enable = 0;
        }else{
            enable = 1;
        }
        var modData = {"id":_this.attr("data-id"),"enable":enable};
		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            type: "POST",
            url: "dept/mod",
            data: JSON.stringify(modData),
            success: function(data){
                if(data.code==200){
                    setTimeout(function(){
                        layer.close(index);
                        layer.msg("状态修改成功！");
                    },500);
                }else{
                    top.layer.close(index);
                    top.layer.msg("状态修改失败！");
                }
            },
            contentType: "application/json"
        });
	})

    $("body").on("click",".dept_del",function(){  //删除
        var _this = $(this);
        var data = {"id":_this.attr("data-id")};
        //alert(JSON.stringify(data));
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
            $.ajax({
                type: "POST",
                url: "dept/del",
                data: JSON.stringify(data),
                success: function(data){
                    if(data.code==200){
                        //弹出loading
                        setTimeout(function(){
                            top.layer.msg("部门删除成功！");
                            _this.parents("tr").remove();
                        },1000);
                    }else{
                        top.layer.close(index);
                        top.layer.msg("部门删除失败！");
                    }
                },
                contentType: "application/json"
            });
            layer.close(index);
        });
    })

	function page(current){
        current = current==null?1:current;
        var deptName = $(".search_input").val();
        if(deptName == ''){
            deptName = 'all';
        }
        $.ajax({
            url: "dept/query/"+current+"/"+size+"/"+deptName,
            success: function(data){
                //执行加载数据的方法
                var dataHtml = '';
                if(data.code!=200){
                    dataHtml = '<tr><td colspan="8">查询异常</td></tr>';
                    return dataHtml;
                }
                currData = data.data.records;//.concat().splice(curr*nums-nums, nums);
                if(data.data.total != 0){
                    for(var i=0;i<currData.length;i++){
                        dataHtml += '<tr>'
                            +'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                            +'<td align="left">'+currData[i].deptName+'</td>'
                            +'<td>'+currData[i].sortNo+'</td>'
                            +'<td>'+currData[i].parentId+'</td>';
                        dataHtml += '<td><input type="checkbox" name="enable" lay-skin="switch" lay-text="启用|禁用" lay-filter="isEnable" data-id="'+currData[i].id + '\" is-enable="'+currData[i].enable + '\"';
                        if(currData[i].enable==1){
                            dataHtml += ' checked';
                        }
                        dataHtml += '></td>'
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
                    pages : data.data.pages,
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
