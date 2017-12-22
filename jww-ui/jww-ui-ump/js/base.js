/*
 @Author: wanyong
 @Time: 2017-12-03
 @Tittle: base
 @Description: 基础配置
 */
layui.define(["jquery"], function (exports) {
    var $ = layui.jquery;

    $.ajaxSetup({
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function (evt, request, settings) {
            //request.url = 'http://localhost:8089/' + request.url;
            request.url = '/' + request.url;
        },
        dataFilter: function (result) {
            try {
                var resultObj = JSON.parse(result);
                // 没有登录
                if (resultObj.code == 401) {
                    window.top.location.href = "/page/login/login.html";
                    return null;
                }
                return result;
            } catch (e) {
                return result;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            switch (jqXHR.status) {
                case (404):
                    toaster.clear('*');
                    toaster.pop('error', '', "未找到请求的资源");
                    break;
                case (405):
                    window.location.href = "page/login/login.html";
                    break;
            }
        }
    });

    exports("base", function () {
        alert("======== 加载基础配置模块 ========");
    });
})