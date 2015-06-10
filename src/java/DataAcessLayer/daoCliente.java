package DataAcessLayer;

import Entity.entCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoCliente {

    public void insertCliente(entCliente objClient) {
        System.out.println("Cadastro de Cliente!!");
        try {
            Conexao.Conectar();
            System.out.println("Gerando intChave");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT MAX(intChave) FROM tblCliente");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                objClient.setIntChave(rs.getInt(1) + 1);
            }
            System.out.println("Inserindo Cliente!");
//            String strQuery = "INSERT INTO tblCliente (intChave, strNome, strCPF, strEndereco, strTelefone, strEmail, dblDivida) Values ('%d','%s','%s','%s','%s','%s','%s') ";
            String strQuery = "INSERT INTO tblcliente (intChave, strNome, strCPF, strEndereco, strTelefone, strEmail, dblDivida) Values ('%d','%s','%s','%s','%s','%s','%s') ";
            System.out.println(String.format(String.format(strQuery, objClient.getIntChave(), objClient.getStrNome(), objClient.getStrCPF(), objClient.getStrEndereco(), objClient.getStrTelefone(), objClient.getStrEmail(), objClient.getDblDivida())));
            stmt.execute(String.format(strQuery, objClient.getIntChave(), objClient.getStrNome(), objClient.getStrCPF(), objClient.getStrEndereco(), objClient.getStrTelefone(), objClient.getStrEmail(), Double.toString(objClient.getDblDivida()).replace(",", ".s")));

            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }

    public void editCliente(entCliente objClient) {
        System.out.println("edição de Cliente!!");
        try {
            Conexao.Conectar();
            Statement stmt = Conexao.conn.createStatement();

            System.out.println("Editando Cliente!");
            String strQuery = "UPDATE tblcliente SET strNome = '%s', strCPF = '%s', strEndereco = '%s', strTelefone = '%s', strEmail = '%s', dblDivida = '%s' WHERE intChave = %d ";

            stmt.execute(String.format(strQuery, objClient.getStrNome(), objClient.getStrCPF(), objClient.getStrEndereco(), objClient.getStrTelefone(), objClient.getStrEmail(), Double.toString(objClient.getDblDivida()).replace(",", ".s"), objClient.getIntChave()));

            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }

    public entCliente getCliente(int intChave) {
        entCliente Cliente = new entCliente();
        try {
            Conexao.Conectar();
            System.out.println("Coletando clientes!");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT intChave, strNome, strCPF, strEndereco, strTelefone, strEmail, dblDivida FROM tblCliente WHERE intChave = " + intChave);
            ResultSet rs = stmt.getResultSet();
            System.out.println("Coletei resultSet!");

            while (rs.next()) {

                Cliente.setIntChave(rs.getInt("intChave"));
                Cliente.setStrNome(rs.getString("strNome"));
                Cliente.setStrCPF(rs.getString("strCPF"));
                Cliente.setStrEndereco(rs.getString("strEndereco"));
                Cliente.setStrTelefone(rs.getString("strTelefone"));
                Cliente.setStrEmail(rs.getString("strEmail"));
                Cliente.setDblDivida(rs.getDouble("dblDivida"));

            }

            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return Cliente;
    }

    public List<entCliente> getClientes() {
        List<entCliente> listaClientes = new ArrayList<>();
        try {
            Conexao.Conectar();
            System.out.println("Coletando clientes!");
            Statement stmt = Conexao.conn.createStatement();
            stmt.executeQuery("SELECT intChave, strNome, strCPF, strEndereco, strTelefone, strEmail, dblDivida FROM tblCliente");
            ResultSet rs = stmt.getResultSet();
            System.out.println("Coletei resultSet!");
            entCliente objCliente;
            while (rs.next()) {
                objCliente = new entCliente();
                objCliente.setIntChave(rs.getInt("intChave"));
                objCliente.setStrNome(rs.getString("strNome"));
                objCliente.setStrCPF(rs.getString("strCPF"));
                objCliente.setStrEndereco(rs.getString("strEndereco"));
                objCliente.setStrTelefone(rs.getString("strTelefone"));
                objCliente.setStrEmail(rs.getString("strEmail"));
                objCliente.setDblDivida(rs.getDouble("dblDivida"));
                listaClientes.add(objCliente);
            }
            System.out.println("Preenchi a lista!!");
            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return listaClientes;
    }

    public void deleteCliente(int intChave) {
        try {
            Conexao.Conectar();
            System.out.println("Apagando chave: " + intChave);
            Statement stmt = Conexao.conn.createStatement();
            stmt.execute("DELETE FROM tblCliente WHERE intChave = " + intChave);

            Conexao.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }
}
