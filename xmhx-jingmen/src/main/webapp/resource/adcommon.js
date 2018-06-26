/**
 * @requires JQuery
 * @namespace util
 * @author daybreak
 * @version 1.0
 */
var util = $.extend({}, util);
/**
 * POST 同步 获取数据
 */
util.POST = function (url,data){
	var info = {} ;
	$.ajax({
		type:"POST",
		async:false,
		url:url,
		data:data,
		dataType:"json",
		success:function(data){
			info = data;
		}
	});
	return info;
};
/**
 * GET 同步 获取数据
 */
util.GET = function (url,data){
	var info = {} ;
	$.ajax({
		type:"GET",
		url:url,
		async:false,
		data:data,
		dataType:"json",
		success:function(data){
			info = data;
		}
	});
	return info;
};
/**
 * @requires jQuery
 * 将form表单元素的值序列化成对象
 * @returns object
 */
util.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};
/**
 * 将JSON对象转换为字符串
 */
util.Json2Str = function(jsonObj){
	var i = 0;
	var convStr = "";
	for(var key in jsonObj){
		if(i++==0){
			if (jsonObj[key] == undefined) {
				convStr +=  key + "=" + "";
			} else {
				convStr +=  key + "=" + jsonObj[key];
			}
		} else {
			if (jsonObj[key] == undefined) {
				convStr += "&"  + key + "=" + "";
			} else {
				convStr += "&" + key + "=" + jsonObj[key];
			}
		}
	}
	return convStr;
};
/**
 * 将一个Json对象加载到表单中
 */
util.load = function($form,jsonObj){
	$.each($form.serializeArray(), function(index,item) {
		var $item = $form.find('[name='+this['name']+']');
		if($item.length>1){//可能为Radio或者checkBox
			$form.find('[name='+this['name']+'][value='+jsonObj[this['name']]+']').prop('checked',true);
		}else{
			$item.val(jsonObj[this['name']]);
		}
	});
};
/**
 * 省市级联 - 待美化
 */
util.cityChoose = function(privinceId,cityId){
	var data = util.GET("choose_city.do",{"provinceid":$(privinceId).val()});
	var optionhtml = "";
	$.each(data,function(i,item){
		optionhtml +='<option value="'+item.cityid+'">'+item.cityname+'</option>';
	});
	$(cityId).html(optionhtml);
};
/**
 * 初始化选中的值
 */
util.initChooseCity =function(cityId,provinceVal,cityVal){
	if(!$.trim(provinceVal)){return;}
	var data = util.GET("choose_city.do",{"provinceid":provinceVal});
	var optionhtml = "";
	$.each(data,function(i,item){
		if(item.cityid==cityVal){
			optionhtml +='<option value="'+item.cityid+'" selected="selected">'+item.cityname+'</option>';
		}else{
			optionhtml +='<option value="'+item.cityid+'">'+item.cityname+'</option>';
		}
		
	});
	$(cityId).html(optionhtml);
};
/**
 * 过滤HTML标签
 */
util.filterHTMLTag = function (str) {
    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
    //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
    return str;
};
/**
 * 扩展Array方法  ==== 是否包含某个元素
 */
Array.prototype.contains = function(obj) {
	var i = this.length;
	while (i--) {
		if (this[i] === obj) {
			return true;
		}
	}
	return false;
};
/**
 * 扩展Array方法  ==== 根据元素值移除value
 */
var newarr = [];
Array.prototype.remove = function(value) {
	newarr = [];
	$.each(this,function(index,item){
		if(value==item){
			return true;
		}
		newarr.push(item);
	});
	return newarr;
};
/**
 * 上传图片
 * @required jquery.upload.js
 * 
 */
util.upload = function(fileName){
	$.upload({
		// 上传地址
		url:"upload_image.do",
		fileName: fileName, 
		params: {'fileName':fileName},
		// 上传之后回调
		onComplate: function(data) {
			if(data.status){
				$('#'+fileName+'_show').attr('src','../'+data.info).slideDown(500);
				$('#'+fileName+'_val').val(data.info);
			}else{
				parent.ShowMsg("操作提示：",data.info);
			}
		}
	});
};
/**
 * layer加载层
 * @param loadText 加载显示文字，有默认
 * @return 层id
 */
util.loading = function(loadText){
	var loadMsg = loadText||'请求数据中，请稍候…';
	return layer.msg(loadMsg, 0, {type: 16, shade: [0.1, '#ccc']});
};
/**
 * Confirm是否操作
 * option：传入对象参数
 * {msg:'Confirm框提示的消息',
 * confirm:function(){//点击确定的回调函数},
 * cancel:function(){//点击取消的回调函数}}
 */
util.confirm = function(option){
	$.layer({
	    shade: [0],
	    area: ['auto','auto'],
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:false,
	    dialog: {
	        msg: option.msg,
	        btns: 2,                    
	        type: 4,
	        btn: ['[确定]','[取消]'],
	        yes: function(index){
	        	option.confirm(index);
	        },
	        no: function(index){
	        	var cancel = option.cancel;
	        	if(cancel!=undefined){
	        		cancel(index);
	        	}
	        	layer.close(index);
	        }
	    }
	});
};
/**
 * layer dialog
 * @param opts 参数对象
 * @returns
 */
util.dialog = function(opts){
	this.defaults = {
		'id':'',//dialog内容id,class,使用Jq选择器
		'title': 'dialog title',
		'width':650,
		'height': 500,
		'index':0
	},
	this.options = $.extend({}, this.defaults, opts);
	if($(window).height()<(this.options.height+80)){
		this.options.height = $(window).height()-80;
	}
	this.options.index = $.layer({
	    type : 1,
	    shade : [0],
	    moveOut: true,
	    area : [this.options.width+'px', this.options.height+'px'],
	    title : '<b>'+this.options.title+'</b>',
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:true,
	    border: [10, 0.5, '#666'],
	    offset: ['25px' , ''],
	    success: function(){
	    	layer.shift('top', 400);
	    },
	    page : {dom : this.options.id}
	});
	return this.options;
};
/**
 * template辅助方法 number格式化方法
 * @param value 值
 * @param pattern 保留小数位
 */
/*template.helper('numberFormat', function (value, pattern) {
	return value.toFixed(pattern);
});*/
/**
 * template辅助方法 字符自适应
 * @param value 值
 * @param pattern 保留字数
 */
/*template.helper('textsub', function (value, pattern) {
	if(value.length>pattern){
		return value.substring(1,pattern)+"...";
	}
	return value;
});*/
