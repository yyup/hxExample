<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>新闻详情页</title>
    <%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="container">
	<div class="news-detail">
		<input type="hidden" id="id" name="id" value="${news.id }">
		<div class="news-title">${news.newsTitle }</div>
		<div class="news-tips">					
			<span class="ml-10">发布时间：${news.date_created }</span>
		</div>
		<div class="article article-18">
            <div id="viewContent" class="goods-depict">
               	<img alt="" src="${attachpath }">
               	<p>${news.newsContent }</p>
			</div>
			<div class="news-prev-next clearfix">
				<span class="news-prev txt-ellipsis" id="newsprevid"></span>
				<span class="news-next txt-ellipsis" id="newsnextid"></span>
			</div>
		</div>
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
    	
    	// 展示上一条，下一条记录
    	previousAndNext();
    });
    
    function goto2(selector){
    	$.scrollTo ( selector , 1000);	
    }
    
    function previousAndNext() {
    	var id = $("#id").val();
    	$.post("newsPreviousAndNext.hx", {"id": id}, function(data) {
    		if (data != null && data.status) {
    			if (data.attr.previousNews != null) {
    				var hxuuid = data.attr.previousNews.hxuuid;
    				var title = data.attr.previousNews.newsTitle;
    				var _html="<a class='news-prev txt-ellipsis' href='newsDetail.hx?id=" + hxuuid + "' title='"+ title +"'>上一篇："+ title +"</a>";
    				$("#newsprevid").html(_html);
    			}
    			
    			if (data.attr.nextNews != null) {
    				var hxuuid = data.attr.nextNews.hxuuid;
    				var title = data.attr.nextNews.newsTitle;
    				var _html="<a class='news-next txt-ellipsis' href='newsDetail.hx?id=" + hxuuid + "' title='"+ title +"'>下一篇："+ title +"</a>";
    				$("#newsnextid").html(_html);
    			}
    		}
    	});
    }
</script>
</body>
</html>
