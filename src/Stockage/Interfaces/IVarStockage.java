package Stockage.Interfaces;

import java.util.Map;

public interface IVarStockage {
    public void ajouter(String variable, Double valeur);
    public void retirer(String variable);
    public Double obtenir(String variable);
    public Map<String, Double> obtenirTout();
    public boolean existe(String variable);
}
