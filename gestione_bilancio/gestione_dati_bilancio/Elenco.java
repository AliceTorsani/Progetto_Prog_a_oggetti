package gestione_bilancio.gestione_dati_bilancio;

import java.io.Serializable;
import java.util.*;
/**
 * Classe che sfrutta una struttura dati di libreria (ArrayList) per memorizzare oggetti
 * di classe Voce_bilancio, mette a disposizione metodi per gestire l'elenco di voci (Elenco)
 * Verrà usata come struttura dati. 
 * Ho scelto di utilizzare un Arraylist e non un Vector, perchè Vector è più lento di Arraylist. 
 * Arraylist non è thread-safe (Vector invece lo è), ma non ho utilizzato i thread, quindi ho scelto un Arraylist. 
 * Ho scelto Arraylist perchè memorizza una sequenza di elementi e si ridimensiona dinamicamente. 
 * @author Alice Torsani
 */
public class Elenco implements Serializable{

    private List<Voce_bilancio> voci;

    /**
     * costruttore
     */
    public Elenco(){
        voci = new ArrayList<Voce_bilancio>();
    }

    /**
     * Aggiunge una voce all'elenco
     * @param v voce
     */
    public void aggiungiVoce(Voce_bilancio v){
        voci.add(v);
    }

    /**
     * Rimuove una voce dall'elenco
     * rimuovo solo la prima occorrenza
     * @param v voce
     */
    public void rimuoviVoce(Voce_bilancio v){
        voci.remove(v);
    }

    /**
     * rimuove tutte le voci
     */
    public void cancellaTutti(){
        voci.clear();
    }

    /**
     * Ritorna la lista di voci
     * @return la lista di voci
     */
    public List<Voce_bilancio> getAll(){
        return voci;
    }

    /**
     * Ritorna la dimensione dell'elenco di voci
     * @return il numero di voci in elenco
     */
    public int size(){
        return voci.size();
    }

    /**
     * Ritorna l'elemento all'indice n
     * @param n indice
     * @return la voce di bilancio corrispondente
     */
    public Voce_bilancio getElementAt(int n){
        return voci.get(n);
    }

    /**
     * Rimpiazza l'elemento all'indice specificato 
     * con l'elemento specificato
     * @param index indice
     * @param element elemento da inserire
     * @return l'elemento che occupava in precedenza la posizione specificata
     */
    public Voce_bilancio set(int index, Voce_bilancio element){
        return voci.set(index, element);
    }

}
