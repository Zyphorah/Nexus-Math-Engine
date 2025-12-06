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
        System.out.println("=== Commandes disponibles ===");
        System.out.println("");
        System.out.println("  Expression directe:");
        System.out.println("    42              - Évalue un nombre");
        System.out.println("    2 + 3 * 4       - Évalue une expression");
        System.out.println("    x = 5           - Assigne une variable");
        System.out.println("");
        System.out.println("  Commandes:");
        System.out.println("    analyse <expr>  - Analyse une expression (opérateurs, nombres, variables)");
        System.out.println("    var             - Affiche toutes les variables et constantes");
        System.out.println("    var <nom>       - Affiche la valeur d'une variable/constante");
        System.out.println("    var <nom>=<val> - Assigne une variable");
        System.out.println("    constantes <f>  - Charge un paquet de constantes depuis un fichier");
        System.out.println("    histoire        - Affiche l'historique des commandes");
        System.out.println("    aide            - Affiche cette aide");
        System.out.println("    Calculer <Expression> - Calculer une expression"); 
        System.out.println("    quitter         - Ferme le programme");
        System.out.println("");
        this._historique.ajouter("aide");
    }
}
