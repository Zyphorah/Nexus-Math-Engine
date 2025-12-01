package REPL.Commande;

import REPL.Commande.interfaces.ICommande;

public class Aide implements ICommande {

    @Override
    public void execute() {
        System.out.println("analyse");
        System.out.println("chargerConstance pathFichier");
        System.out.println("var nomVar = valeur");
        System.out.println("histoire");
        System.out.println("calculer equation");
        System.out.println("quitter");
    }
}
