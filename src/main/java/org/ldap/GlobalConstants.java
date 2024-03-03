package org.ldap;

public interface GlobalConstants {
    public static final String CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    public static final String LDAP_URL = "ldap://localhost:10389";
    public static final String USER_NAME_PREFIX = "cn=";
    public static final String UID_PREFIX = "uid=";
    public static final String SECURITY_AUTHENTICATION = "";
    public static final String USER_NAME = "uid=admin,ou=system";
    public static final  String PASSWORD = "secret";
    public static final String BASE_NAME = "ou=users,ou=system";
}
