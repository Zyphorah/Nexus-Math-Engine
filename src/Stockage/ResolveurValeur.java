package Stockage;

import java.util.List;

import Stockage.Interfaces.IStockage;

public class ResolveurValeur {
    
    private final List<IStockage> _stockages;
    
    public ResolveurValeur(IStockage... stockages) {
        this._stockages = List.of(stockages);
    }
    
    public String resoudre(String nom) {
        for (IStockage stockage : this._stockages) {
            if (stockage.existe(nom)) {
                return stockage.obtenir(nom).toString();
            }
        }
        throw new IllegalArgumentException("Variable ou constante inconnue: " + nom);
    }
}
