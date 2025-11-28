import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Factory.Registre.RegistreSymbole;
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
   
    public static void main(String[] args) {

        String equationSimple = "2*53+2*4+2";
        
        ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation( new ChaineOperateurs() ,new RegistreSymbole().creerSymbole());

        IExpression noeudFinal = constructeurArbreEquation.construire(equationSimple);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
