package Parsing.ChaineResponsabilite;

import Parsing.ChaineResponsabilite.Interfaces.IOperateurHandler;

public abstract class OperateurHandler implements IOperateurHandler {
    protected IOperateurHandler prochain;
    protected ParentheseService parentheseService;
    
    protected abstract char getOperateur();

    public void setParentheseService(ParentheseService parentheseService) {
        this.parentheseService = parentheseService;
    }
    
    @Override
    public void setProchain(IOperateurHandler prochain) {
        this.prochain = prochain;
    }
    
    @Override
    public int trouverOperateur(String equation) {
        return parentheseService.trouverDernierAuNiveauZero(equation, getOperateur());
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
