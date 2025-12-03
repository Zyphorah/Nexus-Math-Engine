package Interpreteur.Registre;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import Interpreteur.Operation;
import Interpreteur.Interfaces.INoeud;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;

// lamda de new pour les operations 

public class RegistreSymbole implements IRegistreSymbole {
    private final Map<Character, Supplier<INoeud>> symboleMaps;
    
    public RegistreSymbole() {
        symboleMaps = new HashMap<>();
        symboleMaps.put('+', () -> new Operation((a,b)-> a+b));
        symboleMaps.put('-', () -> new Operation((a,b)-> a-b));
        symboleMaps.put('*', () -> new Operation((a,b)-> a*b));
        symboleMaps.put('/', () -> new Operation((a, b) -> 
        {
            if (b == 0) {
                throw new ArithmeticException("Division par zéro détectée");
            }
            return a / b;  
        }));
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
