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
        $(".parentName").click(function(){
            layer.open({
                type: 2,
                title: '选择上级菜单',
                shadeClose: true,
                shade: 0.5,
                area: ['320px', '70%'],
                content: 'menuTree.html'
            });
        });

        //菜单类型选择事件监听，不同选项显示不同字段
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

            }

        });
    }
    if(pageOpt=='detail'||pageOpt=='edit') {
        $("#id").val(menuId);
        $.ajax({
            type: "GET",
            url: "menu/query/"+menuId,
            success: function(data){
                if(data.code==200){
                    //$(".layui-form input:radio[name='menuType']").eq(data.data.menuType).attr("checked",'checked');
                    $(':radio[name="menuType"]').eq(data.data.menuType).attr("checked","checked");
                    form.render('radio');
                    $(".layui-input.menuName").val(data.data.menuName);
                    $(".layui-input.parentId").val(data.data.parentId);
                    $(".layui-input.parentName").val(data.data.parentName);
                    $(".layui-input.request").val(data.data.request);
                    $(".layui-input.permission").val(data.data.permission);
                    $(".layui-input.sortNo").val(data.data.sortNo);
                    $(".layui-input.iconcls").val(data.data.iconcls);
                }else{
                    top.layer.close(index);
                    top.layer.msg("查询异常！");
                }
            },
            contentType: "application/json"
        });
        if(pageOpt=='detail') {
            $(".layui-form input").attr("readonly", true);
            $(".layui-form input").attr("placeholder", "");
            $('.layui-form button').hide();
            $(".layui-form input:radio").attr("disabled", true);
        }
    }




})
