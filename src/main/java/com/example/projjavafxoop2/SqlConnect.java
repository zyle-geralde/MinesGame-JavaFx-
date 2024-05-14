package com.example.projjavafxoop2;


//import com.almasb.fxgl.net.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnect {
    public static final String URL = "jdbc:mysql://localhost:3306/dbcasinoplusplus";
    public static final String username = "";
    public static final String password = "";

    public static Connection getConnection(){
        Connection c = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL,username,password);

        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return c;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
