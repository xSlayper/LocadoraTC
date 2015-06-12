/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import DataAcessLayer.daoPagamento;
import Entity.entCliente;
import Entity.entPagamento;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Netinho
 */
@ManagedBean(name = "pagamentoHdl")
@RequestScoped
public class pagamentoBean {

    private static Boolean blnCheque;
    private static Boolean blnCartao;
    private static entPagamento objPagamento;

    
    

    public pagamentoBean() {
    }

    public Boolean getBlnCheque() {
        return blnCheque;
    }

    public Boolean getBlnCartao() {
        return blnCartao;
    }

    public entPagamento getObjPagamento() {
        return objPagamento;
    }

    public void setObjPagamento(entPagamento objPagamento) {
        this.objPagamento = objPagamento;
    }




    @PostConstruct
    public void init() {
        this.blnCartao = false;
        this.blnCheque = false;
//        if(objPagamento == null)
//        {
//            objPagamento = new entPagamento();
//        }
    }

    public void setPagamentoType(AjaxBehaviorEvent event) {        

        if (objPagamento.getIntChaveTipoPagamento() == 3) {
            this.blnCartao = true;
            this.blnCheque = false;
        } else if (objPagamento.getIntChaveTipoPagamento() == 4) {
            this.blnCartao = false;
            this.blnCheque = true;
        } else {
            this.blnCartao = false;
            this.blnCheque = false;
        }
    }
    
    public void setPagamentoDias(AjaxBehaviorEvent event) { 
        System.out.println("Numero Parcelas: " + objPagamento.getIntCartaoParcelas());
        System.out.println("Dias prazo: " + objPagamento.getIntChequeDias());
    }
    
    public String efetuarPagamento()
    {
        daoPagamento dalPg = new daoPagamento();
        dalPg.insertPagamento(objPagamento);
        System.out.println("Numero Parcelas: " + objPagamento.getIntCartaoParcelas());
        System.out.println("Dias prazo: " + objPagamento.getIntChequeDias());
        return "listarAluguel";
    }
}
