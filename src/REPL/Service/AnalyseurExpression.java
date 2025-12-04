package REPL.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Stockage.Interfaces.IConstanteStockage;

public class AnalyseurExpression {
    
    private static final Pattern PATTERN_NOMBRE = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");
    private static final Pattern PATTERN_IDENTIFIANT = Pattern.compile("[a-zA-Z_]\\w*");
    
    private final IConstanteStockage _stockageConstante;
    
    public AnalyseurExpression(IConstanteStockage stockageConstante) {
        this._stockageConstante = stockageConstante;
    }
    
    public ResultatAnalyse analyser(String expression) {
        return new ResultatAnalyse(
            compterCaractere(expression, '+'),
            compterCaractere(expression, '-'),
            compterCaractere(expression, '/'),
            compterCaractere(expression, '*'),
            extraireNombres(expression),
            extraireVariables(expression),
            extraireConstantes(expression)
        );
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
    
    private int compterCaractere(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) count++;
        }
        return count;
    }
}
