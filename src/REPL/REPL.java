package REPL;

import java.util.Scanner;

import REPL.Commande.interfaces.ICommande;
import REPL.Detecteur.ChaineDetecteurs;
import REPL.Registre.RegistreCommande;
import REPL.Registre.Interfaces.IRegistreCommande;

public class REPL {
    private static final String NOM_PROGRAMME = "Calculateur Console";
    private static final String VERSION = "1.0.0";
    
    private String _argumentsCourants = "";
    private final IRegistreCommande _registreCommande = new RegistreCommande(() -> this._argumentsCourants);
    private final ChaineDetecteurs _chaineDetecteurs = new ChaineDetecteurs();
    private final Scanner _scanner = new Scanner(System.in);

    public void lancerREPL() {
        afficherBienvenue();
        
        Boolean fin = false;
        while(!fin)
        {
            System.out.print("> ");
            String entree = this._scanner.nextLine();
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
        this._chaineDetecteurs.traiter(
            saisie,
            args -> this._argumentsCourants = args,
            this::executerCommande
        );
    }
    
    private void executerCommande(String nomCommande) {
        ICommande commande = this._registreCommande.obtenirCommande(nomCommande);
        if (commande != null) {
            commande.execute();
        }
    }
}
