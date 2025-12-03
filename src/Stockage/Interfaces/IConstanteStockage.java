package Stockage.Interfaces;

import java.util.Map;

public interface IConstanteStockage {
    void charger(String nomFichier);
    Double obtenir(String constante);
    Map<String, Double> obtenirTout();
    boolean existe(String constante);
}
