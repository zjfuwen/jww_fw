layui.config({
    base : "../../js/"
}).use(['base','form','layer','jquery','layedit','laydate'],function(){
	var base = layui.base,
		form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		//layedit = layui.layedit,
		//laydate = layui.laydate,
		$ = layui.jquery;

	//创建一个编辑器
 	//var editIndex = layedit.build('dept_content');
 	//var adddeptArray = [],adddept;
    var adddept;
 	form.on("submit(adddept)",function(data){
 		//是否添加过信息
	 	// if(window.sessionStorage.getItem("adddept")){
	 	// 	adddeptArray = JSON.parse(window.sessionStorage.getItem("adddept"));
	 	// }
	 	//显示、状态
 		var isEnable = data.field.enable=="on" ? "1" : "";

 		adddept = '{"deptName":"'+$(".deptName").val()+'",';  //部门名称
 		adddept += '"parentId":"'+$(".parentId option").eq($(".parentId").val()).val()+'",'; //上级部门
 		adddept += '"sortNo":"'+$(".sortNo").val()+'",'; //排序
 		adddept += '"enable":"'+ isEnable +'"}'; //状态
		//alert(adddept);

        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            type: "POST",
            url: "dept/add",
            data: adddept,
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
