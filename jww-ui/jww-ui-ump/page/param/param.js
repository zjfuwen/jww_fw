layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', "treecheck"], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        submitUrl = "param/add";

    // 获取父页面的pageOperation，判断是查看、添加、修改
    if (parent.pageOperation === 1) { // 添加
    } else if (parent.pageOperation === 2) { // 修改
        submitUrl = "param/modify";
    } else { // 查看
        $(".layui-form input").prop("disabled", true);
        $('.layui-form button').hide();
    }

    if (parent.pageOperation === 2 || parent.pageOperation === 0) {
        $("#id").val(parent.checkedparamId);
        // 查询参数数据，初始化页面属性值
        $.ajax({
            type: 'POST',
            url: 'param/query',
            data: JSON.stringify(parent.checkedparamId),
            success: function (data) {
                if (data.code === 200) {
                    if (data.data !== null) {
                        $("#paramKey").val(data.data.paramKey);
                        $("#paramValue").val(data.data.paramValue);
                        $(':radio[name="enable"][value="' + data.data.enable + '"]').attr("checked", "checked");
                        form.render('radio');
                        $("#remark").val(data.data.remark);
                    }
                } else {
                    layer.msg(data.message, {icon: 2});
                }
            }
        });
    }
    // 监听submit
    if (parent.pageOperation === 1 || parent.pageOperation === 2) {
        form.on('submit(addFilter)', function (data) {
            $.ajax({
                type: 'POST',
                url: submitUrl,
                data: JSON.stringify(data.field),
                success: function (data) {
                    if (data.code === 200) {
                        if (parent.pageOperation === 1) {
                            // 重置表单
                            $("#paramForm")[0].reset();
                            layer.msg('参数添加成功', {icon: 1});
                        } else {
                            layer.msg('参数修改成功', {icon: 1});
                        }
                    } else {
                        layer.msg(data.message, {icon: 2});
                    }
                }
            });

            // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
            return false;
        });
    }
});

