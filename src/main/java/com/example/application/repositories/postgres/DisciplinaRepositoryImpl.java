package com.example.application.repositories.postgres;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.application.entidades.Disciplina;
import com.example.application.repositories.DisciplinaRepository;

public class DisciplinaRepositoryImpl implements DisciplinaRepository {

    public void inserir(Disciplina disciplina) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste", "postgres", "2515");
            String sql = "INSERT INTO disciplina (nome, carga_horaria) VALUES (?,?);";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, disciplina.getNome());
            pstm.setInt(2, disciplina.getCargahoraria());
            pstm.execute();
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }

    }

    public List<Disciplina> listar() {
        List<Disciplina> lista = new ArrayList<>();
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste", "postgres", "2515");
            String sql = "SELECT * FROM disciplina;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Disciplina d = new Disciplina();
                d.setNome(rs.getString("nome"));
                d.setId(rs.getInt("id"));
                d.setCargahoraria(rs.getInt("carga_horaria"));
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
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste", "postgres", "2515");
            String sql = "DELETE FROM disciplina where id=?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public void editar(Disciplina disciplina) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste", "postgres", "2515");
            String sql = "UPDATE disciplina SET nome=?, carga_horaria=? WHERE id=?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, disciplina.getNome());
            pstm.setInt(2, disciplina.getCargahoraria());
            pstm.setInt(3, disciplina.getId());
            pstm.execute();
            con.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}
