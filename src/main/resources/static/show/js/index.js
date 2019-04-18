$(document).ready(function(){
	queryResourcesCommon();
	queryModuleList();
	queryFirstModule();
	//开启时钟
	var titleDateString = setInterval("getDateString()",1000);
});

var interValCleaArray = [];

var pageTop = avalon.define({
	$id : "pageTop",
	moduleList : [],
	resourcesCommon : {},
	nowDateString : "",
	nowModuleId : 0,//当前模块id
	viewPressCatId : 0,//要查看更多的资讯id
	skipPage : function(moduleId,pageUrl){
		pageTop.nowModuleId = moduleId;
		$("#content").load(pageUrl);
		//清除当前页面的定时任务
		for(var i=0;i<interValCleaArray.length;i++){
			clearInterval(interValCleaArray[i]);
		}
	}
});

var footer = avalon.define({
	$id : "footer",
	moduleList : [],
	resourcesCommon : {}
});

function queryFirstModule(){
	$.ajax({
		url : "../view/queryFirstPage",
		type : 'post',
		error : function(){
			alert("系统异常");
		},
		success : function(data){

			if(data.code == "0"){
				pageTop.nowModuleId = data.obj.moduleId;
				$("#content").load(data.obj.pageUrl);
			}else{
				window.location.href = "localhost:8080/single/show/404.html";
			}
		}
	});
}

function queryResourcesCommon(){
	$.ajax({
		url : "../view/queryResourcesCommon",
		type : 'post',
		error : function(){
			alert("系统异常");
		},
		success : function(data){

			pageTop.resourcesCommon = data.obj;
			footer.resourcesCommon = data.obj;
		}
	});
}

function queryModuleList(){
	$.ajax({
		url : "../view/queryModuleList",
		type : 'post',
		error : function(){
			alert("系统异常");
		},
		success : function(data){

			pageTop.moduleList = data.obj;
			footer.moduleList = data.obj;
		}
	});
}

function getDateString(){
	pageTop.nowDateString = getNowDateString();
}
