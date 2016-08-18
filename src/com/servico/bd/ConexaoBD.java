package com.servico.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ConexaoBD {

    public static Connection con;
    
    public static String whatServer = "localhost";
    
    public static Connection conectar() {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            
            String serverName;
            String database;
            String username;
            String password;
            
            serverName = "localhost"; 
            database = "despesas_tcc";
            username = "root";
            password = "batman123";
            
            String url = "jdbc:mysql://" + serverName + "/" + database;
            con = DriverManager.getConnection(url, username, password);
            
            return con;
        } catch (ClassNotFoundException e) {  
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados." + e.getMessage());
            return null;
        }
    }

    public static void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            ImageIcon erro = new ImageIcon("./icones/erro.png");
            JOptionPane.showMessageDialog(null, "Erro Banco de Dados. Detalhes: " + ex.toString(),
                    "Erro", JOptionPane.ERROR_MESSAGE, erro);
        }
    }
}