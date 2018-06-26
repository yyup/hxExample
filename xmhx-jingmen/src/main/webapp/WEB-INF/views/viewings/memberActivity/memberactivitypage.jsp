<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8"/>
	<title>会员中心-活动</title>
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
                        <li><a href="memberJoinActityPage.hx">活动-我的参与</a></li> 
                    </ul>
                </div>
                <!-- /Widget: Categories -->


            </div>
            <!-- /Blog Sidebar -->
            <!-- Blog Content -->
            <div class="col-md-9">
				<div class="main_right">
              <div class="main_right_title">
                  <h1>我的发布</h1>
              </div>
              
              <div class="top_bor">
               <div class="f_left">
                   <form action="memberActityPage.hx" method="post" id="activityForm">
                   <div class="bot">
                     <span> 关键词:</span><input type="text" class="key" name="title" value="${activity.title }"/>
                   </div >
                   <div  class="bot">
                       <span>活动类型:</span>
                        <select class="sel" name="tp">
                             <c:if test="${activity.tp == null || activity.tp=='' }">
	                             <option value="">请选择</option>
	                             <option value="1">聚会</option>
	                             <option value="2">沙龙</option>
	                             <option value="3">培训</option>
	                             <option value="4">路演</option>
	                             <option value="5">开心农场</option>
                             </c:if>
                             <c:if test="${activity.tp == '1' }">
	                             <option value="">请选择</option>
	                             <option value="1" selected="selected">聚会</option>
	                             <option value="2">沙龙</option>
	                             <option value="3">培训</option>
	                             <option value="4">路演</option>
	                             <option value="5">开心农场</option>
                             </c:if>
                             <c:if test="${activity.tp == '2' }">
	                             <option value="">请选择</option>
	                             <option value="1">聚会</option>
	                             <option value="2" selected="selected">沙龙</option>
	                             <option value="3">培训</option>
	                             <option value="4">路演</option>
	                             <option value="5">开心农场</option>
                             </c:if>
                            <c:if test="${activity.tp == '3' }">
	                             <option value="">请选择</option>
	                             <option value="1">聚会</option>
	                             <option value="2">沙龙</option>
	                             <option value="3" selected="selected">培训</option>
	                             <option value="4">路演</option>
	                             <option value="5">开心农场</option>
                             </c:if>  
                             <c:if test="${activity.tp == '4' }">
	                             <option value="">请选择</option>
	                             <option value="1">聚会</option>
	                             <option value="2">沙龙</option>
	                             <option value="3">培训</option>
	                             <option value="4" selected="selected">路演</option>
	                             <option value="5">开心农场</option>
                             </c:if>
                             <c:if test="${activity.tp == '5' }">
	                             <option value="">请选择</option>
	                             <option value="1">聚会</option>
	                             <option value="2">沙龙</option>
	                             <option value="3">培训</option>
	                             <option value="4">路演</option>
	                             <option value="5" selected="selected">开心农场</option>
                             </c:if>                                                                                 
                         </select>
                          </div>
                    <!-- <div class="bot">
                        <span>活动时间:</span>
                          <input type="text" class="dmate"  name="start_time" id="date_created" value="${activity.start_time }" readonly="true" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'date_updated\')}'});"/>
                           - 
                          <input type="text" class="dmate" name="end_time" id="date_updated"  value="${activity.end_time }" readonly="true" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'date_created\')}'});"/>
                    </div>   -->
                    <div  class="bot">
                       <span>审核状态:</span>
                        <select class="sel" name="verifystatus">
                            <c:if test="${activity.verifystatus == null ||activity.verifystatus==''  }">
	                            <option value="">请选择</option>
	                            <option value="0">待审核</option>
	                            <option value="1">审核通过</option>
	                            <option value="2">审核不通过</option>
                            </c:if>
                            <c:if test="${activity.verifystatus == '0'  }">
	                            <option value="">请选择</option>
	                            <option value="0" selected="selected">待审核</option>
	                            <option value="1">审核通过</option>
	                            <option value="2">审核不通过</option>
                            </c:if>
                            <c:if test="${activity.verifystatus == '1'  }">
	                            <option value="">请选择</option>
	                            <option value="0">待审核</option>
	                            <option value="1" selected="selected">审核通过</option>
	                            <option value="2">审核不通过</option>
                            </c:if>
                            <c:if test="${activity.verifystatus == '2'  }">
	                            <option value="">请选择</option>
	                            <option value="0">待审核</option>
	                            <option value="1">审核通过</option>
	                            <option value="2" selected="selected">审核不通过</option>
                            </c:if>                                                                                    
                         </select>
                      </div>                       
                    <input type="submit" value="查询" class="search"/>
                    <input type="hidden" name="pageNumber" id="pageNumber" />
                    </form>  
                 </div> 
                 <div class="f_right"><a href="toMemberberActivity.hx">我要发布</a></div>                   
               </div> 
               <div  class="list1">
                  <ul>
                      <li>活动封面</li>
                      <li>活动主题</li>
                      <li>活动开始时间</li>
                      <li>活动结束时间</li>
                      <li>报名人数</li>
                      <li>审核状态</li>
                      <li>操作</li>
                  </ul>              
              </div>
              <c:if test="${!empty activityList}">
               <c:forEach items="${activityList}" var="activity">
              <div class="list2">
                  <ul>
                    <li><a href="" class="ac_img"><img src="${attachpath }${activity.firstpic }" alt="" width="80px" height="80px"/></a></li>
                    <c:if test="${fn:length(activity.title) < 6 }">
                    <li>${activity.title }</li>
                    </c:if>
                    <c:if test="${fn:length(activity.title) > 6 }">
                    <li>${fn:substring(activity.title,0,6) }...</li>
                    </c:if>
                    <li>${activity.stime }</li>
                    <li>${activity.etime}</li>
                    <li>${activity.enternum}人</li>
                    <c:if test="${activity.verifystatus=='0' }">
                    <li class="yellow">待审核</li>
                    </c:if>     
                    <c:if test="${activity.verifystatus=='1' }">
                    <li class="blue">审核通过</li>
                    </c:if> 
                    <c:if test="${activity.verifystatus=='2' }">
                    <li class="red">审核不通过</li>
                    </c:if>     
                    <li>  
                        <c:choose>
                             <c:when test="${activity.verifystatus=='0' }">
                                 <a href="queryMemberActivity.do?activityid=${activity.hxuuid }" target="_blank" >详情</a> 
                             </c:when>
                             <c:otherwise>
                                 <a href="queryMemberActivity.do?activityid=${activity.hxuuid }" target="_blank" style="display: block;"	>详情</a> 
                             </c:otherwise>
                         </c:choose>
                         <c:if test="${activity.verifystatus=='1' }">
                         <a href="queryMemberActivityEnter.do?activityid=${activity.hxuuid }">查看报名</a>
                         <!-- <a href="queryMemberActivityComment.do?activityid=${activity.hxuuid }">查看评论</a> -->
                         </c:if> 
                         <c:if test="${activity.verifystatus=='2' }">
		                    <input  class="del" type="button" value="删除" onclick="del('${activity.hxuuid}')"/>
		                 </c:if>   
                     </li>
                  </ul>                           
              </div> 
               </c:forEach> 
               </c:if> 
              
              <div class="main-page">
              		<%@include file="/WEB-INF/views/include/list_pagintion.jsp"%> 
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
  //删除广告
  function del(activityid){
	  Showbo.Msg.confirm('您确定删除这条记录吗？',function(flag){
	     if(flag=='yes'){
		   	  $.post('delMemberActivity.do',{"activityid":activityid},function(info){
					if(info.status){
						alert(info.info);
						setTimeout(function(){location.href = "memberActityPage.do" ;},500);
					}else{
						alert(info.info);
					}
				});
	     }
	   });
  }
  //页面跳转
	function go_page(pageNumber){
	    $("#pageNumber").val(pageNumber);
	    $("#activityForm").submit();
	}
</script>		
</body>
</html>
