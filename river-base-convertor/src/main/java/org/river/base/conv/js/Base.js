function format(pattern,args){
    return java.lang.String.format(pattern,args);
}

/**
 * <p>
 * 克隆对象
 * @param obj
 * @returns
 */
function clone(obj){
	var cloneObj;
	if((typeof obj)==='object'){
		var name;
		for(name in obj){
			if((typeof obj[name])==='Object'){
				cloneObj[name]=clone(obj[name]);
			}else{
				cloneObj[name]=obj[name];
			}			
		}
	}else{
		cloneObj=obj;
	}
	return cloneObj;
}

/**
 * 按击中比例返回comps值
 * @param comps ['a','b','c','d']
 * @param ratio [0.5,0.1,0.2,0.2]
 */
function hitTargetWithRatio(comps,ratio){
	return comps[hit(ratio)];
}

/**
 * 按击中比例返回数组位数
 * @param ratio [0.5,0.1,0.2,0.2]
 */
function hit(ratio){
	var i;
	var sum=0;
	for(i=0;i<ratio.length;i++){
		sum=sum+ratio[i];		
	}
	
	var newRatio=new Array();	
	for(i=0;i<ratio.length;i++){
		newRatio.push(ratio[i]/sum);	
	}
	
	var random=Math.random();
	var newSum=0;
	for(i=0;i<newRatio.length;i++){
		newSum=newSum+newRatio[i];
		if(random<=newSum){
			return i;
		}		
	}
	
	return 0;
}


/**
 * <p>
 * 时间格式化
 */
Date.prototype.format =function(format)
{
	var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"H+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
	}
	
	if(/(y+)/.test(format)) {
		format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4- RegExp.$1.length));
	}
	
	for(var k in o){
		if(new RegExp("("+ k +")").test(format)){
			format = format.replace(RegExp.$1,RegExp.$1.length==1? o[k] :("00"+ o[k]).substr((""+ o[k]).length));
		}			
	}
		
	return format;
}
