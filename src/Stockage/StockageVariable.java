package Stockage;

import Stockage.Interfaces.IVarStockage;
import java.util.Map;

public class StockageVariable implements IVarStockage {
    private Map<String,Double> _variable;
    
    public StockageVariable(Map<String,Double> variable)
    {
        this._variable = variable;
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
        }
         throw new IllegalArgumentException(
                "Variable inconnue: " + variable
            );
    }
}
