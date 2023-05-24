package CRUD_Books;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
    public static void main(String[] args) {
        DB_Connection obj_DB_connection = new DB_Connection();
        System.out.println(obj_DB_connection.get_connection());
    }
    public Connection get_connection(){
        Connection connection = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?useSSL=false","root","Allah.aalam@9251");
        } catch(Exception e){
            System.out.println(e);
        }

        return connection;
    }
}
