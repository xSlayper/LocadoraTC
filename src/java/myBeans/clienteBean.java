package myBeans;

import DataAcessLayer.daoCliente;
import Entity.entCliente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="ClienteHdl")
@RequestScoped
public class clienteBean {
    private entCliente objCliente;  
   
    public clienteBean() {
        this.objCliente = new entCliente();
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
        daoCliente dalCli = new daoCliente();
        dalCli.insertCliente(objCliente);
        return "sucesso";
    }
}
