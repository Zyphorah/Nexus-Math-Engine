package REPL.Registre;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import REPL.Commande.interfaces.ICommande;
import REPL.Registre.Interfaces.IRegistreCommande;
import REPL.Registre.Registreur.IRegistreurCommande;

public class RegistreCommande implements IRegistreCommande {
    private final Map<String, ICommande> _commandes;
    private final IRegistreurCommande _registreur;
    
    public RegistreCommande(IRegistreurCommande registreur) {
        this._commandes = new HashMap<>();
        this._registreur = registreur;
    }
    
    public void enregistrer(String nom, Supplier<ICommande> supplier) {
        this._registreur.enregistrer(nom, supplier);
    }
    
    public ICommande obtenirCommande(String nomCommande) {
        Supplier<ICommande> supplier = this._registreur.obtenir(nomCommande);
        
        if (supplier == null) {
            System.out.println("Commande inconnue: " + nomCommande + ". Tapez 'aide' pour la liste des commandes.");
            return null;
        }
        
        // Créer l'instance et la mettre en cache
        if (!this._commandes.containsKey(nomCommande)) {
            this._commandes.put(nomCommande, supplier.get());
        }
        return this._commandes.get(nomCommande);
    }
    
    public boolean existe(String nomCommande) {
        return this._registreur.existe(nomCommande);
    }
    
    public Set<String> obtenirNoms() {
        return this._registreur.obtenirNoms();
    }
}
