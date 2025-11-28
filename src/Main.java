import java.util.Map;

import Interpreteur.Valeur;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Factory.Registre.RegistreSymbole;
import Interpreteur.Factory.Registre.Interfaces.IRegisteSymbole;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Interfaces.IExpression;
import Parsing.ChaineOperateurs;

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
    private static ChaineOperateurs chaineOperateurs = new ChaineOperateurs();

   private static IExpression interpreter(String equationSimple,int niveau) {

        int index = chaineOperateurs.trouverOperateur(equationSimple);
        
        // Si pas d'opérateur trouvé, parser avec une valeur simple
        if (index == -1) {
            Double valeur = Double.parseDouble(equationSimple.trim());
            return new Valeur(valeur);
        }
        
        String partieGauche = equationSimple.substring(0, index).trim();
        String partieDroite = equationSimple.substring(index + 1).trim();
        
        IExpression noeudGauche = interpreter(partieGauche,niveau);
        IExpression noeudDroit = interpreter(partieDroite,niveau);

        IArbre noeud = symboleMap.get(equationSimple.charAt(index)).creerNoeud();
        
        noeud.ajouterExpression(noeudGauche, noeudDroit);
        
        return noeud;
    }
    public static void main(String[] args) {

        String equationSimple = "2*53+2*4+2";
        IExpression noeudFinal = interpreter(equationSimple,0);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
