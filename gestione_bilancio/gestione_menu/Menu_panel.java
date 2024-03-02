package gestione_bilancio.gestione_menu;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import gestione_bilancio.gestione_tabella.*;
import gestione_bilancio.gestione_dati_bilancio.*;
import javax.swing.*;

/**
 * Classe per la gestione del menù. 
 * Il menù permette di selezionare se visualizzare le informazioni di un singolo giorno,
 * di una settimana, di un mese oppure di un anno
 * Non è un pannello, ma una JMenuBar personalizzata che poi verrà aggiunta al pannello principale
 * @author Alice Torsani
 */
public class Menu_panel extends JMenuBar implements ActionListener{

    private JTable t;
    private Elenco el;
    private JLabel l;
    private Table_model tm;

    /**
     * Costruttore
     * @param t tabella usata per impostare il filtro sulle righe
     * @param el struttura dati che contiene i dati della tabella su cui filtrerò
     * @param l etichetta che contiene il totale
     * @param tm tableModel
     */
    public Menu_panel(JTable t, Elenco el, JLabel l, Table_model tm){

        this.t = t;
        this.el = el;
        this.l = l;
        this.tm = tm;

        JMenuItem m11 = new JMenuItem("Seleziona giorno");
        JMenuItem m12 = new JMenuItem("Seleziona settimana");
        JMenuItem m13 = new JMenuItem("Seleziona mese");
        JMenuItem m14 = new JMenuItem("Seleziona anno");
        JMenuItem m15 = new JMenuItem("Visualizza tutto");
        JMenu m1 = new JMenu("Menù: Visualizza informazioni di...");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m1.add(m15);
        this.add(m1);

        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m14.addActionListener(this);
        m15.addActionListener(this);

    }

    /**
     * In base alla voce di menù selezionata verrà applicato il filtro corrispondente
     */
    public void actionPerformed(ActionEvent ev){
        if(ev.getActionCommand().equals("Seleziona giorno")){
            Object obj = JOptionPane.showInputDialog(null, "Scrivere la data", "Seleziona giorno", JOptionPane.QUESTION_MESSAGE, null, null, LocalDate.now().toString());
            //in obj c'è la data selezionata dall'utente
            //converto obj in LocalDate e faccio i controlli di formato, poi filtro i risultati in tabella
            //controllo il formato
            if(obj != null){
                LocalDate ld = LocalDate.now();//inizializzo con la data odierna
                try{
                    ld = LocalDate.parse((String)obj);
                }
                catch(DateTimeParseException ex){
                    JOptionPane.showMessageDialog(null, "Inserire una data valida: formato non valido");
                }
                //filtro i risultati in tabella
                Elenco Etemp = new Elenco();
                for(int i=0;i<el.size();i++){
                    if(el.getElementAt(i).getData().equals(ld)){
                        //lo inserisco in una struttura dati temporanea
                        Etemp.aggiungiVoce(el.getElementAt(i));
                    }
                }
                //visualizzo solo le voci che mi interessano
                tm = new Table_model(Etemp, l);
                //visualizzo la tabella
                t.setModel(tm);
                double tot = tm.calcolaTotale();
                l.setText(" " + tot);
            }

        }
        if(ev.getActionCommand().equals("Seleziona settimana")){
            //mostro i sette giorni a partire dal giorno specificato dall'utente
            Object obj = JOptionPane.showInputDialog(null, "Scrivere la data", "Seleziona giorno da cui far partire la settimana", JOptionPane.QUESTION_MESSAGE, null, null, LocalDate.now().toString());
            if(obj != null){
                LocalDate ld = LocalDate.now();//inizializzo con la data odierna
                try{
                    ld = LocalDate.parse((String)obj);
                }
                catch(DateTimeParseException ex){
                    JOptionPane.showMessageDialog(null, "Inserire una data valida: formato non valido");
                }
                //filtro i risultati in tabella
                Elenco Etemp = new Elenco();
                //calcolo la data di fine range
                LocalDate fine = ld.plusDays(6);
                for(int i=0;i<el.size();i++){
                    if((el.getElementAt(i).getData().isAfter(ld) && el.getElementAt(i).getData().isBefore(fine)) || 
                        el.getElementAt(i).getData().equals(ld) || el.getElementAt(i).getData().equals(fine)){
                        //lo inserisco in una struttura dati temporanea
                        Etemp.aggiungiVoce(el.getElementAt(i));
                    }
                }
                tm = new Table_model(Etemp, l);
                t.setModel(tm);
                double tot = tm.calcolaTotale();
                l.setText(" " + tot);
            }

        }
        if(ev.getActionCommand().equals("Seleziona mese")){
            //seleziona mese e anno
            Object obj = JOptionPane.showInputDialog(null, "Scrivere la data", "Seleziona mese e anno", JOptionPane.QUESTION_MESSAGE, null, null, YearMonth.now().toString());
            if(obj != null){
                YearMonth ld = YearMonth.now();//inizializzo con la data odierna
                try{
                    ld = YearMonth.parse((String)obj);
                }
                catch(DateTimeParseException ex){
                    JOptionPane.showMessageDialog(null, "Inserire una data valida: formato non valido");
                }
                //filtro i risultati in tabella
                Elenco Etemp = new Elenco();
                for(int i=0;i<el.size();i++){
                    if(el.getElementAt(i).getData().getMonth().equals(ld.getMonth()) && 
                        (el.getElementAt(i).getData().getYear() == (ld.getYear()))){
                        //lo inserisco in una struttura dati temporanea
                        Etemp.aggiungiVoce(el.getElementAt(i));
                    }
                }
                tm = new Table_model(Etemp, l);
                t.setModel(tm);
                double tot = tm.calcolaTotale();
                l.setText(" " + tot);
            }

        }
        if(ev.getActionCommand().equals("Seleziona anno")){
            Object obj = JOptionPane.showInputDialog(null, "Scrivere la data", "Seleziona anno", JOptionPane.QUESTION_MESSAGE, null, null, Year.now().toString());
            if(obj != null){
                Year ld = Year.now();//inizializzo con la data odierna
                try{
                    ld = Year.parse((String)obj);
                }
                catch(DateTimeParseException ex){
                    JOptionPane.showMessageDialog(null, "Inserire una data valida: formato non valido");
                }
                //filtro i risultati in tabella
                Elenco Etemp = new Elenco();
                int anno = ld.getValue();
                for(int i=0;i<el.size();i++){
                    if(el.getElementAt(i).getData().getYear() == anno){
                        //lo inserisco in una struttura dati temporanea
                        Etemp.aggiungiVoce(el.getElementAt(i));
                    }
                }
                tm = new Table_model(Etemp, l);
                t.setModel(tm);
                double tot = tm.calcolaTotale();
                l.setText(" " + tot);
            }

        }
        if(ev.getActionCommand().equals("Visualizza tutto")){
            //mostro tutta la tabella
            tm = new Table_model(el, l);
            t.setModel(tm);
            double tot = tm.calcolaTotale();
            l.setText(" " + tot);
        }
    }

    
}
