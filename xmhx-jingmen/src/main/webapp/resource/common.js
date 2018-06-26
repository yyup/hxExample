/****全局变量****/
var UA = navigator.userAgent.toLowerCase();
//判断浏览器
var NV = { 
	version: (UA.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [])[1], 
	safari: ( (/safari/.test(UA)) && (!(/chrome/.test(UA))) ), 
	opera:  /opera/.test(UA),
	chrome:( (/chrome/.test(UA)) && (!(/maxthon/.test(UA))) ),
	msie: /msie/.test(UA), 
	firefox: /firefox/.test(UA),
	maxthon: /maxthon/.test(UA)
};
//屏蔽所有页面的backspace键，以免按错了退出到登录页面
document.onkeydown=function(){
	if(event.keyCode == 8){
		if( event.srcElement.tagName.toLowerCase()!="input" && 
			event.srcElement.tagName.toLowerCase()!="text" &&
			event.srcElement.tagName.toLowerCase()!="textarea"
		){
			event.returnValue = false;
		}
	}
}
/****通用方法****/
//去空格
function trim(str){
	if(null==str||str==undefined||str==""){
		return "";
	}
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function formatsimpledate(strTime) {
	var date = new Date(strTime);
	return myformatter(date);
}
//日期格式化
function dateFormate(obj){
	if(obj==null||obj==undefined){
		return "";
	}else{
		var year = obj.getYear();
		var month = obj.getMonth()+1;
		var date = obj.getDate();
		return year+"-"+monthFormate(month)+"-"+date;
	}
}
//日期格式化(2014-03-27 13:47:44.0)
function dateFormateStr(obj,patten){
	if(obj==""||obj==null){
		return null;
	}
	var arys = obj.split(" ");
	var date = arys[0];
	var time = arys[1];
	time = time.substring(0,time.length-1);
	var ymd = date.split("-");
	if(patten=="yyyy年MM月dd日"){
		return ymd[0]+"年"+ymd[1]+"月"+ymd[2]+"日";
	}else if(patten=="yyyy年MM月dd日  hh:mm:ss"){
		return ymd[0]+"年"+ymd[1]+"月"+ymd[2]+"日 "+hms;
	}
	return ymd[0]+"年"+ymd[1]+"月"+ymd[2]+"日";
}
//月份格式化
function monthFormate(month){
	if(month<10){
		return "0"+month;
	}else{
		return month;
	}
}
//说明： 判断是否为数字(包括负值)
function isFloat(iNum){
	if(!iNum){
		return false;
	}
	var strP = /^(-{0,1})\d+(\.\d+)?$/;    //数字的正则表达
	//不符合正则表达失，返回false
	if(!strP.test(iNum)){
		return false;
	}
	try{
  		if(parseFloat(iNum)!=iNum){
  			return false;
  		}
  	}catch(ex){
   		return false;
  	}
  	return true;
}
//只允许输入正整数
function inputNumber(e){
	var code = (e.keyCode ? e.keyCode : e.which);
	if( (code>=48&&code<=57) || (code>=96&&code<=105) || code==8 || code==13 || code==37 || code==39 ){
		return;
	}
	if(NV.firefox){
		e.preventDefault();
	}else if(NV.chrome){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.opera){
		e.preventDefault();
	}else if(NV.safari){
		e.preventDefault();
	}else if(NV.maxthon){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.msie){
		var version = NV.version;
		if(version>8){
			e.preventDefault();
		}else{
			e.returnValue=false;
		}
	}
	return;
}
//只允许输入正负整数
function inputNumbers(e,obj){
	var code = (e.keyCode ? e.keyCode : e.which);
	if( (code>=48&&code<=57) || (code>=96&&code<=105) || code==8 || code==13 || code==37 || code==39 ){
		return;
	}
	//'-'
	if((code==109||code==189)&&obj.selectionStart==0){
		return;
	}
	if(NV.firefox){
		e.preventDefault();
	}else if(NV.chrome){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.opera){
		e.preventDefault();
	}else if(NV.safari){
		e.preventDefault();
	}else if(NV.maxthon){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.msie){
		var version = NV.version;
		if(version>8){
			e.preventDefault();
		}else{
			e.returnValue=false;
		}
	}
	return;
}
//只允许输入正数
function inputFloat(e,obj){
	var code = (e.keyCode ? e.keyCode : e.which);
	if( (code>=48&&code<=57) || (code>=96&&code<=105) || code==8 || code==13 || code==37 || code==39 ){
		return;
	}
	//'.' 
	if(code==190||code==110){
		var vl = obj.value; 
		if(vl.indexOf(".")==-1){
			return;
		}
	}
	if(NV.firefox){
		e.preventDefault();
	}else if(NV.chrome){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.opera){
		e.preventDefault();
	}else if(NV.safari){
		e.preventDefault();
	}else if(NV.maxthon){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.msie){
		var version = NV.version;
		if(version>8){
			e.preventDefault();
		}else{
			e.returnValue=false;
		}
	}
	return;
}
//只允许输入正负数
function inputFloats(e,obj){
	var code = (e.keyCode ? e.keyCode : e.which);
	if( (code>=48&&code<=57) || (code>=96&&code<=105) || code==8 || code==13 || code==37 || code==39 ){
		return;
	}
	//'-'
	if((code==109||code==189)&&obj.selectionStart==0){
		return;
	}
	//'.' 
	if(code==190||code==110){
		var vl = obj.value; 
		if(vl.indexOf(".")==-1){
			return;
		}
	}
	if(NV.firefox){
		e.preventDefault();
	}else if(NV.chrome){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.opera){
		e.preventDefault();
	}else if(NV.safari){
		e.preventDefault();
	}else if(NV.maxthon){
		//229--在输入法状态下的键盘值
		if(code==229){
			obj.blur();
			return;
		}
		e.preventDefault();
	}else if(NV.msie){
		var version = NV.version;
		if(version>8){
			e.preventDefault();
		}else{
			e.returnValue=false;
		}
	}
	return;
}
//说明： 判断是否为数字(不包括负值)
function isNumber(iNum){
	if(!iNum){
		return false;
	}
	var strP = /^\d+$/;    //数字的正则表达
	//不符合正则表达失，返回false
	if(!strP.test(iNum)){
		return false;
	}
	try{
  		if(parseFloat(iNum)!=iNum){
  			return false;
  		}
  	}catch(ex){
   		return false;
  	}
  	return true;
}
//说明： 判断是否为数字(包括负值)
function isNumbers(iNum){
	if(!iNum){
		return false;
	}
	var strP = /^-{0,1}\d+$/;    //数字的正则表达
	//不符合正则表达失，返回false
	if(!strP.test(iNum)){
		return false;
	}
	try{
  		if(parseFloat(iNum)!=iNum){
  			return false;
  		}
  	}catch(ex){
   		return false;
  	}
  	return true;
}
//说明： 判断是否为中文
function isExistZh(vl){
	if(!vl){
		return false;
	}
	var strP = /^.*[\u4E00-\u9FA5]+.*$/;//正则表达
	//不符合正则表达失，返回false
	if(!strP.test(vl)){
		return false;
	}
  	return true;
}

//允许图片的格式
var allowImgExt=".jpg|.jpeg|.png|";
function checkExt(obj,msg){
	if(obj.value==""){
		return false;
	}
	var fileExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
	//判断文件类型是否允许上传
	if(allowImgExt.indexOf(fileExt+"|")==-1) {
		$.messager.alert('提示信息',msg+"必须为jpg或者png格式的图片");
		obj.value="";
	    return false;
	}
	return true;
}

//重填
function toreset(frm){
	document.getElementById(frm).reset();
}

//添加编辑器
var kindEditor={};
function addEditor(textarea,form){
	KindEditor.ready(function(K) {
		var projectNm="xmhx-kechuang";
		kindEditor = KindEditor.create('textarea[name="'+textarea+'"]', {
			cssPath : '/'+projectNm+'/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/'+projectNm+'/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/'+projectNm+'/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true
		});
	});
}

function getScrollTop(){
	var scrollTop=0;
	if(document.documentElement&&document.documentElement.scrollTop){
		scrollTop=document.documentElement.scrollTop;
	}else if(document.body){
		scrollTop=document.body.scrollTop;
	}
	return scrollTop;
}

//加法函数  
function accAdd(arg1, arg2) {  
    var r1, r2, m;  
    try {  
        r1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r1 = 0;  
    }  
    try {  
        r2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r2 = 0;  
    }  
    m = Math.pow(10, Math.max(r1, r2));  
    return (arg1 * m + arg2 * m) / m;  
}   
//给Number类型增加一个add方法，，使用时直接用 .add 即可完成计算。   
Number.prototype.add = function (arg) {  
    return accAdd(arg, this);  
};

//减法函数  
function Subtr(arg1, arg2) {  
    var r1, r2, m, n;  
    try {  
        r1 = arg1.toString().split(".")[1].length;  
    }
    catch (e) {  
        r1 = 0;  
    }
    try {  
        r2 = arg2.toString().split(".")[1].length;  
    }
    catch (e) {  
        r2 = 0;  
    }
    m = Math.pow(10, Math.max(r1, r2));  
     //last modify by deeka  
     //动态控制精度长度  
    n = (r1 >= r2) ? r1 : r2;  
    return ((arg1 * m - arg2 * m) / m).toFixed(n);  
}
//给Number类型增加一个add方法，，使用时直接用 .sub 即可完成计算。   
Number.prototype.sub = function (arg) {  
    return Subtr(this, arg);  
};

//乘法函数  
function accMul(arg1, arg2) {  
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();  
    try {  
        m += s1.split(".")[1].length;  
    }  
    catch (e) {  
    }  
    try {  
        m += s2.split(".")[1].length;  
    }  
    catch (e) {  
    }  
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);  
}   
//给Number类型增加一个mul方法，使用时直接用 .mul 即可完成计算。   
Number.prototype.mul = function (arg) {  
    return accMul(arg, this);  
};   
  
