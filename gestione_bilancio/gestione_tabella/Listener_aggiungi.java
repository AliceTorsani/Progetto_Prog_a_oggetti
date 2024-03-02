package gestione_bilancio.gestione_tabella;

import gestione_bilancio.gestione_dati_bilancio.*;
import java.awt.event.*;
import java.time.LocalDate;


/**
 * Classe che rappresenta l'ascoltatore del bottone "Aggiungi voce". 
 * Contiene i metodi che agiscono quando viene premuto il pulsante
 * @author Alice Torsani
 */
public class Listener_aggiungi implements ActionListener{
    private Elenco el;
    private Table_model tm;

    /**
     * Costruttore della classe Listener_aggiungi
     * Deve farsi dare come parametro l'Elenco e il table model
     * @param e Elenco
     * @param tm table model
     */
    public Listener_aggiungi(Elenco e, Table_model tm){
        el = e;
        this.tm = tm;
    }

    /**
     * Metodo che si attiva alla pressione del bottone
     * Aggiunge una voce all'elenco e la fa comparire nella tabella 
     * La data di default proposta è quella odierna
     */
    public void actionPerformed(ActionEvent a){
        LocalDate date = LocalDate.now();
        Voce_bilancio v = new Voce_bilancio(date, "Inserire descrizione", 0);
        el.aggiungiVoce(v);
        tm.fireTableRowsInserted(0, el.size()); //notifica l'aggiunta di una riga e la fa comparire in tabella
    }

}
