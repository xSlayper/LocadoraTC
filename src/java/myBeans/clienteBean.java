package myBeans;

import DataAcessLayer.daoCliente;
import Entity.entCliente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="ClienteHdl")
@ViewScoped
public class clienteBean {
    private entCliente objCliente;
    private List<entCliente> listaCliente = null;
    daoCliente dalCli = new daoCliente();
       
    @ManagedProperty(value="#{editClienteBean}")
    private editClienteBean editClienteBean;  
    
    
    // <editor-fold defaultstate="collapsed" desc="Gets n Sets">
    public clienteBean() {
        this.objCliente = new entCliente();
    }

    public List<entCliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<entCliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public entCliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(entCliente objCliente) {
        this.objCliente = objCliente;
        this.objCliente.setDblDivida(0);
    }
    
        public editClienteBean getEditClienteBean() {
        return editClienteBean;
    }

    public void setEditClienteBean(editClienteBean editClienteBean) {
        this.editClienteBean = editClienteBean;
    }
    // </editor-fold>
    
    public String addCliente()
    {
        System.out.println("Inserindo o cliente: '" + objCliente.getStrNome() + "' no banco!");        
        dalCli.insertCliente(objCliente);
        return "sucesso";
    }
    
       
    public String listarClientes()
    {
        this.listaCliente = dalCli.getClientes();
        return "listarClientes";
    }
    
        public String deleteCliente(Object obj)
    {
        System.out.println("Entrei no delteCliente");
        dalCli.deleteCliente(((entCliente)obj).getIntChave());
        return "listarClientes";
    }
        
        public String editCliente(Object obj)
        {
            entCliente objToEdit = ((entCliente)obj);
            if(editClienteBean == null)
            {
                editClienteBean = new editClienteBean();
            }
            
            editClienteBean.setObjCliente(objToEdit);
            return "editarCliente.xhtml";
        }
    
    @PostConstruct
    public void init()
    {
        if(this.listaCliente == null)
        {     
            this.listaCliente = dalCli.getClientes();
        }
    }
}
