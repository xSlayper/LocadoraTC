/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import DataAcessLayer.daoCarro;
import Entity.entCarro;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author Netinho
 */
@ManagedBean(name = "editCarroHdl")
@RequestScoped
public class editCarroBean {

    private static entCarro objCarro;
    daoCarro dalCli = new daoCarro();
    private Part file;

    // <editor-fold defaultstate="collapsed" desc="Gets n Sets">

    public editCarroBean() {
    }

    public entCarro getObjCarro() {
        return objCarro;
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

    // </editor-fold>
    public String executeEdit() {
        try {
            if (file != null) {
                System.out.println("Fudeu!!!!!");
                this.objCarro.setIsFoto(file.getInputStream());
                this.objCarro.setTamanhoFoto((int) file.getSize());
            }
            else
            {
                this.objCarro.setIsFoto(objCarro.getBlbFoto().getBinaryStream());
                this.objCarro.setTamanhoFoto((int)objCarro.getBlbFoto().length());
            }
            dalCli.editCarro(objCarro);
        } catch (IOException ex) {
            Logger.getLogger(editCarroBean.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
                    Logger.getLogger(editCarroBean.class.getName()).log(Level.SEVERE, null, ex);
                }
        return "listarCarros";
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
}
