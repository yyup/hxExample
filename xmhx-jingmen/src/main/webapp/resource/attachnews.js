// 上传附件
function uploadattach(attachmod, attachfkid, name) {
	uploadFile('uploadattachment.hx', attachmod, attachfkid, name);
}
//上传图片
function uploadImg(attachmod, attachfkid, name) {
	uploadFile('uploadattachment.hx', attachmod, attachfkid, name);
}
//更新图片
function updateImg(attachmod, attachfkid, name) {
	uploadFile('updateAttachment.hx', attachmod, attachfkid, name);
}

function uploadFile(action_url, attachmod, attachfkid, name) {
	// 上传方法
	$.upload({
		// 上传地址
		url : action_url,
		// 文件域名字
		fileName : 'file',
		// 其他表单数据
		params : {
			"attachmod" : attachmod,
			"attachfkid" : attachfkid
		},
		// 上传完成后, 返回json, text
		dataType : 'json',
		// 上传之前回调,return true表示可继续上传
		onSend : function() {
			return true;
		},
		// 上传之后回调
		onComplate : function(data) {
			if (data.status) {
				//附件列表datagrid
				if(typeof($("#attachdatagrid"))!='undefined'){
					$("#attachdatagrid").datagrid('reload');
				}
				//附件id
				$("#"+name).val(data.attr.id);
				//附件预览按钮解除禁用
				if(typeof($("#showImage"))!='undefined'){
					$("#showImage").linkbutton({disabled:false});
				}
				displayMsg(data.info, true);
			} else {
				displayMsg(data.info, false);
			}
		}
	});
}

// 操作附件
function operateattach(val, row) {
	var _html = '';
	if (row.attachtype == '.png' || row.attachtype == '.PNG'
			|| row.attachtype == '.jpg' || row.attachtype == '.JPG'
			|| row.attachtype == '.bmp' || row.attachtype == '.BMP'
			|| row.attachtype == '.txt') {
		_html += '<a class="easyui-linkbutton l-btn l-btn-plain" iconcls="icon-search" plain="true" href=javascript:showattach("' 
				+ row.id 
				+ '");><span class="l-btn-left"><span class="l-btn-text icon-search l-btn-icon-left">查看</span></span></a>';
	}
	_html += '<a class="easyui-linkbutton l-btn l-btn-plain" iconcls="icon-tip" plain="true" href=javascript:downloadattach("'
			+ row.id
			+ '");><span class="l-btn-left"><span class="l-btn-text icon-tip l-btn-icon-left">下载</span></span></a>'
			+ '<a class="easyui-linkbutton l-btn l-btn-plain" iconcls="icon-recover" plain="true" href=javascript:delattach("'
			+ row.id
			+ '");><span class="l-btn-left"><span class="l-btn-text icon-recover l-btn-icon-left">删除</span></span></a>';
	return _html;
}
// 显示附件
function showattach(id) {
	//chrome（及firefox、opera）升级后不再支持showModalDialog
	//window.showModalDialog('showattachmentById.hx?id='+id, 'show', 'dialogWidth:800px;dialogHeight:450px;center:yes;help:no;resizable:no;status:yes;scroll:yes');
	var iWidth = 720;
	var iHeight = 500;
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	window.open("showattachmentById.hx?id="+id, "预览窗口",'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no'); 
}
// 下载附件
function downloadattach(id) {
	AttachForm.action='postDownloadattach.hx?id='+id;
	AttachForm.method='post';
	AttachForm.submit();
}
// 删除附件
function delattach(id) {
	$.messager.confirm('确认', '您确认要删除吗？', function(r) {
		if (r) {
			$.post("delattach.hx", {"id": id}, function(data) {
				if (data.status) {
					displayMsg(data.info, true);
					$("#attachdatagrid").datagrid('reload');
				} else {
					displayMsg(data.info, false);
				}
			});
		}
	});
}
// 查询附件列表
function queryattach(fk) {
	$("#attachdatagrid").datagrid('load', {
		url : "queryattachByfkid.hx",
		attachfkid : fk
	});
}