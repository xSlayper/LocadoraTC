/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dev
 */
public class Conexao  {
    public static Connection conn;

    public Conexao() {
    }
    
    public static void Conectar()
    {
        System.out.println("Conectando ao banco....");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/locadora","root","123456");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
        } catch (SQLException ex) {
            System.out.println("Erro ao abrir conexão! " + ex.getMessage());
        }
    }
}
