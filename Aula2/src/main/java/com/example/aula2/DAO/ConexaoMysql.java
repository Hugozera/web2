package com.example.aula2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMysql implements ConexaoJDBC{

    public static void main(String[] args) {

        //testar conexão
        System.out.println(new ConexaoMysql().criarConexao());

    }

    /**
     * método que vai retornar uma conexão
     * @return
     */
    @Override
    public Connection criarConexao(){
        try {
            //carregar o driver de conexão
            Class.forName("com.mysql.cj.jdbc.Driver");
            //parâmetros
            String url = "jdbc:mysql://localhost:3307/aula2";
            String usuario = "root";
            String senha = "asferna123";
            //retorna a conexão com o banco de dados
            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}