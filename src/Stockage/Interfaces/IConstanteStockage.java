package Stockage.Interfaces;

import java.util.Map;

public interface IConstanteStockage extends IStockage {
    void charger(String nomFichier);
    Map<String, Double> obtenirTout();
}
