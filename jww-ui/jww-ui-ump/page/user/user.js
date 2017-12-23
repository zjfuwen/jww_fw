var $;
layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', 'laydate', 'laypage'], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#date'
    });

    // 监听submit
    form.on('submit(addUser)', function (data) {
        if(typeof(data.field.enable) == "undefined"|| data.field.enable =='undefined'){
            data.field.enable=0;
        }
        alert(JSON.stringify(data.field));
        $.ajax({
            type: 'POST',
            url: 'user/add',
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

