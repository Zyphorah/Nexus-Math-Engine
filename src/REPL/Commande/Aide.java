package REPL.Commande;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;

public class Aide implements ICommande {

    private final Historique _historique;

    public Aide(Historique historique)
    {
        this._historique = historique;
    }
    @Override
    public void execute() {
        System.out.println("________________________________________");
        System.out.println("analyse");
        System.out.println("chargerConstance pathFichier");
        System.out.println("var nomVar = valeur");
        System.out.println("histoire");
        System.out.println("calculer equation");
        System.out.println("quitter");
        this._historique.ajouter("Aide");
    }
}
