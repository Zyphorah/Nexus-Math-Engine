package REPL.Commande;

import java.util.Map;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class VariablesPluriel implements ICommande {
    private final IVarStockage _stockageVariable;
    private final IConstanteStockage _stockageConstante;
    private final Historique _historique;

    public VariablesPluriel(IVarStockage stockageVariable, IConstanteStockage stockageConstante, Historique historique) {
        this._stockageVariable = stockageVariable;
        this._stockageConstante = stockageConstante;
        this._historique = historique;
    }

    @Override
    public void execute() {
        Map<String, Double> variables = this._stockageVariable.obtenirTout();
        Map<String, Double> constantes = this._stockageConstante.obtenirTout();

        if (variables.isEmpty() && constantes.isEmpty()) {
            System.out.println("Aucune variable ou constante stockée.");
            return;
        }

        System.out.println("=== Variables ===");
        if (variables.isEmpty()) {
            System.out.println("  (aucune)");
        } else {
            variables.forEach((k, v) -> System.out.println("  " + k + " = " + v));
        }

        System.out.println("=== Constantes ===");
        if (constantes.isEmpty()) {
            System.out.println("  (aucune)");
        } else {
            constantes.forEach((k, v) -> System.out.println("  [const] " + k + " = " + v));
        }

        this._historique.ajouter("vars");
    }
}
