/**
	 * 
	 *附件操作
	 */
	function itfaceattachoperatenew(val, row) {
		var _html = '';
		if (row.attachtype == 'png' || row.attachtype == 'PNG'
				|| row.attachtype == 'jpg' || row.attachtype == 'JPG'
				|| row.attachtype == 'bmp' || row.attachtype == 'BMP'
				|| row.attachtype == 'txt') {
			_html += '<a class="easyui-linkbutton l-btn l-btn-plain" iconcls="icon-search" plain="true" href=javascript:itfaceshowattach("' 
					+ row.id 
					+ '");><span class="l-btn-left"><span class="l-btn-text icon-search l-btn-icon-left">查看</span></span></a>';
		}
		_html += '<a class="easyui-linkbutton l-btn l-btn-plain" iconcls="icon-tip" plain="true" href=javascript:itfacedownloadattach("'
				+ row.id
				+ '");><span class="l-btn-left"><span class="l-btn-text icon-tip l-btn-icon-left">下载</span></span></a>'
				+ '<a class="easyui-linkbutton l-btn l-btn-plain" iconcls="icon-recover" plain="true" href=javascript:itfacedelattach("'
				+ row.id
				+ '");><span class="l-btn-left"><span class="l-btn-text icon-recover l-btn-icon-left">删除</span></span></a>';
		return _html;
	}
	function itfaceshowattach(id) {
		window.showModalDialog('itfaceshowattachById.do?id='+id, 'viewattachpic', 'dialogWidth:800px;dialogHeight:450px;center:yes;help:no;resizable:no;status:yes;scroll:yes');
	}
	function itfacedownloadattach(id) {
		attachmentForm.action='itfacedownloadattach.do?id='+id;
		attachmentForm.method='post';
		attachmentForm.submit();
	}
	function itfacedelattach(id) {
		if(typeof($("#bugstatus").val()) != 'undefined'){
			var bugstatus = $("#bugstatus").val();
			if(bugstatus > 3){
				displayMsg('缺陷确认接受后，不允许删除', false);
				return;
			}
		}
		$.messager.confirm('确认', '您确认要删除吗？', function(r) {
			if (r) {
				$.post("itfacedelattach.do", {"id": id}, function(data) {
					if (data.status) {
						displayMsg(data.info, true);
						$("#datagrid").datagrid('reload');
					} else {
						displayMsg(data.info, false);
					}
				});
			}
		});
	}
	function itfacequeryAttachment(ids) {
		$("#datagrid").datagrid('load', {
			url : "itfaceloadattachment.do",
			id: ids
		});
	}