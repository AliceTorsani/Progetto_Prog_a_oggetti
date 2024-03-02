package gestione_bilancio.gestione_esportazione;

import gestione_bilancio.gestione_dati_bilancio.*;

/**
 * Classe astratta che contiene il metodo astratto "esporta" per l'esportazione 
 * in più formati
 * Usata per l'ereditarietà e per realizzare poi il polimorfismo. 
 * @author Alice Torsani
 */
public abstract class Esportazione {

    /**
     * Costruttore
     */
    public Esportazione(){
    }
    
    /**
     * Metodo astratto usato per l'esportazione
     * @param lista struttura dati che contiene le voci da scrivere su file
     */
    public abstract void esporta(Elenco lista);
}
