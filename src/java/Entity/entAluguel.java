/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Netinho
 */
public class entAluguel {
    private int intChave;
    private int intChaveCliente;
    private int intChaveCarro;
    private int intDias;
    private double dblValorTotal;
    private int intChavePagamento;
    
    private String strNomeCliente;
    private String strMarcaModeloCarro;
    private String strPago;

    public entAluguel() {
    }

    public int getIntChave() {
        return intChave;
    }

    public void setIntChave(int intChave) {
        this.intChave = intChave;
    }

    public int getIntChaveCliente() {
        return intChaveCliente;
    }

    public void setIntChaveCliente(int intChaveCliente) {
        this.intChaveCliente = intChaveCliente;
    }

    public int getIntChaveCarro() {
        return intChaveCarro;
    }

    public void setIntChaveCarro(int intChaveCarro) {
        this.intChaveCarro = intChaveCarro;
    }

    public int getIntDias() {
        return intDias;
    }

    public void setIntDias(int intDias) {
        this.intDias = intDias;
    }

    public double getDblValorTotal() {
        return dblValorTotal;
    }

    public void setDblValorTotal(double dblValorTotal) {
        this.dblValorTotal = dblValorTotal;
    }

    public int getIntChavePagamento() {
        return intChavePagamento;
    }

    public void setIntChavePagamento(int intChavePagamento) {
        this.intChavePagamento = intChavePagamento;
    }

    public String getStrNomeCliente() {
        return strNomeCliente;
    }

    public void setStrNomeCliente(String strNomeCliente) {
        this.strNomeCliente = strNomeCliente;
    }

    public String getStrMarcaModeloCarro() {
        return strMarcaModeloCarro;
    }

    public void setStrMarcaModeloCarro(String strMarcaModeloCarro) {
        this.strMarcaModeloCarro = strMarcaModeloCarro;
    }

    public String getStrPago() {
        return strPago;
    }

    public void setStrPago(String strPago) {
        this.strPago = strPago;
    }
    
    
}
