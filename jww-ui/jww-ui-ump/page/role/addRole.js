layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', 'laydate', 'laypage', "treecheck"], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        treecheck = layui.treecheck;

    // 初始化部门列表
    function initDeptSelect() {
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
    }

    initDeptSelect();

    // 初始化功能权限tree
    var tree = treecheck({
        elem: 'funcAuthTree', // 放xtree的容器，id，不要带#号（必填）
        form: form, // layui form对象 （必填）
        data: "menu/functree", // 服务端地址（必填）
        isopen: true, // 初次加载时全部展开，默认true
        color: "#000", // 图标颜色
        icon: { // 图标样式 （必填，不填会出点问题）
            open: "&#xe7a0;", // 节点打开的图标
            close: "&#xe622;", // 节点关闭的图标
            end: "&#xe621;" // 末尾节点的图标
        }
    });

    // 监听submit
    form.on('submit(addFilter)', function (data) {
        var parentMenuIdArray = [];
        var menuIdArray = [];
        var oCks = tree.GetChecked();
        for (var i = 0; i < oCks.length; i++) {
            menuIdArray[i] = oCks[i].value;
            var parentCks = tree.GetParent(oCks[i].value);
            for (var j = 0; j < parentCks.length; j++) {
                parentMenuIdArray[j] = parentCks[j].value;
                alert(parentCks[j].value);
            }
        }
        alert(JSON.stringify(parentMenuIdArray));
        data.field["menuIdList"] = menuIdArray;
        $.ajax({
            type: 'POST',
            url: 'role/add',
            data: JSON.stringify(data.field),
            success: function (data) {
                if (data.code == 200) {
                    // 重置表单
                    $("#userForm")[0].reset();
                    layer.msg('用户添加成功', {icon: 1});
                } else {
                    layer.msg(data.message, {icon: 2});
                }
            }
        });

        //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        return false;
    });

});

