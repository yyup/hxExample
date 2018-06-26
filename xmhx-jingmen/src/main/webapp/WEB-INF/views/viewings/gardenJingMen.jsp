<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>荆门园区</title>
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
		<span class="">荆门园区</span>
	</div>
	<div class="row zy_top">
		<div class="col-md-6">
			<img src="${ctx }/resource/kechuang/img/jmfm.jpg" />
		</div>
		<div class="col-md-6">
			<p>荆门云创智谷</p>
			<div>
				<span >园区位置：</span>
				湖北省荆门市东宝区
			</div>
			<div>
				<span>园区标签：</span>
				产业聚集空间
			</div>
			<a href="" class="btn-color-green">进入园区</a>
		</div>		
	</div>
	<div class="title-space">
        <span>园区简介</span><i></i>
    </div>
    <p>
    	2016年8月荆门市政府与弘信创业工场合作共建荆门云创智谷移动互联科技产业园，项目地址位于湖北省荆门市东宝区。首期启动区包括东宝区泉口路21号万华城云创智谷及东宝区信息产业园云创智谷，运营面积16000平方米，2000平方米创业人才专享创业公寓，二期根据相关规划及需求，打造不少于10万平方米产业聚集空间。
    </p>
    <img src="${ctx }/resource/kechuang/img/jmkj1.jpg" width="80%"/>
    <p>
    	荆门云创智谷致力于为新兴产业可持续发展构建产业链和创业创新环境，积极打造以创业孵化、产业加速器、创业公寓等为支撑的服务载体链。为入园企业提供一体化服务体系，包括众创孵化、辅导培训、商务推广、基础保障与生活配套等公共服务平台。多层次金融体系服务于企业，融资租赁、互联网金融、创业投资、股权融资、上市辅导等服务系统建设。
    </p>
    <img src="${ctx }/resource/kechuang/img/jmkj2.jpg" width="80%"/>
    <p>
    	荆门云创智谷依托弘信创业工场“云创业模式”，以为中小企业提供创新发展服务为宗旨，为地域经济发展助力，云聚优势资源，创想美好未来。
    </p>
    
   
    <div class="title-space">
        <span>地理位置</span><i></i>
    </div>
    <img src="${ctx }/resource/kechuang/img/jmmap.jpg" width="60%"  style="margin-bottom: 30px;"/>
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
    		}, 300);//返回顶部所用的时间 返回顶部也可调用goto()函数
    	});
    });
    function goto2(selector){
    	$.scrollTo ( selector , 1000);	
    }
</script>
</body>
</html>
