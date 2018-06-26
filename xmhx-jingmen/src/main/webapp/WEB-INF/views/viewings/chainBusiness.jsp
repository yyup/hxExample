<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>连锁运营</title>
   	<%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- news -->
<div class="operating">
	<input type="hidden" id="anchorpoint" name="anchorpoint" value="${anchorpoint }">
</div>
<div class="park-bg gridding">
	<div class="container" id="sccsm">
		<div class="col-md-5 part_top">
			<h1>市场传送门</h1>
			<p>通过各地云创智谷的协同运营，推动全国各地有意向往外地开拓市场的企业实现快速低成本的扩张，最终实现信息流动及资源共享。</p>
		</div>
		<div class="col-md-7">
			<img src="${ctx }/resource/kechuang/img/sccsm.png"  style="margin-top: 50px;"/>
		</div>
	</div>
</div>
<div class="park-bg  gridding2">
	<div class="container" id="cxjh">		
		<div class="col-md-7">
			<img src="${ctx }/resource/kechuang/img/cx.jpg" />
		</div>
		<div class="col-md-5 part_top2" >
			<h1>创享计划</h1>
			<p>通过各地云创智谷的协同运营，提高各园区的空间利用率，让企业和个人在异地办公也能享受主场之便利。</p>
		</div>
	</div>
</div>
<div class="park-bg  gridding">
	<div class="container" id="yjhkj">
		<div class="col-md-5 part_top">
			<h1>遇见黑科技</h1>
			<p>以全国云创智谷为物理空间支持，让黑科技的产品在线下得到充分的展示及应用，让高科技产品触手可及，帮助创业者进行有效的市场推广。</p>
		</div>
		<div class="col-md-7">
			<img src="${ctx }/resource/kechuang/img/yjhkj.jpg"  style="margin-top: 50px;"/>
		</div>
	</div>
</div>
<div class="park-bg gridding2">
	<div class="container" id="ycjyk">		
		<div class="col-md-7">
			<img src="${ctx }/resource/kechuang/img/party.jpg" width="70%" style="margin-top: 30px;"/>
		</div>
		<div class="col-md-5 part_top2" >
			<h1>云创家园卡</h1>
			<p>吃喝玩乐，一卡通行百城智谷。</p>
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
