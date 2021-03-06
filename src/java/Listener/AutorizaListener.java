package Listener;

import myBeans.loginBean;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class AutorizaListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
 
        FacesContext contexto = event.getFacesContext();
        String paginaAtual = contexto.getViewRoot().getViewId();
        boolean isLoginPage = paginaAtual.lastIndexOf("index.xhtml") > -1;
        
        loginBean sessao;
        sessao = (loginBean)contexto.getApplication().
                evaluateExpressionGet(contexto, "#{autenticacao}", loginBean.class);
        
        if(!isLoginPage && !sessao.getAutenticado())
        {
            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            sessao.setObjUser(null);
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
