<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>登录</title>
		<%@ include file="/WEB-INF/views/include/source.jsp" %>
		<script type="text/javascript" src="${ctx }/resource/adcommon.js"></script>
	</head>
	<body class="login_bg" style="background-color: #126aca;">
		<div class="login">
			<form action="" method="post" name="loginFrm" id="loginFrm">
				<table class="login_table">
					<tr>
						<th>账号：</th>
						<td>
							<div>
								<input autocomplete="off" name="usrNo" id="usrNo" value="${usrNo}" style="border:0;height:38px;line-height: 38px;" 
								    onkeydown="tologinSubmit(event);"/>
							</div>
						</td>
						<td><span id="usrNoTip" class="onshow"></span></td>
					</tr>
					<tr>
						<th>密码：</th>
						<td>
							<div>
								<input autocomplete="off" type="password" name="usrPwd" id="usrPwd" value="${usrPwd}" style="border:0;height:38px;line-height: 38px;"
									onkeydown="tologinSubmit(event);"/>
							</div>
						</td>
						<td><span id="usrPwdTip" class="onshow"></span></td>
					</tr>
					<tr style="height: 10px;">
						<td  style="height: 10px;" colspan="3">
							<p id="error" style="display: none;line-height: 10px;"></p>
						</td>
					</tr>
					<tr>
						<!-- <th>&nbsp;</th> -->
						<td style="margin-top:25px; text-align:center;padding-left: 17px;" colspan="2">
							<input autocomplete="off" type="button" class="btn1" title="登录" onclick="toSubmit()"/>
						</td>
						<td><div id="error" style="display: none;"></div></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="copyright" style="color: black;">CopyRight @ 2015 弘信创业工场投资集团股份有限公司</div>
		<script type="text/javascript">
			$(function(){
				$.formValidator.initConfig({formID:"loginFrm"});
				$("#usrNo").formValidator({onShow:"请输入账号",onFocus:"请输入账号",onCorrect:"请输入账号"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"账号两边不能有空符号"},onError:"账号不能为空,请输入"});
				$("#usrPwd").formValidator({onShow:"请输入密码",onFocus:"请输入密码",onCorrect:"请输入密码"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请输入"});
				$("#usrNo").focus();
				showMsg("${showMsg}");
				
				$("#usrNo").focus(function() {
					$('#error').html('');
				});
				$("#usrPwd").focus(function() {
					$('#error').html('');
				});
			});
			// 回车登陆
			function tologinSubmit(e){
				var key = window.event?e.keyCode:e.which;
				if(key==13){
					toSubmit();
				}
			}
			// 登陆
			function toSubmit(){
				var usrNo = $("#usrNo").val();
				var usrPwd = $("#usrPwd").val();
				if ($.trim(usrNo) != null && $.trim(usrNo) != ""
						&& $.trim(usrPwd) != null && $.trim(usrPwd) != "") {
					dosubmit();
				}
			}
			function dosubmit() {
				$.post('${ctx}/manages/dologon.hx',util.serializeObject($('#loginFrm')),function(info){
					if(info.status){
						$('#error').html('<i class="form-success-basic"></i>登录成功').slideDown(100);
						setTimeout(function(){location.href = "${ctx}/manages/"+info.info;},500);
					}else{
						$('#error').html('<i class="form-error-basic"></i>'+info.info).slideDown(150);
					}
				});
			}
		</script>
	</body>
</html>
