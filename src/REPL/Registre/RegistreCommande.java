package REPL.Registre;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.ParentheseService;
import Interpreteur.Registre.RegistreSymbole;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
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
    private final IRegistreSymbole _registreSymbole;
    
    public RegistreCommande(Supplier<String> argumentsSupplier) {
        this._historique = new Historique();
        this._commandes = new HashMap<>();
        this._parentheseService = new ParentheseService();
        this._chaineOperateurs = new ChaineOperateurs(_parentheseService);
        this._stockageVariable = new StockageVariable();
        this._stockageConstante = new StockageConstante();
        this._registreSymbole = new RegistreSymbole();
        
        // Charger les constantes par défaut au démarrage
        this._stockageConstante.charger("constantes.txt");
        
        initialiserCommandes(argumentsSupplier);
    }
    
    private void initialiserCommandes(Supplier<String> argumentsSupplier) {
        this._commandes.put("analyse", new Analyse(this._historique, this._stockageVariable, this._stockageConstante, argumentsSupplier));
        this._commandes.put("aide", new Aide(this._historique));
        this._commandes.put("histoire", new Histoire(this._historique));
        this._commandes.put("calculer", new Calculer(this._historique, this._parentheseService, this._chaineOperateurs, argumentsSupplier, this._stockageVariable, this._stockageConstante, this._registreSymbole));
        this._commandes.put("constantes", new ChargerConstance(this._historique, this._stockageConstante, argumentsSupplier));
        this._commandes.put("var", new Variable(this._stockageVariable, this._stockageConstante, this._historique, argumentsSupplier));
        this._commandes.put("vars", new Variable(this._stockageVariable, this._stockageConstante, this._historique, argumentsSupplier));
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