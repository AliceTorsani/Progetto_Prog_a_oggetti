package gestione_bilancio.gestione_tabella;

import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import gestione_bilancio.gestione_dati_bilancio.*;
import gestione_bilancio.gestione_esportazione.Listener_CSV_testo;
import gestione_bilancio.gestione_menu.*;
import gestione_bilancio.gestione_salvataggio_caricamento.Listener_salva_ricarica;
import java.awt.BorderLayout;

/**
 * Classe che rappresenta il pannello. 
 * Il pannello ha il compito di ascoltare il bottone "Ricerca" e il bottone "Successivo"
 * @author Alice Torsani
 */
public class Pannello extends JPanel implements ActionListener{
    
    private Elenco lista;
    private JTextField txt1, txt2;
    private JTable t;

    /**
     * Costruttore
     * @param lista lista di voci
     */
    public Pannello(Elenco lista){

        this.setLayout(new BorderLayout());

        this.lista = lista;

        LocalDate date = LocalDate.now();
        Voce_bilancio voc = new Voce_bilancio(date, "inserire descrizione", 0);
        lista.aggiungiVoce(voc);

        JLabel lNum = new JLabel("0");

        Table_model tm = new Table_model(lista, lNum);

        this.t = new JTable(tm){

            /**
             * Ritornare la classe di ogni colonna permetterà a
             * renderers ed editors differenti di essere utilizzati basandosi sulla classe
             * @param column indice di colonna
             * @return la classe della colonna
             */
            public Class getColumnClass(int column){

                for (int row = 0; row < getRowCount(); row++){
                
                    Object o = getValueAt(row, column);

                    if (o != null)
                        return o.getClass();
                }

                return Object.class;
            }
        };


        JScrollPane scrollPane = new JScrollPane(t);
        JPanel ptab = new JPanel();
        ptab.setLayout(new BorderLayout());
        JLabel lTot = new JLabel("TOTALE:");
        ptab.add(scrollPane, BorderLayout.CENTER); //aggiunge lo scroller e la tabella in automatico
        ptab.add(t.getTableHeader(), BorderLayout.NORTH);
        JPanel panTot = new JPanel(); //Flowlayout di default
        panTot.setLayout(new BorderLayout());
        panTot.add(lTot, BorderLayout.WEST);
        panTot.add(lNum, BorderLayout.CENTER);

        Menu_panel bar = new Menu_panel(t, lista, lNum, tm); //menuBar personalizzata
        panTot.add(bar, BorderLayout.EAST);

        JPanel pSave = new JPanel();
        JButton salva = new JButton("salva bilancio");
        JButton ricarica = new JButton("ricarica bilancio");
        salva.addActionListener(new Listener_salva_ricarica(lista, lNum, t));
        ricarica.addActionListener(new Listener_salva_ricarica(lista, lNum, t));
        pSave.add(salva);
        pSave.add(ricarica);
        panTot.add(pSave,BorderLayout.SOUTH);

        ptab.add(panTot, BorderLayout.SOUTH);
        this.add(ptab, BorderLayout.NORTH);

        JPanel pannelloCentro = new JPanel();

        this.txt1 = new JTextField(25);
        JButton b = new JButton("Ricerca");
        this.txt2 = new JTextField(35);
        this.txt2.setEditable(false);
        b.addActionListener(this);
        pannelloCentro.add(this.txt1);

        JPanel pannelloSud = new JPanel();

        pannelloCentro.add(b);

        JButton successivo = new JButton("Successivo");
        successivo.addActionListener(this);
        pannelloCentro.add(successivo);

        pannelloCentro.add(this.txt2);

        JButton aggiungi = new JButton("Aggiungi voce");
        aggiungi.addActionListener(new Listener_aggiungi(lista, tm));
        pannelloSud.add(aggiungi);

        JButton rimuovi = new JButton("Rimuovi voce");
        rimuovi.addActionListener(new Listener_rimuovi(lista, t, tm, lNum));
        pannelloSud.add(rimuovi);

        JButton exp_CSV = new JButton("esporta CSV");
        JButton exp_testo = new JButton("esporta testo");
        exp_CSV.addActionListener(new Listener_CSV_testo(lista));
        exp_testo.addActionListener(new Listener_CSV_testo(lista));
        pannelloSud.add(exp_CSV);
        pannelloSud.add(exp_testo);

        this.add(pannelloCentro, BorderLayout.CENTER);
        this.add(pannelloSud, BorderLayout.SOUTH);

    }

    /**
     * Codice eseguito quando viene premuto il bottone di "Ricerca" oppure quando viene premuto "Successivo".
     * Viene eseguita la porzione di codice corrispondente al bottone premuto. 
     * Caso "Ricerca": Ricerca la chiave richiesta nell'elenco di voci e ritorna i dati del movimento trovato, se esistente.
     * La voce trovata viene selezionata.  
     * Se non trova nulla, ritorna un messaggio di errore.
     * Caso "Successivo": agisce come il bottone "Ricerca", ma considera solamente le voci a partire dalla voce successiva 
     * a quella precedentemente selezionata
     */
    public void actionPerformed(ActionEvent arg0){
        //recupero il nome del pulsante premuto che ha generato l'evento
        String operazione = arg0.getActionCommand();
        if(operazione.equals("Ricerca")){
            String key = this.txt1.getText();
            if(key.equals("")){
                this.txt2.setText("INSERIRE DEL TESTO PRIMA DI EFFETTUARE LA RICERCA");
                return;
            }
            for(int i=0; i<this.lista.size(); i++){
                if(lista.getElementAt(i).getData().toString().contains(key) ||
                    lista.getElementAt(i).getDescrizione().contains(key) || 
                    String.valueOf(lista.getElementAt(i).getAmmontare()).contains(key)){
                        this.txt2.setText("Trovato " + lista.getElementAt(i).getData() + " " + lista.getElementAt(i).getDescrizione() + " " 
                        + lista.getElementAt(i).getAmmontare());
                        //seleziono la riga corrispondente
                        t.setRowSelectionInterval(i, i);
                        return;
                    }
                    this.txt2.setText("NESSUNA CORRISPONDENZA TROVATA");
            }

        }
        else{
            if(operazione.equals("Successivo")){
                String key = this.txt1.getText();
                if(key.equals("")){
                    this.txt2.setText("INSERIRE DEL TESTO PRIMA DI EFFETTUARE LA RICERCA");
                    return;
                }
                int rowIndex = t.getSelectedRow(); //riga selezionata dalla ricerca precedente
                for(int i = (rowIndex+1); i<this.lista.size(); i++){
                    if(lista.getElementAt(i).getData().toString().contains(key) ||
                        lista.getElementAt(i).getDescrizione().contains(key) || 
                        String.valueOf(lista.getElementAt(i).getAmmontare()).contains(key)){
                            this.txt2.setText("Trovato " + lista.getElementAt(i).getData() + " " + lista.getElementAt(i).getDescrizione() + " " 
                            + lista.getElementAt(i).getAmmontare());
                            //seleziono la riga corrispondente
                            t.setRowSelectionInterval(i, i);
                            return;
                        }
                        this.txt2.setText("NESSUNA CORRISPONDENZA TROVATA");

                }
            }
        }
    }


}
