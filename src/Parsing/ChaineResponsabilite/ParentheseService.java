package Parsing.ChaineResponsabilite;

import Interpreteur.Registre.RegistreSymbole;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;

public class ParentheseService implements IParentheseHandler {
    
    private final IRegistreSymbole _registreSymbole;

    public ParentheseService() {
        this._registreSymbole = new RegistreSymbole();
    }
    
     //Enlève les parenthèses qui englobent toute l'expression
     //Ex: "(1+2)" -> "1+2", mais "(1+2)*(3+4)" reste inchangé
    public String enleverParenthesesEnglobantes(String equation) {
        equation = equation.trim();
        
        while (equation.startsWith("(") && equation.endsWith(")")) 
        {
            if (estEnglobante(equation)) 
            {
                equation = equation.substring(1, equation.length() - 1).trim();
            } else 
            {
                break;
            }
        }
        return equation;
    }
    
    //Vérifie si les parenthèses externes englobent toute l'expression
    private boolean estEnglobante(String equation) {
        int profondeur = 0;
        for (int i = 0; i < equation.length() - 1; i++) {
            char c = equation.charAt(i);
            profondeur = calculerProfondeur(c, profondeur);
            if (profondeur == 0) 
            {
                return false;
            }
        }
        return true;
    }

    //Trouve le dernier opérateur au niveau zéro (hors parenthèses)
    public int trouverDernierAuNiveauZero(String equation, char operateur) {
        int profondeur = 0;
        for (int i = equation.length() - 1; i >= 0; i--) 
        {
            char c = equation.charAt(i);
            profondeur = calculerProfondeurInverse(c, profondeur);
            if (c == operateur && profondeur == 0) 
            {
                // Ignorer si c'est un signe unaire (début d'expression ou après un opérateur/parenthèse)
                if (estSigneUnaire(equation, i)) {
                    continue;
                }
                if(i != 0)
                {
                    if(operateur == equation.charAt(i-1))
                    {
                        return i-1;
                    }
                }
                return i;
            }
        }
        return -1;
    }

    //Vérifie si le caractère à la position donnée est un signe unaire (+ ou -)
    private boolean estSigneUnaire(String equation, int index) {
        if (index == 0) {
            return true; // Au début de l'expression, c'est un signe unaire
        }
        char precedent = equation.charAt(index - 1);
        // C'est un signe unaire si le caractère précédent est un opérateur ou une parenthèse ouvrante
        
        if (this._registreSymbole.estOperateur(precedent)) {
            return true;
        } else if (precedent == '(') {
            return true;
        }
        return false;
    }

    //Calcule la profondeur des parenthèses (parcours gauche à droite)
    private int calculerProfondeur(char c, int profondeur) {
        if (c == '(') {
            return profondeur + 1;
        } else if (c == ')') {
            return profondeur - 1;
        }
        return profondeur;
    }

    //Calcule la profondeur des parenthèses (parcours droite à gauche)
    private int calculerProfondeurInverse(char c, int profondeur) {
        if (c == ')') {
            return profondeur + 1;
        } else if (c == '(') {
            return profondeur - 1;
        }
        return profondeur;
    }
}
