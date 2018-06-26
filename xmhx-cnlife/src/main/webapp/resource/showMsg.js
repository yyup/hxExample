//消息提示框
function showMsg(showMsg, flag){
	if(!showMsg)return;
	var msg="";
	if(showMsg=="1"){
		msg="添加成功";
	}else  if(showMsg=="2"){
		msg="修改成功";
	}else if(showMsg=="3"){
		msg="删除成功";
	}else if(showMsg=="4"){
		msg="添加失败";
	}else if(showMsg=="5"){
		msg="修改失败";
	}else if(showMsg=="6"){
		msg="删除失败";
	}else if(showMsg=="7"){
		msg="数据库操作失败,请与管理员联系";
	}else if(showMsg=="8"){
		msg="操作失败";
	}else if(showMsg=="9"){
		msg="菜单下有子菜单,不可以删除";
	}else if(showMsg=="10"){
		msg="功能按钮下不能有菜单";
	}else if(showMsg=="11"){
		msg="账号不存在";
	}else if(showMsg=="12"){
		msg="密码输入不正确";
	}else if(showMsg=="13"){
		msg="没有权限";
	}else if(showMsg=="14"){
		msg="名称已存在";
	}else if(showMsg=="15"){
		msg="登陆出错";
	}else if(showMsg=="16"){
		msg="用户名已注册,请更换用户名!";
	}else if(showMsg=="17"){
		msg="用户已停用!";
	}else if(showMsg=="18"){
		msg="请先删除所有下级!";
	}else if(showMsg=="19"){
		msg="该用户没有对应企业!";
	}else if(showMsg=="20") {
		msg="传参无值，不能下步操作!";
	}else if(showMsg=="21"){
		msg="查询无数据，不能下步操作!";
	}else{
		msg=showMsg;
	}
	if(msg){
		var _html = msg;
		if (flag == 1) {
			_html='<span style="font-weight: bold;color: green">'+msg+'</span>';
		} else if (flag == 2) {
			_html='<span style="font-weight: bold;color: red">'+msg+'</span>';
		} else {
			_html = msg;
		}
		$.messager.show({
			title:'信息结果',
			msg:_html,
			showType:'slide'
		});
	}
}
function displayMsg(showMsg, bln) {
	if(!showMsg)return;
	var _html='';
	if (bln) 
		_html='<span style="font-weight: bold;color: green">'+showMsg+'</span>';
	else 
		_html='<span style="font-weight: bold;color: red">'+showMsg+'</span>';
	$.messager.show({
		title:'信息提示',
		msg: _html,
		showType:'slide'
	});
}
function displayMsgTop(showMsg, bln) {
	if(!showMsg)return;
	var _html='';
	if (bln) 
		_html='<span style="font-weight: bold;color: green">'+showMsg+'</span>';
	else 
		_html='<span style="font-weight: bold;color: red">'+showMsg+'</span>';
	$.messager.show({
		title:'信息提示',
		msg: _html,
		showType:'slide',
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}
function displayAlert(title, msg) {
	$.messager.alert(title, msg);
}
function displayAlert(title, msg, icon) {
	$.messager.alert(title, msg, icon);
}
function showProgress(){
	$.messager.progress({title:'提示信息',msg:'正在处理中,请收稍等...'});
}
function closeProgress(){
	$.messager.progress('close');
}