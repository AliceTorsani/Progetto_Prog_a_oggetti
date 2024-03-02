package gestione_bilancio.gestione_esportazione;

import gestione_bilancio.gestione_dati_bilancio.*;
import java.io.*;
import java.time.LocalDate;
import javax.swing.*;

/**
 * Sottoclasse concreta di "Esportazione"
 * ridefinisce il metodo astratto "esporta". 
 * Usata per applicare l'ereditarietà, estende la classe astratta "Esportazione"
 * Verrà poi usata per realizzare il polimorfismo
 * Contiene il metodo per l'esportazione nel formato testo
 * @author Alice Torsani
 */
public class Esporta_testo extends Esportazione{

    /**
     * Costruttore
     */
    public Esporta_testo(){
        super();
    }

    /**
     * Metodo per l'esportazione in formato testo
     * @param lista struttura dati che contiene le voci da scrivere su file
     */
    @Override
    public void esporta(Elenco lista){
        FileWriter fout = null;
        try{
            fout = new FileWriter("testo.txt");
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Apertura file fallita");
        }
        try{
            for(int i=0; i<lista.size(); i++){
                Voce_bilancio voce = lista.getElementAt(i);
                LocalDate data = voce.getData();
                String descr = voce.getDescrizione();
                double ammontare = voce.getAmmontare();
                String data_string = data.toString();
                String buffer = null;
                buffer = data_string;
                fout.write(buffer);
                fout.write("\t");
                buffer = descr;
                fout.write(buffer);
                fout.write("\t");
                buffer = Double.toString(ammontare);
                fout.write(buffer);
                fout.write("\n");
            }
            fout.close();
            JOptionPane.showMessageDialog(null, "Esportazione in formato testo avvenuta con successo nel file 'testo.txt'");
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Errore di scrittura");
        }

    }
    
}
