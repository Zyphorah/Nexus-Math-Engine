package Parsing;

import Parsing.Interfaces.IOperateurHandler;

public class ChaineOperateurs {
    private IOperateurHandler debut;
    
    public ChaineOperateurs() {
        // Construire la chaîne dans l'ordre de priorité
        IOperateurHandler addition = new AdditionHandler();
        IOperateurHandler soustraction = new SoustractionHandler();
        IOperateurHandler multiplication = new MultiplicationHandler();
        IOperateurHandler division = new DivisionHandler();
        
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
