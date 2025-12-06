package REPL.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultatAnalyse {
    
    private final Map<Character, Integer> _comptesOperateurs;
    private final List<String> _nombres;
    private final Set<String> _variables;
    private final Set<String> _constantes;
    
    public ResultatAnalyse(Map<Character, Integer> comptesOperateurs,
                           List<String> nombres, Set<String> variables, Set<String> constantes) {
        this._comptesOperateurs = comptesOperateurs;
        this._nombres = nombres;
        this._variables = variables;
        this._constantes = constantes;
    }
    
    public void afficher() {
        // Afficher les opérateurs de manière dynamique
        StringBuilder operateursStr = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : _comptesOperateurs.entrySet()) {
            if (operateursStr.length() > 0) operateursStr.append(" ");
            operateursStr.append(entry.getValue()).append(" ").append(entry.getKey());
        }
        
        System.out.println(operateursStr.toString());
        System.out.println(this._nombres.size() + " nombres: " + String.join(" ", this._nombres));
        System.out.println(this._variables.size() + " variables: " + String.join(" ", this._variables));
        System.out.println(this._constantes.size() + " constantes: " + String.join(" ", this._constantes));
    }
}
