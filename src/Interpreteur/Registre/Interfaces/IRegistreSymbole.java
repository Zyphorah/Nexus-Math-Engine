package Interpreteur.Registre.Interfaces;

import Interpreteur.Interfaces.IArbre;

public interface IRegistreSymbole {
    public IArbre obtenirNoeud(char symbole);
    public boolean estOperateur(char symbole);
}
