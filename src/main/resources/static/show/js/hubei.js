
avalon.ready(function(){
	/*公共资源查询*/
	queryResourcesCommon();//查询头部图片以及头部、底部文字、模块列表
	vm.nowModuleId = paramsMap.moduleId;//定义当前模块
	/*公共资源查询结束*/
	queryIndexPageContent(paramsMap.moduleId);//查询当前模块资源
});

var vm = avalon.define({
	$id : "hubei",
	/*八个公共属性和方法*/
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
	moduleName : "",
	imgList : [],
	graphics : [],
	waterSys1 : true,
	waterSys2 : true,
	waterSys3 : true,
	waterSys4 : true
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
			for(var i=0;i<data.obj.graphicsList[3].list.length;i++){
				var n = "isShow";
				data.obj.graphicsList[3].list[i][n] = false;
			}
			vm.graphics = data.obj.graphicsList;
		}
	});
}

function firendLink(url){
	if(url == "" || url == null || url == undefined){
		return false;
	}
	window.open(url);
}
