package REPL.Registre;
import java.util.HashMap;
import java.util.Map;

import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.ParentheseService;
import REPL.Commande.*;
import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import REPL.Registre.Interfaces.IRegistreCommande;
import Stockage.StockageVariable;

public class RegistreCommande implements IRegistreCommande {
    private final Map<String, ICommande> _commandes;
    private final Historique historique;
    private final ParentheseService parentheseService;
    private final ChaineOperateurs chaineOperateurs;
    
    public RegistreCommande() {
        this.historique = new Historique();
        this._commandes = new HashMap<>();
        this.parentheseService = new ParentheseService();
        this.chaineOperateurs = new ChaineOperateurs(parentheseService);
        initialiserCommandes();
    }
    
    private void initialiserCommandes() {
        this._commandes.put("analyse", new Analyse(historique));
        this._commandes.put("aide", new Aide(historique));
        this._commandes.put("histoire", new Histoire(historique));
        this._commandes.put("calculer", new Calculer(historique, parentheseService, chaineOperateurs));
        this._commandes.put("chargerConstance", new ChargerConstance(historique));
        this._commandes.put("var", new Variable(new StockageVariable(new HashMap<>()), historique));
    }
    
    public ICommande obtenirCommande(String nomCommande) {
        ICommande commande = _commandes.get(nomCommande);
        
        if (commande == null) {
            System.out.println("votre commande "+nomCommande+" n'existe pas faite la commande aide pour obtenir la liste des commandes valide");
        }
        return commande;
    }
    
    public boolean existe(String nomCommande) {
        return _commandes.containsKey(nomCommande);
    }
}