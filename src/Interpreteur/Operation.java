package Interpreteur;
import java.util.function.BiFunction;
import Interpreteur.Interfaces.INoeud;
import Interpreteur.Interfaces.IExpression;

public class Operation implements INoeud {
    private IExpression _droite; 
    private IExpression _gauche;
    private BiFunction<Double, Double, Double> _operateur;

    public Operation(BiFunction<Double, Double, Double> operateur) {
        this._operateur = operateur;
    }

    @Override
    public INoeud ajouterExpression(IExpression gauche, IExpression droite) {
        if (droite == null || gauche == null) {
            throw new IllegalArgumentException("Les noeuds fournis sont nuls");
        }
        if (this._droite == null && this._gauche == null) {
            this._gauche = gauche;
            this._droite = droite;
        } else {
            throw new IllegalStateException("Les noeuds existent déjà pour cette opération");
        }
        return this;
    }

    @Override
    public Double Resoudre() {
        return this._operateur.apply(this._gauche.Resoudre(), this._droite.Resoudre()); 
    }
}
