package com.example.aula2.DAO;

import java.sql.Connection;

public class MinhaConexao{

    public static Connection conexao(){
        ConexaoJDBC conexao = new ConexaoMysql();
        return conexao.criarConexao();
    }

}