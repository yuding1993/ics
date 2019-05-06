avalon.ready(function(){
	/*公共资源查询*/
	queryResourcesCommon();//查询头部图片以及头部、底部文字、模块列表
	vm.nowModuleId = paramsMap.moduleId;//定义当前模块
	/*公共资源查询结束*/
	queryIndexPageContent(paramsMap.moduleId);//查询当前模块资源
	
});

var vm = avalon.define({
	$id : "apply",
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
    login : function(){//登陆
    var path = window.location.pathname + window.location.search;
    path = path.substring(1, path.length);//当index.jsp地址改变时，截取的值也需改变
    window.location.href = "../sec/"+path;
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
	imgOne : {},
	actionMap : {},
	displayContro : [],
	openActionLayer : function(index,status){
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
			vm.imgOne = data.obj.imgList[0];
			vm.moduleName = data.obj.moduleName;
			vm.actionMap = data.obj.actionMap;
			/*for(var i=0;i<data.obj.actionMap;i++){
				vm.displayContro.push(0);
			}*/
			
			//定位当前弹出框，由datacenter调用页面
			var layFlag = paramsMap.flag;
			if(layFlag != null && layFlag != undefined && layFlag != ""){
				console.log(layFlag);
				vm.openActionLayer(layFlag,0);
			}
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
			layer.msg('服务器异常！', {icon: 7,time:3000});
		},
		success : function(data){
			if(data.code == "0"){
				window.open(data.obj);
			}else{
				layer.msg('系统错误！', {icon: 7,time:3000});
			}
		}
	});
}

