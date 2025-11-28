package Parsing;

import Parsing.Interfaces.IOperateurHandler;

public abstract class OperateurHandler implements IOperateurHandler {
    protected IOperateurHandler prochain;
    
    @Override
    public void setProchain(IOperateurHandler prochain) {
        this.prochain = prochain;
    }
    
    @Override
    public int traiter(String equation) {
        int index = trouverOperateur(equation);
        
        if (index != -1) {
            return index;
        }
        
        if (prochain != null) {
            return prochain.traiter(equation);
        }
        
        return -1;
    }
}
