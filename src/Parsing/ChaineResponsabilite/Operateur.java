package Parsing.ChaineResponsabilite;

public enum Operateur {
    ADDITION('+'),
    SOUSTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/');
    
    private final char symbole;
    
    Operateur(char symbole) {
        this.symbole = symbole;
    }
    
    public char getSymbole() {
        return symbole;
    }
}
