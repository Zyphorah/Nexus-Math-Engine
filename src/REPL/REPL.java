package REPL;

import java.util.Scanner;

import REPL.Commande.interfaces.ICommande;
import REPL.Registre.RegistreCommande;
import REPL.Registre.Interfaces.IRegistreCommande;

public class REPL {
    private IRegistreCommande _registreCommande = new RegistreCommande();
    private Scanner scanner = new Scanner(System.in);

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
                ICommande commande = this._registreCommande.obtenirCommande(saisie);
                if (commande != null) {
                    commande.execute();
                }
            }
        }
        
    }
}
