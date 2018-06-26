<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>会员中心</title>
    <%@include file="/WEB-INF/views/include/viewings.jsp"%>
    <script type="text/javascript" src="resource/jquery.upload.js"></script>
    <script type="text/javascript" src="resource/md5.js"></script>
    
</head>
<body>

<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- /Header -->

<!-- Section: Page Header -->
<section class="section-page-header">
    <div class="container">
        <div class="row">

            <!-- Page Title -->
            <div class="col-md-8">
                <h1 class="title">工商注册/变更</h1>
                
            </div>
            <!-- /Page Title -->

            <!-- Breadcrumbs -->
            <div class="col-md-4">
                <ul class="breadcrumb">
                    <li><i class="fa fa-fw fa-home"></i> <a href="index.html">首页</a></li>
                    <li><a href="会员中心.html">会员中心</a></li>
                    <li>基本资料</li>
                </ul>
            </div>
            <!-- /Breadcrumbs -->

        </div>
    </div>
</section>
<!-- /Section: Page Header -->

<!-- Main -->
<main class="main-container">
    <div class="container"  style="margin-top: 20px;">
        <div class="row">

            <!-- Blog Sidebar -->
            <div class="col-md-3">            	
                <!-- Widget: Categories -->
                <div class="widget">
                    <h4 class="title">会员中心</h4>
                    <ul class="sidebar-menu">
                        <li id="cur"><a href="会员中心.html">基本资料</a></li>
                        <li><a href="我的预约.html">我的预约</a></li>
                        <li><a href="我的留言.html">我的留言</a></li>
                        <li><a href="购物车.html">购物车</a></li> 
                        <li><a href="memberActityPage.hx">活动-我的发布</a></li> 
                        <li><a href="">活动-我的参与</a></li> 
                    </ul>
                </div>
                <!-- /Widget: Categories -->


            </div>
            <!-- /Blog Sidebar -->

            <!-- Blog Content -->
            <div class="col-md-9">

                <!-- Posts List -->
                <div class="posts-list">

                    <!-- Single Post With Image -->
                    <article class="blog-post">
                        <!-- Post Title -->
                        <h2 class="title">基本资料</h2>
                        <!-- /Post Title -->
                        <!-- Post Content -->
                        <div class="content" style="margin-top: 30px;">
                        	<form class="personal" method="post" id="usrFrm" name="usrFrm">
                        		<input type="hidden" value="${userDTO.hxuuid }" name="hxuuid"/>
                        		<input type="hidden" value="${userDTO.member_pwd }" name="oldPwd" id="oldPwd"/>
                        		<div class="name"><label>昵称：</label><input name="member_nick" id="member_nick" type="text" value="${userDTO.member_nick }"/></div> 
                        		<div class="tel"><label>手机号码：</label><input name="member_mobile" id="member_mobile" disabled="disabled" type="tel" value="${userDTO.member_mobile }"/></div>
                        		<div class="name"><label>真实姓名：</label><input name="member_name" id="member_name" type="text" value="${userDTO.member_name }"/></div>
                        		<div class="password"><label>密码：</label><input name="member_pwd" id="member_pwd" type="password" value="${userDTO.member_pwd }"/></div>
                        		<div class="file"><label>头像：</label>
                        			<img id="member_headImg" src="resource/images/headerPic.png" />
                        			<input name="file" id="file" type="button" value="上传头像" width="58px;" height="58px;" onclick="doUpload('${userDTO.hxuuid }','member_head')" />
                        			<input type="hidden" value="${userDTO.member_head }" id="member_head" name="member_head"/>
                        		</div> 
                        		<div class="btn-save"><input class="sub" type="button" value="保存" onclick="javascript:dosubmit();"></div>
                        	</form>
                           
                        </div>
                        <!-- /Post Content -->      
                    </article>
                    <!-- /Single Post With Image -->
                </div>
                <!-- /Posts List -->
            </div>
            <!-- /Blog Content -->

        </div>
    </div>
</main>
<!-- /Main -->

<!-- Footer -->
<%@include file="/WEB-INF/views/include/viewingsfooter.jsp"%>
<!-- /Footer -->

<!-- Scroll To Top -->
<div id="scroll-to-top" class="scroll-to-top">
    <i class="icon fa fa-angle-up"></i>
</div>
<!-- /Scroll To Top -->

<!-- Modal: Result Message -->
<div class="modal fade" id="result" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="fa fa-times"></i>
                </button>
                <h4 class="modal-title">Sending message</h4>
            </div>

            <div class="modal-body">

                <div class="result-icon">
                    <div class="icon-border">
                        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" preserveAspectRatio="none">
                            <circle cx="8" cy="8" r="6.215" transform="rotate(90 8 8)"></circle>
                        </svg>
                        <i class="icon fa fa-check"></i>
                    </div>
                </div>

                <p class="modal-result h4 text-center"></p>
            </div>

        </div>
    </div>
</div>
<!-- /Modal: Result Message -->

<!-- SCRIPTS -->
<!-- <script src="js/jquery-2.2.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.stellar.min.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/masonry.pkgd.min.js"></script>
<script src="js/general.js"></script> -->
<!-- /SCRIPTS -->

<!-- TODO: Remove this block, Demo-version only -->
<script type="text/javascript">
	$(function(){
		//头像
		var member_head = "${userDTO.member_head}";
		if (member_head!='' && member_head!=null) {
			$("#member_headImg").attr("src", member_head);
		}
	});
	//修改用户信息
	function dosubmit() {
		var member_name = $("#member_name").val();
		var member_pwd = $("#member_pwd").val();
		console.log(member_name);
		console.log(member_pwd);
		var oldPwd = $("#oldPwd").val();
		if ($.trim(member_name)==null || $.trim(member_name)=="") {
			alert("真实姓名不能为空");
			return;
		}
		if ($.trim(member_pwd)==null || $.trim(member_pwd)=="") {
			alert("密码不能为空");
			return;
		}
		//新密码加密
		if (member_pwd!= oldPwd) {
			$("#member_pwd").val(hex_md5(member_pwd).toUpperCase());
		}
		$.post('updateUser.hx',util.serializeObject($('#usrFrm')),function(info){
			if(info.status){
				alert(info.info);
				$("#usrNm").html(info.attr.usrNm);
			}else{
				alert(info.info);
			}
		});
	}
	//上传图片
	function doUpload(hxuuid, idfz) {
		if(confirm("你确认要修改图片？")){
			$.upload({
				// 上传地址
				url : 'updateMemberHead.hx',
				// 文件域名字
				fileName : 'file',
				// 其他表单数据
				params : {
					"hxuuid" : hxuuid,
				},
				// 上传完成后, 返回json, text
				dataType : 'json',
				// 上传之前回调,return true表示可继续上传
				onSend : function() {
					return true;
				},
				// 上传之后回调
				onComplate : function(data) {
					if (data.status) {
						$("#"+idfz+"Img").attr("src", "/head/"+data.attr.filename);
						$("#"+idfz).val(data.attr.filename);
					} else {
						if(data.info!=null && data.info!='undefined'){
							alert(data.info);
						}else{
							alert("登录已超时，请重新登录！");
							setTimeout(function(){location.href = "toUsrlogin.do";},500);
						}
					}
				}
			});
		}
	}
</script>
</body>
</html>
