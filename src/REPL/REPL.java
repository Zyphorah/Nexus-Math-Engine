package REPL;

import java.util.Scanner;

import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import REPL.Commande.interfaces.ICommande;
import REPL.Registre.RegistreCommande;
import REPL.Registre.Interfaces.IRegistreCommande;

public class REPL {
    private static final String NOM_PROGRAMME = "Calculateur Console";
    private static final String VERSION = "1.0.0";
    
    private String _argumentsCourants = "";
    private final IRegistreCommande _registreCommande;
    private final DetecteurSaisie _detecteur = new DetecteurSaisie();
    private final Scanner _scanner = new Scanner(System.in);

    public REPL(IRegistreSymbole registreSymbole, ChaineOperateurs chaineOperateurs) {
        this._registreCommande = new RegistreCommande(() -> this._argumentsCourants, registreSymbole, chaineOperateurs);
    }

    public void lancerREPL() {
        afficherBienvenue();
        
        boolean fin = false;
        while (!fin) {
            System.out.print("> ");
            String entree = this._scanner.nextLine();
            String saisie = entree == null ? "" : entree.trim();
            
            if ("quitter".equalsIgnoreCase(saisie)) {
                fin = true;
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
        this._argumentsCourants = this._detecteur.extraireArguments(saisie);
        String nomCommande = this._detecteur.detecterCommande(saisie);
        executerCommande(nomCommande);
    }
    
    private void executerCommande(String nomCommande) {
        ICommande commande = this._registreCommande.obtenirCommande(nomCommande);
        if (commande != null) {
            commande.execute();
        }
    }
}
