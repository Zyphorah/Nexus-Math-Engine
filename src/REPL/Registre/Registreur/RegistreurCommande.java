package REPL.Registre.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import REPL.Commande.interfaces.ICommande;

public class RegistreurCommande implements IRegistreurCommande {
    private final Map<String, Supplier<ICommande>> commandes;
    
    public RegistreurCommande() {
        this.commandes = new HashMap<>();
    }
    
    @Override
    public void enregistrer(String nom, Supplier<ICommande> supplier) {
        this.commandes.put(nom.toLowerCase(), supplier);
    }
    
    @Override
    public Supplier<ICommande> obtenir(String nom) {
        return this.commandes.get(nom.toLowerCase());
    }
    
    @Override
    public boolean existe(String nom) {
        return this.commandes.containsKey(nom.toLowerCase());
    }
    
    @Override
    public Set<String> obtenirNoms() {
        return this.commandes.keySet();
    }
}
