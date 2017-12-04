layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', 'table'], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        table = layui.table;

    table.render({
        //设置表头
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'account', title: '登录名'},
            {field: 'userName', title: '姓名'},
            {field: 'sex', title: '性别', templet: '<div>{{d.sex === 1 ? "男" : "女"}}</div>'},
            {field: 'phone', title: '手机号'},
            {field: 'idCard', title: '身份证'},
            {field: 'dept', title: '部门'},
            {field: 'position', title: '职位'},
            {field: 'email', title: '邮箱'},
            {field: 'opt', fixed: 'right', width: 160, align: 'center', toolbar: '#toolBar'}
        ]],
        url: 'user/listPage',
        method: 'post',
        request: {
            pageName: 'current', //页码的参数名称，默认：page
            limitName: 'size' //每页数据量的参数名，默认：limit
        },
        response: {
            statusCode: 200, //成功的状态码，默认：0
        },
        elem: '#userTable',
        page: {
            elem: 'pageDiv',
            limit: 10,
            limits: [10, 20, 30, 40, 50]
        }
    });

    //监听表格复选框选择
    table.on('checkbox(tableFilter)', function (obj) {
        alert(JSON.stringify(obj));
    });

    //添加会员
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加会员",
            type: 2,
            content: "addUser.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    })

});