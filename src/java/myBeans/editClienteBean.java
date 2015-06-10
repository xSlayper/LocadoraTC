/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import DataAcessLayer.daoCliente;
import Entity.entCliente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Netinho
 */
@ManagedBean(name = "editClienteHdl")
@RequestScoped
public class editClienteBean {

    private static entCliente objCliente;
    daoCliente dalCli = new daoCliente();
    // <editor-fold defaultstate="collapsed" desc="Gets n Sets">
    public editClienteBean() {
    }

    public entCliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(entCliente objCliente) {
        this.objCliente = objCliente;
    }

    // </editor-fold>
    
    public String executeEdit()
    {
        dalCli.editCliente(objCliente);
        return "listarClientes";
    }
}
