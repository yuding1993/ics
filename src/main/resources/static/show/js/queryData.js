
avalon.ready(function(){
	/*公共资源查询*/
	queryResourcesCommon();//查询头部图片以及头部、底部文字、模块列表
	vm.nowModuleId = paramsMap.moduleId;
	/*公共资源查询结束*/
	queryIndexPageContent(vm.nowModuleId);//查询当前模块资源
	$("#dataCenter").load("daili.html");
});

var dailiUrl = "/datacenter.wh-eport.cn/map.html";

var vm = avalon.define({
	$id : "queryData",
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
                    alert("没有权限访问");
                    return;
                }
                window.location.href = pageUrl+"?moduleId="+moduleId;//跳转模块页面
            },
            error : function(){
                alert("服务器异常");
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
	openUrl : function(id,loginType,hasSuffix,status,doesTurnQp){//打开新页面
        $(".new-tishi").hide();
        $(".new-login").hide();
		if(status == 'n'){
            $(".new-tishi").show();
			return ;
		}
		$.post("../view/checkUserType",{id:id},function(data){

			if(data.code == "0"){
				if(loginType == "no"){
					openActionUrl(id);
				}else if(loginType == "pt"){
					if(vm.isLogin){
						if(hasSuffix == "y"){
							$.get("../view/getSuffixUrl",{id:id},function(data){

								if(data.code == "0"){
									window.open(data.obj);//有参数的登录限制url必须以？参数名=   为结束
								}else{
                                    // alert("请先登录本系统！");
                                    $(".loginGen").show();
									return false;
								}
							});
						}else{
							openActionUrl(id);
						}
					}else{
						// alert("请先登录本系统！");
                        $(".loginGen").show();
						return false;
					}
				}else if(loginType == "ic"){
					if(vm.isLogin){
						$.get("../view/checkIsIcLogin",{},function(data){

							if(data.code == "0"){
								if(doesTurnQp == "y"){
									$.get("../view/turnToQp",{id:id},function(data){

										if(data.code == "0"){
											window.open(data.obj);
										}else{
											// alert("必须通过IC卡登陆本系统方可访问！请先退出登录然后使用IC卡登陆!");
			                                $(".icLogin").show();
											return false;
										}
									});
								}else if(hasSuffix == "y"){
									$.get("../view/getSuffixUrl",{id:id},function(data){

										if(data.code == "0"){
											window.open(data.obj);//有参数的登录限制url必须以？参数名=   为结束
										}else{
		                                    // alert("请先登录本系统！");
		                                    $(".loginGen").show();
											return false;
										}
									});
								}else{
									openActionUrl(id);
								}
							}else{
								// alert("必须通过IC卡登陆本系统方可访问！");
		                        $(".icLogin").show();
								return false;
							}
						});
					}else{
						// alert("必须通过IC卡登陆本系统方可访问！");
                        $(".icLogin").show();
						return false;
					}
				}
			}else{
				//alert("抱歉，您没有权限访问此功能");
                $(".noAuth").show();
			}
		});
	},
	/*八个公共属性和方法结束*/
	moduleName : "",
	imgList : [],
	actionMap : {},
	advert : [],
	checkLiIsShow : function(index,num){
		index = new Number(index);
		num = new Number(num);
		if((index+1) % num == 0){//取余数
			return true;
		}else{
			return false;
		}
	},
	checkLiIsClose : function(length,num){
		length = new Number(length);
		num = new Number(num);
		if(length % num == 0){
			return false;
		}else{
			return true;
		}
	},
	openActionLayer : function(index){
		layer.open({
     		type:1, 
    		title:false,
    		content:$("#hidden_pop"+index),
    		area: ['880px', '538px'],
    		shade: 0.3, 
    		shadeClose: true,
    		anim:0 ,
    		success : function(){
                $(".enter_list a").hide();
            },
            end : function(){
                $(".enter_list a").show();
                $(".new-tishi").hide();
                $(".new-login").hide();
            }
  		});
	},
	getStringLength: function(str){
		return str.length;
	}
});

function queryResourcesCommon(){
	vm.nowDateString = getNowDateString();
	$.ajax({
		url : "../view/queryResourcesCommon",
		type : 'post',
		error : function(){
			alert("系统异常");
		},
		success : function(data){

			vm.resourcesCommon = data.obj.resourcesCommon;
			vm.moduleList = data.obj.moduleList;
			vm.friendLinkCommon = data.obj.friendLink;
		}
	});
}

function queryIndexPageContent(moduleId){
	$.ajax({
		url : "../view/queryIndexPageContent",
		data : {moduleId:moduleId},
		dataType : 'json',
		type : 'post',
		error : function(){
			alert("系统异常");
		},
		success : function(data){	
			vm.moduleName = data.obj.moduleName;
			vm.imgList = data.obj.imgList;
			vm.actionMap = data.obj.actionMap;
			vm.advert = data.obj.advertList;
		}
	});
}

function getDateString(){
	vm.nowDateString = getNowDateString();
}

function isLogin(){
	$.ajax({
		url : "../view/isLogin",
		type : 'post',
		error : function(){
			alert("系统异常");
		},
		success : function(data){

			vm.isLogin = data.obj;
		}
	});
}

function firendLink(url){
	if(url == "" || url == null || url == undefined){
		return false;
	}
	window.open(url);
}

function openActionUrl(id){
	$.ajax({
		url : "../view/getActionUrlById",
		data : {id:id},
		dataType : "json",
		type : "get",
		error : function(){
			alert("系统异常");
		},
		success : function(data){
			if(data.code == "0"){
				window.open(data.obj);
			}else{
				alert("系统错误");
			}
		}
	});
}

