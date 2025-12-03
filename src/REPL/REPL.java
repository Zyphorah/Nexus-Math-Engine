package REPL;

import java.util.Scanner;

import REPL.Commande.interfaces.ICommande;
import REPL.Registre.RegistreCommande;
import REPL.Registre.Interfaces.IRegistreCommande;

public class REPL {
    private static final String NOM_PROGRAMME = "Calculateur Console";
    private static final String VERSION = "1.0.0";
    
    private String _argumentsCourants = "";
    private final IRegistreCommande _registreCommande = new RegistreCommande(() -> this._argumentsCourants);
    private final Scanner scanner = new Scanner(System.in);

    public void lancerREPL() {
        afficherBienvenue();
        
        Boolean fin = false;
        while(!fin)
        {
            System.out.print("> ");
            String entree = scanner.nextLine();
            String saisie = entree == null ? "" : entree.trim();
            
            if ("quitter".equalsIgnoreCase(saisie)) {
                fin = true;
                break;
            } else if (!saisie.isEmpty()) {
                traiterSaisie(saisie);
            }
        }
        System.out.println("Au revoir!");
    }
    
    private void afficherBienvenue() {
        System.out.println("=================================");
        System.out.println(NOM_PROGRAMME + " v" + VERSION);
        System.out.println("Tapez 'aide' pour la liste des commandes");
        System.out.println("Tapez 'quitter' pour fermer le programme");
        System.out.println("=================================");
    }
    
    private void traiterSaisie(String saisie) {
        // Vérifier si c'est une assignation directe (ex: x = 3)
        if (saisie.matches("[a-zA-Z_][a-zA-Z0-9_]*\\s*=\\s*.+")) {
            this._argumentsCourants = saisie;
            ICommande commande = this._registreCommande.obtenirCommande("var");
            if (commande != null) commande.execute();
            return;
        }
        
        // Vérifier si c'est une expression mathématique directe
        if (estExpression(saisie)) {
            this._argumentsCourants = saisie;
            ICommande commande = this._registreCommande.obtenirCommande("calculer");
            if (commande != null) commande.execute();
            return;
        }
        
        // Sinon, traiter comme une commande
        String[] parties = saisie.split("\\s+", 2);
        String nomCommande = parties[0];
        this._argumentsCourants = parties.length > 1 ? parties[1] : "";
        
        ICommande commande = this._registreCommande.obtenirCommande(nomCommande);
        if (commande != null) {
            commande.execute();
        }
    }
    
    private boolean estExpression(String saisie) {
        // Une expression commence par un chiffre, une parenthèse, ou un identifiant suivi d'un opérateur
        return saisie.matches("^[0-9(].*") || 
               saisie.matches("^[a-zA-Z_][a-zA-Z0-9_]*\\s*[+\\-*/].*");
    }
}
