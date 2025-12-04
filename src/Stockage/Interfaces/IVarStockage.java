package Stockage.Interfaces;

import java.util.Map;

public interface IVarStockage extends IStockage {
    void ajouter(String variable, Double valeur);
    void retirer(String variable);
    Map<String, Double> obtenirTout();
}
