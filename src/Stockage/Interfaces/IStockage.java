package Stockage.Interfaces;

public interface IStockage {
    boolean existe(String nom);
    Double obtenir(String nom);
}