//除法函数  
function accDiv(arg1, arg2) {  
    var t1 = 0, t2 = 0, r1, r2;  
    try {  
        t1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
    }  
    try {  
        t2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
    }  
    with (Math) {  
        r1 = Number(arg1.toString().replace(".", ""));  
        r2 = Number(arg2.toString().replace(".", ""));  
        return (r1 / r2) * pow(10, t2 - t1);  
    }  
}   
//给Number类型增加一个div方法，，使用时直接用 .div 即可完成计算。   
Number.prototype.div = function (arg) {  
    return accDiv(this, arg);  
};

// 时间控件datebox格式为年-月-日
function myformatterdate(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparserdate(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	}else{
		return new Date();
	}
}

// 时间控件datetimebox格式为年-月-日 时:分:秒
function myformatterdatetime(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var h = date.getHours();
    var min = date.getMinutes();
    var sec = date.getSeconds();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(sec<10?('0'+sec):sec);
}
function myparserdatetime(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	var h = parseInt(ss[3],10);
	var min = parseInt(ss[4],10);
	var sec = parseInt(ss[5],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)){
		return new Date(y,m-1,d,h,min,sec);
	}else{
		return new Date();
	}
}

// 首页查询
function searchInfomation(_searchcode) {
	if(!_searchcode)return;
	var regexNEED = /^NEED_.+$/ig;
	var regexSR = /^SR_.+$/ig;
	var regexCD = /^CD_.+$/ig;
	var regexBUG = /^BUG_.+$/ig;
	var regexPRD = /^PRD_.+$/ig;
	var regexsprd = /^sprd_.+$/ig;
	var regexsbug = /^sbug_.+$/ig;
	//var regexHT = /^HT_.+$/ig;
	var regexRD = /^RD_.+$/ig;
	var regexRM = /^RM_.+$/ig;
	var regexVersion = /.+/;
	var _operUrl='';
	if (regexNEED.test(_searchcode)) {
		_operUrl="needinfo.do?requireid=" + _searchcode;
	} else if(regexSR.test(_searchcode)) {
		_operUrl="srdevelopeinfo.do?srid=" + _searchcode;
	} else if(regexCD.test(_searchcode)) {
		_operUrl="xxx.do?cdid=" + _searchcode;
	} else if(regexBUG.test(_searchcode)) {
		_operUrl="bugreport.do?bugid=" + _searchcode;
	} else if(regexPRD.test(_searchcode)) {
		_operUrl="prdreport.do?prdid=" + _searchcode;
	} else if(regexsprd.test(_searchcode)) {
		_operUrl="subprdallot.do?sprdid=" + _searchcode;
	} else if(regexsbug.test(_searchcode)) {
		_operUrl="subbugallot.do?sbugid=" + _searchcode;	
	//} else if(regexHT.test(_searchcode)) {
	//	_operUrl="xxx.do?htid=" + _searchcode;
	} else if(regexRD.test(_searchcode)) {
		_operUrl="rdbaseinfo.do?rdid=" + _searchcode;
	} else if(regexRM.test(_searchcode)) {
		_operUrl="verbaseinfo.do?rmid=" + _searchcode;
	} else if(regexVersion.test(_searchcode)) {
		_operUrl="verbaseinfo.do?versionid=" + _searchcode;
	}
	return _operUrl;
}
//判断不为空
function notEmpty(_str){
	if(_str != null && $.trim(_str) != ''){
		return true;
	}
	return false;
}
//判断为空
function isEmpty(_str){
	if(_str == null || $.trim(_str) == ''){
		return true;
	}
	return false;
}
// 构建动态对象
function dynamicObject(fieldid, index) {
	var DynamicObj = new Object();
	DynamicObj.field = fieldid+index;
	var _dynamicObject = $("#"+DynamicObj.field+"");
	return _dynamicObject;
}
// 构建动态值
function dynamicValue(fieldid, index) {
	var DynamicObj = new Object();
	DynamicObj.field = fieldid+index;
	var _dynamicValue = $("#"+DynamicObj.field+"").val();
	return _dynamicValue;
}
// 判断一个字符串是否包含一个子串
function isContains(str, substr) {
    return new RegExp(substr).test(str);
}

