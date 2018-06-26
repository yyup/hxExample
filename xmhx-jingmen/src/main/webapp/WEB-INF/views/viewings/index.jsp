<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>云创智谷（北京）科技有限公司</title>
    <%@include file="/WEB-INF/views/include/viewings.jsp"%>
</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/include/viewingsheader.jsp"%>
<!-- banner -->
<div id="ad-carousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#ad-carousel" data-slide-to="0" class="active"></li>
        <li data-target="#ad-carousel" data-slide-to="1"></li>
        <li data-target="#ad-carousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="item active">
            <img src="${ctx }/resource/kechuang/img/banner/banner1.jpg" alt="1 slide">
        </div>
        <div class="item">
            <img src="${ctx }/resource/kechuang/img/banner/banner2.jpg" alt="2 slide">
        </div>
        <div class="item">
            <img src="${ctx }/resource/kechuang/img/banner/banner3.jpg" alt="3 slide">
        </div>
    </div>
    <a class="left carousel-control" href="#ad-carousel" data-slide="prev"><span
            class="glyphicon glyphicon-chevron-left"></span></a>
    <a class="right carousel-control" href="#ad-carousel" data-slide="next"><span
            class="glyphicon glyphicon-chevron-right"></span></a>
</div>
<!-- News -->
<div class="container summary">
    <div class="row news" id="summary-container">
    	<div class="tt">
		    <span class="fl"><i></i>园区动态</span>
		    <a href="news.hx" class="fr atext">更多</a>
		</div>
		<div class="new-box clearfix">
			<c:forEach var="news" items="${newslist }"  begin="0" end="1" varStatus="status">
				<div class="col-md-6">
					<div class="col-sm-4 news_img"><img alt="" src="${imglist[status.index] }" width="150%"/></div>
					<div class="col-sm-8">
						<a href="newsDetail.hx?id=${news.hxuuid }">
							<h3>${news.newsTitle }</h3>
							<p>${fn:substring(news.newsSubtitle, 1, 50)}</p>
							<span>${news.date_created }</span>
						</a>
					</div>       	
	            </div> 
            </c:forEach>       
		</div>
		<div class="list_news">
        	<div class="col-md-6 list2">
        		<ul>
        			<c:forEach var="news" items="${newslist }"  begin="2" end="6" step="2">
	        			<li><a href="newsDetail.hx?id=${news.hxuuid }">${news.newsTitle }</a><span>${news.date_created }</span></li>
	        		</c:forEach>
        		</ul>
        	</div>
        	<div class="col-md-6 list2" >
        		<ul>
        			<c:forEach var="news" items="${newslist }"  begin="3" end="7" step="2">
	        			<li><a href="newsDetail.hx?id=${news.hxuuid }">${news.newsTitle }</a><span>${news.date_created }</span></li>
	        		</c:forEach>
        		</ul>
        	</div>
	    </div>        
	</div>        
</div>
<div class="services">
    <div class="container ">
    	<div class="services_title">
    		<h3>连锁运营<span>/Chain operation</span></h3>
    		<p>O2O社区整体在线智慧园区资源与服务平台，打造线上线下相结合的创新园区</p>
    	</div>
    	<div class="row">
    		<div class="col-md-4 s1">
    			<a href="chainBusiness.hx?anchorpoint=sccsm">
    			   <div class="img"><img src="${ctx }/resource/kechuang/img/s1.jpg"/>
	    			   	<div class="sexangle"> 
	                    </div>
    			   </div>
    			   <p>市场传送门</p>
    			</a>
    		</div>
    		<div class="col-md-4 s1">
    			<a href="chainBusiness.hx?anchorpoint=cxjh">
    			   <div class="img"><img src="${ctx }/resource/kechuang/img/s2.jpg"/>
	    			   	<div class="sexangle"> 
		                </div>
    			   </div>
    			   <p>创享计划</p>
    			</a>
    		</div>
    		<div class="col-md-4 s1">
    			<a href="chainBusiness.hx?anchorpoint=yjhkj">
    			   <div class="img"><img src="${ctx }/resource/kechuang/img/s5.jpg"/>
    			   	    <div class="sexangle"> 
		                </div>
    			   </div>
    			   <p>遇见黑科技</p>
    			</a>
    		</div>
    	</div>
    	<div class="row">
    		<div class="col-md-4 s1">
    			<a href="chainBusiness.hx?anchorpoint=ycjyk">
    			   <div class="img"><img src="${ctx }/resource/kechuang/img/s4.jpg"/>
    			   	    <div class="sexangle"> 
		                </div>
    			   </div>
    			   <p>云创家园卡</p>
    			</a>
    		</div>
    		<div class="col-md-4 s1">
    			<a href="chainBusiness.hx">
    			   <div class="img"><img src="${ctx }/resource/kechuang/img/s3.jpg"/>
    			   	   <div class="sexangle"> 
		                </div>
    			   </div>
    			   <p>云创空间</p>
    			</a>
    		</div>
    		<div class="col-md-4 s1">
    			<a >
    			   <div class="img"><img src="${ctx }/resource/kechuang/img/s6.jpg"/>    			      	
    			   </div>
    			   <p>敬请期待</p>
    			</a>
    		</div>
    	</div>
    </div>
</div>
<div class="data">
	<div class="container ">
		<div class="row data_list">
			<div class="col-md-2"><h3>6<span>个</span></h3><p>园区数</p></div>
			<div class="col-md-3"><h3>360<span>家</span></h3><p>企业数</p></div>
			<div class="col-md-3"><h3>7850<span>人</span></h3><p>员工数</p></div>
			<div class="col-md-2"><h3>35<span>亿</span></h3><p>产值</p></div>
			<div class="col-md-2"><h3>3.9<span>亿</span></h3><p>纳税额</p></div>
		</div>
	</div>
</div>
<div class="company">
	<div class="container">
		<div class="c_list_title">
			<h3>最新进驻企业<span>/New companies settled</span></h3>
			<a href="containCompanys.hx">已进驻50家 &gt;&gt;</a>
		</div>
		<div class="row" style="clear: both;">
		   <div class="col-md-2">
			   <a href="containCompanys.hx?anchorpoint=huanxin" alt="新环科技" title="新环科技"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_40.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="containCompanys.hx?anchorpoint=dongtaihe" alt="东泰合" title="东泰合"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_39.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="containCompanys.hx?anchorpoint=bochenkuaiji" alt="博诚会计" title="博诚会计"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_38.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="containCompanys.hx?anchorpoint=zhongnonghu" alt="众农汇" title="众农汇"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_41.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="containCompanys.hx?anchorpoint=xinsanhe" alt="新三和" title="新三和"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_36.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="containCompanys.hx?anchorpoint=xinantai" alt="兴安泰科技" title="兴安泰科技"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_37.jpg"/></div></a>
		   </div>			
		</div>
		<div class="row" style="clear: both;">
		   <div class="col-md-2">
			   <a href="" alt="弘信物流" title="弘信物流"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_21.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="" alt="弘信物流" title="爱基因"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_22.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="" alt="弘信物流" title="弘信绿色"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_23.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="" alt="弘信物流" title="弘信海运"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_24.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="" alt="弘信物流" title="厦门众创空间产业协会"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_25.jpg"/></div></a>
		   </div>
		   <div class="col-md-2">
			   <a href="" alt="弘信物流" title="弘信创业"><div class="company_div"><img src="${ctx }/resource/kechuang/img/1web_26.jpg"/></div></a>
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
    });
    function goto2(selector){
    	$.scrollTo ( selector , 1000);	
    }
</script>
</body>
</html>
