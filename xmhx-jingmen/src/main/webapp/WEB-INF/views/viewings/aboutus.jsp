<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>关于我们</title>
    <%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="about_us">
	<div class="container about_us_nav">	
		<ul>
			<li class="current"><a href="aboutus.hx">公司简介</a></li>
			<li><a href="containCompanys.hx">进驻企业</a></li>
			<li><a href="contactus.hx">联系我们</a></li>
		</ul>
	</div>
</div>
<div class="container con">
	<b>关于科创</b><br />
    <p>云创智谷（北京）科技有限公司是弘信创业工场旗下国内独树一帜的创新创业平台公司，以“创业报国、科技强国”为使命，秉持平等、开放、服务、整合的精神，以云创业模式，打造云端资源平台，构建各地云创智谷基地——众创空间公共服务支持平台 ，通过创业服务、金融加速和资本运营三大支撑，向云创智谷内的众创空间、中小微企业提供战略、人才、管理、金融、市场、产业政策咨询等软硬资源，构建全方位的创业服务支持平台，为创业者提供全创业价值链整合平台运营服务，营造中国乃至世界最优质的云创业生态圈。</p>

    <b>关于产品</b><br />
    <p>◎基地孵化器：电商产业园、文创园、科技园、双创园等等；</p>
    <p>◎云创业服务：政策、教练、信息、传媒、财税、商学院、市场营销等等；</p>
    <p>◎创业金融：融资租赁、互联网金融等等；</p>
    <p>◎资本运作：战略投资、云创资本、云创业移动互联基金等等。</p>

	<b>关于服务</b><br />
	<p>◎云创业全体系“一站式”服务；</p>
	<p>◎园区服务；</p>
	<p>◎社群人文服务；</p>

	<b>关主要特色</b><br />
	<p>◎数据化：云上新生活（社群数据化、服务数据化)</p>
	<p>◎连锁化：品牌连锁运营（资源共享、服务共享）</p>
	<p>◎生态化：产业生态圈、创业服务生态圈</p>

	<b>发展历程</b><br />
	<p>◎15年历史沉淀：2016年以前，投资孵化125家各类实体企业，管理资产达150亿人民币。</p>
	<p>◎云创智谷生根发芽：2016年，公司已在北京、深圳、厦门、昆山、大连、南宁、资阳、荆门等市筹建、运营与管理10个云创智谷。</p>
	<p>◎云创智谷品牌连锁运营：2017年-2018年，全国布局50座城市，输出“云创智谷”品牌与云创业商业模式，打造以云创智谷、云上新生活为软硬件融合的载体，云创业服务、金融、资本为价值的商业生态。</p>
	<p>◎中国创业科技创业服务一流品牌：2019年，全面构建支持中小企业创业创新生态圈领导者品牌。</p>
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
