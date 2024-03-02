package gestione_bilancio.gestione_dati_bilancio;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe che rappresenta una voce di bilancio. 
 * @author Alice Torsani
 */
public class Voce_bilancio implements Serializable{
    
    private LocalDate data;
    private String descrizione;
    private double ammontare;

    /**
     * Costruttore
     * @param data data del movimento
     * @param descrizione descrizione: entrata o spesa
     * @param ammontare somma di denaro 
     */
    public Voce_bilancio(LocalDate data, String descrizione, double ammontare){
        this.data = data;
        this.descrizione = descrizione;
        this.ammontare = ammontare;
    }

    /**
     * per impostare la data
     * @param data
     */
    public void setData(LocalDate data){
        this.data = data;
    }

    /**
     * per imostare la descrizione
     * @param descrizione
     */
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }

    /**
     * per impostare l'ammontare
     * @param ammontare
     */
    public void setAmmontare(double ammontare){
        this.ammontare = ammontare;
    }

    /**
     * per ottenere la data
     * @return data
     */
    public LocalDate getData(){
        return data;
    }

    /**
     * per ottenere la descrizione
     * @return descrizione
     */
    public String getDescrizione(){
        return descrizione;
    }

    /**
     * per ottenere l'ammontare
     * @return ammontare
     */
    public double getAmmontare(){
        return ammontare;
    }
}
