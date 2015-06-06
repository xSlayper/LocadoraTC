/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import Entity.entCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dev
 */
public class daoCliente {
    
    public void insertCliente(entCliente objClient)
    {
        System.out.println("Cadastro de Cliente!!");
        try {
            Conexao.Conectar();
            System.out.println("Gerando intChave");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT MAX(intChave) FROM tblCliente");
            ResultSet rs = stmt.getResultSet();
            
             while(rs.next())
            {   
                objClient.setIntChave(rs.getInt(1) + 1);
            }
            System.out.println("Inserindo Cliente!");
            String strQuery = "INSERT INTO tblCliente (intChave, strNome, strCPF, strEndereco, strTelefone, strEmail, dblDivida) Values ('%d','%s','%s','%s','%s','%s','%s') ";
                        System.out.println(String.format(String.format(strQuery, objClient.getIntChave(), objClient.getStrNome(), objClient.getStrCPF(), objClient.getStrEndereco(), objClient.getStrTelefone(), objClient.getStrEmail(), objClient.getDblDivida())));
            stmt.execute(String.format(strQuery, objClient.getIntChave(), objClient.getStrNome(), objClient.getStrCPF(), objClient.getStrEndereco(), objClient.getStrTelefone(), objClient.getStrEmail(), Double.toString(objClient.getDblDivida()).replace(",", ".s")));

            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            
        }
        
    }
    
}
