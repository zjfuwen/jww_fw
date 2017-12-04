layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery'], function () {
    var base = layui.base,
        form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    // 初始化userTable数据
    page.renderPageData("imovie-page-div", null, "page_template_id",
        "page_template_body_id", 'user/listPage');



});