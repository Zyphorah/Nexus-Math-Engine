package Parsing.ChaineResponsabilite;

import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;

public class ParentheseService implements IParentheseHandler {
    
    private final IRegistreSymbole _registreSymbole;

    public ParentheseService(IRegistreSymbole registreSymbole) {
        this._registreSymbole = registreSymbole;
    }
    
     //Enlève les parenthèses qui englobent toute l'expression
     //Ex: "(1+2)" -> "1+2", mais "(1+2)*(3+4)" reste inchangé
    public String enleverParenthesesEnglobantes(String equation) {
        equation = equation.trim();
        while (equation.startsWith("(") && equation.endsWith(")") && estEnglobante(equation)) {
            equation = equation.substring(1, equation.length() - 1).trim();
        }
        return equation;
    }
    
    private boolean estEnglobante(String equation) {
        int profondeur = 0;
        for (int i = 0; i < equation.length() - 1; i++) {
            profondeur += (equation.charAt(i) == '(' ? 1 : equation.charAt(i) == ')' ? -1 : 0);
            if (profondeur == 0) return false;
        }
        return true;
    }

    public int trouverDernierAuNiveauZero(String equation, char operateur) {
        int profondeur = 0;
        for (int i = equation.length() - 1; i >= 0; i--) {
            char c = equation.charAt(i);
            profondeur += (c == ')' ? 1 : c == '(' ? -1 : 0);
            
            if (c == operateur && profondeur == 0 && !estSigneUnaire(equation, i)) {
                if (i != 0 && operateur == equation.charAt(i - 1)) {
                    return i - 1;
                }
                return i;
            }
        }
        return -1;
    }

    private boolean estSigneUnaire(String equation, int index) {
        if (index == 0) return true;
        char precedent = equation.charAt(index - 1);
        return this._registreSymbole.estOperateur(precedent) || precedent == '(';
    }
}
