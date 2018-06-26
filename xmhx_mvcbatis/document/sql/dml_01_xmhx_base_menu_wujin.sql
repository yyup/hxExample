
/* 以下是初始化数据 */
-- 顶目录
insert into xmhx_base_menu(menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values ('资讯管理', 'zixunguanli', 'top', '资讯管理菜单', '', null, '1', 'system', now(), 'system', now(), 1);

insert into xmhx_base_menu(menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values ('审核管理', 'shenheguanli', 'top', '审核管理菜单', '', null, '1', 'system', now(), 'system', now(), 2);


insert into xmhx_base_menu(menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values ('新闻政策管理', 'zxmanage', 'zixunguanli', '新闻政策管理', 'manages/zxmanage.do', null,  '1', 'system', now(), 'system', now(), 1);

insert into xmhx_base_menu(menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values ('企业秀审核', 'qiyexiuverify', 'shenheguanli', '企业秀审核', 'manages/qiyexiuverify.do', null,  '1', 'system', now(), 'system', now(), 1);

insert into xmhx_base_menu(menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values ('活动审核', 'huodongverify', 'shenheguanli', '活动审核', 'manages/huodongverify.do', null,  '1', 'system', now(), 'system', now(), 2);

