package MathVM;

class CPU {
    //--------------------------------------------------------------------------
    // Le CPU a besoin de 2 instances pour travailler (injection de déps)
    // On doit lui founir une instance d'une classe concrète implémentant l'interface IVarStockage
    // et une autre implémentant l'interface ISortie.
    public CPU(IVarStockage stockage, ISortie sortie) {
        stockage_ = stockage;
        sortie_ = sortie;
    }

    //--------------------------------------------------------------------------
    // La seule méthode publique du CPU : executer un programme.
    // IProgramme est le programme à exécuter.
    // Le booléen terminerSurErreur indique au CPU s'il doit abandonner l'exécution
    // du programme s'il rencontre une erreur. Les interpréteurs en mode REPL
    // ne quitte pas l'exécution même si une erreur s'est produite.
    // Valeur de retour: un booléen représentant si l'exécution s'est déroulée sans erreur.
    public boolean executer(IProgramme programme, boolean terminerSurErreur) {
        stockage_.reboot();

        while (!programme.fin()) {
            String operation = programme.prochaineInstruction();
            if (estFin(operation)) {
                return true;
            }

            boolean execOK = executerOperation(operation);
            if (!execOK) {
                System.out.println("ERREUR: " + operation);
                if (terminerSurErreur) {
                    return false;
                }
            }
        }

        return true;
    }

    //--------------------------------------------------------------------------
    // Implémentation privée de la classe
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    private boolean executerOperation(String op) {
        op = op.trim();
        if (op.length() == 0)  // Sauter les lignes vides
        {
            return true;
        }

        StringBuilder expr = new StringBuilder();
        StringBuilder dst = new StringBuilder();
        if (estAssignation(op, dst, expr)) {
            final float valeur = eval(expr.toString());
            if (dst.length() > 0 && !Float.isNaN(valeur)) {
                stockage_.set(dst.toString(), valeur);
                return true;
            }
            return false;
        } else if (estPrint(op, expr)) {
            sortie_.ecrire(Float.toString(eval(expr.toString())));
            return true;
        }

        return false;
    }

    private boolean estFin(String expression) {
        return expression.trim().equals("fin");
    }

    private boolean estPrint(String expression, StringBuilder txt) {
        final int indexEspace = expression.indexOf(" ");
        if (indexEspace >= 0) {
            if (expression.substring(0, indexEspace).trim().equals("print")) {
                txt.append(expression.substring(indexEspace).trim());
                return true;
            }
        }
        return false;
    }

    private boolean estAssignation(String expression, StringBuilder varDest, StringBuilder exprValeur) {
        final int indexAssignation = expression.indexOf("=");
        if (indexAssignation >= 0) {
            try {
                varDest.append(expression.substring(0, indexAssignation).trim());
                exprValeur.append(expression.substring(indexAssignation + 1).trim());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private boolean estVariable(String expression) {
        return !Float.isNaN(stockage_.get(expression));
    }

    private boolean contientOperateur(String expression, StringBuilder gauche, StringBuilder operateur, StringBuilder droite) {
        int indexOp = expression.indexOf("*");
        if (indexOp < 0) {
            indexOp = expression.indexOf("/");
        }
        if (indexOp < 0) {
            indexOp = expression.indexOf("+");
        }
        if (indexOp < 0) {
            indexOp = expression.indexOf("-");
        }

        if (indexOp >= 0) {
            gauche.append(expression.substring(0, indexOp).trim());
            operateur.append(expression.substring(indexOp, indexOp + 1).trim());
            droite.append(expression.substring(indexOp + 1).trim());
            return true;
        }
        return false;
    }

    private float eval(String expression) {
        // Variable
        if (estVariable(expression)) {
            return stockage_.get(expression);
        }

        // Expression avec opérateur
        StringBuilder gauche = new StringBuilder();
        StringBuilder operateur = new StringBuilder();
        StringBuilder droite = new StringBuilder();
        if (contientOperateur(expression, gauche, operateur, droite)) {
            final float g = eval(gauche.toString());
            final float d = eval(droite.toString());
            switch (operateur.toString()) {
                case "+":
                    return g + d;
                case "-":
                    return g - d;
                case "*":
                    return g * d;
                case "/":
                    return g / d;
                default:
                    return Float.NaN;
            }
        }

        // Un littéral numérique
        try {
            return Float.parseFloat(expression);
        } catch (Exception e) {
            return Float.NaN;
        }
    }

    private boolean estValeur(String expression) {
        try {
            final float valeur = Float.parseFloat(expression);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    //--------------------------------------------------------------------------
    private IVarStockage stockage_ = null;
    private ISortie sortie_ = null;
}
