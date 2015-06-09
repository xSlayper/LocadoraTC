/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import Entity.entCarro;
import Entity.entCliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Netinho
 */
public class daoCarro {

    public void insertCarro(entCarro objCarro) {
        System.out.println("Cadastro de novo Carro!!");
        try {
            Conexao.Conectar();
            System.out.println("Gerando intChave");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT MAX(intChave) FROM tblCarro");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                objCarro.setIntChave(rs.getInt(1) + 1);
            }
            System.out.println("Inserindo Cliente!");

            Conexao.conn.setAutoCommit(false);

            PreparedStatement ps = Conexao.conn.prepareStatement("INSERT INTO tblCarro (intChave, strModelo, strMarca, intAno, strPlaca, dblDiaria, btFoto) values(?,?,?,?,?,?,?)");
            ps.setInt(1, objCarro.getIntChave());
            ps.setString(2, objCarro.getStrModelo());
            ps.setString(3, objCarro.getStrMarca());
            ps.setInt(4, objCarro.getIntAno());
            ps.setString(5, objCarro.getStrPlaca());
            ps.setDouble(6, objCarro.getDblDiaria());
            ps.setBinaryStream(7, objCarro.getIsFoto(), objCarro.getTamanhoFoto());
            ps.executeUpdate();

            Conexao.conn.commit();
            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }

    public entCarro getCarro(int idCarro) {
        System.out.println("get Carro!!");
        entCarro objCarro = new entCarro();
        try {
            Conexao.Conectar();
            System.out.println("Gerando intChave");

            PreparedStatement ps = Conexao.conn.prepareStatement("SELECT intChave, strModelo, strMarca, intAno, strPlaca, dblDiaria, btFoto FROM tblCarro WHERE intChave = ?");
            ps.setInt(1, idCarro);
            ResultSet rs = ps.executeQuery();
            rs.next();
            objCarro.setIntChave(rs.getInt("intChave"));
            objCarro.setStrModelo(rs.getString("strModelo"));
            objCarro.setStrMarca(rs.getString("strMarca"));
            objCarro.setIntAno(rs.getInt("intAno"));
            objCarro.setStrPlaca(rs.getString("strPlaca"));
            objCarro.setDblDiaria(rs.getDouble("dblDiaria"));
            objCarro.setBlbFoto(rs.getBlob("btFoto"));

            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return objCarro;
    }

    public List<entCarro> getAllCarros() {
        List<entCarro> listaCarros = new ArrayList<>();
        try {
            Conexao.Conectar();
            System.out.println("Coletando todos os carros!");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT intChave, strModelo, strMarca, intAno, strPlaca, dblDiaria, btFoto FROM tblCarro");
            ResultSet rs = stmt.getResultSet();
            System.out.println("Coletei resultSet!");
            entCarro objCarro;
            while (rs.next()) 
            {
                objCarro = new entCarro();
                objCarro.setIntChave(rs.getInt("intChave"));
                objCarro.setStrModelo(rs.getString("strModelo"));
                objCarro.setStrMarca(rs.getString("strMarca"));
                objCarro.setIntAno(rs.getInt("intAno"));
                objCarro.setStrPlaca(rs.getString("strPlaca"));
                objCarro.setDblDiaria(rs.getDouble("dblDiaria"));
                objCarro.setBlbFoto(rs.getBlob("btFoto"));
                listaCarros.add(objCarro);
            }
            System.out.println("Preenchi a lista!!");
            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return listaCarros;
    }
}
