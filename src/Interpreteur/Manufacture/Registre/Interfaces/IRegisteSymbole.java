package Interpreteur.Manufacture.Registre.Interfaces;

import java.util.List;
import java.util.Map;

import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public interface IRegisteSymbole {
    List<Map<Character, INoeudFactory>> creerSymbole();
}
