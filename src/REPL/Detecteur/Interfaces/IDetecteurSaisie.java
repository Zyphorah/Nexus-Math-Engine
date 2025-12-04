package REPL.Detecteur.Interfaces;

public interface IDetecteurSaisie {
    boolean correspond(String saisie);
    String getNomCommande(String saisie);
    String getArguments(String saisie);
}
