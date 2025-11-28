package Interpreteur.Factory.Registre;

import java.util.HashMap;
import java.util.Map;

import Interpreteur.Factory.AditionFactory;
import Interpreteur.Factory.DivisionFactory;
import Interpreteur.Factory.MultiplicationFactory;
import Interpreteur.Factory.SoustractionFactory;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Factory.Registre.Interfaces.IRegisteSymbole;

public class RegistreSymbole implements IRegisteSymbole{
    
    public Map<Character, INoeudFactory> creerSymbole()
    {
        Map<Character, INoeudFactory> symboleMaps = new HashMap<>();
        symboleMaps.put('+', new AditionFactory());
        symboleMaps.put('-', new SoustractionFactory());
        symboleMaps.put('*', new MultiplicationFactory());
        symboleMaps.put('/', new DivisionFactory());
  
        return symboleMaps;     
    }
}
