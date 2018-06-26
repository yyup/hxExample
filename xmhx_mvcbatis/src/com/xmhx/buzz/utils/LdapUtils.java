package com.xmhx.buzz.utils;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;

/**
 * 域服务器校验用户名与密码
 * @author 吴进 by 20160119
 *
 */
public class LdapUtils {

	//域服务器校验
	public boolean ldapCheck(String usrNo, String usrPwd) {		
		String ldapURL = "ldap://10.5.32.14:389";
		Properties env = new Properties();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");// "none","simple","strong"
		env.put(Context.SECURITY_PRINCIPAL, usrNo+"@SDB.LOCAL");
		env.put(Context.SECURITY_CREDENTIALS, usrPwd);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapURL);
		try {
			 new InitialLdapContext(env, null);
		} catch (Exception e) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
}
