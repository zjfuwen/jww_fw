layui.config({
    base : "../../js/"
}).use(['base','form','layer','jquery','tree'],function(){
	var base = layui.base,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		//layedit = layui.layedit,
		//laydate = layui.laydate,
		$ = layui.jquery;
    var menuId = window.parent.document.getElementById("menuId").value;
    //新增、编辑跳转则加载菜单树
    $.ajax({
        type: "GET",
        url: "menu/queryTree/"+menuId,
        success: function (data) {
            if (data.code == 200) {
                layui.tree({
                    elem: '#demo' //传入元素选择器
                    , nodes: data.data,
                    click: function (node) {
                        var ifrc = window.parent.frames[0];
                        var winc = ifrc.window||ifrc.contentWindow;
                        winc.document.getElementById("parentId").value = node.id;
                        winc.document.getElementById("parentName").value = node.name;
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        // console.log(node) //node即为当前点击的节点数据
                    }
                });
            } else {
                top.layer.msg("菜单加载失败！");
            }
        },
        contentType: "application/json"
    });
})
