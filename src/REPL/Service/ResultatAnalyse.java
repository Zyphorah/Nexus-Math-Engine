package REPL.Service;

import java.util.List;
import java.util.Set;

public class ResultatAnalyse {
    
    private final int _countPlus;
    private final int _countMoins;
    private final int _countDiv;
    private final int _countMult;
    private final List<String> _nombres;
    private final Set<String> _variables;
    private final Set<String> _constantes;
    
    public ResultatAnalyse(int countPlus, int countMoins, int countDiv, int countMult,
                           List<String> nombres, Set<String> variables, Set<String> constantes) {
        this._countPlus = countPlus;
        this._countMoins = countMoins;
        this._countDiv = countDiv;
        this._countMult = countMult;
        this._nombres = nombres;
        this._variables = variables;
        this._constantes = constantes;
    }
    
    public void afficher() {
        System.out.println(this._countPlus + " + " + this._countMoins + " - " + this._countDiv + " / " + this._countMult + " *");
        System.out.println(this._nombres.size() + " nombres: " + String.join(" ", this._nombres));
        System.out.println(this._variables.size() + " variables: " + String.join(" ", this._variables));
        System.out.println(this._constantes.size() + " constantes: " + String.join(" ", this._constantes));
    }
}
