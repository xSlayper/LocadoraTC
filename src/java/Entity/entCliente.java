package Entity;

public class entCliente {
    private int intChave;
    private String strNome;
    private String strCPF;
    private String strEndereco;
    private String strTelefone;
    private String strEmail;
    private double dblDivida;

    public entCliente() {
    }

    public String getStrNome() {
        return strNome;
    }

    public void setStrNome(String strNome) {
        this.strNome = strNome;
    }

    
    public int getIntChave() {
        return intChave;
    }

    public void setIntChave(int intChave) {
        this.intChave = intChave;
    }

    public String getStrCPF() {
        return strCPF;
    }

    public void setStrCPF(String strCPF) {
        this.strCPF = strCPF;
    }

    public String getStrEndereco() {
        return strEndereco;
    }

    public void setStrEndereco(String strEndereco) {
        this.strEndereco = strEndereco;
    }

    public String getStrTelefone() {
        return strTelefone;
    }

    public void setStrTelefone(String strTelefone) {
        this.strTelefone = strTelefone;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public double getDblDivida() {
        return dblDivida;
    }

    public void setDblDivida(double dblDivida) {
        this.dblDivida = dblDivida;
    }
    
    
}
