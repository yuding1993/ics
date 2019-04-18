
//返回 欢迎您！5月18日 Thu 15:38:23格式字符串
function getNowDateString(){
	var resultString = "欢迎您！";
	var nowDate = new Date();
	
	var hours = nowDate.getHours()+"";
	var minutes = nowDate.getMinutes()+"";
	var seconds = nowDate.getSeconds()+"";
	if(hours.length < 2){
		hours = "0"+hours;
	}
	if(minutes.length < 2){
		minutes = "0"+minutes;
	}
	if(seconds.length < 2){
		seconds = "0"+seconds;
	}
	
	var week = nowDate.getDay();
	var weeks = getEnglishWeek(week);
	
	resultString += nowDate.getMonth()+1+"月"+nowDate.getDate()+"日";
	resultString += weeks+" "+hours+":"+minutes+":"+seconds;
	return resultString;
}

function getEnglishWeek(week){
	var result = "";
	if(week == 0){
		result = "Sun";
	}else if(week == 1){
		result = "Mon";
	}else if(week == 2){
		result = "Tue";
	}else if(week == 3){
		result = "Wed";
	}else if(week == 4){
		result = "Thu";
	}else if(week == 5){
		result = "Fri";
	}else if(week == 6){
		result = "Sat";
	}
	return result;
}