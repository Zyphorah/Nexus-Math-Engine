package REPL.Registre;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.ParentheseService;
import REPL.Commande.*;
import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import REPL.Registre.Interfaces.IRegistreCommande;
import Stockage.StockageConstante;
import Stockage.StockageVariable;
import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class RegistreCommande implements IRegistreCommande {
    private final Map<String, ICommande> _commandes;
    private final Historique _historique;
    private final ParentheseService _parentheseService;
    private final ChaineOperateurs _chaineOperateurs;
    private final IVarStockage _stockageVariable;
    private final IConstanteStockage _stockageConstante;
    
    public RegistreCommande(Supplier<String> argumentsSupplier) {
        this._historique = new Historique();
        this._commandes = new HashMap<>();
        this._parentheseService = new ParentheseService();
        this._chaineOperateurs = new ChaineOperateurs(_parentheseService);
        this._stockageVariable = new StockageVariable();
        this._stockageConstante = new StockageConstante();
        
        // Charger les constantes par défaut au démarrage
        this._stockageConstante.charger("constantes.txt");
        
        initialiserCommandes(argumentsSupplier);
    }
    
    private void initialiserCommandes(Supplier<String> argumentsSupplier) {
        this._commandes.put("analyse", new Analyse(_historique, _stockageVariable, _stockageConstante, argumentsSupplier));
        this._commandes.put("aide", new Aide(_historique));
        this._commandes.put("histoire", new Histoire(_historique));
        this._commandes.put("calculer", new Calculer(_historique, _parentheseService, _chaineOperateurs, argumentsSupplier, _stockageVariable, _stockageConstante));
        this._commandes.put("constantes", new ChargerConstance(_historique, _stockageConstante, argumentsSupplier));
        this._commandes.put("var", new Variable(_stockageVariable, _stockageConstante, _historique, argumentsSupplier));
        this._commandes.put("vars", new Variable(_stockageVariable, _stockageConstante, _historique, argumentsSupplier));
    }
    
    public ICommande obtenirCommande(String nomCommande) {
        ICommande commande = this._commandes.get(nomCommande);
        
        if (commande == null) {
            System.out.println("Commande inconnue: " + nomCommande + ". Tapez 'aide' pour la liste des commandes.");
        }
        return commande;
    }
    
    public boolean existe(String nomCommande) {
        return this._commandes.containsKey(nomCommande);
    }
}