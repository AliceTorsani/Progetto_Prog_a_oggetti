package gestione_bilancio.gestione_esportazione;

import java.awt.event.*;
import gestione_bilancio.gestione_dati_bilancio.*;

/**
 * Classe che ascolta i bottoni "esporta CSV" e "esporta testo"
 * Questa classe contiene una dimostrazione di polimorfismo. 
 * Chiama la funzione esporta ridefinita da "Esporta_CSV" e da "Esporta_testo",
 * verrà chiamata quella corrispondente in base alla classe a cui appartiene l'oggetto su 
 * cui verrà chiamato il metodo "esporta"
 * @author Alice Torsani
 */
public class Listener_CSV_testo implements ActionListener{

    private Elenco lista;

    /**
     * Costruttore
     * @param lista struttura dati che contiene le informazioni da esportare
     */
    public Listener_CSV_testo(Elenco lista){
        this.lista = lista;
    }

    /**
     * Metodo che si attiva alla pressione dei bottoni "esporta CSV" e "esporta testo"
     * In base al bottone premuto verrà attuata l'esportazione corrispondente
     */
    public void actionPerformed(ActionEvent e){
        //recupero il nome del pulsante premuto che ha generato l'evento
        String operazione = e.getActionCommand();
        Esportazione exp;
        if(operazione.equals("esporta CSV")){
            exp = new Esporta_CSV(); //regola di conformità
            exp.esporta(lista); //polimorfismo
        }
        else{
            if(operazione.equals("esporta testo")){
                exp = new Esporta_testo(); //regola di conformità
                exp.esporta(lista); //polimorfismo
            }
        }
    }
    
}
