import java.util.ArrayList;
import java.util.List;

import Interpreteur.Operation;
import Interpreteur.Registre.RegistreSymbole;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.Operateur;
import Parsing.ChaineResponsabilite.OperateurHandler;
import Parsing.ChaineResponsabilite.ParentheseService;
import REPL.REPL;

public class Main {
    public static void main(String[] args) {

        List<OperateurHandler> operateurs = creerOperateurHandler();
        IRegistreSymbole registreSymbole = creerRegistreSymbole();

        ParentheseService parentheseService = new ParentheseService(registreSymbole);
        ChaineOperateurs chaineOperateurs = new ChaineOperateurs(parentheseService, operateurs);


        new REPL(registreSymbole, chaineOperateurs).lancerREPL();
    }
    
    private static IRegistreSymbole creerRegistreSymbole() {
        RegistreSymbole registre = new RegistreSymbole();
        
        registre.enregistrer('+', () -> new Operation((a, b) -> a + b));
        registre.enregistrer('-', () -> new Operation((a, b) -> a - b));
        registre.enregistrer('*', () -> new Operation((a, b) -> a * b));
        registre.enregistrer('/', () -> new Operation((a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Division par zéro détectée");
            }
            return a / b;
        }));
        
        return registre;
    }

    private static List<OperateurHandler> creerOperateurHandler()
    {
        List<OperateurHandler> operateurs = new ArrayList<>();
        // Construire la chaîne dans l'ordre de priorité
        OperateurHandler addition = new OperateurHandler(Operateur.ADDITION);
        OperateurHandler soustraction = new OperateurHandler(Operateur.SOUSTRACTION);
        OperateurHandler multiplication = new OperateurHandler(Operateur.MULTIPLICATION);
        OperateurHandler division = new OperateurHandler(Operateur.DIVISION);

        operateurs.add(addition);
        operateurs.add(soustraction);
        operateurs.add(multiplication);
        operateurs.add(division);

        return operateurs;
    }
}
