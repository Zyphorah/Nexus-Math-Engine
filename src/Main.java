import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Factory.Registre.RegistreSymbole;
import Interpreteur.Interfaces.IExpression;
import Parsing.ChaineResponsabilite.ChaineOperateurs;

public class Main {
   
    public static void main(String[] args) {

        String equationSimple = "((3 + 5) * (2 - 8)) / 2";
        String a = "10--10";
        
        ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation( new ChaineOperateurs() ,new RegistreSymbole().creerSymbole());

        IExpression noeudFinal = constructeurArbreEquation.construire(a);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
