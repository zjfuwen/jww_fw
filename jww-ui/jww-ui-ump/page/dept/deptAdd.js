layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;

	//创建一个编辑器
 	var editIndex = layedit.build('dept_content');
 	var adddeptArray = [],adddept;
 	form.on("submit(adddept)",function(data){
 		//是否添加过信息
	 	if(window.sessionStorage.getItem("adddept")){
	 		adddeptArray = JSON.parse(window.sessionStorage.getItem("adddept"));
	 	}
	 	//显示、审核状态
 		var isShow = data.field.show=="on" ? "checked" : "",
 			deptStatus = data.field.shenhe=="on" ? "审核通过" : "待审核";

 		adddept = '{"deptName":"'+$(".deptName").val()+'",';  //文章名称
 		adddept += '"deptId":"'+new Date().getTime()+'",';	 //文章id
 		adddept += '"deptLook":"'+$(".deptLook option").eq($(".deptLook").val()).text()+'",'; //开放浏览
 		adddept += '"deptTime":"'+$(".deptTime").val()+'",'; //发布时间
 		adddept += '"deptAuthor":"'+$(".deptAuthor").val()+'",'; //文章作者
 		adddept += '"isShow":"'+ isShow +'",';  //是否展示
 		adddept += '"deptStatus":"'+ deptStatus +'"}'; //审核状态
 		adddeptArray.unshift(JSON.parse(adddept));
 		window.sessionStorage.setItem("adddept",JSON.stringify(adddeptArray));
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            top.layer.close(index);
			top.layer.msg("文章添加成功！");
 			layer.closeAll("iframe");
	 		//刷新父页面
	 		parent.location.reload();
        },2000);
 		return false;
 	})
	
})
