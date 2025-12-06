package REPL.Registre.Factory;

import java.util.Set;
import java.util.function.Supplier;
import REPL.Commande.interfaces.ICommande;

public interface IRegistreurCommande {
    void enregistrer(String nom, Supplier<ICommande> supplier);
    Supplier<ICommande> obtenir(String nom);
    boolean existe(String nom);
    Set<String> obtenirNoms();
}
