package REPL.Registre.Interfaces;
import REPL.Commande.interfaces.ICommande;

public interface IRegistreCommande {
     public ICommande obtenirCommande(String nomCommande);
     boolean existe(String nom);
}
