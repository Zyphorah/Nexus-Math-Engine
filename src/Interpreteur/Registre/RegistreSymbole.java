package Interpreteur.Registre;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import Interpreteur.Interfaces.INoeud;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;

public class RegistreSymbole implements IRegistreSymbole {
    private final Map<Character, Supplier<INoeud>> symboleMaps;
    
    public RegistreSymbole() {
        symboleMaps = new HashMap<>();
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
}
