/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.InputStream;
import java.sql.Blob;

/**
 *
 * @author Netinho
 */
public class entCarro {
    private int intChave;
    private String strModelo;
    private String strMarca;
    private int intAno;
    private String strPlaca;
    private double dblDiaria;
    private Blob blbFoto;
    private InputStream isFoto;
    private int tamanhoFoto;

    public entCarro() {
    }

    public int getIntChave() {
        return intChave;
    }

    public void setIntChave(int intChave) {
        this.intChave = intChave;
    }

    public String getStrModelo() {
        return strModelo;
    }

    public void setStrModelo(String strModelo) {
        this.strModelo = strModelo;
    }

    public String getStrMarca() {
        return strMarca;
    }

    public void setStrMarca(String strMarca) {
        this.strMarca = strMarca;
    }

    public int getIntAno() {
        return intAno;
    }

    public void setIntAno(int intAno) {
        this.intAno = intAno;
    }

    public String getStrPlaca() {
        return strPlaca;
    }

    public void setStrPlaca(String strPlaca) {
        this.strPlaca = strPlaca;
    }

    public double getDblDiaria() {
        return dblDiaria;
    }

    public void setDblDiaria(double dblDiaria) {
        this.dblDiaria = dblDiaria;
    }

    public Blob getBlbFoto() {
        return blbFoto;
    }

    public void setBlbFoto(Blob blbFoto) {
        this.blbFoto = blbFoto;
       // this.tamanhoFoto = ((int)blbFoto.length());
    }

    public InputStream getIsFoto() {
        return isFoto;
    }

    public void setIsFoto(InputStream isFoto) {
        this.isFoto = isFoto;
    }

    public int getTamanhoFoto() {
        return tamanhoFoto;
    }

    public void setTamanhoFoto(int tamanhoFoto) {
        this.tamanhoFoto = tamanhoFoto;
    }


    
    
}
