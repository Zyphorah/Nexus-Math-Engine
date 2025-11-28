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

    public Map<Character, INoeudFactory> creerSymbole()
    {
    
        Map<Character, INoeudFactory> symboleMap = new HashMap<>();
        symboleMap.put('+', new AditionFactory());
        symboleMap.put('-', new SoustractionFactory());
        symboleMap.put('*', new MultiplicationFactory());
        symboleMap.put('/', new DivisionFactory());

        return symboleMap;     
    }
}
