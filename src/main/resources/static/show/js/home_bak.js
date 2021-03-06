
avalon.ready(function(){
	/*公共资源查询*/
	queryResourcesCommon();//查询头部图片以及头部、底部文字、模块列表
	vm.nowModuleId = paramsMap.moduleId;//定义当前模块
	/*公共资源查询结束*/
	queryIndexPageContent(paramsMap.moduleId);//查询当前模块资源
});

var dailiUrl = "";

var vm = avalon.define({
	$id : "home",
	/*公共属性和方法*/
	resourcesCommon : {},//头和尾的公共资源
	moduleList : [],//模块列表
	friendLinkCommon : {},//公共友情链接
	nowDateString : "",//头部时间字符串
	nowModuleId : 0,//现在的模块id
	isLogin : false,//登陆状态
	userName : "",//登陆用户名
	skipPage : function(moduleId,pageUrl){
        $.ajax({
            url : "../view/checkMenuAuth",
            data : {
                type : "module",
                id : moduleId
            },
            dataType : "json",
            async : false,
            success : function(resp){
                if(resp.code != 0){
                    layer.msg('没有权限访问！', {icon: 7,time:3000});
                    return;
                }
                window.location.href = pageUrl+"?moduleId="+moduleId;//跳转模块页面
            },
            error : function(){
                layer.msg('服务器异常！', {icon: 7,time:3000});
            }
        });
	},
	login : function(){//登陆
		var path = window.location.pathname + window.location.search;
		path = path.substring(1, path.length);//当index.jsp地址改变时，截取的值也需改变
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
	openFriendLink : function(url, linkId){//打开新页面
		if(url == "" || url == null || url == undefined){
			return false;
		}
        $.ajax({
            url : "../view/checkMenuAuth",
            data : {
                type : "friendLink",
                id : linkId
            },
            dataType : "json",
            async : false,
            success : function(resp){
                if(resp.code != 0){
                    layer.msg('没有权限访问！', {icon: 7,time:3000});
                    return;
                }
                window.open(url);
            },
            error : function(){
                layer.msg('服务器异常！', {icon: 7,time:3000});
            }
        });
	},
	gotoPressCatList : function(catIdOne,belongModuleId,pressId){//跳转资讯分类页面
		if(pressId != null && pressId != "" && pressId != undefined){
			window.location.href = "pressCatList.html?catIdOne="+catIdOne+"&moduleId="+belongModuleId+"&viewPressId="+pressId;
		}else{
			window.location.href = "pressCatList.html?catIdOne="+catIdOne+"&moduleId="+belongModuleId;
		}
	},
	viewDetail : function(pressId,parentCatId,belongModuleId){//跳转到资讯详情页面
		if(pressId == "" || pressId == undefined || pressId == null){
			return false;
		}
		window.location.href = "pressCatList.html?catIdOne="+parentCatId+"&viewPressId="+pressId+"&moduleId="+belongModuleId;
	},
	/*公共属性和方法结束*/
	carousel : {},
	pressMap : {},
	advertList : [],
	friendLinkList : [],
	friendLinkShowList : [],
	imgNowIndexOne : 0,
	imgNowIndexTwo : 0,
	friendLinkMin : 0,
	friendLinkMax : 4,
	nowFriendPianyi : 0,
	backToTop : function(){
		var speed=200;//滑动的速度
        $('body,html').animate({ scrollTop: 0 }, speed);
        return false;
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
			vm.carousel = data.obj.carouselList;
			vm.pressMap = data.obj.pressMap;
			vm.advertList = data.obj.advertList;
			vm.friendLinkList = data.obj.friendLinkList;
			vm.friendLinkShowList = data.obj.friendLinkList[0].list.concat();
		}
	});
}

function firendLink(url){
	if(url == "" || url == null || url == undefined){
		return false;
	}
	window.open(url);
}

function viewDetail(pressId,parentCatId,belongModuleId)//跳转到资讯详情页面
{
	if(pressId == "" || pressId == undefined || pressId == null){
		return false;
	}
	window.location.href = "pressDetail.html?catIdOne="+parentCatId+"&pressId="+pressId+"&moduleId="+belongModuleId;
}

