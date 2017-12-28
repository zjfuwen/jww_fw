layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', 'laydate', 'laypage'], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    var initData = function () {
        var account = layui.data("JWW_UMP").CUURENT_USER.account;
        $("#account").val(account);
    };
    initData();

    // 添加验证规则
    form.verify({
        oldPwd: function (value, item) {
        },
        newPwd: function (value, item) {
            if (value.length < 6) {
                return "密码长度不能小于6位";
            }
        },
        confirmPwd: function (value, item) {
            var oldPwd = $("#newPwd").val();
            if (oldPwd !== value) {
                return "两次输入密码不一致，请重新输入！";
            }
        }
    });

    form.on('submit(changePwd)', function (data) {

        return false;

        var role = [];
        $('input[name="role"]:checked').each(function (index, element) {
            role[index] = $(this).val();
        });
        data.field.role = role;

        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            type: 'POST',
            url: submitUrl,
            data: JSON.stringify(data.field),
            success: function (data) {
                if (data.code === 200) {
                    top.layer.close(index);
                    if (parent.pageOperation === 1) {
                        // 重置表单
                        $("#roleDiv").empty();
                        form.render('checkbox');
                        $("#userForm")[0].reset();
                        layer.msg('用户添加成功', {icon: 1});
                    } else if (parent.pageOperation === 2) {
                        setTimeout(function () {
                            top.layer.msg("用户修改成功！");
                            layer.closeAll("iframe");
                            //刷新父页面
                            parent.location.reload();
                        }, 500);
                    }
                } else {
                    top.layer.close(index);
                    layer.msg(data.message, {icon: 2});
                }
            }
        });

        //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        return false;
    });
});

