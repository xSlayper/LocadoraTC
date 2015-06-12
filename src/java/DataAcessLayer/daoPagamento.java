/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import Entity.entAluguel;
import Entity.entPagamento;
import Entity.entCliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Netinho
 */
public class daoPagamento {
    public void insertPagamento(entPagamento objPagamento) {
        System.out.println("Cadastro de novo Pagamento!!");
        try {
            Conexao.Conectar();
            System.out.println("Gerando intChave");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT MAX(intChave) FROM tblPagamento");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                objPagamento.setIntChave(rs.getInt(1) + 1);
            }

            Conexao.conn.setAutoCommit(false);

            PreparedStatement ps = Conexao.conn.prepareStatement("INSERT INTO tblPagamento (intChave, intChaveTipoPagamento, intChaveAluguel, intChequeDias, intCartaoParcelas) values(?,?,?,?,?)");

            ps.setInt(1, objPagamento.getIntChave());
            ps.setInt(2, objPagamento.getIntChaveTipoPagamento());
            ps.setInt(3, objPagamento.getIntChaveAluguel());
            ps.setInt(4, objPagamento.getIntChequeDias());
            ps.setDouble(5, objPagamento.getIntCartaoParcelas());
            ps.executeUpdate();
            Conexao.conn.commit();
            Conexao.conn.close();

            
            daoAluguel dalAlug = new daoAluguel();
            entAluguel aluguelToPay = dalAlug.getAluguel(objPagamento.getIntChaveAluguel());
            aluguelToPay.setIntChavePagamento(objPagamento.getIntChave());
            dalAlug.updateAluguel(aluguelToPay);
           
            daoCliente dalCli = new daoCliente();
            entCliente atualizarDividaCli = dalCli.getCliente(aluguelToPay.getIntChaveCliente());
            atualizarDividaCli.setDblDivida(atualizarDividaCli.getDblDivida() - aluguelToPay.getDblValorTotal());
            dalCli.editCliente(atualizarDividaCli);

        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }
}
