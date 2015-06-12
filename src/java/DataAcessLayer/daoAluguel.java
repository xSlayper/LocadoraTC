/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import Entity.entAluguel;
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
public class daoAluguel {

    public void insertAluguel(entAluguel objAluguel) {
        System.out.println("Cadastro de novo Aluguel!!");
        try {
            Conexao.Conectar();
            System.out.println("Gerando intChave");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT MAX(intChave) FROM tblAluguel");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                objAluguel.setIntChave(rs.getInt(1) + 1);
            }

            Conexao.conn.setAutoCommit(false);

            PreparedStatement ps = Conexao.conn.prepareStatement("INSERT INTO tblAluguel (intChave, intChaveCliente, intChaveCarro, intDias, dblValorTotal, intChavePagamento) values(?,?,?,?,?,?)");

            ps.setInt(1, objAluguel.getIntChave());
            ps.setInt(2, objAluguel.getIntChaveCliente());
            ps.setInt(3, objAluguel.getIntChaveCarro());
            ps.setInt(4, objAluguel.getIntDias());
            ps.setDouble(5, objAluguel.getDblValorTotal());
            ps.setInt(6, objAluguel.getIntChavePagamento());
            ps.executeUpdate();
            Conexao.conn.commit();
            Conexao.conn.close();

            daoCliente dalCli = new daoCliente();
            entCliente dividaCliente = dalCli.getCliente(objAluguel.getIntChaveCliente());
            dividaCliente.setDblDivida(dividaCliente.getDblDivida() + objAluguel.getDblValorTotal());
            dalCli.editCliente(dividaCliente);

        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }

    public List<entAluguel> getAlugueis() {
        List<entAluguel> listaAluguels = new ArrayList<>();
        try {
            Conexao.Conectar();
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery(" SELECT"
                    + " al.intChave, al.intChaveCliente, cl.strNome, al.intChaveCarro,"
                    + " cr.strMarca, cr.strModelo, al.intDias, al.dblValorTotal, al.intChavePagamento FROM"
                    + " tblAluguel al,"
                    + " tblCarro cr,"
                    + " tblCliente cl"
                    + " WHERE"
                    + " al.intChaveCarro = cr.intChave"
                    + " AND"
                    + " al.intChaveCliente = cl.intChave");
            ResultSet rs = stmt.getResultSet();
            System.out.println("Coletei resultSet!");
            entAluguel objAluguel;
            while (rs.next()) {
                objAluguel = new entAluguel();
                objAluguel.setIntChave(rs.getInt("intChave"));
                objAluguel.setIntChaveCliente(rs.getInt("intChaveCliente"));
                objAluguel.setStrNomeCliente(rs.getString("strNome"));
                objAluguel.setIntChaveCarro(rs.getInt("intChaveCarro"));
                objAluguel.setStrMarcaModeloCarro(rs.getString("strMarca") + " - " + rs.getString("strModelo"));
                objAluguel.setIntDias(rs.getInt("intDias"));
                objAluguel.setDblValorTotal(rs.getDouble("dblValorTotal"));
                objAluguel.setIntChavePagamento(rs.getInt("intChavePagamento"));

                if (objAluguel.getIntChavePagamento() == 0) {
                    objAluguel.setStrPago("Não");
                } else {
                    objAluguel.setStrPago("Sim");
                }
                listaAluguels.add(objAluguel);
            }
            System.out.println("Preenchi a lista!!");
            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoAluguel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return listaAluguels;
    }

    public entAluguel getAluguel(int intChave) {
        entAluguel objAluguel = new entAluguel();
        try {
            Conexao.Conectar();
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery(" SELECT"
                    + " al.intChave, al.intChaveCliente, cl.strNome, al.intChaveCarro,"
                    + " cr.strMarca, cr.strModelo, al.intDias, al.dblValorTotal, al.intChavePagamento FROM"
                    + " tblAluguel al,"
                    + " tblCarro cr,"
                    + " tblCliente cl"
                    + " WHERE"
                    + " al.intChaveCarro = cr.intChave"
                    + " AND"
                    + " al.intChaveCliente = cl.intChave"
                    + " AND al.intChave = " + intChave);
            ResultSet rs = stmt.getResultSet();
            System.out.println("Coletei resultSet!");            
            
            while (rs.next()) {
               
                objAluguel.setIntChave(rs.getInt("intChave"));
                objAluguel.setIntChaveCliente(rs.getInt("intChaveCliente"));
                objAluguel.setStrNomeCliente(rs.getString("strNome"));
                objAluguel.setIntChaveCarro(rs.getInt("intChaveCarro"));
                objAluguel.setStrMarcaModeloCarro(rs.getString("strMarca") + " - " + rs.getString("strModelo"));
                objAluguel.setIntDias(rs.getInt("intDias"));
                objAluguel.setDblValorTotal(rs.getDouble("dblValorTotal"));
                objAluguel.setIntChavePagamento(rs.getInt("intChavePagamento"));

                if (objAluguel.getIntChavePagamento() == 0) {
                    objAluguel.setStrPago("Não");
                } else {
                    objAluguel.setStrPago("Sim");
                }
                
            }
            System.out.println("Preenchi a lista!!");
            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoAluguel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return objAluguel;
    }
    
    public void updateAluguel(entAluguel objAluguel) {
     
        try {
            Conexao.Conectar();
         
            Conexao.conn.setAutoCommit(false);

            PreparedStatement ps = Conexao.conn.prepareStatement("UPDATE tblAluguel SET intChaveCliente = ?, intChaveCarro = ?, intDias = ?, dblValorTotal = ?, intChavePagamento = ? WHERE intChave = ?");

            
            ps.setInt(1, objAluguel.getIntChaveCliente());
            ps.setInt(2, objAluguel.getIntChaveCarro());
            ps.setInt(3, objAluguel.getIntDias());
            ps.setDouble(4, objAluguel.getDblValorTotal());
            ps.setInt(5, objAluguel.getIntChavePagamento());
            ps.setInt(6, objAluguel.getIntChave());
            ps.executeUpdate();
            Conexao.conn.commit();
            Conexao.conn.close();


        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }
}
