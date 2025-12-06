package REPL.Registre.Interfaces;
import java.util.Set;

import REPL.Commande.interfaces.ICommande;

public interface IRegistreCommande {
     public ICommande obtenirCommande(String nomCommande);
     boolean existe(String nom);
     Set<String> obtenirNoms();
}
