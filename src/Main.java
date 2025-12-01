
import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Registre.RegistreSymbole;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.ParentheseService;

public class Main {
   
    public static void main(String[] args) {

        String equationSimple = "((3 + 5) * (2 - 8)) / 2";
        String a = "10";
        
        IRegistreSymbole symboleMaps = new RegistreSymbole();
        ParentheseService parentheseService  = new ParentheseService();
        ChaineOperateurs chaineOperateurs = new ChaineOperateurs(parentheseService);

        ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation(chaineOperateurs, symboleMaps, parentheseService);

        IExpression noeudFinal = constructeurArbreEquation.construire(equationSimple);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
