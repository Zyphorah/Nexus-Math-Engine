import java.util.Map;

import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Factory.Registre.RegistreSymbole;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Interfaces.IExpression;
import Parsing.ChaineResponsabilite.ChaineOperateurs;

public class Main {
   
    public static void main(String[] args) {

        String equationSimple = "((3 + 5) * (2 - 8)) / 2";
        String a = "+10--10";
        
        Map<Character, INoeudFactory> symboleMaps = new RegistreSymbole().creerSymbole();
        ChaineOperateurs chaineOperateurs = new ChaineOperateurs(symboleMaps.keySet());
        ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation(chaineOperateurs, symboleMaps);

        IExpression noeudFinal = constructeurArbreEquation.construire(a);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
