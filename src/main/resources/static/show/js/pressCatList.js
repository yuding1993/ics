
avalon.ready(function(){
	/*公共资源查询*/
	queryResourcesCommon();//查询头部图片以及头部、底部文字、模块列表
	vm.nowModuleId = paramsMap.moduleId;//定义当前模块
	/*公共资源查询结束*/
	vm.nowParentId = paramsMap.catIdOne;
	queryPressCatList();
});

var vm = avalon.define({
	$id : "pressCatList",
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
					layer.msg('您还未登录，请登录后再试！', {icon: 7,time:3000});
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
		window.location.href = linkUrl;
	},
	openUrl : function(url){//打开新页面
		window.open(url);
	},
	/*八个公共属性和方法结束*/
	catInfo : {},
	pressInfo : {},
	nowPressPage : 1,
	nowPressIndex : 0,
	nowParentId : 0,
	changePressIndex : function(index){
		vm.nowPressIndex = index;
		queryByPage(vm.nowPressPage);
		if(vm.isViewDetail){
			vm.isViewDetail = !vm.isViewDetail;
		}
	},
	pageQuery : function(page){
		if(new Number(page) < 1){
			return false;
		}else if(new Number(page) > vm.pressInfo.pageList.length){
			return false;
		}
		queryByPage(page);
	},
	viewDetail : function(pressId){
		$.ajax({
			url : "../view/queryPressDetail",
			type : 'post',
			data : {catIdOne:vm.nowParentId,pressId:pressId},
			dataType : 'json',
			error : function(){
				layer.msg('服务器异常！', {icon: 7,time:3000});
			},
			success : function(data){
				vm.pressDetail = null;
				vm.pressDetail = data.obj;
				vm.isViewDetail = true;
			}
		});
	},
	downLoadFile : function(pressId){
		$.ajax({
			url : "../view/queryPressRelDownLoad",
			async: false,//关闭异步，否则浏览器会阻止window.open()
			type : 'post',
			data : {catIdOne:vm.nowParentId,pressId:pressId},
			dataType : 'json',
			error : function(){
				layer.msg('服务器异常！', {icon: 7,time:3000});
			},
			success : function(data){
				window.open(data.obj.fileInfo.fileUrl);
			}
		});
	},
	isViewDetail : false,
	pressDetail : {}
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

function queryPressCatList(){
	$.ajax({
		url : "../view/queryPressCatList",
		data : {catIdOne:paramsMap.catIdOne},
		dataType : 'json',
		type : 'post',
		error : function(){
			layer.msg('服务器异常！', {icon: 7,time:3000});
		},
		success : function(data){
			vm.catInfo = data.obj;
			if(data.obj.pressCatList.length > 0){
				queryByPage(vm.nowPressPage,vm.nowPressIndex);
			}
			var pressId = paramsMap.viewPressId;
			if(pressId != null && pressId != "" && pressId != undefined){
				vm.viewDetail(pressId);
			}
		}
	});
}

function getDateString(){
	vm.nowDateString = getNowDateString();
}

function queryByPage(page){
	var index = vm.nowPressIndex;
	$.ajax({
		url : "../view/pageQueryPressList",
		type : 'post',
		data : {page:page, parentId:vm.catInfo.pressCat.id, catId:vm.catInfo.pressCatList[index].id},
		dataType : 'json',
		error : function(){
			layer.msg('服务器异常！', {icon: 7,time:3000});
		},
		success : function(data){
			vm.pressInfo = data.obj;
		}
	});
}

function firendLink(url){
	if(url == "" || url == null || url == undefined){
		return false;
	}
	window.open(url);
}
