<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>资阳园区</title>
	<%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="container ">
	<div class="park_title">
		<i class="loaction-icon"></i>
		<a href="index.hx">首页</a>
		<span>&gt;</span>
		<a href="gardenDesc.hx">园区介绍</a>
		<span>&gt;</span>
		<span class="">资阳园区</span>
	</div>
	<div class="row zy_top">
		<div class="col-md-6">
			<img src="${ctx }/resource/kechuang/img/zyfm.jpg" />
		</div>
		<div class="col-md-6">
			<p>资阳云创智谷</p>
			<div>
				<span >园区位置：</span>
				四川省资阳经济开发区
			</div>
			<div>
				<span>园区标签：</span>
				资阳云创智谷双创基地
			</div>
			<a href="" class="btn-color-green">进入园区</a>
		</div>		
	</div>
	<div class="title-space">
        <span>园区简介</span><i></i>
    </div>
    <p>
    	由弘信创业工场（资阳）投资有限公司投资建设的“云创智谷”资阳双创基地项目是资阳市重点招商引资项目，也是资阳市领先的众创空间和创新创业平台。
“云创智谷”资阳双创基地项目设有 “创客金融”“创业咖啡”、“传送门计划”、“创享计划”、“遇见黑科技”等多个扶持计划，帮助创业团队解决基础硬件需求，生活配套服务需求，以及资金、市场、异地办公等多方面的问题。我们积极响应克强总理提出的“大众创业、万众创新”，至力于创业微环境的打造，从而引爆创业项目的内生动力，以此促进地方经济的发展与繁荣。
“云创智谷”内设有：创业咖啡、公共前台、公共会议室、洽谈室、书吧、健身区、游戏区等各类共享空间。提供包括：金融/人事/财务/税务/法务/工商/行政事务的支持代理、企业宣传、摄影工作室、美工/形像设计、活动策划、网站代管、程序开发、网络支持、物业等各项服务。配备包括：白领公寓、智能停车、员工食堂、园区班车、汽车租赁、洗衣房、银行、邮政、购物中心、健身瑜珈、开心农场、运动场馆、等便利生活设施。园区将为各类创新创业者提供一个宜居宜业、配套完善的良好创业环境。
    </p>
    <img src="${ctx }/resource/kechuang/img/zy3.jpg" />
    <img src="${ctx }/resource/kechuang/img/zy2.jpg" />
    <img src="${ctx }/resource/kechuang/img/zy1.jpg" />
    
    <div class="title-space">
        <span>地理位置</span><i></i>
    </div>
    <img src="${ctx }/resource/kechuang/img/zy4.jpg" width="60%" />
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
