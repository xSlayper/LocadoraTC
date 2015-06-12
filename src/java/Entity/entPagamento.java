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
public class entPagamento {
    private int intChave;
    private int intChaveTipoPagamento;
    private int intChaveAluguel;
    private int intChequeDias;
    private int intCartaoParcelas;

    public entPagamento() {
    }

    public int getIntChave() {
        return intChave;
    }

    public void setIntChave(int intChave) {
        this.intChave = intChave;
    }

    public int getIntChaveTipoPagamento() {
        return intChaveTipoPagamento;
    }

    public void setIntChaveTipoPagamento(int intChaveTipoPagamento) {
        this.intChaveTipoPagamento = intChaveTipoPagamento;
    }

    public int getIntChaveAluguel() {
        return intChaveAluguel;
    }

    public void setIntChaveAluguel(int intChaveAluguel) {
        this.intChaveAluguel = intChaveAluguel;
    }

    public int getIntChequeDias() {
        return intChequeDias;
    }

    public void setIntChequeDias(int intChequeDias) {
        this.intChequeDias = intChequeDias;
    }

    public int getIntCartaoParcelas() {
        return intCartaoParcelas;
    }

    public void setIntCartaoParcelas(int intCartaoParcelas) {
        this.intCartaoParcelas = intCartaoParcelas;
    }
    
    
}
