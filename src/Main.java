import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Interpreteur.Adition;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Manufacture.Registre.RegistreSymbole;
import Interpreteur.Manufacture.Registre.Interfaces.IRegisteSymbole;
import Utils.Parse.Expression.ExpressionParse;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;
import Utils.Parse.Expression.Interfaces.IExpressionParse;

// Faire le système de parser pour l'opération des parentèses

// Priorité des opérations
/*
    Les Parenthèses

    Les Multiplications et les Divisions (de la gauche vers la droite)

    Les Additions et les Soustractions (de la gauche vers la droite)
 */

public class Main {
    public static void main(String[] args) {

        String equation = "((4 + 2) + 2) + 4";
        String equationSimple = "53-2+2/2";
        List<Character> filtreIgnoreCaractere = Arrays.asList(' ');

        IRegisteSymbole registeSymbole = new RegistreSymbole();
        List<Map<Character,INoeudFactory>> symboleMap = registeSymbole.creerSymbole();

        // Parser l'équation en nombres et opérateurs séparés
        List<IExpression> noeudsList = new ArrayList<>();
        List<Character> operateurs = new ArrayList<>();
        
        IExpressionParse expressionParse  = new ExpressionParse();
        List<Double> nombres = new ArrayList<>();
        expressionParse.parserEquation(equationSimple, nombres, operateurs);
        
        // Initialiser la liste de nœuds avec les nombres
        for (double nombre : nombres) {
            noeudsList.add(new Adition(nombre));
        }
        
        // Traiter les priorités du PLUS élevé au MOINS élevé
        IExpression noeudFinal = null;
        
        for (int niveau = symboleMap.size() - 1; niveau >= 0; niveau--) {
            Map<Character, INoeudFactory> opsNiveau = symboleMap.get(niveau);
            
            // Parcourir les opérateurs de gauche à droite, en cherchant ceux du niveau actuel
            int i = 0;
            while (i < operateurs.size()) {
                char op = operateurs.get(i);
                
                if (opsNiveau.containsKey(op)) {
                    IExpression gauche = noeudsList.get(i);
                    IExpression droite = noeudsList.get(i + 1);
                    
                    IExpression noeud = opsNiveau.get(op).creerNoeud();
                    noeud.ajouterExpression(gauche, droite);
                    
                    // Remplacer dans la liste par le nouveau nœud
                    noeudsList.set(i, noeud);
                    noeudsList.remove(i + 1);
                    operateurs.remove(i);
                } else {
                    i++; 
                }
            }
        }
        
        noeudFinal = noeudsList.get(0);
        
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
