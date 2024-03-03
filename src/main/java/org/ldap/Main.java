package org.ldap;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

public class Main {
    public static void main(String[] args) {
        Connection connection = new Connection();
        Service service = new Service();
        try {

            DirContext ctx = connection.createLdapContext.apply(GlobalConstants.USER_NAME, GlobalConstants.PASSWORD);
            //Add user
            String addUser = service.addUser(ctx, "ABCD");
            System.out.println(addUser);
            //get all user
            var usersList = service.getAllUsers(ctx);
            System.out.println(usersList);
            //delete user
            String deleteUser = service.deleteUser(ctx, "ABCD");
            System.out.println(deleteUser);
            //get all user
            var usersList1 = service.getAllUsers(ctx);
            System.out.println(usersList1);
            //update user
            var updateUser = service.updateUser(ctx, "employeeNumber","135","newUser");
            System.out.println(updateUser);
            //get user
            var getUser = service.getUser(ctx,"55");
            System.out.println(getUser);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}