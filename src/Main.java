import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Interpreteur.Operation;
import Interpreteur.Registre.RegistreSymbole;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.OperateurHandler;
import Parsing.ChaineResponsabilite.ParentheseService;
import REPL.REPL;
import REPL.DetecteurSaisie;
import REPL.Historique.Historique;
import REPL.Registre.RegistreCommande;
import REPL.Registre.Factory.RegistreurCommande;
import REPL.Commande.*;
import REPL.Service.AnalyseurExpression;
import REPL.Service.EvaluateurExpression;
import REPL.Service.GestionnaireVariable;
import Stockage.StockageConstante;
import Stockage.StockageVariable;
import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class Main {
    public static void main(String[] args) {
        
        IRegistreSymbole registreSymbole = creerRegistreSymbole();
        
        // Créer les opérateurs et la chaîne
        List<OperateurHandler> operateurs = creerOperateurHandler(registreSymbole);
        ParentheseService parentheseService = new ParentheseService(registreSymbole);
        ChaineOperateurs chaineOperateurs = new ChaineOperateurs(parentheseService, operateurs);
        
        // Créer les objets de stockage
        Historique historique = new Historique();
        IVarStockage stockageVariable = new StockageVariable();
        IConstanteStockage stockageConstante = new StockageConstante();
        
        // Charger les constantes au démarrage
        stockageConstante.charger("constantes.txt");
        
        // Créer les objets réutilisables pour substitution et construction
        Stockage.ResolveurValeur resolveurValeur = new Stockage.ResolveurValeur(stockageVariable, stockageConstante);
        Stockage.SubstituteurVariable substituteurVariable = new Stockage.SubstituteurVariable(resolveurValeur);
        Interpreteur.ConstructeurEquation.ConstructeurArbreEquation constructeurArbreEquation = new Interpreteur.ConstructeurEquation.ConstructeurArbreEquation(chaineOperateurs, registreSymbole, parentheseService);
        
        // Créer l'analyseur et l'évaluateur
        AnalyseurExpression analyseur = new AnalyseurExpression(stockageConstante, registreSymbole);
        EvaluateurExpression evaluateur = new EvaluateurExpression(constructeurArbreEquation, substituteurVariable);
        
        // Créer le gestionnaire de variables
        GestionnaireVariable gestionnaireVariable = new GestionnaireVariable(
                stockageVariable, stockageConstante, evaluateur);
        
        // === ENREGISTREMENT DES COMMANDES VIA REGISTREUR ===
        RegistreurCommande registreurCommande = new RegistreurCommande();
        
        // Créer un supplier pour les arguments (mis à jour par REPL)
        java.util.concurrent.atomic.AtomicReference<String> arguments = new java.util.concurrent.atomic.AtomicReference<>("");
        
        // Enregistrer toutes les commandes avec injection de dépendances
        registreurCommande.enregistrer("aide", () -> new Aide(historique));
        registreurCommande.enregistrer("histoire", () -> new Histoire(historique));
        registreurCommande.enregistrer("analyse", () -> new Analyse(historique, analyseur, arguments::get));
        registreurCommande.enregistrer("calculer", () -> new Calculer(historique, substituteurVariable, constructeurArbreEquation, arguments::get));
        registreurCommande.enregistrer("constantes", () -> new ChargerConstance(historique, stockageConstante, arguments::get));
        registreurCommande.enregistrer("var", () -> new Variable(gestionnaireVariable, historique, arguments::get));
        registreurCommande.enregistrer("vars", () -> new VariablesPluriel(stockageVariable, stockageConstante, historique));
        
        // Créer le registre de commandes
        RegistreCommande registreCommande = new RegistreCommande(registreurCommande);
        
        // Créer les objets REPL nécessaires
        DetecteurSaisie detecteur = new DetecteurSaisie(registreCommande);
        Scanner scanner = new Scanner(System.in);
        
        // Lancer la REPL
        REPL repl = new REPL(registreCommande, detecteur, scanner, arguments::set);
        repl.lancerREPL();
        
        scanner.close();
    }
    
    private static IRegistreSymbole creerRegistreSymbole() {
        RegistreSymbole registre = new RegistreSymbole();
        // L'ordre d'enregistrement détermine la priorité
        // Premier enregistré = PRIORITÉ BASSE
        // Dernier enregistré = PRIORITÉ HAUTE  
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

    private static List<OperateurHandler> creerOperateurHandler(IRegistreSymbole registreSymbole) {
        List<OperateurHandler> operateurs = new ArrayList<>();
        
        // Générer automatiquement la chaîne depuis l'ordre d'enregistrement du registre
        for (char symbole : registreSymbole.obtenirSymboles()) {
            operateurs.add(new OperateurHandler(symbole));
        }

        return operateurs;
    }
}
