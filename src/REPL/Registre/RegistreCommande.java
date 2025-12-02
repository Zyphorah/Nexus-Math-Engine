package REPL.Registre;
import java.util.HashMap;
import java.util.Map;

import REPL.Commande.*;
import REPL.Commande.interfaces.ICommande;
import REPL.Registre.Interfaces.IRegistreCommande;
import Stockage.StockageVariable;

public class RegistreCommande implements IRegistreCommande {
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
        this._commandes.put("chargerConstance", new ChargerConstance());
        this._commandes.put("var", new Variable(new StockageVariable(new HashMap<>())));
    }
    
    public ICommande obtenirCommande(String nomCommande) {
        ICommande commande = _commandes.get(nomCommande);
        
        if (commande == null) {
            System.out.println("votre commande "+nomCommande+" n'existe pas faite la commande aide pour obtenir la liste des commandes valide");
        }
        return commande;
    }
    
    // Méthodes utiles supplémentaires
    public boolean existe(String nomCommande) {
        return _commandes.containsKey(nomCommande);
    }
}