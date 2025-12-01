package Interpreteur.Registre;

import java.util.HashMap;
import java.util.Map;

import Interpreteur.Addition;
import Interpreteur.Division;
import Interpreteur.Multiplication;
import Interpreteur.Soustraction;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;

public class RegistreSymbole implements IRegistreSymbole {
    private final Map<Character, IArbre> symboleMaps;
    
    public RegistreSymbole() {
        symboleMaps = new HashMap<>();
        symboleMaps.put('+', new Addition());
        symboleMaps.put('-', new Soustraction());
        symboleMaps.put('*', new Multiplication());
        symboleMaps.put('/', new Division());
    }
     
    public IArbre obtenirNoeud(char symbole) {
        IArbre noeud = symboleMaps.get(symbole);
        if (noeud == null) {
            throw new IllegalArgumentException("Symbole inconnu: " + symbole);
        }
        return noeud;
    }
    
    public boolean estOperateur(char symbole) {
        return symboleMaps.containsKey(symbole);
    }
}