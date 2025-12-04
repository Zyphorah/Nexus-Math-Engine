package REPL.Commande;

import java.util.function.Supplier;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import REPL.Service.GestionnaireVariable;

public class Variable implements ICommande {

    private final Historique _historique;
    private final GestionnaireVariable _gestionnaire;
    private final Supplier<String> _argumentsSupplier;

    public Variable(GestionnaireVariable gestionnaire, Historique historique, Supplier<String> argumentsSupplier) {
        this._historique = historique;
        this._gestionnaire = gestionnaire;
        this._argumentsSupplier = argumentsSupplier;
    }
    
    @Override
    public void execute() {
        String arguments = this._argumentsSupplier.get();
        
        this._gestionnaire.traiterSaisie(arguments);
        this._historique.ajouter("var " + (arguments != null ? arguments : ""));
    }
}
