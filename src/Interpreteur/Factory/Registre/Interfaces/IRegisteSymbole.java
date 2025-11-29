package Interpreteur.Factory.Registre.Interfaces;

import java.util.Map;

import Interpreteur.Factory.Interfaces.INoeudFactory;

public interface IRegisteSymbole {
   Map<Character, INoeudFactory> creerSymbole();
}
