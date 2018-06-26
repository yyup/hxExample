<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>联系我们</title>
   	<%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="about_us">
	<div class="container about_us_nav">	
		<ul>
			<li><a href="aboutus.hx">公司简介</a></li>
			<li><a href="containCompanys.hx">进驻企业</a></li>
			<li class="current"><a href="contactus.hx">联系我们</a></li>
		</ul>
	</div>
</div>
<div class="container contact_us">
	<div class="col-md-6 contact_p">
		<p>联系电话：0592-5670106</p>
		<p>联系地址：厦门市湖里区高殿路10号B栋602室</p>
		<p>联系人：谢小姐</p>
	</div>
	<div class="col-md-6">
		<img src="${ctx }/resource/kechuang/img/contact_us_bg.png" width="100%" />
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
</script>
</body>
</html>
