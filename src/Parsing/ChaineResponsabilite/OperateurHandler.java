package Parsing.ChaineResponsabilite;

import Parsing.ChaineResponsabilite.Interfaces.IOperateurHandler;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;

public class OperateurHandler implements IOperateurHandler {
    protected IOperateurHandler prochain;
    protected IParentheseHandler parentheseHandler;
    protected char symbole;
    
    public OperateurHandler(char symbole) {
        this.symbole = symbole;
    }

    public void setParentheseHandler(IParentheseHandler parentheseHandler) {
        this.parentheseHandler = parentheseHandler;
    }
    
    @Override
    public void setProchain(IOperateurHandler prochain) {
        this.prochain = prochain;
    }
    
    @Override
    public int trouverOperateur(String equation) {
        return parentheseHandler.trouverDernierAuNiveauZero(equation, symbole);
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
