layui.config({
    base : "../../js/"
}).use(['base','form','layer','jquery','laydate','tree'],function(){
	var base = layui.base,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		//layedit = layui.layedit,
		//laydate = layui.laydate,
		$ = layui.jquery;
    var menuId = window.parent.document.getElementById("menuId").value;
    var pageOpt = window.parent.document.getElementById("pageOpt").value;
    //新增、编辑跳转则加载菜单树
    if(pageOpt=='add'||pageOpt=='edit') {
        //新增则提交到新增url，编辑则提交到修改url
        var url = pageOpt=='add' ? "menu/add" : "menu/modify";
        form.on("submit(addmenu)",function(data){
            var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            // alert(JSON.stringify(data.field));
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(data.field),
                success: function(data){
                    if(data.code==200){
                        //弹出loading
                        setTimeout(function(){
                            top.layer.close(index);
                            top.layer.msg("操作成功！");
                            layer.closeAll("iframe");
                            //刷新父页面
                            parent.location.reload();
                        },1000);
                    }else{
                        top.layer.close(index);
                        top.layer.msg("操作失败！");
                    }
                },
                contentType: "application/json"
            });

            return false;
        });
    }
    if(pageOpt=='detail'||pageOpt=='edit') {
        $.ajax({
            type: "GET",
            url: "menu/query/"+menuId,
            success: function(data){
                if(data.code==200){
                    // alert(JSON.stringify(data));
                    var rest = eval(data.data);
                    //循环实体
                    for (var i in rest) {
                        // console.log(i + '='+ rest[i]+ ' '+$("." + i).attr("type"));
                        //文本框赋值
                        if($("." + i).attr("type")=="text"||$("." + i).attr("type")=="hidden"){
                            $("." + i).val(rest[i]);
                            if(pageOpt=='detail'){
                                $("." + i).prop("placeholder","");
                            }
                        //复选框改变状态
                        }else if($("." + i).attr("type")=="checkbox"){
                            if(rest[i]==0){
                                $("." + i).removeAttr("checked");
                                form.render('checkbox');
                            }
                        }
                    }
                }else{
                    top.layer.close(index);
                    top.layer.msg("查询异常！");
                }
            },
            contentType: "application/json"
        });
        if(pageOpt=='detail') {
            $(".layui-form input").prop("readonly", true);
            $(".enable").prop("disabled", "disabled");
            $('.layui-form button').hide();
        }
    }

    form.on('radio(menuType)', function(data){
        switch (data.value){
            case '0':
                $(".layui-form-item.request").hide();
                $(".layui-input.request").attr("disabled", "disabled");
                $(".layui-form-item.permission").hide();
                $(".layui-input.permission").attr("disabled", "disabled");

                $(".layui-form-item.iconcls").show();
                $(".layui-input.iconcls").removeAttr("disabled");
                $(".layui-form-item.sortNo").show();
                $(".layui-input.sortNo").removeAttr("disabled");
                break;
            case '1':
                $(".layui-form-item.request").show();
                $(".layui-input.request").removeAttr("disabled");
                $(".layui-form-item.permission").show();
                $(".layui-input.permission").removeAttr("disabled");
                $(".layui-form-item.iconcls").show();
                $(".layui-input.iconcls").removeAttr("disabled");
                $(".layui-form-item.sortNo").show();
                $(".layui-input.sortNo").removeAttr("disabled");
                break;
            case '2':
                $(".layui-form-item.request").hide();
                $(".layui-input.request").attr("disabled", "disabled");
                $(".layui-form-item.permission").show();
                $(".layui-input.permission").removeAttr("disabled");
                $(".layui-form-item.iconcls").hide();
                $(".layui-input.iconcls").removeAttr("disabled");
                $(".layui-form-item.sortNo").hide();
                $(".layui-input.sortNo").removeAttr("disabled");
                break;
            default:
                alert('default');
        }

    });

    $(".parentName").click(function(){
        layer.open({
            type: 2,
            title: '选择上级菜单',
            shadeClose: true,
            shade: 0.8,
            area: ['380px', '90%'],
            content: 'menuTree.html'
        });
    });
})
