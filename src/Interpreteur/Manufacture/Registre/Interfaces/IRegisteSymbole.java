package Interpreteur.Manufacture.Registre.Interfaces;

import java.util.Map;

import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public interface IRegisteSymbole {
    Map<Character,INoeudFactory> creerSymbole();
}
