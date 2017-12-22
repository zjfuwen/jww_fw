layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', 'laydate', 'laypage', "treecheck"], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        treecheck = layui.treecheck,
        treeUrl = "menu/funcTree",
        treeData = "",
        treeCheckBoxAllDisable = false,
        submitUrl = "role/add";

    // 获取父页面的pageOperation，判断是查看、添加、修改
    if (parent.pageOperation === 1) {
        // 添加角色

    } else if (parent.pageOperation === 2) {
        // 修改角色
        treeUrl = "menu/roleFuncTree";
        treeData = JSON.stringify(parent.checkedRoleId);
        submitUrl = "role/modify";
    } else {
        // 查看角色
        treeUrl = "menu/roleFuncTree";
        treeData = JSON.stringify(parent.checkedRoleId);
        treeCheckBoxAllDisable = true;

        $(".layui-form input").prop("disabled", true);
        $('.layui-form button').hide();
    }

    // 初始化部门列表
    /*function initDeptSelect() {
     var param = {
     size: 99999,
     current: 1
     };
     $.ajax({
     type: 'POST',
     url: 'dept/queryListPage',
     data: JSON.stringify(param),
     success: function (data) {
     if (data.code == 200) {
     var array = data.data;
     for (var i = 0; i < array.length; i++) {
     $("#deptId").append("<option value='" + array[i].id + "'>" + array[i].deptName + "</option>");
     }
     form.render('select'); //刷新select选择框渲染
     } else {
     layer.msg(data.message, {icon: 2});
     }
     }
     });
     }*/

    // initDeptSelect();

    // 初始化功能权限tree
    var tree = treecheck({
        elem: 'funcAuthTree', // 放xtree的容器，id，不要带#号（必填）
        form: form, // layui form对象 （必填）
        url: treeUrl, // 服务端地址（必填）
        data: treeData, // 参数
        isopen: true, // 初次加载时全部展开，默认true
        color: "#000", // 图标颜色
        icon: { // 图标样式 （必填，不填会出点问题）
            open: "&#xe7a0;", // 节点打开的图标
            close: "&#xe622;", // 节点关闭的图标
            end: "&#xe621;" // 末尾节点的图标
        },
        allDisable: treeCheckBoxAllDisable // 全部checkbox是否不可用
    });

    if (parent.pageOperation === 2 || parent.pageOperation === 0) {
        // 查询角色
        $.ajax({
            type: 'POST',
            url: 'role/query',
            data: JSON.stringify(parent.checkedRoleId),
            success: function (data) {
                if (data.code === 200) {
                    if (data.data !== null) {
                        $("#roleName").val(data.data.roleName);
                        $("#deptId").val(data.data.deptId);
                        $("#remark").val(data.data.remark);
                    }
                } else {
                    layer.msg(data.message, {icon: 2});
                }
            }
        });
    }

    // 监听submit
    form.on('submit(addFilter)', function (data) {
        var menuIdArray = tree.GetAllCheckedValue();
        data.field["menuIdList"] = menuIdArray;
        $.ajax({
            type: 'POST',
            url: submitUrl,
            data: JSON.stringify(data.field),
            success: function (data) {
                if (data.code === 200) {
                    if (parent.pageOperation === 1) {
                        // 重置表单
                        $("#userForm")[0].reset();
                        layer.msg('角色添加成功', {icon: 1});
                    } else {
                        layer.msg('角色修改成功', {icon: 1});
                    }
                } else {
                    layer.msg(data.message, {icon: 2});
                }
            }
        });

        //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        return false;
    });

});

