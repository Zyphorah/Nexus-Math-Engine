package REPL.Commande;

import REPL.Commande.interfaces.ICommande;
import Stockage.Interfaces.IVarStockage;

public class Variable implements ICommande {

    private final IVarStockage _stockageVariable; 

    public Variable(IVarStockage stockageVariable)
    {
        this._stockageVariable = stockageVariable;
    }
    @Override
    public void execute() {
        
    }
}
