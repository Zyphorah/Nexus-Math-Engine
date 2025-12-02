package REPL.Commande;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;

public class ChargerConstance implements ICommande  {
    private final Historique _historique; 

    public ChargerConstance(Historique historique)
    {
        this._historique = historique;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
