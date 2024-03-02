package gestione_bilancio.gestione_tabella;

import gestione_bilancio.gestione_dati_bilancio.*;
import javax.swing.JTable;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe che rappresenta l'ascoltatore del bottone "Rimuovi voce". 
 * Contiene i metodi che agiscono quando viene premuto il pulsante
 * @author Alice Torsani
 */
public class Listener_rimuovi implements ActionListener{
    private Elenco el;
    private JTable t;
    private Table_model tm;
    private JLabel l;

    /**
     * Costruttore della classe Listener_rimuovi
     * Deve farsi dare come parametro l'Elenco, la tabella su cui dovrà andare ad agire, il table model e la Label da modificare per il ricalcolo del totale
     * @param e Elenco
     * @param tab tabella
     * @param tm table model
     * @param l label
     */
    public Listener_rimuovi(Elenco e, JTable tab, Table_model tm, JLabel l){
        el = e;
        t = tab;
        this.tm = tm;
        this.l = l;
    }

    /**
     * Metodo che si attiva alla pressione del bottone
     * Rimuove la riga selezionata dalla tabella e aggiorna il totale
     * @param r
     */
    public void actionPerformed(ActionEvent r){

        //controlla che ci sia una riga selezionata
        if(t.getSelectedRow() != -1) {
            //rimuove la riga selezionata dal modello
            Voce_bilancio v = tm.getVoce(t.getSelectedRow());
            el.rimuoviVoce(v);

            tm.fireTableRowsDeleted(0, el.size()); //notifica la cancellazione di una riga e la fa sparire dalla tabella

            //aggiorno il totale
            double totale = tm.calcolaTotale();
            l.setText(" " + totale);

            JOptionPane.showMessageDialog(null, "La riga selezionata è stata cancellata con successo");
        }

    }

    
}
