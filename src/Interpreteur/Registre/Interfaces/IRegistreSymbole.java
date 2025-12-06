package Interpreteur.Registre.Interfaces;

import java.util.Set;
import java.util.function.Supplier;

import Interpreteur.Interfaces.INoeud;

public interface IRegistreSymbole {
    public void enregistrer(char symbole, Supplier<INoeud> supplier);
    public INoeud obtenirNoeud(char symbole);
    public boolean estOperateur(char symbole);
    public Set<Character> obtenirSymboles();
}
