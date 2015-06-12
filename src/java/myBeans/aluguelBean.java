/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import DataAcessLayer.daoAluguel;
import DataAcessLayer.daoCarro;
import DataAcessLayer.daoCliente;
import Entity.entAluguel;
import Entity.entCarro;
import Entity.entCliente;
import Entity.entPagamento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Netinho
 */
@ManagedBean(name = "aluguelCarroHdl")
@ViewScoped
public class aluguelBean {

    daoCarro dalCarro = new daoCarro();
    daoCliente dalCli = new daoCliente();
    daoAluguel dalAluguel = new daoAluguel();
    private List<entCarro> listaCarros;
    static entCarro CarroSelected;
    private List<entCliente> listaClientes;
    static entCliente ClienteSelected;

    private List<entAluguel> listaAlugueis;
    
    private double dblValorAluguel;
    private int intDias;
    private int intChaveCarro;
    private int intChavePessoa;

    @ManagedProperty(value = "#{pagamentoBean}")
    private pagamentoBean pgmtoBean;

    public pagamentoBean getPgmtoBean() {
        return pgmtoBean;
    }

    public void setPgmtoBean(pagamentoBean pgmtoBean) {
        this.pgmtoBean = pgmtoBean;
    }

    public List<entAluguel> getListaAlugueis() {
        return listaAlugueis;
    }

    public void setListaAlugueis(List<entAluguel> listaAlugueis) {
        this.listaAlugueis = listaAlugueis;
    }

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

        System.out.println("Passou pelo postCliente!!!!");

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
        return "novoAlugel";
    }

    @PostConstruct
    public void init() {
        listaCarros = dalCarro.getAllCarros();
        listaClientes = dalCli.getClientes();
        CarroSelected = listaCarros.get(0);
        ClienteSelected = listaClientes.get(0);
        intChavePessoa = listaClientes.get(0).getIntChave();
        intChaveCarro = listaCarros.get(0).getIntChave();
        listaAlugueis = dalAluguel.getAlugueis();

    }

    public String registrarAluguel() {
        entAluguel objAluguel = new entAluguel();
        objAluguel.setIntChaveCarro(intChaveCarro);
        objAluguel.setIntChaveCliente(intChavePessoa);
        objAluguel.setIntDias(intDias);
        objAluguel.setDblValorTotal(dblValorAluguel);
        objAluguel.setIntChavePagamento(0);
        dalAluguel.insertAluguel(objAluguel);
        return "sucesso";
    }

    public String fazerPagamento(Object obj) {
        entAluguel objToPay = (entAluguel)obj;
        entPagamento objPayment = new entPagamento();
        
        objPayment.setIntChaveAluguel(objToPay.getIntChave());
       
        if(pgmtoBean == null)
        {
            pgmtoBean = new pagamentoBean();
        }
        pgmtoBean.setObjPagamento(objPayment);
        return "pagarAluguel";
    }
}
