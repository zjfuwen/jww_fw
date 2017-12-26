layui.config({
    base: "../../js/"
}).use(['base', 'form', 'layer', 'jquery', 'table'], function () {
    var base = layui.base,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表加载
    var tableIns = table.render({
        //设置表头
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'userName', title: '用户名'},
            {field: 'operation', title: '用户操作'},
            {field: 'method', title: '请求方法'},
            {field: 'params', title: '请求参数'},
            {field: 'time', title: '执行时长(毫秒)'},
            {field: 'ip', title: 'IP地址'},
            {field: 'createTime', title: '创建时间'}
        ]],
        url: 'log/queryListPage',
        method: 'POST',
        request: {
            pageName: 'current', //页码的参数名称，默认：page
            limitName: 'size' //每页数据量的参数名，默认：limit
        },
        response: {
            statusCode: 200, //成功的状态码，默认：0
            msgName: 'message', //状态信息的字段名称，默认：msg
        },
        elem: '#logTable',
        page: {
            elem: 'pageDiv',
            limit: 10,
            limits: [10, 20, 30, 40, 50]
        }
    });


    //查询
    $(".search_btn").click(function () {
        var searchKey = $(".search_input").val();
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                condition: {
                    user_name: searchKey,
                    operation: searchKey
                }
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    //批量删除
    $(".batchDel").click(function () {
        var checkStatus = table.checkStatus('logTable');
        if (checkStatus.data.length === 0) {
            layer.msg("请选择要删除的用户", {icon: 0, time: 2000});
            return;
        }
        layer.confirm('确定删除选中的信息？', {icon: 3, title: '确认'}, function (index) {
            var indexMsg = layer.msg('删除中，请稍候', {icon: 16, time: false, shade: 0.8});
            var logIds = [];
            for (var i = 0; i < checkStatus.data.length; i++) {
                logIds[i] = checkStatus.data[i].id;
            }
            $.ajax({
                type: 'DELETE',
                url: 'log/delBatchByIds',
                data: JSON.stringify(logIds),
                success: function (data) {
                    if (data.code == 200) {
                        if (data.data === true) {
                            layer.close(indexMsg);
                            layer.msg("删除成功", {icon: 1, time: 2000});
                            tableIns.reload({
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                        }
                    } else {
                        // alert(JSON.stringify(data));
                        layer.msg(data.message, {icon: 2});
                    }
                }
            });
        });
    });

});