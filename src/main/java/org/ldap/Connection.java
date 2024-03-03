package org.ldap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
import java.util.function.BiFunction;

public class Connection {
    BiFunction<String,String,DirContext> createLdapContext = (userName, password) ->
    {
        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, GlobalConstants.CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, GlobalConstants.LDAP_URL);
        env.put(Context.SECURITY_PRINCIPAL, userName);
        env.put(Context.SECURITY_CREDENTIALS, password);
        try {
            return new InitialDirContext(env);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    };

}
