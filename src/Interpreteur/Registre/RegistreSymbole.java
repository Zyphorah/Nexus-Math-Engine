package Interpreteur.Registre;

import java.util.HashMap;
import java.util.Map;

import Interpreteur.Operation;
import Interpreteur.Interfaces.INoeud;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;

public class RegistreSymbole implements IRegistreSymbole {
    private final Map<Character, INoeud> symboleMaps;
    
    public RegistreSymbole() {
        symboleMaps = new HashMap<>();
        symboleMaps.put('+', new Operation((a,b)-> a+b));
        symboleMaps.put('-', new Operation((a,b)-> a-b));
        symboleMaps.put('*', new Operation((a,b)-> a*b));
        symboleMaps.put('/', new Operation((a, b) -> 
        {
            if (b == 0) {
                throw new ArithmeticException("Division par zéro détectée");
            }
            return a / b;  
        }));
    }
     
    public INoeud obtenirNoeud(char symbole) {
        INoeud noeud = symboleMaps.get(symbole);
        if (noeud == null) {
            throw new IllegalArgumentException("Symbole inconnu: " + symbole);
        }
        return noeud;
    }
    
    public boolean estOperateur(char symbole) {
        return symboleMaps.containsKey(symbole);
    }
}