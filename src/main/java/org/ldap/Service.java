package org.ldap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public List<String> getAllUsers(DirContext ctx)
            throws NamingException
    {
        String filter = "(objectClass=inetOrgPerson)";
        String[] reqAtt = {"cn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration<SearchResult> users = ctx.search(GlobalConstants.BASE_NAME, filter, controls);
        List<String> usersList = new ArrayList<>();
        while (users.hasMore()) {
            SearchResult result = users.next();
            Attributes attrs = result.getAttributes();

            usersList.add(result.getName());
            //code to get attributes
            NamingEnumeration<? extends Attribute> attrEnum = attrs.getAll();
            while (attrEnum.hasMore()) {
                Attribute attr = attrEnum.next();
                //System.out.println(attr.getID() + ": " + attr.get());
            }
        }
        return usersList;
    }

    public String addUser(DirContext ctx,
                          String newUserName)
            throws NamingException
    {
        Attributes attributes = new BasicAttributes();
        Attribute attribute = new BasicAttribute("objectClass");

        attribute.add("inetOrgPerson");
        attributes.put(attribute);

        attributes.put("sn", "n");
        ctx.createSubcontext(GlobalConstants.USER_NAME_PREFIX+newUserName+","+GlobalConstants.BASE_NAME,attributes);

        return "success";
    }

    public String deleteUser(DirContext ctx,
                             String userName)
            throws NamingException
    {
        ctx.destroySubcontext(GlobalConstants.USER_NAME_PREFIX+userName+","+GlobalConstants.BASE_NAME);

        return "Deleted";
    }

    public String updateUser(DirContext ctx,
                             String attributeName,
                             String attributeValue,
                             String userName)
            throws NamingException
    {
        Attribute attribute = new BasicAttribute(attributeName, attributeValue);
        ModificationItem[] mods = new ModificationItem[1];
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,attribute);
        ctx.modifyAttributes(GlobalConstants.USER_NAME_PREFIX+userName+","+GlobalConstants.BASE_NAME,mods);
        return "updated";
    }

    public List<String> getUser(DirContext ctx,
                                String uidValue)
            throws NamingException
    {
        String searchFilter = "(uid="+uidValue+")";
        String[] reqAtt = {"cn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);
        NamingEnumeration<SearchResult> users = ctx.search(GlobalConstants.BASE_NAME,searchFilter,controls);
        List<String> usersList = new ArrayList<>();
        while (users.hasMore()) {
            SearchResult result = users.next();
            Attributes attrs = result.getAttributes();

            usersList.add(result.getName());
            //code to get attributes
            NamingEnumeration<? extends Attribute> attrEnum = attrs.getAll();
            while (attrEnum.hasMore()) {
                Attribute attr = attrEnum.next();
                //System.out.println(attr.getID() + ": " + attr.get());
            }
        }
        return usersList;
    }
}