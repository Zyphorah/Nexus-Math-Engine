package REPL;

import java.util.Scanner;

import REPL.Commande.interfaces.ICommande;
import REPL.Registre.RegistreCommande;
import REPL.Registre.Interfaces.IRegistreCommande;

public class REPL {
    private String _argumentsCourants = "";
    private final IRegistreCommande _registreCommande = new RegistreCommande(() -> this._argumentsCourants);
    private final Scanner scanner = new Scanner(System.in);

    public void lancerREPL() {

        Boolean fin = false;
        while(!fin)
        {
            System.out.println(">");
            String entree = scanner.nextLine();
            String saisie = entree == null ? "" : entree.trim();
            
            if ("quitter".equalsIgnoreCase(saisie)) {
                fin = true;
                break;
            } else if (!saisie.isEmpty()) {
                // Séparer la commande des arguments
                String[] parties = saisie.split("\\s+", 2);
                String nomCommande = parties[0];
                this._argumentsCourants = parties.length > 1 ? parties[1] : "";
                
                ICommande commande = this._registreCommande.obtenirCommande(nomCommande);
                if (commande != null) {
                    commande.execute();
                }
            }
        }
        
    }
}
