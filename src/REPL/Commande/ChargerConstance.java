package REPL.Commande;

import java.util.function.Supplier;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.Interfaces.IConstanteStockage;

public class ChargerConstance implements ICommande {
    private final Historique _historique;
    private final IConstanteStockage _stockageConstante;
    private final Supplier<String> _argumentsSupplier;

    public ChargerConstance(Historique historique, IConstanteStockage stockageConstante, Supplier<String> argumentsSupplier)
    {
        this._historique = historique;
        this._stockageConstante = stockageConstante;
        this._argumentsSupplier = argumentsSupplier;
    }

    @Override
    public void execute() {
        String nomFichier = this._argumentsSupplier.get();
        
        if (nomFichier == null || nomFichier.trim().isEmpty()) {
            System.out.println("Erreur: Veuillez fournir le nom du fichier de constantes.");
            System.out.println("Exemple: constantes mesconstantes.txt");
            return;
        }
        
        this._stockageConstante.charger(nomFichier.trim());
        System.out.println("Constantes chargées depuis: " + nomFichier);
        this._historique.ajouter("constantes " + nomFichier);
    }
}
