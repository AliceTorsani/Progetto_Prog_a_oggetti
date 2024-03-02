package gestione_bilancio.gestione_tabella;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.table.AbstractTableModel;
import gestione_bilancio.gestione_dati_bilancio.*;
import javax.swing.*;


/**
 * Classe che rappresenta il modello di dati. 
 * @author Alice Torsani
 */
public class Table_model extends AbstractTableModel{
    
    private Elenco arrayVoci;
    private String[] ColName = {"Data", "Descrizione", "Ammontare"};
    private JLabel l;

    /**
     * Costruttore
     * @param el elenco di voci
     * @param l etichetta che contiene il totale
     */
    public Table_model(Elenco el, JLabel l){
        this.arrayVoci = el;
        this.l = l;
    }

    /**
     * ritorna il numero di colonne
     * @return il numero di colonne
     */
    public int getColumnCount(){
        return 3;
    }
    /**
     * ritorna il numero di righe
     * @return il numero di righe
     */
    public int getRowCount(){
        return arrayVoci.size();
    }

    /**
     * Metodo per la ricerca di una voce
     * @param row riga
     * @param col colonna
     * @return il campo richiesto della voce in base agli indici di riga e colonna
     */
    public Object getValueAt(int row, int col){
        switch (col){
            case 0: return arrayVoci.getElementAt(row).getData().toString();
            case 1: return arrayVoci.getElementAt(row).getDescrizione();
            case 2: return arrayVoci.getElementAt(row).getAmmontare();
        }
        return "";
    }

    /**
     * Restituisce il nome della colonna
     * @param col indice di colonna
     * @return il nome della colonna corrispondente all'indice di colonna
     */
    public String getColumnName(int col){
        return ColName[col];
    }

    /**
     * Restituisce il tipo dei valori
     * serve per allineare correttamente il contenuto delle celle della tabella
     * @param col indice di colonna
     * @return il tipo
     */
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }

    /**
     * Specifica se le celle sono editabili
     * Ogni cella può essere modificata
     * @param row indice di riga
     * @param col indice di colonna
     * @return true
     */
    public boolean isCellEditable(int row, int col){     
        return true;
    }

    /**
     * Metodo per gestire le modifiche dell'utente
     * @param value nuovo valore da impostare
     * @param row indice di riga
     * @param col indice di colonna
     */
    public void setValueAt(Object value, int row, int col){
        Voce_bilancio voce = (Voce_bilancio)arrayVoci.getElementAt(row);

        if(col == 0){
            //modifica la data
            //se la stringa passata tramite value non corrisponde al formato scelto da LocalDate, verrà lanciata una eccezione.
            try{
                voce.setData(LocalDate.parse((String)value));
            }
            catch(DateTimeParseException e1){
                JOptionPane.showMessageDialog(null, "Inserire una data valida: formato non valido");
            }
        }
        if(col == 1){
            //modifica la descrizione
            voce.setDescrizione(((String)value).toString());
        }
        if(col == 2){
            //modifica l'ammontare
            voce.setAmmontare(((Double)value).doubleValue());
            //ora aggiorno il totale calcolando il nuovo valore totale e lo imposto
            double tot = calcolaTotale();
            l.setText(" " + tot);
        }

        //notifica il cambiamento
        fireTableDataChanged();
        //per aggiornare il contenuto della cella
        fireTableCellUpdated(row, col);
    }

    /**
     * Metodo per calcolare il totale della colonna dell'ammontare
     * @return il totale
     */
    public double calcolaTotale(){
        double totale = 0;
        for(int i = 0; i<arrayVoci.size(); i++){
            double val = arrayVoci.getElementAt(i).getAmmontare();
            totale = totale + val;
        }

        return totale;
    }

    /**
     * Metodo per rimuovere una riga
     * @param row indice della riga da rimuovere
     */
    public void removeRow(int row){
        removeRow(row);
    }

    /**
     * Ritorna una voce di bilancio dato l'indice di riga
     * @param row indice di riga
     * @return la voce di bilancio corrispondente
     */
    public Voce_bilancio getVoce(int row){
        return arrayVoci.getElementAt(row);
    }

}
