<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>园区介绍</title>
    <%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="park">	
</div>
<div class="container park_con">
	<h1>园区+互联网</h1>
	<div class="row">
		<div class="col-md-4">
			<a href="gardenXiaMen.hx" class="a1">
				<div class="back_bg">
					厦门云创智谷
				</div>
			</a>
		</div>
		<div class="col-md-4">
			<a href="gardenZiYang.hx" class="a2">
				<div class="back_bg">
					资阳云创智谷
				</div>
			</a>
		</div>
		<div class="col-md-4">
			<a href="gardenJingMen.hx" class="a3">
				<div class="back_bg">
					荆门云创智谷
				</div>
			</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<a href="javascript:void(0);" class="a2">
				<div class="back_bg">
					南宁云创智谷
				</div>
			</a>
		</div>
		<div class="col-md-4">
			<a href="javascript:void(0);" class="a2">
				<div class="back_bg">
					大连云创智谷
				</div>
			</a>
		</div>
	</div>
</div>
<!-- footer -->
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
</script>
</body>
</html>
