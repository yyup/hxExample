<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新闻详情</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%@include file="/WEB-INF/views/include/source.jsp" %>
	</head>
	<style>
		.bottombtn {
			height: 40px;
			padding-left:480px;
			border-collapse: collapse;
			border-top: 1px solid #F6D098;
			background: #FCEAD9;
			margin-top: 20px;	
		}
		.toolBarTb td {
			padding: 8px;		
		}
		.winbottombtn {
			height: 40px;
			border-collapse: collapse;
			border-top: 1px solid #F7BF94;
			background: #FCEAD9;
			margin-top:20px;
		}
		.winshow{			
			border: 1px solid #F7BF94;
		}
		.wintable{			
			border: 1px solid #DEDEE0; 
			background:#F9F9F9; 
			padding:10px; 
			margin:15px;
		}
	</style>
	<body class="body1" >
		<div class="formInfo">
			审核状态：
			<select name="verifyStatus" id="verifyStatus" style="width: 100px;height: 25px;" disabled>
				<option value=""></option>
				<option value="0" <c:if test="${news.verifyStatus=='0' }">selected</c:if>>待审核</option>
				<option value="1" <c:if test="${news.verifyStatus=='1' }">selected</c:if>>审核通过</option>
				<option value="2" <c:if test="${news.verifyStatus=='2' }">selected</c:if>>审核不通过</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${news.verifyStatus=='0' }">
				<a class="easyui-linkbutton" style="margin-left:10px;" onclick="javascript:verify(1);">审核通过</a>
				<a class="easyui-linkbutton" style="margin-left:20px;" onclick="javascript:verify(2);">审核不通过</a>
			</c:if>
			<c:if test="${news.verifyStatus!='0' }">
				<span id="redoVerify">
					<a class="easyui-linkbutton" style="margin-left:10px;" onclick="javascript:re_verify();">重新审核</a>
				</span>
			</c:if>
			
		</div>
		<div id="page-wrap">
			<div class="divcss1">
				<div class="news_body">
					<div class="box" align="center" style="margin-top: 30px;">
						<input type="hidden" id="hxuuid" name="hxuuid" value="${news.hxuuid }" />
						<p style="font-weight: bolder;font-size: 23">${news.newsTitle}</p>
						<p style="font-weight: lighter;font-size: 16;margin-top: 5">${news.newsSubtitle}</p>
						<p style="font-size: 14px; color: #A1A1A1;border-bottom: 1 dashed #e5e5e5; padding-bottom:10px;">${news.date_created}   阅读量:${news.readCount }</p>
					</div>
					<img id="attachImg" src="${attachpath }" />
					<br/>
					<div style="margin-top: 5px; width: 100%; display: block; word-break: break-all; word-wrap: break-word; text-indent:2em;">
						<div style="width: 100%; display: block; word-break: break-all; word-wrap: break-word;">
							${news.newsContent }
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<script type="text/javascript">
		function verify(val){
			var _hxuuid=$("#hxuuid").val();
			$.ajax({
				url:"verifyNews.hx",
				data:{"verifyStatus":val,"hxuuid":_hxuuid},
				type:"post",
				success:function(data){
					if(data.status){
						displayMsg(data.info, true);
						setTimeout(function(){
							window.location.href = window.location.href;
						},500);
					}else{
						displayMsg(data.info, false);
					}
				}
			});
		}
		//重新审核
		function re_verify(){
			var _html = "";
			_html += '<a class="easyui-linkbutton" style="margin-left:10px;" onclick="javascript:verify(1);">审核通过</a>';
			_html += '<a class="easyui-linkbutton" style="margin-left:10px;" onclick="javascript:verify(2);">审核不通过</a>';
			$("#redoVerify").html(_html);
			$.parser.parse($('#redoVerify').parent());
		}
		</script>
	</body>
</html>