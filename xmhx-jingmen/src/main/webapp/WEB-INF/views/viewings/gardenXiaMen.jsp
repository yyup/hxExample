<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>厦门园区</title>
	<%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="container " style="margin-bottom: 30px;">
	<div class="park_title">
		<i class="loaction-icon"></i>
		<a href="index.hx">首页</a>
		<span>&gt;</span>
		<a href="gardenDesc.hx">园区介绍</a>
		<span>&gt;</span>
		<span class="">厦门园区</span>
	</div>
	<div class="row zy_top">
		<div class="col-md-6">
			<img src="${ctx }/resource/kechuang/img/xmfm.jpg" />
		</div>
		<div class="col-md-6">
			<p>厦门云创智谷</p>
			<div>
				<span >园区位置：</span>
				福建省厦门市湖里区高殿路2-12号
			</div>
			<div>
				<span>园区标签：</span>
				创业创新产业生态圈
			</div>
			<a href="" class="btn-color-green">进入园区</a>
		</div>		
	</div>
	<div class="title-space">
        <span>园区简介</span><i></i>
    </div>
    <p>
    	云创智谷位于湖里区高殿路2-12号，紧邻杏林大桥、成功大道，位于厦门自贸区核心区域，毗邻象屿保税物流区及航空港连接区。项目总用地面积4.7万平方米，改造后总面积102612平方米，总投资1.2亿，是区域内最大的智慧园区。项目定位为移动互联、创业创新及打造众创空间全体系支持平台，着力于服务两岸80、90后创业新群体，打造特色的创业创新产业生态圈，为 “创客”提供低成本、全服务、开放式的办公环境，助力企业腾飞发展。
    目前,园区已签约并进场装修客户200多家，主要有：福建省正禾农业、上糖网、唯捷城市配送、云创艾维传媒、绅沅跨境电子商务、厦门基科生物科技有限公司等, 引入台籍人士53位、入驻台商企业及创业团队18家，如朋茂食品（厦门）有限公司、厦门台吉品商贸有限公司、晏精生物科技有限公司、厦门元器健康科技有限公司等台资企业，覆盖食品、保健品、农产品、生物科技、健康照护、教育培训等产业。

    </p>
    <img src="${ctx }/resource/kechuang/img/snxgt.jpg" width="70%"/>
    
    <div class="title-space">
        <span>地理位置</span><i></i>
    </div>
    <img src="${ctx }/resource/kechuang/img/wzt.png" width="60%" />
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
