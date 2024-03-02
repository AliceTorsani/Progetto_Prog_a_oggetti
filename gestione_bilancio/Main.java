package gestione_bilancio;

import gestione_bilancio.gestione_dati_bilancio.*;
import gestione_bilancio.gestione_tabella.Pannello;
import javax.swing.JFrame;

/**
 * Main del progetto. 
 * @author Alice Torsani
 */
public class Main {
    public static void main(String[] args){

        JFrame f = new JFrame("Gestione Bilancio");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Elenco e = new Elenco();

        Pannello p = new Pannello(e);
        f.add(p);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
}
