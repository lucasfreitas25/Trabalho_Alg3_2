package com.example.application.repositories.postgres;

import com.example.application.entidades.Cliente;
import com.example.application.entidades.Venda;
import com.example.application.repositories.VendaRepository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendaRepositoryImpl implements VendaRepository {

    public void inserir(Venda venda) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/venda", "postgres", "2515");
            String sql = "INSERT INTO venda (cpf, quantidade, entrega) VALUES (?,?,?);";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, venda.getCpf());
            pstm.setInt(2, venda.getQuantidade());
            pstm.setString(3, venda.getEntrega());
            pstm.execute();
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }

    }

    public List<Venda> listar() {
        List<Venda> lista = new ArrayList<>();
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/venda", "postgres", "2515");
            String sql = "SELECT * FROM venda;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Venda d = new Venda();
                d.setId_venda(rs.getInt("id_venda"));
                d.setCpf(rs.getString("cpf"));
                d.setQuantidade(rs.getInt("quantidade"));
                d.setEntrega(rs.getString("entrega"));
                lista.add(d);
            }
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }

    public void remover(int id) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/venda", "postgres", "2515");
            String sql = "DELETE FROM venda where id_venda=?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public void editar(Venda venda) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/venda", "postgres", "2515");
            String sql = "UPDATE Venda SET cpf=?, quantidade=?, entrega=? WHERE id_venda=?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, venda.getCpf());
            pstm.setInt(2, venda.getQuantidade());
            pstm.setString(3, venda.getEntrega());
            pstm.setInt(4, venda.getId_venda());
            pstm.execute();
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    /*
     * public List<Venda> contadorVendas() {
     * List<Venda> lista = new ArrayList<>();
     * Connection con;
     * try {
     * con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Venda",
     * "postgres", "2515");
     * String sql = "SELECT COUNT(nome) FROM produto;";
     * Statement st = con.createStatement();
     * ResultSet rs = st.executeQuery(sql);
     * while (rs.next()) {
     * Venda d = new Venda();
     * d.setNome(rs.getString("nome"));
     * lista.add(d);
     * }
     * con.close();
     * } catch (Exception erro) {
     * throw new RuntimeException(erro);
     * }
     * return lista;
     * }
     */
    public boolean verificador(String cp) {
        List<Cliente> lista = new ArrayList<>();
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cliente", "postgres", "2515");
            String sql = "SELECT cpf FROM cliente;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente d = new Cliente();
                d.setCpf(cp);
                lista.add(d);
            }
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        return true;
    }

}
