<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>入驻企业</title>
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
			<li  class="current"><a href="containCompanys.hx">进驻企业</a></li>
			<li><a href="contactus.hx">联系我们</a></li>
		</ul>
	</div>
</div>
<div class="container">
	<input type="hidden" id="anchorpoint" name="anchorpoint" value="${anchorpoint }">
	<div class="row" style="margin-top: 30px;">
		<div class="col-md-8 list1">
			<div class="border-e" id="huanxin">
				<a href="http://www.nennn.com/" target="_blank"><img
					src="${ctx }/resource/kechuang/img/1web_40.jpg" /></a>
				<p>四川新环科技有限公司：四川新环科技有限公司（新环科技）是一家专业从事环境在线监测设备开发和销售、环境污染治理设备设施开发、环保教学及实验设备开发的新型科技企业，公司致力于环保事业的探索与发展，以包容之心广纳贤才，牢记绿色使命、肩负社会责任，用科技的力量为用户创造自然、健康的新环境。
					新环科技技术团队人员来自环境监测、环境工程、环保设备等多个领域，对食品、化工、医疗、生活等多个领域的废水废气监测、治理、设备维护有着丰富的设计施工及运行维护经验。团队能独立承担焦油废气回收、脱硫脱硝除尘、工业中水回用、医院废水、食品废水、生活污水等环境工程项目的设计、施工和运行维护，为客户提供一站式的治污解决方案。</p>
			</div>
			<div class="border-e" id="dongtaihe">
				<a href="http://www.sztontex.com/" target="_blank"><img
					src="${ctx }/resource/kechuang/img/1web_39.jpg" /></a>
				<p>深圳市东泰合科教有限公司：深圳市东泰合科教有限公司是一家集产品研发、生产、销售于一体的高科技企业。
					公司主要战略是电子教育设备和教育信息产品研发、生产和销售，将电磁感应定位技术应用到教育产品领域。公司拥有发明专利的产品---交互式电磁触控投影幕，是全球独家研发的新一代高科技触控产品，产品广泛使用于中小学、高校、酒店、宾馆、培训机构等场所的互动教学，并与众多国内知名电教企业有良好的合作关系。
					公司在产品研发上始终投入大量的人力、物力、财力，特别是以清华大学博导杨华中教授为核心的研发团队，其研发水平在行业内一直处于领先地位。公司现有人员370人，其中高级研发工程师36人；到2014年12月，公司生产的大尺寸触控投影幕2万台，年产值已经突破8千万，产品享誉国内外。</p>
			</div>
			<div class="border-e" id="bochenkuaiji">
				<a href="http://www.zybckj.com" target="_blank"><img
					src="${ctx }/resource/kechuang/img/1web_38.jpg" /></a>
				<p>资阳市博诚会计代理记账有限公司：资阳市博诚会计代理记账有限公司是一家经资阳市雁江区财政局批准，并在资阳市工商行政管理局登记注册成立的专业记账代理公司、具有公司注册与管理的代理机构，我们凭借着在资阳市建立的广泛业务关系网和丰富的专业经验，为各层面、各行业的客户提供优质而高效的服务。更可为客户解决公司注册时面临的各种疑难问题。我们一直恪守诚信服务的宗旨，深得客户的信赖及推崇。凭借着三十多年会计师的工作经验，至今，已成功帮助许多内资和外资企业完成注册登记、代理记账。</p>
			</div>
			<div class="border-e" id="zhongnonghu">
				<a href="http://www.chinaznh.com" target="_blank"><img
					src="${ctx }/resource/kechuang/img/1web_41.jpg" /></a>
				<p>众农汇成立于2015年，是四川众易汇科技有限公司旗下一家独立运营的中国农业产业链全综合的服务平台。众农汇秉承广阔农村大有作为的建站理念，专注农业、农村、农民发展，积极响应国务院提倡的“互联网+”模式，专注于打造“互联网+农业”全综合服务平台，构建专业农业大数据，服务中国三农，造福亿万农民。
					我们将通过建设地理标志农产品运营体系，通过与地方政府和农业大户、专业合作社，把区域性的地理标志农产品通过一系列的包装、运营，进行网络推广运作，增加农产品的品牌化、知名度，加大销售区域和销售数量。通过互联网、大数据以及线上线下的撮合服务，帮助农民实现农产品产销对接，缓解农产品买卖难和卖价低等传统难题。</p>
			</div>
			<div class="border-e" id="xinsanhe">
				<a href="http://www.sanhefoods.cn/" target="_blank"><img
					src="${ctx }/resource/kechuang/img/xshsp.png" /></a>
				<p>厦门悯农优谷食品有限公司负责新三和（烟台）食品有限责任公司产品的销售营销，为新三和（烟台）食品有限责任公司的全资子公司。
					新三和（烟台）食品有限责任公司由澳大利亚彩虹投资有限公司独资创办，早在上世纪九十年代，当冻干食品还是一个鲜为人知的概念时，新三和研发团队和管理核心人员已经开始致力于在中国发展冻干食品加工技术的研究与突破。1992年正式坐落于美丽的仙境——山东省蓬莱市，致力于真空冷冻干燥食品（简称“冻干食品”,英文简写FD）的研发、生产和销售业务。
				</p>
			</div>
			<div class="border-e" id="xinantai">
				<a href="http://www.santel.net.cn" target="_blank"><img
					src="${ctx }/resource/kechuang/img/xinan.png" /></a>
				<p>厦门兴安泰科技有限公司（原厦门兴安泰机电设备有限公司）
					成立于2005年，主要面向制造行业提供产品快速成型系统、逆向工程、模具自动化控制系统、微型精密多色注塑系统、激光雕刻切割系统等专业集成解决方案。</p>
			</div>
		</div>
		<div class="col-md-4 list1">
			<div class="border-e">
				<p>福建银路通智能技术有限公司</p>
				<p>爱定制（厦门）贸易有限公司</p>
				<p>厦门建为生物科技有限公司</p>
				<p>厦门雨田乐贸易有限公司</p>
				<p>福建盟派特信息技术服务有限公司</p>
				<p>厦门伊欧特商贸有限公司</p>
				<p>厦门悯农优谷食品有限公司</p>
				<p>厦门中康达医疗器械有限公司</p>
				<p>厦门天卫科技有限公司</p>
				<p>厦门市小準飞图信息技术有限公司</p>
				<p>厦门太初茶叶有限公司</p>
				<p>厦门合净美生物科技有限公司</p>
			</div>
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
        
        /* 返回顶部 */
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
    	
    	/* 锚定 */
    	anchorpoint();
    });
    
    function goto2(selector){
    	$.scrollTo ( selector , 1000);	
    }
    
    // 锚定
    function anchorpoint() {
    	var _anchorpoint = $("#anchorpoint").val();
    	document.getElementById(_anchorpoint).scrollIntoView();
    }
</script>
</body>
</html>
