package DataAcessLayer;

import Entity.entUsuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoUsuario {
    
    public entUsuario getUser(String userName, String Password)
    {
        entUsuario objUser = new entUsuario();
        
        
        try {
            Conexao.Conectar();
            System.out.println("Conexao Aberta!");
            Statement stmt = Conexao.conn.createStatement();
//            String strQuery = "SELECT intChave, strUsuario, strSenha, strNome, intCargo FROM tblUsuario WHERE strUsuario = '%s' AND strSenha = '%s'";
            String strQuery = "SELECT intChave, strUsuario, strSenha, strNome, intCargo FROM tblusuario WHERE strUsuario = '%s' AND strSenha = '%s'";
           stmt.executeQuery(String.format(strQuery, userName, Password));
            System.out.println(String.format(strQuery, userName, Password));
            ResultSet rs = stmt.getResultSet();
            
            
            while(rs.next())
            {   
                objUser.setIntChave(rs.getInt("intChave"));
                objUser.setStrNome(rs.getString("strNome"));
                objUser.setStrSenha(rs.getString("strSenha"));
                objUser.setStrUsuario(rs.getString("strUsuario"));
                objUser.setIntCargo(rs.getInt("intCargo"));
            }
            
            
            
            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            
        }
        
        return objUser;
    }
}
