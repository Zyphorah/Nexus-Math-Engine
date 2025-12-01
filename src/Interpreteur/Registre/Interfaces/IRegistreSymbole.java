package Interpreteur.Registre.Interfaces;

import Interpreteur.Interfaces.INoeud;

public interface IRegistreSymbole {
    public INoeud obtenirNoeud(char symbole);
    public boolean estOperateur(char symbole);
}
