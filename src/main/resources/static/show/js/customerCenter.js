
avalon.ready(function(){
	/*公共资源查询*/
	queryResourcesCommon();//查询头部图片以及头部、底部文字、模块列表
	vm.nowModuleId = paramsMap.moduleId;//定义当前模块
	/*公共资源查询结束*/
	queryIndexPageContent(paramsMap.moduleId);//查询当前模块资源
	queryQuestionAndAnswer();//查询问答
	queryVideo();//查询视频
});

var vm = avalon.define({
	$id : "customer",
	/*八个公共属性和方法*/
	resourcesCommon : {},//头和尾的公共资源
	moduleList : [],//模块列表
	friendLinkCommon : {},//公共友情链接
	nowDateString : "",//头部时间字符串
	nowModuleId : 0,//现在的模块id
	isLogin : false,//登陆状态
	userName : "",//登陆用户名
	skipPage: function (moduleId, pageUrl) {
		// 某些模块未登录禁止访问（后台判断）
		$.ajax({
			url: "../view/checkIsVisit",
			data: {
				type: "module",
				id: moduleId
			},
			dataType: "json",
			async: false,
			success: function (resp) {
				if (resp.code != 0) {
					layer.msg('必须登录本系统方可访问！', {icon: 7,time:3000});
					return;
				}
				$.ajax({
					url: "../view/checkMenuAuth",
					data: {
						type: "module",
						id: moduleId
					},
					dataType: "json",
					async: false,
					success: function (resp) {
						if (resp.code != 0) {
							layer.msg('没有权限访问！', {icon: 7,time:3000});
							return;
						}
						window.location.href = pageUrl + "?moduleId=" + moduleId;//跳转模块页面
					},
					error: function () {
						layer.msg('服务器异常！', {icon: 7,time:3000});
					}
				});
			},
			error: function () {
				layer.msg('服务器异常！', {icon: 7,time:3000});
			}
		});
	},
	gotoPressCatList : function(catIdOne,belongModuleId){//跳转资讯分类页面
		window.location.href = "pressCatList.html?catIdOne="+catIdOne+"&moduleId="+belongModuleId;
	},
	login : function(){//登陆
		var path = window.location.pathname + window.location.search;
		path = path.substring(8, path.length);
		window.location.href = "../sec/"+path;
	},
	openUrlSelf : function(linkUrl){//打开新页面
		if(linkUrl == "" || linkUrl == null || linkUrl == undefined){
			return false;
		}
		window.location.href = linkUrl;
	},
	openUrl : function(url){//打开新页面
		if(url == "" || url == null || url == undefined){
			return false;
		}
		window.open(url);
	},
	/*八个公共属性和方法结束*/
	moduleName : "",
	imgList : [],
	question : [],//问答
	video : [],//视频
	showIndex : 0,
	addViewCount : function(videoId,index){//增加视频查看次数
		$.post("../view/addViewCount",{videoId:videoId},function(data){

			if(data.code == "0"){//刷新本视频的查看次数
				vm.video[index].viewCount = data.obj;
			}
		});
	},
	isTousu : false,
	complaintFileValue : "未选择任何文件",
	checkFile : function(){//选择文件
		$("#complaintFile").click();
	},
	submitComplaint : function(){//提交投诉
		submitComplaint();
	},
	toggleQuestion : function(index){
		$("#questionUl").find("li").eq(index).toggleClass('open');
		$("#questionUl").find("li").eq(index).find("div").eq(1).slideToggle();
	}
});

function queryResourcesCommon(){
	$.ajax({
		url : "../view/queryResourcesCommon",
		type : 'post',
		error : function(){
			layer.msg('服务器异常！', {icon: 7,time:3000});
		},
		success : function(data){

			vm.resourcesCommon = data.obj.resourcesCommon;
			vm.moduleList = data.obj.moduleList;
			vm.friendLinkCommon = data.obj.friendLink;
		}
	});
}

function getDateString(){
	vm.nowDateString = getNowDateString();
}

function queryIndexPageContent(moduleId){
	$.ajax({
		url : "../view/queryIndexPageContent",
		data : {moduleId:moduleId},
		dataType : 'json',
		type : 'post',
		error : function(){
			layer.msg('服务器异常！', {icon: 7,time:3000});
		},
		success : function(data){
			vm.moduleName = data.obj.moduleName;
			vm.imgList = data.obj.imgList;
		}
	});
}

function firendLink(url){
	if(url == "" || url == null || url == undefined){
		return false;
	}
	window.open(url);
}

function queryQuestionAndAnswer(){
	$.ajax({
		url : "../view/queryQuestionAndAnswer",
		type : 'post',
		error : function(){
			layer.msg('服务器异常！', {icon: 7,time:3000});
		},
		success : function(data){

			vm.question = data.obj;
		}
	});
}

function queryVideo(){
	$.ajax({
		url : "../view/queryVideo",
		type : 'post',
		error : function(){
			layer.msg('服务器异常！', {icon: 7,time:3000});
		},
		success : function(data){

			vm.video = data.obj;
		}
	});
}

function fileChange(value){
	vm.complaintFileValue = value;
}

function submitComplaint(){
	if(!$("#name").val()){
		layer.msg('请输入姓名！', {icon: 7,time:3000});
		$("#name").focus();
		return false;
	}
	if(!$("#mobile").val()){
		layer.msg('请输入电话！', {icon: 7,time:3000});
		$("#mobile").focus();
		return false;
	}else{
		var mobile = $("#mobile").val();
		if(!checkMobile(mobile) && !checkPhone(mobile)){
			layer.msg('请输入正确的电话！', {icon: 7,time:3000});
			$("#mobile").focus();
			return false;
		}
	}
	if(!$("#content").val()){
		layer.msg('请输入内容！', {icon: 7,time:3000});
		return false;
	}
	if($("#complaintFile").val()){
		var mime = $("#complaintFile").val().toLowerCase().substr($("#complaintFile").val().lastIndexOf("."));
		if(mime != ".doc" && mime != ".docx"){
			layer.msg('只允许doc,docx文件！', {icon: 7,time:3000});
			return false;
		}
	}
	if(!$("#captcha").val()){
		layer.msg('请输入验证码！', {icon: 7,time:3000});
		return false;
	}
	$("#complaintForm").attr("action","../view/submitComplaint");
		$('#complaintForm').ajaxForm({
			type:'POST',
			dataType: 'json',
			success: function(data){
				if(data.code == "0"){
					vm.isTousu = true;
				}else{
					alert(data.msg);
				}
			},
			error: function(){
				alert(data.msg);
				return false;
			}
	}).submit();
}

function checkMobile(str) {
   var re = /^1\d{10}$/;
   if (re.test(str)) {
       return true;
   }else {
       return false;
   }
}

function checkPhone(str){
   var re = /^0\d{2,3}-?\d{7,8}$/;
   if(re.test(str)){
	   return true;
   }else{
	   return false;
   }
}

function changeImg() {
	var imgSrc = $("#imageCaptcha");    
	var oldsrc = imgSrc.attr("src");
	var newsrc = chgUrl(oldsrc);
	imgSrc.attr("src",newsrc);
}

function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	var postion = url.indexOf("?");
	if (postion >= 0) {
		var suburl = "";
		var timeS=url.indexOf("timestamp=");
		if(timeS>0) {
			suburl = url.substr(0, timeS);
		} else {
			suburl = url+"&";
		}
		url = suburl + "timestamp=" + timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}
	return url;
}

