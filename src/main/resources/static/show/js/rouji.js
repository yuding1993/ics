$(document).ready(function(){
	$.ajax({
		url : "../view/isLogin",
		type : 'post',
		error : function(){
			alert("系统异常");
		},
		success : function(data){

			vm.isLogin = data.obj.isLogin;
			vm.isNotLogin = !data.obj.isLogin;
			var userName = data.obj.userName;
			userName = "***"+userName.substring(userName.length-3,userName.length);
			vm.userName = userName;
			if(vm.isLogin && vm.nowModuleId == 1){//首页
				dailiUrl = "http://datacenter.wh-eport.cn/single.html";
				$("#dataCenter").load("daili.html");
			}else if(vm.nowModuleId == 4){//数据中心
				dailiUrl = "http://datacenter.wh-eport.cn/map.html?userId="+data.obj.userId;
				$("#dataCenter").load("daili.html");
			}
		}
	});
});