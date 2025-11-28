import java.util.List;
import java.util.Map;

import Interpreteur.Valeur;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Manufacture.Registre.RegistreSymbole;
import Interpreteur.Manufacture.Registre.Interfaces.IRegisteSymbole;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

// Faire le système de parser pour l'opération des parentèses

// Priorité des opérations
/*
    Les Parenthèses

    Les Multiplications et les Divisions (de la gauche vers la droite)

    Les Additions et les Soustractions (de la gauche vers la droite)
 */

public class Main {
    private static IRegisteSymbole registeSymbole = new RegistreSymbole();
    private static Map<Character,INoeudFactory> symboleMap = registeSymbole.creerSymbole();

   private static IExpression interpreter(String equationSimple) {

        int index = -1;
        
        if (equationSimple.indexOf('+') !=  -1)
        {
            index = equationSimple.indexOf('+');
        }
        else if (equationSimple.indexOf('-') != -1)
        {
            index = equationSimple.indexOf('-');
        }
        else if (equationSimple.indexOf('*') != -1)
        {
            index = equationSimple.indexOf('*');
        }
        else if (equationSimple.indexOf('/') != -1) 
        {
            index = equationSimple.indexOf('/');
        }
        
        
        // Si pas d'opérateur trouvé, parser avec une valeur simple
        if (index == -1) {
            Double valeur = Double.parseDouble(equationSimple.trim());
            return new Valeur(valeur);
        }
        
        String partieGauche = equationSimple.substring(0, index).trim();
        String partieDroite = equationSimple.substring(index + 1).trim();
        
        IExpression noeudGauche = interpreter(partieGauche);
        IExpression noeudDroit = interpreter(partieDroite);

        IArbre noeud = symboleMap.get(equationSimple.charAt(index)).creerNoeud();
        
        noeud.ajouterExpression(noeudGauche, noeudDroit);
        
        return noeud;
    }
    public static void main(String[] args) {

        String equationSimple = "53+2+2*4";
        IExpression noeudFinal = interpreter(equationSimple);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
