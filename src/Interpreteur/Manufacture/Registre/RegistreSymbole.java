package Interpreteur.Manufacture.Registre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interpreteur.Manufacture.AditionFactory;
import Interpreteur.Manufacture.DivisionFactory;
import Interpreteur.Manufacture.MultiplicationFactory;
import Interpreteur.Manufacture.SoustractionFactory;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;
import Interpreteur.Manufacture.Registre.Interfaces.IRegisteSymbole;

public class RegistreSymbole implements IRegisteSymbole{
    
    // Utilisation de l'index de la list comme ordre de priorité, une map pour lier les caractères 
    public List<Map<Character, INoeudFactory>> creerSymbole()
    {
        List<Map<Character, INoeudFactory>> niveaux = new ArrayList<>();
        
        // Niveau 0 : Priorité BASSE (Addition et Soustraction)
        Map<Character, INoeudFactory> niveau0 = new HashMap<>();
        niveau0.put('+', new AditionFactory());
        niveau0.put('-', new SoustractionFactory());
        niveaux.add(niveau0);
        
        // Niveau 1 : Priorité HAUTE (Multiplication et Division)
        Map<Character, INoeudFactory> niveau1 = new HashMap<>();
        niveau1.put('*', new MultiplicationFactory());
        niveau1.put('/', new DivisionFactory());
        niveaux.add(niveau1);
        
        return niveaux;     
    }
}
