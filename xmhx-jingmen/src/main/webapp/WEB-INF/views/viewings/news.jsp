<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>新闻动态</title>
    <%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="news">
	<div class="container">
		<ul class="news-list_zx">
			<c:forEach var="news" items="${newslist }" varStatus="status">
				<li>
					<a class="news-pic col-md-4" href="newsDetail.hx?id=${news.hxuuid }">
						<img class="news_img_n" src="${news.attachFullpath }" alt="" width="300" height="160">
					</a>
					<div class="news-info col-md-8">
	                    <a href="newsDetail.hx?id=${news.hxuuid }" class="news-tit c-gray">${news.newsTitle }</a>
	                    <div class="tag">${news.newsSubtitle }</div>
	                    <span class="types mr-10">发布时间：${news.date_created }</span>
	                    <a href="newsDetail.hx?id=${news.hxuuid }" class="look-more">查看详情 &gt;</a>
	                </div>												
				</li>
			</c:forEach>
		</ul>
		
		<!-- Pagination  -->
        <div class="col-md-12">
            <%@include file="/WEB-INF/views/include/pagintionlist.jsp"%> 
        </div>
        <!-- /Pagination -->
	</div>
</div>
<%@include file="/WEB-INF/views/include/viewingsfooter.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#ad-carousel').carousel();
        $('#menu-nav .navbar-collapse a').click(function (e) {
            var href = $(this).attr('href');
            var tabId = $(this).attr('data-tab');
            if ('#' !== href) {
                e.preventDefault();
                $(document).scrollTop($(href).offset().top - 70);
                if (tabId) {
                    $('#feature-tab a[href=#' + tabId + ']').tab('show');
                }
            }
        });
        
        /*返回顶部*/
    	$('#roll_top').hide();
    	$(window).scroll(function () {
    		if ($(window).scrollTop() > 300) {
    			$('#roll_top').fadeIn(400);//当滑动栏向下滑动时，按钮渐现的时间
    		} else {
    			$('#roll_top').fadeOut(0);//当页面回到顶部第一屏时，按钮渐隐的时间
    		}
    	});
    	
    	$('#roll_top').click(function () {
    		$('html,body').animate({
    			scrollTop : '0px'
    		}, 300);//返回顶部所用的时间 返回顶部也可调用goto2()函数
    	});
    });
    
    function goto2(selector){
    	$.scrollTo ( selector , 1000);	
    }
    
    //页面跳转
    function go_page(pageNumber) {
    	location.href = "news.hx?page="+pageNumber;
    }
</script>
</body>
</html>
