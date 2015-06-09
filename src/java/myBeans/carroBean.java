package myBeans;

import DataAcessLayer.daoCarro;
import Entity.entCarro;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
//import javax.mail.MessagingException;
import javax.servlet.http.Part;

@ManagedBean(name = "CarroHdl")
@RequestScoped
public class carroBean {
    
    daoCarro dal = new daoCarro();
    private entCarro objCarro;
    private Part file;
    private String fileContent;

    private List<entCarro> listaCarros;
    public carroBean() {
        objCarro = new entCarro();
    }

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

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String cadastrarCarro() {  
        try {
            this.objCarro.setIsFoto(file.getInputStream());
            this.objCarro.setTamanhoFoto((int)file.getSize());
            
            dal.insertCarro(objCarro);
            //fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
        } catch (IOException e) {
            // Error handling
        } 
        return "sucesso";
    }   

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {

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
    
    public String listarCarros()
    {
        this.listaCarros = dal.getAllCarros();
    return "listarCarros";
    }

}
