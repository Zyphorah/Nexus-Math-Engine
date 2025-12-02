package REPL.Commande;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;

public class Analyse implements ICommande {
    private final Historique historique;

    public Analyse(Historique historique) {
        this.historique = historique;
    }

    @Override
    public void execute() {
        historique.ajouter("analyse");
    }
}
