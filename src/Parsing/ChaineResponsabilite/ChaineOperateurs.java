package Parsing.ChaineResponsabilite;

import Parsing.ChaineResponsabilite.Interfaces.IOperateurHandler;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;

public class ChaineOperateurs {
    private IOperateurHandler debut;
    private final IParentheseHandler _parentheseHandler;
    
    public ChaineOperateurs(IParentheseHandler parentheseHandler) {
        this._parentheseHandler = parentheseHandler;
        
        // Construire la chaîne dans l'ordre de priorité
        OperateurHandler addition = new OperateurHandler(Operateur.ADDITION);
        OperateurHandler soustraction = new OperateurHandler(Operateur.SOUSTRACTION);
        OperateurHandler multiplication = new OperateurHandler(Operateur.MULTIPLICATION);
        OperateurHandler division = new OperateurHandler(Operateur.DIVISION);
        
        // Injecter le ParentheseHandler
        addition.setParentheseHandler(this._parentheseHandler);
        soustraction.setParentheseHandler(this._parentheseHandler);
        multiplication.setParentheseHandler(this._parentheseHandler);
        division.setParentheseHandler(this._parentheseHandler);
        
        // Chaîner les handlers
        addition.setProchain(soustraction);
        soustraction.setProchain(multiplication);
        multiplication.setProchain(division);
        
        this.debut = addition;
    }
    
    public int trouverOperateur(String equation) {
        return debut.traiter(equation);
    }
}
