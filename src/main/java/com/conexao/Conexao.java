package com.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author lucas
 */
public class Conexao {
    
    // Padrão Singleton
    
    private Conexao(){}
    private static Connection instance;
    
    private static Connection getConnection(){
        Connection conexao = null;
        final String DRIVER = "org.postgresql.Driver";
//        final String DRIVER = "com.mysql.jdbc.Driver";  MySQL
        final String USER = "username";
        final String PASS = "password";
        
        try{
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crud_javaee", USER, PASS);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return conexao;
    }
    
    public static Connection getInstance(){
        if(instance == null){
            instance = getConnection();
        }
        return instance;
    }
}