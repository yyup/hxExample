<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8"/>
	<title>会员中心-活动</title>
	<link href="${ctx }/resource/kechuang/css/memberCenter/base.css" rel="stylesheet">
	<link href="${ctx }/resource/kechuang/css/memberCenter/index.css" rel="stylesheet">
	<%@include file="/WEB-INF/views/include/viewings.jsp"%>
	<script type="text/javascript" src="resource/userplat/js/jquery.leanModal.min.js"></script>
</head>

<body>

<!-- 顶部导航 -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>

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
                      <div class="main_right">
		              <div class="main_right_title">
		              <h1>发布活动</h1>
		              </div>
		              <form method="post" action=""  id="activityform" class="publish">
		              <div class="file"><label>活动封面:</label>
		                  <span id="spanpic"></span>
		                  <span onclick="doUpload('','','attachid');" style="cursor: pointer;"> <img src="resource/kechuang/img/memberCenter/add.png" class="add"/></span>
		                  <input type="hidden" value="31" id="attachmod"/>
		              </div>
		              <div>
		              <label>活动类型:</label> 
		              <select class="xl" name="tp" id="tp">
		                  <option value="">请选择</option>
		                  <option value="1">聚会</option>
		                  <option value="2">沙龙</option>
		                  <option value="3">培训</option>
		                  <option value="4">路演</option>
		                  <option value="5">开心农场</option>
		              </select>
		              </div>
		              <div class="zt"><label>活动主题:</label><input type="text" class="tex" placeholder="请输入主题" name="title" id="title"/></div>
		              <div class="detail"><label>活动详情:</label><textarea placeholder="请输入详情" name="content" id="content"></textarea></div>
		              <div class="date"><label>活动开始时间:</label><input type="text"  placeholder="开始时间" name="stime" id="stime" readonly="true" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'etime\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></div>
		              <div class="date"><label>活动结束时间:</label><input type="text" placeholder="结束时间" name="etime" id="etime" readonly="true" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'stime\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></div>
		              <div class="date"><label>报名截止时间:</label><input type="text" placeholder="截止时间" name="overtime" id="overtime" readonly="true" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'etime\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></div>
		              <div class="adr"><label>活动地点:</label><div class="kua"><input type="text" placeholder="活动地点" name="address" id="address"/><a href=""></a></div></div>
		              <div class="date"><label>联系人:</label><input type="text" placeholder="真实姓名" name="contact" id="contact"/></div>
		              <div class="date"><label>联系电话:</label><input type="text" placeholder="联系电话" name="tel" id="tel"/></div>
		              <div class="btn"><input class="sub" type="button" onclick="tosubmit()" value="提交"/><input class="back" type="button" value="返回" onclick="toback()"/></div>
		              <input type="hidden" id="attachid" name="attachid"/> 
		              </form>
		               <form id="AttachForm" name="AttachForm" action="" method="post"></form>
		          </div>                    
            </div>
          </div>
            <!-- /Blog Content -->

        </div>
    </div>
</main>
<!-- /Main -->

<!-- Footer -->
<%@include file="/WEB-INF/views/include/viewingsfooter.jsp"%>
<!-- /Footer -->
<script type="text/javascript">
	//返回
	function toback() {
		location.href = "memberActityPage.hx";
	}
	//添加活动
	function tosubmit() {
	   if($("#attachid").val() ==""){
	       alert("请上传活动封面图");
	       return;
	   }else if($("#tp").val() ==""){
	       alert("请选择活动类型");
	       return;
	   }else if($("#title").val() ==""){
	       alert("请填写活动主题");
	       return;
	   }else if($("#content").val() ==""){
	       alert("请填写活动内容");
	       return;
	   }else if($("#stime").val() ==""){
	       alert("请选择活动开始时间");
	       return;
	   }else if($("#etime").val() ==""){
	       alert("请选择活动结束时间");
	       return;
	   }else if($("#overtime").val() ==""){
	       alert("请选择报名截止时间");
	       return;
	   }else if($("#address").val() ==""){
	       alert("请填写活动地址");
	       return;
	   }else if($("#contact").val() ==""){
	       alert("请填写联系人");
	       return;
	   }else if($("#tel").val() ==""){
	       alert("请填写联系电话");
	       return;
	   }
		$.post('addMemberActivity.hx',util.serializeObject($('#activityform')),function(info){
			if(info.status){
				alert(info.info);
				setTimeout(function(){location.href = "memberActityPage.hx" ;},500);
			}else{
				alert(info.info);
			}
		});
	}
    //上传图片
	function doUpload(attachmod,attachfkid,attachid) {
	attachmod = $("#attachmod").val();
	var aid = $("#"+attachid).val();
	// 上传方法
	$.upload({
		// 上传地址
		url : 'uploadattachment2.hx',
		// 文件域名字
		fileName : 'file',
		// 其他表单数据
		params : {
			"attachmod" : attachmod,
			"attachfkid" : attachfkid
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
				var id = data.attr.id;
				if(aid != '') {
					aid = aid +","+data.attr.id;
					$("#attachid").val(aid);
				}else {
					$("#attachid").val(data.attr.id);
				}
				console.log(data.attr.id);
				$.post("queryAttrPath.hx",{"attrId":data.attr.id},function(data){
					if(data.status){//data.attr.attachPath
					    $("#spanpic").append("<img src='"+data.attr.attachPath+"' width='70px' height='65px' style='margin-right:5px;' id='"+id+"' onclick='delImg(\""+id+"\")'>");
						//模块参数+1
						$("#attachmod").val(String(parseInt(attachmod)+1));
					}else{
						alert(data.info);
					}
				});
			} else {
				alert(data.info);
			}
		}
	});
	}
	//删除图片
    function delImg(id){
	  Showbo.Msg.confirm('您确定删除这张图片吗？',function(flag){
	     if(flag=='yes'){
		   	  $.post('delActivityImg.hx',{"hxuuid":id},function(info){
					if(info.status){
						$("#"+id).remove();
						var attachid = "";
						var ids = $("#attachid").val().split(",");
					    for(var i = 0; i < ids.length; i++){
						       if(ids[i] != id){
						          if(attachid == ""){
						           attachid = ids[i];
						          }else {
						           attachid += ","+ids[i];
						          }
						       }
						 }
					    $("#attachid").val(attachid);
					}else{
						alert(info.info);
					}
				});
	     }
	   });
    }
</script>			
</body>
</html>
