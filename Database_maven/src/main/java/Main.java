
import Database.DButil;
import Database.MySQL;
import Database.Oracle;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        DButil mysql=new MySQL();
        DButil oracle=new Oracle();

        mysql.select("users");
        oracle.select("users");


        mysql.insert("users",
                new String[]{"username", "password"},
                new String[]{"moon", "9321"});
        mysql.select("users");
        mysql.insert("users",
                new String[]{"username", "password"},
                new String[]{"sql", "98321"});
        mysql.select("users");
        oracle.insert("users",
                new String[]{"username", "password"},
                new String[]{"oracle", "987654321"});
        oracle.select("users");
        ///////////////////////////////////////////////////////////////////////////////////
        System.out.println("deleting");
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter Username : ");
        String username = sc.next();

        if (mysql.checkUserIfExist(username)) {
            String query = String.format("delete from users where username = '%s'", username);
            mysql.delete(query);
            System.out.println("User has been deleted.");
            mysql.select("users");

        } else {
            System.out.println("User doesn't  exist.");
        }

        //Scanner sc = new Scanner(System.in);
        System.out.println("deleting");
        System.out.print("Please Enter Username : ");
        username = sc.next();

        if (oracle.checkUserIfExist(username)) {
            String query = String.format("delete from users where username = '%s'", username);
            oracle.delete(query);
            System.out.println("User has been deleted.");
            oracle.select("users");

        } else {
            System.out.println("User doesn't  exist.");
        }
        //////////////////////////////////////////////////////////////////////////////////
        System.out.println("update");
        System.out.print("Please Enter ID       : ");
        int id=sc.nextInt();
        System.out.print("Please Enter new Username : ");
        username = sc.next();
        System.out.print("Please Enter new password : ");
        String password=sc.next();

        if (mysql.checkUserIfExist(id)) {
            mysql.update("users",
                    new String[]{"username", "password"},
                    new String[]{username, password},id);
            mysql.select("users");
            System.out.println("User has been updated.");

        } else {
            System.out.println("User id doesn't  exist.");
        }

        System.out.print("Please Enter ID       : ");
        id=sc.nextInt();
        System.out.print("Please Enter new Username : ");
        username = sc.next();
        System.out.print("Please Enter new password : ");
        password=sc.next();

        if (oracle.checkUserIfExist(id)) {
            oracle.update("users",
                    new String[]{"username", "password"},
                    new String[]{username, password},id);
            oracle.select("users");
            System.out.println("User has been updated.");

        } else {
            System.out.println("User id doesn't  exist.");
        }


        sc.close();
    }
}
