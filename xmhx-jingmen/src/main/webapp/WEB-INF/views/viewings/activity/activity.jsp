<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8"/>
	<title>活动</title>
	<link href="${ctx }/resource/kechuang/css/activity/theme.css" rel="stylesheet">
	<!-- <script src="${ctx }/resource/kechuang/js/activity/owl.carousel.min.js"></script>
	<script src="${ctx }/resource/kechuang/js/activity/jquery.magnific-popup.min.js"></script>
	<script src="${ctx }/resource/kechuang/js/activity/jquery.stellar.min.js"></script>
	<script src="${ctx }/resource/kechuang/js/activity/wow.min.js"></script>
	<script src="${ctx }/resource/kechuang/js/activity/general.js"></script> -->
	<%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>

<!-- 顶部导航 -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>

<!-- Main -->
<main class="main-container">

    <section>
        <div class="container" style="margin-top: 20px;">
        	<!-- Nav tabs -->
			<ul class="nav nav-tabs activity" role="tablist">
			  <li role="presentation" class="active">
			  	<a href="#home" role="tab" data-toggle="tab">
			  		不限
			  		<input value="" type="hidden" id="bx">
			  	</a>
			  </li>
			  <li role="presentation">
			  	<a href="#party" role="tab" data-toggle="tab">
			  		聚会
			  		<input value="1" type="hidden" id="jh">
			  	</a>
			  </li>
			  <li role="presentation">
			  	<a href="#salo" role="tab" data-toggle="tab">
			  		沙龙
			  		<input value="2" type="hidden" id="sl">
			  	</a>
			  </li>
			  <li role="presentation">
			  	<a href="#tarin" role="tab" data-toggle="tab">
			  		培训
			  		<input value="3" type="hidden" id="px">
			  	</a>
			  </li>
			  <li role="presentation">
			  	<a href="#luyan" role="tab" data-toggle="tab">
			  		路演
			  		<input value="4" type="hidden" id="ly">
			  	</a>
			  </li>
			  <li role="presentation">
			  	<a href="#others" role="tab" data-toggle="tab">
			  		其他
			  		<input value="5" type="hidden" id="qt">
			  	</a>
			  </li>
			</ul>
			
			<!-- Tab panes -->
			<div class="tab-content">
				<c:if test="${tp=='' }">
				  	<div role="tabpanel" class="tab-pane active" id="home">
				  		<div class="row activ_list">
				  			<c:forEach items="${activityList }" var="activity">
			                    <div class="col-md-6">
			                    	<div class="col-sm-6">
			                           <img src="images/project-01.jpg" width="256" height="185">
			                        </div>
			                        <div class="col-sm-6">
			                        	<h4>${activity.title }</h4>
			                        	<p><span class="glyphicon glyphicon-time"></span>${activity.date_created }</p>
			                        	<p><span class="glyphicon glyphicon-home"></span>${activity.address }</p>
			                        	<p><span class="glyphicon glyphicon-user"></span>已有${activity.enternum }人报名</p>
			                        	<a href="活动详情.html" class="sign_up">我要报名</a>
			                        </div>
			                    </div>
			                 </c:forEach>
			            </div>
			      </div>
			    </c:if>
				<c:if test="${tp=='1' }">
				  <div role="tabpanel" class="tab-pane" id="party">
			            <div class="row activ_list">	
			                    <div class="col-md-6">
			                    	<div class="col-sm-6">
			                           <img src="images/project-03.jpg" width="256" height="185">
			                        </div>
			                        <div class="col-sm-6">
			                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
			                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
			                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
			                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
			                        	<a href="活动详情.html" class="sign_up">我要报名</a>
			                        </div>
			                    </div>
			                     <div class="col-md-6">
			                    	<div class="col-sm-6">
			                           <img src="images/project-04.jpg" width="256" height="185">
			                        </div>
			                        <div class="col-sm-6">
			                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
			                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
			                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
			                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
			                        	<a href="活动详情.html" class="detail">查看详情</a>
			                        </div>
			                    </div>
			            </div>		  
			            <div class="row activ_list">	
			                    <div class="col-md-6">
			                    	<div class="col-sm-6">
			                           <img src="images/project-05.jpg" width="256" height="185">
			                        </div>
			                        <div class="col-sm-6">
			                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
			                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
			                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
			                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
			                        	<a href="活动详情.html" class="detail">查看详情</a>
			                        </div>
			                    </div>
			                     <div class="col-md-6">
			                    	<div class="col-sm-6">
			                           <img src="images/project-06.jpg" width="256" height="185">
			                        </div>
			                        <div class="col-sm-6">
			                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
			                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
			                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
			                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
			                        	<a href="活动详情.html" class="detail">查看详情</a>
			                        </div>
			                    </div>
			            </div>		  
				  </div>
			</c:if>
			<c:if test="${tp=='2' }">
			  <div role="tabpanel" class="tab-pane" id="salo">
			  	<div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-01.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-02.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		            </div>
		            <div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-03.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-04.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="detail">查看详情</a>
		                        </div>
		                    </div>
		            </div>		  
		            <div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-05.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="detail">查看详情</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-06.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="detail">查看详情</a>
		                        </div>
		                    </div>
		            </div>		  
			  </div>
			</c:if>
			<c:if test="${tp=='3' }">
			  <div role="tabpanel" class="tab-pane" id="tarin">
			  	<div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-01.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-02.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		            </div>
		           
		            <div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-05.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="detail">查看详情</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-06.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="detail">查看详情</a>
		                        </div>
		                    </div>
		            </div>		  
			  </div>
			</c:if>
			  <div role="tabpanel" class="tab-pane" id="luyan">
			  	<div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-01.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-02.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		            </div>
		            
			  </div>
			  <div role="tabpanel" class="tab-pane" id="others">
			  	<div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-01.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-02.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		            </div>
		            <div class="row activ_list">	
		                    <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-03.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="sign_up">我要报名</a>
		                        </div>
		                    </div>
		                     <div class="col-md-6">
		                    	<div class="col-sm-6">
		                           <img src="images/project-04.jpg" width="256" height="185">
		                        </div>
		                        <div class="col-sm-6">
		                        	<h4>企业家大讲堂创业辅导专题培训活动</h4>
		                        	<p><span class="glyphicon glyphicon-time"></span>2016-11-29 13:30</p>
		                        	<p><span class="glyphicon glyphicon-home"></span>斜塘老街第五期54幢（坐忘书房）</p>
		                        	<p><span class="glyphicon glyphicon-user"></span>已有24人报名</p>
		                        	<a href="活动详情.html" class="detail">查看详情</a>
		                        </div>
		                    </div>
		            </div>		  
		            
			  </div>
			</div>
            

                <!-- Pagination -->
                <div class="col-md-12">
                    <ul class="pagination">
                        <li><span>Page 1 of 5</span></li>
                        <li><a href="#">&laquo;</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">Next</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>
                <!-- /Pagination -->
 </div>
            </div>
            <!-- /Project -->

        </div>
    </section>

   

</main>
<!-- /Main -->
<!-- /Main -->

<!-- Footer -->
<%@include file="/WEB-INF/views/include/viewingsfooter.jsp"%>
<!-- /Footer -->
<script type="text/javascript">
	//获取标签页切换事件
	$(function(){
	    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	        // 获取已激活的标签页的名称
	        var activeTab = $(e.target).find("input").val();
	        $.post("","",function(){
	        	
	        });
	        console.log(activeTab);
	        // 获取前一个激活的标签页的名称
	        //var previousTab = $(e.relatedTarget).text();
	        //$(".active-tab span").html(activeTab);
	        //$(".previous-tab span").html(previousTab);
	    });
	});
</script>			
</body>
</html>
