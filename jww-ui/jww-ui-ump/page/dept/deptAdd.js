layui.config({
    base : "../../js/"
}).use(['base','form','layer','jquery','laydate','tree'],function(){
	var base = layui.base,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		//layedit = layui.layedit,
		//laydate = layui.laydate,
		$ = layui.jquery;
    var deptId = window.parent.document.getElementById("deptId").value;
    var pageOpt = window.parent.document.getElementById("pageOpt").value;
    //新增、编辑跳转则加载部门树
    if(pageOpt=='add'||pageOpt=='edit') {
        // $.ajax({
        //     type: "GET",
        //     url: "dept/queryTree/"+deptId,
        //     success: function (data) {
        //         if (data.code == 200) {
        //             layui.tree({
        //                 elem: '#demo' //传入元素选择器
        //                 , nodes: data.data,
        //                 click: function (node) {
        //                     $("input[name='parentId']").val(node.id);
        //                     $("input[name='parentName']").val(node.name);
        //                     // console.log(node) //node即为当前点击的节点数据
        //                 }
        //             });
        //         } else {
        //             top.layer.msg("部门加载失败！");
        //         }
        //     },
        //     contentType: "application/json"
        // });
        //新增则提交到新增url，编辑则提交到修改url
        var url = pageOpt=='add' ? "dept/add" : "dept/modify";
        form.on("submit(adddept)",function(data){
            var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            // alert(JSON.stringify(data.field));
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(data.field),
                success: function(data){
                    if(data.code==200){
                        //弹出loading
                        setTimeout(function(){
                            top.layer.close(index);
                            top.layer.msg("操作成功！");
                            layer.closeAll("iframe");
                            //刷新父页面
                            parent.location.reload();
                        },1000);
                    }else{
                        top.layer.close(index);
                        top.layer.msg("操作失败！");
                    }
                },
                contentType: "application/json"
            });

            return false;
        });
    }
    if(pageOpt=='detail'||pageOpt=='edit') {
        $.ajax({
            type: "GET",
            url: "dept/query/"+deptId,
            success: function(data){
                if(data.code==200){
                    // alert(JSON.stringify(data));
                    var rest = eval(data.data);
                    //循环实体
                    for (var i in rest) {
                        // console.log(i + '='+ rest[i]+ ' '+$("." + i).attr("type"));
                        //文本框赋值
                        if($("." + i).attr("type")=="text"||$("." + i).attr("type")=="hidden"){
                            $("." + i).val(rest[i]);
                            if(pageOpt=='detail'){
                                $("." + i).prop("placeholder","");
                            }
                        //复选框改变状态
                        }else if($("." + i).attr("type")=="checkbox"){
                            if(rest[i]==0){
                                $("." + i).removeAttr("checked");
                                form.render('checkbox');
                            }
                        }
                    }
                }else{
                    top.layer.close(index);
                    top.layer.msg("查询异常！");
                }
            },
            contentType: "application/json"
        });
        if(pageOpt=='detail') {
            $(".layui-form input").prop("readonly", true);
            $(".enable").prop("disabled", "disabled");
            $('.layui-form button').hide();
        }
    }
    $(".parentName").click(function(){
        layer.open({
            type: 2,
            title: '选择部门',
            shadeClose: true,
            shade: 0.8,
            area: ['380px', '90%'],
            content: 'deptTree.html?v=1' //iframe的url
        });
    });
})
