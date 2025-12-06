package Interpreteur.Registre;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import Interpreteur.Interfaces.INoeud;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;


 //Registre des opérateurs mathématiques.
 //IMPORTANT: L'ordre d'enregistrement détermine la priorité!
 //Les opérateurs enregistrés EN PREMIER ont une PRIORITÉ PLUS BASSE
 //Les opérateurs enregistrés EN DERNIER ont une PRIORITÉ PLUS HAUTE
public class RegistreSymbole implements IRegistreSymbole {
    // LinkedHashMap conserve l'ordre d'insertion
    private final Map<Character, Supplier<INoeud>> symboleMaps;
    
    public RegistreSymbole() {
        symboleMaps = new LinkedHashMap<>();
    }
    
    public void enregistrer(char symbole, Supplier<INoeud> supplier) {
        symboleMaps.put(symbole, supplier);
    }
     
    public INoeud obtenirNoeud(char symbole) {
        Supplier<INoeud> supplier = symboleMaps.get(symbole);
        if (supplier == null) {
            throw new IllegalArgumentException("Symbole inconnu: " + symbole);
        }
        return supplier.get();
    }
    
    public boolean estOperateur(char symbole) {
        return symboleMaps.containsKey(symbole);
    }

    // Obtenir les symboles dans l'ordre d'enregistrement
    public Set<Character> obtenirSymboles() {
        return symboleMaps.keySet();
    }
}
