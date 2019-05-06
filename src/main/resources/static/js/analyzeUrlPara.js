var paramsMap = {};

/**
 * 解析Url参数
 * 例,window.location.href=aaa.html?h=3
 * 在aaa.html引入此js，在js中直接取paramsMap.h
 */
function analyzeUrlPara(){
	var thisURL = decodeURI(document.URL);    
	var paraUrl =thisURL.split('?')[1];
	
	if(paraUrl == null){return;}
	
	var paraKeyValueSet = paraUrl.split('&');
	for(var i = 0; i < paraKeyValueSet.length; i++){
		var paraKeyValue = paraKeyValueSet[i];
		
		var key = paraKeyValue.split('=')[0];
		var value = paraKeyValue.split('=')[1].replace(/#/g, "");
		
		paramsMap[key] = value;
	}
	
}

analyzeUrlPara();