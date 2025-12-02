package REPL.Commande;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.Interfaces.IVarStockage;

public class Variable implements ICommande {

    private final Historique historique;

    public Variable(IVarStockage stockageVariable, Historique historique)
    {
        this.historique = historique;
    }
    @Override
    public void execute() {
        historique.ajouter("var");
    }
}
