package Parsing.ChaineResponsabilite;

import Parsing.ChaineResponsabilite.Interfaces.IOperateurHandler;

public class ChaineOperateurs {
    private IOperateurHandler debut;
    private final ParentheseService _parentheseService;
    
    public ChaineOperateurs(ParentheseService parentheseService) {
        this._parentheseService = parentheseService;
        
        // Construire la chaîne dans l'ordre de priorité
        AdditionHandler addition = new AdditionHandler();
        SoustractionHandler soustraction = new SoustractionHandler();
        MultiplicationHandler multiplication = new MultiplicationHandler();
        DivisionHandler division = new DivisionHandler();
        
        // Injecter le ParentheseService
        addition.setParentheseService(this._parentheseService);
        soustraction.setParentheseService(this._parentheseService);
        multiplication.setParentheseService(this._parentheseService);
        division.setParentheseService(this._parentheseService);
        
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
