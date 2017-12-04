var page = {

    /**
     * 分页模板的渲染方法
     * @param templateId 分页需要渲染的模板的id
     * @param resultContentId 模板渲染后显示在页面的内容的容器id
     * @param data 服务器返回的json对象
     */
    renderTemplate: function (templateId, resultContentId, data) {
        layui.use(['form', 'laytpl', 'jquery'], function () {
            var laytpl = layui.laytpl,
                $ = layui.jquery;
            laytpl($("#" + templateId).html()).render(data, function (html) {
                $("#" + resultContentId).html(html);
            });
        });
        layui.form().render();// 渲染
    },

    /**
     * layui.laypage 分页封装
     * @param laypageDivId 分页控件Div层的id
     * @param pageParams 分页的参数
     * @param templateId 分页需要渲染的模板的id
     * @param resultContentId 模板渲染后显示在页面的内容的容器id
     * @param url 向服务器请求分页的url链接地址
     */
    renderPageData: function (laypageDivId, pageParams, templateId, resultContentId, url) {
        if (pageParams === null) {
            pageParams = {
                current: 1,
                size: 10
            }
        }

        layui.use(['form', 'laypage', 'jquery'], function () {
            var laypage = layui.laypage,
                $ = layui.jquery;
            $.ajax({
                url: url,
                method: 'post',
                data: JSON.stringify(pageParams),//JSON.stringify(datasub)
                success: function (data) {
                    if (data.code == 200) {
                        page.renderTemplate(templateId, resultContentId, data.data);

                        //重新初始化分页插件
                        laypage({
                            cont: laypageDivId,
                            count: data.data.total,
                            curr: data.data.current,
                            pages: data.data.pages,
                            jump: function (obj, first) {//obj是一个object类型。包括了分页的所有配置信息。first一个Boolean类，检测页面是否初始加载。非常有用，可避免无限刷新。
                                pageParams.current = obj.curr;
                                pageParams.size = data.data.size;
                                if (!first) {
                                    page.renderPageData(laypageDivId, pageParams, templateId, resultContentId, url);
                                }
                            }
                        });
                    } else {
                        layer.alert(data.message);
                    }
                }
            });
        });
    }
}