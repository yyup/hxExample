
/* 以下是初始化数据 */
-- 顶目录
insert into manages_menu(hxuuid, menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values (uuid(), '系统管理', 'system_manage', 'top', '系统管理菜单', '', null, 'Y', 'system', now(), 'system', now(), 1);

insert into manages_menu(hxuuid, menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values (uuid(), '用户管理', 'user_manage', 'system_manage', '用户管理', 'commonmember/list.hx', null, 'Y', 'system', now(), 'system', now(), 1);

insert into manages_menu(hxuuid, menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values (uuid(), '消息管理', 'messages_manage', 'system_manage', '消息管理', 'webservicesmessages/list.hx', null, 'Y', 'system', now(), 'system', now(), 2);

insert into manages_menu(hxuuid, menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values (uuid(), '新闻管理', 'news_manage', 'system_manage', '新闻管理', 'news/list.hx', null, 'Y', 'system', now(), 'system', now(), 3);
/*
insert into manages_menu(hxuuid, menu_name, menu_code, parent_menu_code, menu_desc, menu_url, menu_icon, data_state, created_by, date_created, updated_by, date_updated, weight) 
values (uuid(), '政策管理', 'policy_manage', 'system_manage', '政策管理', 'policy/list.hx', null, 'Y', 'system', now(), 'system', now(), 4);
*/
