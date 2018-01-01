layui.config({
    base: "js/"
}).use(['element'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        $ = layui.jquery;

})
