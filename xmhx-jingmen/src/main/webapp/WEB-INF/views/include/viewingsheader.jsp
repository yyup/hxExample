<%@ page language="java" pageEncoding="UTF-8"%>
<!-- header -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><img src="${ctx }/resource/kechuang/img/logo.png" /></a>
		</div>
		<div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li <c:if test="${activeIndex==1 }">class="active"</c:if>><a href="index.hx">网站首页</a></li>
				<li <c:if test="${activeNews==1 }">class="active"</c:if>><a href="news.hx">园区动态</a></li>
				<li <c:if test="${activeActivity==1 }">class="active"</c:if>><a href="activity.hx">活动</a></li>
				<li <c:if test="${activeChainBusiness==1 }">class="active"</c:if>><a href="chainBusiness.hx">连锁运营</a></li>
				<li <c:if test="${activeGardenDesc==1 }">class="active"</c:if>><a href="gardenDesc.hx">园区介绍</a></li>
				<li <c:if test="${activeAboutus==1 }">class="active"</c:if>><a href="aboutus.hx">关于我们</a></li>
				<li id="usrCenter" <c:if test="${activeMemberCenter==1 }">class="active"</c:if>  style="display:${usrNm!=null?'':'none' }">
					<a href="usrCenter.hx">会员中心</a>
				</li>
				<li id="loginBtn" style="display:${usrNm!=null?'none':'' }">
					<a href="#" data-toggle="modal" data-target="#loginModal">登录</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right" id="loginMessage" style="display:${usrNm!=null?'':'none' }">
				<li><a href="#" id="usrNm">${usrNm }</a></li>
				<li><a href="#" class="outBtn" onclick="jascript:toOut();">退出</a></li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>
 <%@include file="/WEB-INF/views/include/usrViewing.jsp"%>
