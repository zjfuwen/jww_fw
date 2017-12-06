layui.config({
    base : "../../js/"
}).use(['base','form','layer','jquery','layedit','laydate'],function(){
	var base = layui.base,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		//layedit = layui.layedit,
		//laydate = layui.laydate,
		$ = layui.jquery;
 	form.on("submit(adddept)",function(data){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            type: "POST",
            url: "dept/add",
            data: JSON.stringify(data.field),
            success: function(data){
                if(data.code==200){
                    //弹出loading
                    setTimeout(function(){
                        top.layer.close(index);
                        top.layer.msg("部门添加成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    },1000);
                }else{
                    top.layer.close(index);
                    top.layer.msg("部门添加失败！");
				}
            },
            contentType: "application/json"
        });

 		return false;
 	})
	
})
