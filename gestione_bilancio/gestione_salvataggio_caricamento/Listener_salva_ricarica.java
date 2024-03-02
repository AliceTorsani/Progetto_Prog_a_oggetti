package gestione_bilancio.gestione_salvataggio_caricamento;

import java.awt.event.*;
import gestione_bilancio.gestione_dati_bilancio.*;
import gestione_bilancio.gestione_tabella.*;
import javax.swing.*;
import java.io.*;
import java.lang.RuntimeException;

/**
 * Classe che rappresenta l'ascoltatore dei bottoni "salva bilancio" e "ricarica bilancio". 
 * Contiene i metodi che si attivano quando vengono premuti questi pulsanti
 * E' la classe responsabile del salvataggio e del caricamento del bilancio su file
 * @author Alice Torsani
 */
public class Listener_salva_ricarica implements ActionListener{
    
    private Elenco el;
    private JLabel l;
    private JTable t;

    /**
     * Costruttore
     * @param el struttura dati utilizzata
     * @param l lable in cui mostrare il totale aggiornato
     * @param t tabella da aggiornare
     */
    public Listener_salva_ricarica(Elenco el, JLabel l, JTable t){
        this.el = el;
        this.l = l;
        this.t = t;
    }

    /**
     * Metodo che si attiva alla pressione del bottone "salva bilancio" e del bottone "ricarica bilancio"
     * In base al bottone premuto si esegue il salvataggio su file, oppure il caricamento da file
     * Il file viene specificato dall'utente
     */
    public void actionPerformed(ActionEvent evt){
        //recupero il nome del pulsante premuto che ha generato l'evento
        String operazione = evt.getActionCommand();
        if(operazione.equals("salva bilancio")){
            //salvo su file
            salva(el);
        }
        else{
            if(operazione.equals("ricarica bilancio")){
                //ricarico da file
                ricarica();
            }
        }
    }

    /**
     * Funzione per il salvataggio su file (Serializzazione)
     * @param el struttura dati da salvare su file
     */
    public void salva(Elenco el){
        Object obj = JOptionPane.showInputDialog(null, "Inserire il nome del file", "Inserire il nome del file", JOptionPane.QUESTION_MESSAGE, null, null, "Nome_file.dat");
        if(obj != null){
            String nome_file = "";
            try{
                nome_file = obj.toString();
            }
            catch(RuntimeException e){
                JOptionPane.showMessageDialog(null, "Inserire un nome di file valido: formato non valido");
            }
            //Salvo su file
            ObjectOutputStream output = null;
            try{
                output = new ObjectOutputStream(new FileOutputStream(nome_file));
                //scrittura dell'oggetto
                output.writeObject(el);
                output.flush();
                //chiusura OOS
                output.close();
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Il salvataggio non è andato a buon fine");
            }
            JOptionPane.showMessageDialog(null, "Salvataggio completato");
        }
    }

    /**
     * Funzione per ricaricare da file (Deserializzazione)
     */
    public void ricarica(){
        Object obj = JOptionPane.showInputDialog(null, "Inserire il nome del file", "Inserire il nome del file", JOptionPane.QUESTION_MESSAGE, null, null, "Nome_file.dat");
        if(obj != null){
            String nome_file = "";
            try{
                nome_file = obj.toString();
            }
            catch(RuntimeException e){
                JOptionPane.showMessageDialog(null, "Inserire un nome di file valido: formato non valido");
            }
            //ricarico da file
            ObjectInputStream ois = null;
            Elenco ele = new Elenco();
            try{
                ois = new ObjectInputStream(new FileInputStream(nome_file));
                ele = (Elenco)ois.readObject(); 

                el.cancellaTutti();
                for(int i=0; i<ele.size(); i++){
                    el.aggiungiVoce((Voce_bilancio)ele.getElementAt(i));
                }

                //refresh della tabella
                t.repaint();

                Table_model tm = (Table_model)t.getModel();
                tm.fireTableRowsInserted(0, el.size());
                tm.fireTableRowsDeleted(0, el.size());

                double tot = tm.calcolaTotale();
                l.setText(" " + tot);

                JOptionPane.showMessageDialog(null, "Caricamento completato");

            }
            catch(EOFException e1){
                //abbiamo finito di leggere il file
            }
            catch(IOException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Il caricamento non è andato a buon fine");
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Il caricamento non è andato a buon fine");
            }
            try{
                if(ois != null){
                    ois.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }

}
