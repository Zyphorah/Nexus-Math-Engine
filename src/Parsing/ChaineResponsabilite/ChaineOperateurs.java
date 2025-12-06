package Parsing.ChaineResponsabilite;

import java.util.List;

import Parsing.ChaineResponsabilite.Interfaces.IOperateurHandler;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;

public class ChaineOperateurs {
    private IOperateurHandler debut;
    private final IParentheseHandler _parentheseHandler;
    private final List<OperateurHandler> _operateurs;
    
    public ChaineOperateurs(IParentheseHandler parentheseHandler, List<OperateurHandler> operateurs ) {
        this._parentheseHandler = parentheseHandler;
        
     

        this._operateurs = operateurs; 

        for( int i = 0 ; i < this._operateurs.size(); i ++)
        {
            this._operateurs.get(i).setParentheseHandler(this._parentheseHandler);
            if(i < this._operateurs.size()-1)
            {
                this._operateurs.get(i).setProchain(this._operateurs.get(i+1));
            }
        }

        this.debut = this._operateurs.get(0);
    }
    
    public int trouverOperateur(String equation) {
        return debut.traiter(equation);
    }
}
