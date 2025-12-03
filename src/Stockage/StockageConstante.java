package Stockage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Stockage.Interfaces.IConstanteStockage;

public class StockageConstante implements IConstanteStockage {
    private final Map<String, Double> _constantes;
    
    public StockageConstante()
    {
        this._constantes = new HashMap<>();
    }
    
    public void charger(String nomFichier)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                ligne = ligne.trim();
                
                // Ignorer les lignes vides et les commentaires
                if (ligne.isEmpty() || ligne.startsWith("#")) continue;
                
                String[] parties = ligne.split("=", 2);
                if (parties.length == 2) {
                    String nom = parties[0].trim();
                    Double valeur = Double.parseDouble(parties[1].trim());
                    this._constantes.put(nom, valeur);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier: " + nomFichier);
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format dans le fichier: " + nomFichier);
        }
    }
    
    public Double obtenir(String constante)
    {
        if (this._constantes.containsKey(constante)) {
            return this._constantes.get(constante);
        }
        throw new IllegalArgumentException("Constante inconnue: " + constante);
    }
    
    public Map<String, Double> obtenirTout()
    {
        return new HashMap<>(this._constantes);
    }
    
    public boolean existe(String constante)
    {
        return this._constantes.containsKey(constante);
    }
}
