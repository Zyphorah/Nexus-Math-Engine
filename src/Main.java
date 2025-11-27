import Interpreteur.Adition;
import Interpreteur.Interfaces.IExpression;
/*
    Les Parenthèses

    Les Multiplications et les Divisions (de la gauche vers la droite)

    Les Additions et les Soustractions (de la gauche vers la droite)
 */


public class Main {
    public static void main(String[] args) {

        // Construction d'un arbre pour 4 + 2 + 2 + 4 = 12
        // Arbre: ((4 + 2) + 2) + 4
        
        IExpression quatre = new Adition(4);
        IExpression deux1 = new Adition(2);
        IExpression deux2 = new Adition(2);
        IExpression quatre2 = new Adition(4);
        
        // Première addition: 4 + 2
        IExpression add1 = new Adition();
        add1.ajouterExpression(quatre, deux1);
        
        // Deuxième addition: (4 + 2) + 2
        IExpression add2 = new Adition();
        add2.ajouterExpression(add1, deux2);

        // Troisième addition ((4 + 2) + 2) + 4
        IExpression add3 = new Adition();
        add3.ajouterExpression(add2, quatre2);
        
        System.out.println(add3.Resoudre());
    }
}
