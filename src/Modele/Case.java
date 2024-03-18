package Modele;
import java.io.Serializable;

public class Case implements Serializable {
    private static final long serialVersionUID = -145130772363511365L;
    private int value;
    private boolean aEteFusione;
    public boolean isaEteFusione() {
        return aEteFusione;
    }
    public void setaEteFusione(boolean aEteModifie) {
        this.aEteFusione = aEteModifie;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Case(int value){
        this.value = value;
        this.aEteFusione = false;
    }
    public String toString(){
        return new Integer(value).toString();
    }
}
