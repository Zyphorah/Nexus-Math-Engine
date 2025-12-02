package REPL.Commande;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;

public class Histoire implements ICommande {
    private Historique historique;

    public Histoire(Historique historique) {
        this.historique = historique;
    }

    @Override
    public void execute() {
        historique.afficher();
    }
}
