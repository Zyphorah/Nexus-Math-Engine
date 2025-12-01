package REPL.RegistreCommande;

import java.util.HashMap;
import java.util.Map;
import REPL.Commande.*;
import REPL.Commande.interfaces.ICommande;

public class RegistreCommande {
    private final Map<String, ICommande> _commandes;
    
    public RegistreCommande() {
        this._commandes = new HashMap<>();
        initialiserCommandes();
    }
    
    private void initialiserCommandes() {
        this._commandes.put("analyse", new Analyse());
        this._commandes.put("aide", new Aide());
        this._commandes.put("histoire", new Histoire());
        this._commandes.put("calculer", new Calculer());
        this._commandes.put("quitter", new Quitter());
        this._commandes.put("chargerConstance", new ChargerConstance());
    }
    
    public ICommande obtenirCommande(String nomCommande) {
        ICommande commande = _commandes.get(nomCommande);
        
        if (commande == null) {
            throw new IllegalArgumentException(
                "Commande inconnue: " + nomCommande
            );
        }
        return commande;
    }
    
    // Méthodes utiles supplémentaires
    public boolean existe(String nomCommande) {
        return _commandes.containsKey(nomCommande);
    }
}