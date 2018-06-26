<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">
	.outBtn {
		color: #008cd6;
	}
</style>

<!--登录模态框（modal）-->
   <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h4 class="modal-title" id="myModalLabel">登录</h4>
               </div>
               <div class="modal-body">
               		<form method="post" id="loginFrm" name="loginFrm">
               			<div style="height:40px;">
               				<span style="color:red;">*</span>手机号码：<input name="member_mobile" id="memberMobile" type="text" value=""/>
               			</div>
               			<div style="height:40px;">
               				<span style="color:red;">*</span>&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="member_pwd" id="memberPwd" type="password" value=""/>
               			</div>
               		</form>
               		<div><a href="#" data-toggle="modal" data-target="#myModal">还没账户？马上注册</a></div>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button><!--data-dismiss="modal" 用于关闭模态窗口-->
                   <button type="button" class="btn btn-primary" onclick="toLogin();">登录</button>
               </div>
           </div><!-- /.modal-content -->
       </div><!-- /.modal -->
   </div>
<!--注册模态框（modal）-->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h4 class="modal-title" id="myModalLabel">注册</h4>
               </div>
               <div class="modal-body">
               		<form method="post" id="registFrm" name="registFrm">
               			<div style="height:40px;">
               				<span style="color:red;">*</span>手机号码：<input name="member_mobile" id="membermobile" type="text" value=""/>
               			</div>
               			<div style="height:40px;">
               				<span style="color:red;">*</span>真实姓名：<input name="member_name" id="membername" type="text" value=""/>
               			</div>
               			<div style="height:40px;">
               				&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="member_pwd" id="memberpwd" type="password" value=""/>
               			</div>
               		</form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button><!--data-dismiss="modal" 用于关闭模态窗口-->
                   <button type="button" class="btn btn-primary" onclick="toRegist();">注册</button>
               </div>
           </div><!-- /.modal-content -->
       </div><!-- /.modal -->
   </div>
   
   <script type="text/javascript">
   		//登录
   		function toLogin() {
   			//验证手机号码密码不能为空
   			var member_mobile = $("#memberMobile").val();
   			var member_pwd = $("#memberPwd").val();
   			if ($.trim(member_mobile)==null || $.trim(member_mobile)=="") {
   				return;
   			}
			if ($.trim(member_pwd)==null || $.trim(member_pwd)=="") {
				return;
   			}
			$.post('loginPlat.hx', {"member_mobile":member_mobile, "member_pwd":member_pwd}, function(info){
				if(info.status){
					//关闭弹窗
					$('#loginModal').modal('hide');
					//隐藏登录按钮，赋值并展示登录信息
					$("#loginBtn").hide();
					$("#usrCenter").show();
					$("#usrNm").html(info.info.member_name);
					$("#loginMessage").show();
				}else{
					alert(info.info);
				}
			});
   		}
   		//注册
   		function toRegist(){
   			var member_mobile = $("#membermobile").val();
   			var member_name = $("#membername").val();
   			if ($.trim(member_mobile)==null || $.trim(member_mobile)=="") {
   				alert("请填写手机号码");
   				return;
   			}
   			//验证手机号码
			if(!(/^1[34578]\d{9}$/.test(member_mobile))){ 
				alert("请填写正确的手机号码");
   				return; 
		    } 
			if ($.trim(member_name)==null || $.trim(member_name)=="") {
				alert("请填写真实姓名");
   				return;
   			}
			$.post('regist.hx', util.serializeObject($('#registFrm')), function(info){
				if(info.status){
					alert(info.info);
					//关闭弹窗
					$('#myModal').modal('hide');
					$('#loginModal').modal('hide');
					//隐藏登录按钮，赋值并展示登录信息
					$("#loginBtn").hide();
					$("#usrCenter").show();
					$("#usrNm").html(info.attr.userDTO.member_name);
					$("#loginMessage").show();
				}else{
					alert(info.info);
				}
			});
   		}
   		//退出登录
   		function toOut() {
   			window.location.href="loginToOut.hx";
   		}
   </script>