/**
获取两个日期之间的间隔天数
*/
function getDateDiff(startDate,endDate) { 
	var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime(); 
	var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime(); 
	var dates = (endTime-startTime)/(1000*60*60*24); 
	return dates; 
}

/**
 * 指定日期与当前日期的间隔天数
 * endDate : 必须传String
 */
function getDateDiff2(endDate) {
	var startTime = new Date().getTime();
	var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime(); 
	var dates = (endTime-startTime)/(1000*60*60*24); 
	return dates; 
}

//检查查询时的开始、结束时间
function checkSearchDate(stTime, edTime){
	if((stTime!="" && edTime=="") || (stTime=="" && edTime!="")){
		displayMsg("提交开始时间、结束时间需同时选择!",false);
		return false;
	}
 	var dates=getDateDiff(stTime,edTime);
 	//alert(dates);
 	if(dates < 0){
 		displayMsg("开始时间不能大于结束时间!",false);
 		return false;
 	}
 	if(dates >90){
 		displayMsg("查询时间间隔不能超过90天!",false);
 		return false;
 	}
 	return true;
}

//检查是否从下拉列表框中选择的数据
function checkIsComBoxData(inputVal, hiddenVal, tipMsg){
	if(inputVal!="" && hiddenVal==""){
		displayMsg("您所输入的\""+inputVal+"\"不存在，请从\""+tipMsg+"\"下拉框中选择!");
		return false;
	}
	return true;
}

//判断字符串是否包含某个子窜
function isContains(str,substr){
	return new RegExp(substr).test(str);
}
//点击文本框之前里面显示请选择
function beforeclick(obj){
	if(isEmpty(obj.value)){
		obj.style.color = 'gray';
		obj.value = "请选择..";
	}else{
		obj.style.color = 'black';
	}
}
//点击文本框之前里面显示请选择
function afterclick(obj){
	if(obj.value == '请选择..'){
		obj.style.color = 'black';
		obj.value = '';
	}
}