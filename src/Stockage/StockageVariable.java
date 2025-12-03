package Stockage;

import Stockage.Interfaces.IVarStockage;
import java.util.Map;
import java.util.HashMap;

public class StockageVariable implements IVarStockage {
    private Map<String,Double> _variable;
    
    public StockageVariable(Map<String,Double> variable)
    {
        this._variable = variable;
    }
    
    public StockageVariable()
    {
        this._variable = new HashMap<>();
    }
    
    public void ajouter(String variable, Double valeur)
    {
        this._variable.put(variable, valeur);
    }

    public void retirer(String variable)
    {
        if(this._variable.containsKey(variable))
        {
            this._variable.remove(variable);
            return;
        }
        throw new IllegalArgumentException(
            "Variable inconnue: " + variable
        );
    }
    
    public Double obtenir(String variable)
    {
        if(this._variable.containsKey(variable))
        {
            return this._variable.get(variable);
        }
        throw new IllegalArgumentException(
            "Variable inconnue: " + variable
        );
    }
    
    public Map<String, Double> obtenirTout()
    {
        return new HashMap<>(this._variable);
    }
    
    public boolean existe(String variable)
    {
        return this._variable.containsKey(variable);
    }
}
