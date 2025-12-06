package REPL.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Stockage.Interfaces.IConstanteStockage;

public class AnalyseurExpression {
    
    private static final Pattern PATTERN_NOMBRE = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");
    private static final Pattern PATTERN_IDENTIFIANT = Pattern.compile("[a-zA-Z_]\\w*");
    
    private final IConstanteStockage _stockageConstante;
    private final IRegistreSymbole _registreSymbole;
    
    public AnalyseurExpression(IConstanteStockage stockageConstante, IRegistreSymbole registreSymbole) {
        this._stockageConstante = stockageConstante;
        this._registreSymbole = registreSymbole;
    }
    
    public ResultatAnalyse analyser(String expression) {
        return new ResultatAnalyse(
            compterOperateurs(expression),
            extraireNombres(expression),
            extraireVariables(expression),
            extraireConstantes(expression)
        );
    }
    
    private Map<Character, Integer> compterOperateurs(String expression) {
        Map<Character, Integer> comptages = new LinkedHashMap<>();
        
        // Initialiser tous les opérateurs du registre
        for (char symbole : _registreSymbole.obtenirSymboles()) {
            comptages.put(symbole, 0);
        }
        
        // Compter les occurrences
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (comptages.containsKey(c)) {
                comptages.put(c, comptages.get(c) + 1);
            }
        }
        
        return comptages;
    }
    
    private List<String> extraireNombres(String expression) {
        List<String> nombres = new ArrayList<>();
        Matcher matcher = PATTERN_NOMBRE.matcher(expression);
        while (matcher.find()) {
            nombres.add(matcher.group());
        }
        return nombres;
    }
    
    private Set<String> extraireVariables(String expression) {
        Set<String> variables = new HashSet<>();
        Matcher matcher = PATTERN_IDENTIFIANT.matcher(expression);
        while (matcher.find()) {
            String nom = matcher.group();
            if (!this._stockageConstante.existe(nom)) {
                variables.add(nom);
            }
        }
        return variables;
    }
    
    private Set<String> extraireConstantes(String expression) {
        Set<String> constantes = new HashSet<>();
        Matcher matcher = PATTERN_IDENTIFIANT.matcher(expression);
        while (matcher.find()) {
            String nom = matcher.group();
            if (this._stockageConstante.existe(nom)) {
                constantes.add(nom);
            }
        }
        return constantes;
    }
}
