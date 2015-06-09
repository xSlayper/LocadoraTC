/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import DataAcessLayer.daoCarro;
import DataAcessLayer.daoCliente;
import Entity.entCarro;
import Entity.entCliente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;

/**
 *
 * @author Netinho
 */
@ManagedBean(name = "aluguelCarroHdl")
@RequestScoped
public class aluguelBean {

    daoCarro dalCarro = new daoCarro();
    daoCliente dalCli = new daoCliente();
    private List<entCarro> listaCarros;
    static entCarro CarroSelected;
    private List<entCliente> listaClientes;
    static entCliente ClienteSelected;

    private double dblValorAluguel;
    private int intDias;
    private int intChaveCarro;
    private int intChavePessoa;

    public int getIntChaveCarro() {
        return intChaveCarro;
    }

    public void setIntChaveCarro(int intChaveCarro) {
        this.intChaveCarro = intChaveCarro;
    }

    public int getIntChavePessoa() {
        return intChavePessoa;
    }

    public void setIntChavePessoa(int intChavePessoa) {
        this.intChavePessoa = intChavePessoa;
    }

    public double getDblValorAluguel() {
        return dblValorAluguel;
    }

    public void setDblValorAluguel(double dblValorAluguel) {
        this.dblValorAluguel = dblValorAluguel;
    }

    public int getIntDias() {
        return intDias;
    }

    public void setIntDias(int intDias) {
        this.intDias = intDias;

    }

    public void ajaxLoadCarro(AjaxBehaviorEvent event) {
        // this.dblValorAluguel = intDias * CarroSelected.getDblDiaria();
        System.out.println("Passei aqui!");

    }

    public void calculateDiaria(AjaxBehaviorEvent event) {
        this.dblValorAluguel = intDias * CarroSelected.getDblDiaria();
        //this.dblValorAluguel = intDias * ClienteSelected.getIntChave();
        //System.out.println("Calcular diaria! + " + temp);
    }

    public void postCliente(AjaxBehaviorEvent event) {
        for (entCliente listaCliente : this.listaClientes) {
            if (listaCliente.getIntChave() == this.intChavePessoa) {
                this.ClienteSelected = listaCliente;
                break;
            }
        }
        System.out.println("Passei pelo postCliente: " + ClienteSelected.getIntChave());
    }

    public void postCarro(AjaxBehaviorEvent event) {
        for (entCarro listaCarro : this.listaCarros) {
            if (listaCarro.getIntChave() == this.intChaveCarro) {
                this.CarroSelected = listaCarro;
                break;
            }
        }
        System.out.println("Passei pelo postCarro!!!s");
    }

    public aluguelBean() {
        listaCarros = dalCarro.getAllCarros();
        listaClientes = dalCli.getClientes();
    }

    public List<entCarro> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<entCarro> listaCarros) {
        this.listaCarros = listaCarros;
    }

    public List<entCliente> getListaCliente() {
        return listaClientes;
    }

    public void setListaCliente(List<entCliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public entCarro getCarroSelected() {
        return CarroSelected;
    }

    public void setCarroSelected(entCarro CarroSelected) {
        this.CarroSelected = CarroSelected;
    }

    public entCliente getClienteSelected() {
        return ClienteSelected;
    }

    public void setClienteSelected(entCliente ClienteSelected) {
        this.ClienteSelected = ClienteSelected;
    }

    public void tetando123() {
        //System.out.println("Cliente: " + this.intTeste);
    }

    public String loadAlguel() {

        if(listaCarros.size() > 0)
        {
        CarroSelected = listaCarros.get(0);
        }
        if(listaClientes.size()>0)
        {
        ClienteSelected = listaClientes.get(0);
        }
        return "novoAlugel";
    }
    
    
    public String registrarAluguel()
    {
        
        return "sucesso";
    }
}
