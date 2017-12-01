layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //video背景
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
        layer.alert(JSON.stringify(data.field));
        var url = "http://localhost:8089/login";
        $.post(url, data.field, function (data) {
            alert(JSON.stringify(data));
        }, "json");
        // window.location.href = "../../index.html";
        return false;
    })
})
