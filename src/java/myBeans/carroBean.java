package myBeans;

import DataAcessLayer.daoCarro;
import Entity.entCarro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
//import javax.mail.MessagingException;
import javax.servlet.http.Part;

@ManagedBean(name = "CarroHdl")
@ViewScoped
public class carroBean {

    daoCarro dal = new daoCarro();
    private entCarro objCarro;
    private Part file;
 

    @ManagedProperty(value = "#{editCarroBean}")
    private editCarroBean editCarroBean;

    private List<entCarro> listaCarros;

    public carroBean() {
        objCarro = new entCarro();
    }

    // <editor-fold defaultstate="collapsed" desc="Gets n Sets">
    public entCarro getObjCarro() {
        return objCarro;
    }

    public List<entCarro> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<entCarro> listaCarros) {
        this.listaCarros = listaCarros;
    }

    public void setObjCarro(entCarro objCarro) {
        this.objCarro = objCarro;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public editCarroBean getEditCarroBean() {
        return editCarroBean;
    }

    public void setEditCarroBean(editCarroBean editCarroBean) {
        this.editCarroBean = editCarroBean;
    }

    
    // </editor-fold>
    public String cadastrarCarro() {
        try {
            if (file != null) {
                System.out.println("aqui?");
                this.objCarro.setIsFoto(file.getInputStream());
                this.objCarro.setTamanhoFoto((int) file.getSize());
            } else {
                this.objCarro.setTamanhoFoto(0);
            }
            dal.insertCarro(objCarro);
        } catch (IOException e) {
            // Error handling
        }
        return "sucesso";
    }

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {

        if (value != null) {
            List<FacesMessage> msgs = new ArrayList<FacesMessage>();
            Part file = (Part) value;
            if (file.getSize() > 1024 * 1024 * 8) {
                msgs.add(new FacesMessage("A imagem deve ser menor que 8 Mb."));
            }
            if (!"image/jpeg".equals(file.getContentType())) {
                msgs.add(new FacesMessage("Não é um JPEG"));
            }
            if (!msgs.isEmpty()) {
                throw new ValidatorException(msgs);
            }
        }
    }

    public String deleteCarro(Object obj) {
        System.out.println("Entrei no deleteCar");
        dal.deleteCarro(((entCarro) obj).getIntChave());
        return "listarCarros";
    }

    public String listarCarros() {
        this.listaCarros = dal.getAllCarros();
        return "listarCarros";
    }

    public String editCarro(Object obj) {
        entCarro objToEdit = ((entCarro) obj);
        if (editCarroBean == null) {
            editCarroBean = new editCarroBean();
        }

        editCarroBean.setObjCarro(objToEdit);
        return "editarCarro.xhtml";
    }

    @PostConstruct
    public void init() {
        if (this.listaCarros == null) {
            this.listaCarros = dal.getAllCarros();
        }
    }

}
