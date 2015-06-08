package myBeans;

import DataAcessLayer.daoCliente;
import Entity.entCliente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="ClienteHdl")
@RequestScoped
public class clienteBean {
    private entCliente objCliente;
    private List<entCliente> listaCliente = null;
    daoCliente dalCli = new daoCliente();
   
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
    
    
    @PostConstruct
    public void init()
    {
        if(this.listaCliente == null)
        {                        
        }
    }
}
