package Parsing.ChaineResponsabilite;

import java.util.Set;

import Parsing.ChaineResponsabilite.Interfaces.IOperateurHandler;

public class ChaineOperateurs {
    private IOperateurHandler debut;
    
    public ChaineOperateurs(Set<Character> operateurs) {
        ParentheseService parentheseService = new ParentheseService(operateurs);
        
        // Construire la chaîne dans l'ordre de priorité
        AdditionHandler addition = new AdditionHandler();
        SoustractionHandler soustraction = new SoustractionHandler();
        MultiplicationHandler multiplication = new MultiplicationHandler();
        DivisionHandler division = new DivisionHandler();
        
        // Injecter le ParentheseService
        addition.setParentheseService(parentheseService);
        soustraction.setParentheseService(parentheseService);
        multiplication.setParentheseService(parentheseService);
        division.setParentheseService(parentheseService);
        
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
