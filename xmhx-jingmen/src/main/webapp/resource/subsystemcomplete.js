/*
 * 系统与子系统自动填充
 */		
// 系统事件
function systemEvent() {
	// 系统自动填充
	$.post("systemAutocompletedb.do", function(data) {
		systemAutocomplete(data);
	});
}
//子系统事件
function subsystemEvent() {
	var _sysenshortname = $("#sysenshortname").val();	
	if (notEmpty(_sysenshortname)) {
		$.post("subsystemAutocompletedb.do", {"sysenshortname" : _sysenshortname}, function(data) {
			subsystemAutocomplete(data);
		});
	} else {
		// 子系统自动填充
		$.post("subsystemAutocompletedb.do", function(data) {
			subsystemAutocomplete(data);
		});
	}
}
// 子系统ID事件
function subsystemidEvent(){
	// 子系统自动填充
	$.post("subsystemAutocompletedb.do", function(data) {
		subsystemAutocompletebyid(data);
	});
}
/*********************************************************************************************/

/**************************************************************************************/
//系统自动填充
function systemAutocomplete(data) {
	$("#systemname").flushCache();
	$('#systemname').autocomplete(data, {
        max: 12,    			//列表里的条目数
        minChars: 0,    		//自动完成激活之前填入的最小字符
        width: 300,     		//提示的宽度，溢出隐藏
        scrollHeight: 300,   	//提示的高度，溢出显示滚动条
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    	//自动填充
        formatItem: function(row, i, max) {
        	return row.syscnfullname;
        },
        formatMatch: function(row, i, max) {
            return row.sysenshortname;
        },
        formatResult: function(row) {
            return row.syscnfullname;
        }
    }).result(function(event, row, formatted) {
    	$("#sysenshortname").val(row.sysenshortname);
        $("#subsystemid").val();
        $("#subsystemenshortname").val();
        $("#subsystemcnfullname").val();
        $("#devgroup").val();
        $("#subsystemperson").val();
    });
}
// 子系统自动填充
function subsystemAutocomplete(data) {
	$("#subsystemview").flushCache();
	$('#subsystemview').autocomplete(data, {
        max: 12,    			//列表里的条目数
        minChars: 0,    		//自动完成激活之前填入的最小字符
        width: 300,     		//提示的宽度，溢出隐藏
        scrollHeight: 300,   	//提示的高度，溢出显示滚动条
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    	//自动填充
        formatItem: function(row, i, max) {
            return row.subsystemenshortname + '【' + row.subsystemcnfullname + '】';
        },
        formatMatch: function(row, i, max) {
            return row.subsystemenshortname;
        },
        formatResult: function(row) {
            return row.subsystemenshortname + '【' + row.subsystemcnfullname + '】';
        }
    }).result(function(event, row, formatted) {
    	$("#sysenshortname").val(row.sysenshortname);
        $("#systemname").val(row.systemname);
        $("#subsystemid").val(row.subsystemid);
        $("#subsystemenshortname").val(row.subsystemenshortname);
        $("#subsystemcnfullname").val(row.subsystemcnfullname);
        $("#devgroup").val(row.devgroup);
        $("#subsystemperson").val(row.subsystemperson);
        // SR\生产问题\测试问题\版本所用
        $("#subsystemname").val(row.subsystemenshortname + '【' + row.subsystemcnfullname + '】');
        $("#develepementname").val(row.devgroup);
        // 缺陷开发负责人
        $("#bugchargeperson").val('');
        $("#chargeperson").val('');
        // 测试缺陷开发处理人
        $("#bughander").val('');
        $("#devpersonnel").val('');
    });
}
// 子系统ID自动填充
function subsystemAutocompletebyid(data) {
	$("#subsystemid").flushCache();
	$('#subsystemid').autocomplete(data, {
        max: 12,    			//列表里的条目数
        minChars: 0,    		//自动完成激活之前填入的最小字符
        width: 300,     		//提示的宽度，溢出隐藏
        scrollHeight: 300,   	//提示的高度，溢出显示滚动条
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    	//自动填充
        formatItem: function(row, i, max) {
            return row.subsystemid+':'+row.subsystemenshortname + '【' + row.subsystemcnfullname + '】';
        },
        formatMatch: function(row, i, max) {
            return row.subsystemid;
        },
        formatResult: function(row) {
            return row.subsystemid;
        }
    }).result(function(event, row, formatted) {
    	$("#sysenshortname").val(row.sysenshortname);
        $("#systemname").val(row.systemname);
        $("#subsystemenshortname").val(row.subsystemenshortname);
        $("#subsystemcnfullname").val(row.subsystemcnfullname);
        $("#subsystemview").val(row.subsystemenshortname + '【' + row.subsystemcnfullname + '】');
        $("#devgroup").val(row.devgroup);
        $("#subsystemperson").val(row.subsystemperson);
    });
}
// 子系统环境清单
function queryEnvironBySubsystemId(subsystemid) {
	$.post("queryEnvironBySubsystemId.do", {"subsystemid": subsystemid}, function(data) {
		if (data != null) {
			autocompleteEnviron(data);
		}
	});
}
function autocompleteEnviron(data) {
	$("#environname").flushCache();
	$('#environname').autocomplete(data, {
        max: 12,    			//列表里的条目数
        minChars: 0,    		//自动完成激活之前填入的最小字符
        width: 300,     		//提示的宽度，溢出隐藏
        scrollHeight: 300,   	//提示的高度，溢出显示滚动条
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    	//自动填充
        formatItem: function(row, i, max) {
            return row.environmentename + '【' + row.environmentcname + '】';
        },
        formatMatch: function(row, i, max) {
            return row.environmentename;
        },
        formatResult: function(row) {
            return row.environmentename;
        }
    }).result(function(event, row, formatted) {
    	$("#environmentename").val(row.environmentename);
    	$("#environmentcname").val(row.environmentcname);
    });
}
// 开发人员：指定子系统的“系统负责人和开发人员”角色人员
function queryDevpersonnelBySubsystemId(subsystemid) {
	$.post("queryDevpersonnel.do", {"subsystemid": subsystemid}, function(data) {
		if (data != null) {
			autocompleteDevpersonnel(data);
		}
	});
}
//(测试平台接口调用)开发人员：指定子系统的“系统负责人和开发人员”角色人员
function itfacequeryDevpersonnelBySubsystemId(subsystemid) {
	$.post("itfacequeryDevpersonnel.do", {"subsystemid": subsystemid}, function(data) {
		if (data != null) {
			autocompleteDevpersonnel(data);
		}
	});
}
function autocompleteDevpersonnel(data) {
	$("#devpersonnel").flushCache();
	$('#devpersonnel').autocomplete(data, {
        max: 12,    			//列表里的条目数
        minChars: 0,    		//自动完成激活之前填入的最小字符
        width: 300,     		//提示的宽度，溢出隐藏
        scrollHeight: 300,   	//提示的高度，溢出显示滚动条
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    	//自动填充
        formatItem: function(row, i, max) {
        	return row.realname + '【' + row.umname + '】';
        },
        formatMatch: function(row, i, max) {
        	return row.realname + '【' + row.umname + '】';
        },
        formatResult: function(row) {
        	return row.umname;
        }
    }).result(function(event, row, formatted) {
    	$("#prdhander").val(row.umname);
    	$("#sprddeveloper").val(row.umname);
    	$("#developper").val(row.umname);
    	$("#bughander").val(row.umname);
    	$("#sbugdeveloper").val(row.umname);
    	
    });
}
//负责人：指定子系统的“负责人”角色人员
function queryChargepersonBySubsystemId(subsystemid) {
	$.post("queryChargeperson.do", {"subsystemid": subsystemid}, function(data) {
		if (data != null) {
			autocompleteChargeperson(data);
		}
	});
}
//(测试平台接口调用)负责人：指定子系统的“负责人”角色人员
function itfacequeryChargepersonBySubsystemId(subsystemid) {
	$.post("itfacequeryChargeperson.do", {"subsystemid": subsystemid}, function(data) {
		if (data != null) {
			autocompleteChargeperson(data);
		}
	});
}
function autocompleteChargeperson(data) {
	$("#chargeperson").flushCache();
	$('#chargeperson').autocomplete(data, {
        max: 12,    			//列表里的条目数
        minChars: 0,    		//自动完成激活之前填入的最小字符
        width: 300,     		//提示的宽度，溢出隐藏
        scrollHeight: 300,   	//提示的高度，溢出显示滚动条
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: true,    	//自动填充
        cacheLength:0,
        matchSubset:false,
        multiple:false,
        selectFirst:true,
        mustMatch:false,
        formatItem: function(row, i, max) {
        	return row.realname + '【' + row.umname + '】';
        },
        formatMatch: function(row, i, max) {
        	return row.realname + '【' + row.umname + '】';
        },
        formatResult: function(row) {
        	return row.umname;
        }
    }).result(function(event, row, formatted) {
    	$("#prdchargeperson").val(row.umname);
    	$("#sprdhandler").val(row.umname); //生产子问题负责人
    	$("#xname").val(row.realname + '【' + row.umname + '】');
    	$("#bugchargeperson").val(row.umname); //测试缺陷负责人
    	$("#sbughandler").val(row.umname); //测试子问题负责人
    });
}
// 验证人员：指定子系统的“开发人员、系统负责人、应用服务人员、应用服务组长”的角色人员
//function queryValidpersonnelBySubsystemId(subsystemid) {
//	$.post("queryValidpersonnel.do", {"subsystemid": subsystemid}, function(data) {
//		if (data != null) {
//			autocompleteValidpersonnel(data);
//		}
//	});
//}
//function autocompleteValidpersonnel(data) {
//	$('#validpersonnel').autocomplete(data, {
//        max: 12,    			//列表里的条目数
//        minChars: 0,    		//自动完成激活之前填入的最小字符
//        width: 300,     		//提示的宽度，溢出隐藏
//        scrollHeight: 300,   	//提示的高度，溢出显示滚动条
//        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
//        autoFill: false,    	//自动填充
//        formatItem: function(row, i, max) {
//        	return row.realname + '【' + row.umname + '】';
//        },
//        formatMatch: function(row, i, max) {
//        	return row.realname + '【' + row.umname + '】';
//        },
//        formatResult: function(row) {
//        	return row.realname + '【' + row.umname + '】';
//        }
//    }).result(function(event, row, formatted) {
//    	$("#validename").val(row.umname);
//    	$("#bughander").val(row.umname);
//    	$("#prdvalidater").val(row.umname);
//    });
//}