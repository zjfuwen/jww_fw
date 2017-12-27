layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer'], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    // video背景
    $(window).resize(function () {
        if ($(".video-player").width() > $(window).width()) {
            $(".video-player").css({
                "height": $(window).height(),
                "width": $(window).width(),
                // "width": "auto",
                // "left": -($(".video-player").width() - $(window).width()) / 2
            });
        } else {
            $(".video-player").css({
                "width": $(window).width(),
                "height": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        }
    }).resize();

    var getCaptcha = function () {
        $.ajax({
            type: 'GET',
            url: 'captcha',
            success: function (data) {
                if (data.code == 200) {
                    $("#captchaImg").attr('src', 'data:image/jpeg;base64,' + data.data.captcha);
                    $("#captchaId").val(data.data.captchaId);
                } else {
                    layer.alert(data.message);
                }
            }
        });
    };
    getCaptcha();

    // 单击验证码事件
    $("#captchaImg").click(function () {
        getCaptcha();
    });

    //表单验证
    form.verify({
        code: function (value, item) {
            if (value != "jgmxj") {
                return "验证码填写有误";
            }
        }
    })

    //登录按钮事件
    form.on("submit(login)", function (data) {
        var url = "login";
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(data.field),
            success: function (data) {
                if (data.code == 200) {
                    layui.data('JWW_UMP', {
                        key: 'CUURENT_USER', value: data.data
                    });
                    window.location.href = "../../index.html";
                } else {
                    layer.alert(data.message);
                }
            }
        });
        // window.location.href = "../../index.html";
        return false;
    })
})
