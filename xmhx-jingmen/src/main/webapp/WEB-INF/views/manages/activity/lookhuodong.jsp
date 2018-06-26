<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>活动审核详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/WEB-INF/views/include/source.jsp"%>
<link href="${ctx}/resource/kechuang/css/activity/ac_sh.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="news_body">
		<div class="f_reg_but">
			<a class="easyui-linkbutton l-btn l-btn-plain" plain="true"
				data-options="iconCls:'icon-undo'" href="javascript:toback();">返回</a>
			<c:if test="${activityVO.verifystatus == 0}">  
			<a class="easyui-linkbutton l-btn l-btn-plain" plain="true"
				data-options="iconCls:'icon-ok'" href="javascript:toverifypass();">审核通过</a>
			<a class="easyui-linkbutton l-btn l-btn-plain" plain="true"
				data-options="iconCls:'icon-cancel'"
				href="javascript:toverifynot();">审核不通过</a>
			</c:if> 
			<br> 【审核状态： <span
				id="verifystatusid"> <c:choose>
					<c:when test="${activityVO.verifystatus == 0}">待审核</c:when>
					<c:when test="${activityVO.verifystatus == 1}">
						<span style="color: green;">审核通过</span>
					</c:when>
					<c:when test="${activityVO.verifystatus == 2}">
						<span style="color: red;">审核不通过</span>
					</c:when>
					<c:otherwise>审核状态异常</c:otherwise>
				</c:choose>
			</span> 】
		</div>
		<div style="clear:both;"></div>
			<input type="hidden" name="verifystatus" id="verifystatus"value="${activityVO.verifystatus}" /> 
			<input type="hidden"name="activityid" id="activityid" value="${activityVO.hxuuid}" />
		<div class="main_right" style="margin-left: 80px;">
			<form method="" action="" class="publish">
				<div class="file">
					<label>活动封面：</label> 
					<c:forEach items="${activityVO.picList }" var="pics">
						<img src="${pics.attachpath}/${pics.attachname}${pics.attachtype}" width="150px" height="100px" />
					</c:forEach>
				</div>
				<div class="date">
					<label>活动类型：</label> 
					<c:choose>
						<c:when test="${activityVO.tp=='1' }">聚会</c:when>
						<c:when test="${activityVO.tp=='2' }">沙龙</c:when>
						<c:when test="${activityVO.tp=='3' }">培训</c:when>
						<c:when test="${activityVO.tp=='4' }">路演</c:when>
						<c:when test="${activityVO.tp=='5' }">开心农场</c:when>
					</c:choose>
				</div>
				<div class="date">
					<label>活动主题：</label>${activityVO.title }
				</div>
				<div class="date">
					<label>活动时间：</label>${activityVO.stime }&nbsp;~&nbsp;${activityVO.etime }
				</div>
				<div class="date">
					<label>报名截止时间：</label>${activityVO.overtime }
				</div>
				<div class="date">
					<label>活动地点：</label>${activityVO.address }
				</div>
				<div class="date">
					<label>联系人：</label>${activityVO.contact }
				</div>
				<div class="date">
					<label>联系电话：</label>${activityVO.tel }
				</div>
				<div >
					<label>活动详情：</label>
					<span style="float:left;width: 700px; height: 200px;border:none;color: #666666;
    font-family: '微软雅黑',Arial,'Lucida Grande','Lucida Sans Unicode'; font-size:14px;overflow:auto;" >
						${activityVO.content }
					</span>
				</div>
			</form>
		</div>
	</div>
	<!-- 审核不通过审核意见 -->
	<div id="VerifyDiv" class="easyui-window"
		style="top: 50px; width: 700px; height: 400px; padding: 10px;"
		minimizable="false" maximizable="false" collapsible="false"
		closed="true">
		<div style="border: 1px solid #DEDEE0;">
			<form action="" id="ExcutesqlFrm" method="post">
				<div style="margin: 20px; border: 1px solid #DEDEE0;">
					<textarea rows="" cols="" name="verifydesc" id="verifydesc"
						style="width: 600px; height: 200px;"></textarea>
					温馨提示：审核不通意见超过200字符将自动截位
				</div>

				<div id="tbExcutesql" style="padding: 10px; height: auto">
					<a href="javascript:onverifynot();" class="easyui-linkbutton"
						iconCls="icon-add" plain="true" data-options="size:'small'">保存</a>
					<a href="javascript:hiddenVerifyDivWin();"
						class="easyui-linkbutton" iconCls="icon-no" plain="true"
						data-options="size:'small'">关闭</a>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function toback() {
			location.href = "activity/huodongverify.hx";
		}
		function toverifypass() {
			$.messager.confirm('确认', '记录确认审核通过吗？', function(r) {
				if (r) {
					var _verifystatus = $("#verifystatus").val();
					var _activityid = $("#activityid").val();
					if (_verifystatus == 0) {
						$.post("${ctx}/manages/huodongverifypass.hx", {"activityid" : _activityid},
							function(data) {
								if (data.status) {
									$("#verifystatus").val('1');
									$("#verifystatusid").html('<span style="color: green;">审核通过</span>');
								} else {
									$.messager.alert("错误", "审核通过异常", "error");
								}
							});
					} else {
						$.messager.alert("温馨提示", "不是待审核状态，不需重复审核", "info");
					}
				}
			});
		}
		function toverifynot() {
			$.messager.confirm('确认', '记录确认审核不通过吗？', function(r) {
				if (r) {
					var _verifystatus = $("#verifystatus").val();
					if (_verifystatus == 0) {
						showVerifyDivWin('审核不通过意见');
					} else {
						$.messager.alert("温馨提示", "不是待审核状态，不需重复审核", "info");
					}
				}
			});
		}
		function onverifynot() {
			var _activityid = $("#activityid").val();
			var _verifydesc = $("#verifydesc").val();
			if (_verifydesc != null && $.trim(_verifydesc) != '') {
				$.post("${ctx}/manages/huodongverifyno.do", {
					"activityid" : _activityid,
					"verifydesc" : _verifydesc
				}, function(data) {
					if (data.status) {
						hiddenVerifyDivWin();
						$("#verifystatus").val('2');
						$("#verifystatusid").html(
								'<span style="color: red;">审核不通过</span>');
					} else {
						$.messager.alert("错误", "审核不通过异常", "error");
					}
				});
			} else {
				$.messager.alert("温馨提示", "审批意见不能为空", "info");
				return;
			}
		}
		function showVerifyDivWin(title) {
			$("#VerifyDiv").window({
				title : title,
				modal : true
			});
			$("#VerifyDiv").window('open');
		}
		function hiddenVerifyDivWin() {
			$("#VerifyDiv").window('close');
		}
	</script>
</body>
</html>
