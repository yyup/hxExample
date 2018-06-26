<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增新闻</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%@ include file="/WEB-INF/views/include/source.jsp" %>
		<script type="text/javascript" src="${ctx }/resource/attachnews.js"></script>
		<script type="text/javascript" charset="utf-8" src="${ctx }/resource/kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${ctx }/resource/kindeditor/lang/zh_CN.js"></script>
	</head>
	<body>
		<div class="box">
  			<form action="addnews.hx" name="newsfrm" id="newsfrm" method="post">
				 <dl id="dl">
	      			<dt class="f14 b">新增</dt>
	      			<dd>
	      				<span class="title">标题：</span>
	      				<input class="reg_input" name="newsTitle" id="newsTitle" value="" style="width: 240px" />
	      				<span style="color:red">*</span>
	      				<span id="newsTitleTip" class="onshow"></span>
	        		</dd>
	        		<dd>
	      				<span class="title">副标题：</span>
	      				<textarea class="reg_input" name="newsSubtitle" id="newsSubtitle" 
								style="width:350px; height:100px;margin-left:0px;"></textarea>
						<span style="color:red">*</span>
						<span id="newsSubtitleTip" class="onshow"></span>
	        		</dd>
	      			<dd>
	      				<span class="title">标题图：</span>
	      				<a id="uploadImage" class="easyui-linkbutton" href="javascript:doUpload();">图片上传</a>
	      				<span style="color:red">*</span>
	      				<a id="showImage" class="easyui-linkbutton" href="javascript:doQueryattach();">预览图片</a>
	      				<span>建议上传大于285px*215px</span>
	     			</dd>	        		
	        		<dd>
	      				<span class="title">内容：</span>
	      				<div style="margin-left: 150px;">
	        			<textarea class="reg_input" name="newsContent" id="newsContent" 
								style="width: 350px; height: 290px;"></textarea>
	        		    <span id="msgTip" class="onshow"></span>
	        		    </div>
	        		</dd>
	        	</dl>
	          <div class="f_reg_but">
	                <input type="button" value="保存" class="l_button" onclick="onsavenews();"/>
	      			<input type="button" value="返回" class="b_button" onclick="toback();"/>
	     		</div>    
	     		<input type="hidden" id="attachid" name="attachid"/> 
	    	</form>
	    	<!-- AttachForm用于操作附件的公共JS文件 -->
	    	<form id="AttachForm" name="AttachForm" action="" method="post"></form>
		</div>
		<script type="text/javascript">
		$(function() {
			$("#showImage").linkbutton({disabled:true});
			$.formValidator.initConfig({formID:"loginFrm"});
			$("#newsTitle").formValidator({onShow:"请输入1-20长度标题",onFocus:"请输入1-20长度标题",onCorrect:"请输入标题"}).inputValidator({min:1,max:40,onError:"请输入1-20长度字符"});
			$("#newsSubtitle").formValidator({onShow:"请输入1-100长度副标题",onFocus:"请输入1-100长度副标题",onCorrect:"请输入副标题"}).inputValidator({min:1,max:200,onError:"请输入1-100长度字符"});
			addEditor("newsContent","newsfrm","<%=request.getContextPath()%>");
		});
		function doUpload() {
			uploadImg('2', '','attachid');
		}
		function doQueryattach() {
			showattach(''+$("#attachid").val());
		}
		function onsavenews() {
			kindEditor.sync();
			var _title = $("#newsTitle").val();
			if(!notEmpty(_title)){
				displayMsg("标题不允许为空", false);
				return;
			}
			var _subtitle=$("#newsSubtitle").val();
			if(!notEmpty(_subtitle)){
				displayMsg("副标题不允许为空", false);
				return;
			}
			var _attachid = $("#attachid").val();
			if(!notEmpty(_attachid)){
				displayMsg("标题图不允许为空", false);
				return;
			}
			var _content = $("#newsContent").val();
			if(!notEmpty(_content)){
				displayMsg("内容不允许为空", false);
				return;
			}
			$('#newsfrm').form('submit', {
				success:function(data){
					var _data = eval("(" + data + ")");
					if (_data.status) {
						updatelistpage("新闻管理","news/list.hx","newsDatagrid");
						displayMsg(_data.info, true);
						setTimeout(function(){toback();},1500);
					} else {
						displayMsg(_data.info, false);
					}
				}
			});
		}
		function toback(){
			console.log(window.parent.document.getElementById("closecurrent"));
		    window.parent.document.getElementById("closecurrent").click();
		}
		
		//更新相关List页面隶属于iframe的src的状态
		function updatelistpage(title,src_url,datagridId){
			if (window.parent.$("#mainTab").tabs('exists',title)){
				var ifrms = window.parent.document.getElementsByTagName('iframe');
				var k = -1;
				for (var i = 0; i < ifrms.length; i++ ){
					if ( ifrms[i].src.indexOf(src_url) != -1 ){
						k = i;
						break;
					}
				}
				if ( k != -1 ){
					if (typeof(ifrms[k].contentWindow.$('#'+datagridId)) != "undefined"){
						ifrms[k].contentWindow.$('#'+datagridId).datagrid('reload');
					}
				}
			}
		}
		</script>	
	</body>
</html>