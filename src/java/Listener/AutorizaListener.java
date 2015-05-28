/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;


import myBeans.loginBean;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


/**
 *
 * @author Dev
 */
public class AutorizaListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("passei aqui!");
        FacesContext contexto = event.getFacesContext();
        String paginaAtual = contexto.getViewRoot().getViewId();
        boolean isLoginPage = paginaAtual.lastIndexOf("index.xhtml") > -1;
        
        loginBean sessao;
        sessao = (loginBean)contexto.getApplication().
                evaluateExpressionGet(contexto, "#{autenticacao}", loginBean.class);
        
        if(!isLoginPage && !sessao.getAutenticado())
        {
            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            sessao.setStrLogin(null);
            nh.handleNavigation(contexto, null, "sair");
        }
        else if(isLoginPage && sessao.getAutenticado())
        {
                        NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            nh.handleNavigation(contexto, null, "sucesso");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
       
    }

    @Override
    public PhaseId getPhaseId() {
       return PhaseId.RESTORE_VIEW;
    }
    
}
