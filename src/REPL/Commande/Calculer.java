package REPL.Commande;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;

public class Calculer implements ICommande {
    private final Historique historique;

    public Calculer(Historique historique) {
        this.historique = historique;
    }

    @Override
    public void execute() {
        historique.ajouter("calculer");
    }
}
