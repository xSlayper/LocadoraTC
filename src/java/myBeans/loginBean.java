/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import DataAcessLayer.daoUsuario;
import Entity.entUsuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dev
 */
@ManagedBean(name="autenticacao")
@SessionScoped
public class loginBean {
    
    private String strLogin;
    private String strSenha;
    private Boolean autenticado;
    private entUsuario objUser;

    public loginBean() {
        autenticado = false;
    }

    public String getStrLogin() {
        return strLogin;
    }

    public void setStrLogin(String strLogin) {
        this.strLogin = strLogin;
    }

    public String getStrSenha() {
        return strSenha;
    }

    public void setStrSenha(String strSenha) {
        this.strSenha = strSenha;
    }
    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public entUsuario getObjUser() {
        return objUser;
    }

    public void setObjUser(entUsuario objUser) {
        this.objUser = objUser;
    }
    
    public String validaUsuario()
    {
        daoUsuario dal = new daoUsuario();
        this.objUser = dal.getUser(strLogin, strSenha);
        
        System.out.println("autenticado? " + this.autenticado);
        
        if(this.objUser.getIntChave() > 0)
        {
            System.out.println("Autenticou!!!");
        this.autenticado = true;
        return "sucesso";
        }
        else
        {
             System.out.println("Não Autenticou!!!");
            return "falha";        
        }
  
        
    }
    
    
    public String logoff()
    {
        this.objUser = null;
        this.strLogin = null;
        this.strSenha = null;
        this.autenticado = false;
        
        return "sair";
    }
    
}