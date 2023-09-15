package com.example.aula2.DAO;

import com.example.aula2.Model.Pessoa;
import com.example.aula2.Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutoDAO {



        //criar um objeto Connection para receber a conexão
        Connection con;

        public ProdutoDAO(){
            con = MinhaConexao.conexao();
        }

        public List<Produto> buscarProduto() {
            try {
                //comando sql
                String sql = "select * from produto";
                PreparedStatement ps = con.prepareStatement(sql);
                //ResultSet, representa o resultado do comando SQL
                ResultSet rs = ps.executeQuery();
                //cria uma lista de pessoas para retornar
                List<Produto> produtos = new ArrayList();
                //laço para buscar todas as pessoas do banco
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setPreco(rs.getDouble("preco"));
                    //add pessoa na lista
                    produtos.add(p);
                }
                //retorna a lista de pessoas
                return produtos;
            } catch (SQLException ex) {
                Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        public boolean removeProduto(Long id) {
            try {
                //comando sql
                String sql = "delete from produto where id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                //referênciar o parâmetro do método para a ?
                ps.setLong(1, id);
                if(ps.executeUpdate()==1)
                    return true;

            } catch (SQLException ex) {
                Logger.getLogger(com.example.aula2.DAO.PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

        public boolean saveProduto(Produto produto) {
            try {
                //comando sql
                String sql = "insert into produto (descricao, preco) values (?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                //referênciar o parâmetro do método para a ?
                ps.setString(1, produto.getDescricao());
                ps.setDouble(2, produto.getPreco());

                if(ps.executeUpdate()==1)
                    return true;

            } catch (SQLException ex) {
                Logger.getLogger(com.example.aula2.DAO.PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

        public boolean updateProduto(Produto produto) {
            try {
                //comando sql
                String sql = "update produto set descricao=?, preco=? where id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                //referênciar o parâmetro do método para a ?
                ps.setString(1, produto.getDescricao());
                ps.setDouble(2,produto.getPreco());
                ps.setLong(3, produto.getId());

                if (ps.executeUpdate()==1)
                    return true;

            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

        public Produto buscarProduto(Long id) {
            try {
                //comando sql
                String sql = "select * from produto where id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                //referênciar o parâmetro do método para a ?
                ps.setLong(1, id);

                //ResultSet, representa o resultado do comando SQL
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setPreco(rs.getDouble("preco"));
                    return p;
                }
            } catch (SQLException ex) {
                Logger.getLogger(com.example.aula2.DAO.ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }

