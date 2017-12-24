layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', 'tree'], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        //layedit = layui.layedit,
        //laydate = layui.laydate,
        $ = layui.jquery;
    var menuId = parent.menuId;
    $.ajax({
        type: "GET",
        url: "menu/queryTree/" + menuId,
        success: function (data) {
            if (data.code == 200) {
                layui.tree({
                    elem: '#demo' //传入元素选择器
                    , nodes: data.data,
                    click: function (node) {
                        window.parent.document.getElementById("parentId").value = node.id;
                        window.parent.document.getElementById("parentName").value = node.name;
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                });
            } else {
                top.layer.msg("菜单加载失败！");
            }
        },
        contentType: "application/json"
    });
})
