# Nexus Math Engine (NME)

## 1. Présentation

Nexus Math Engine est un interpréteur d'expressions mathématiques en ligne de commande (CLI) développé en Java. Il permet la transformation de chaînes de caractères en arbres syntaxiques récursifs pour une évaluation immédiate.

## 2. Architecture Technique

Le projet suit les principes SOLID et utilise plusieurs patrons de conception pour assurer le découplage des composants :

* **Composite & Interpréteur** : Modélisation de l'équation sous forme d'arbre via les interfaces `INoeud` et `IExpression`.
* **Chaîne de Responsabilité** : Gestion de la priorité des opérateurs arithmétiques (+, -, *, /) via `ChaineOperateurs` et `OperateurHandler`.
* **Command Pattern** : Encapsulation des actions utilisateur dans des classes implémentant `ICommande`.
* **Injection de Dépendances** : Assemblage des composants dans la classe `Main` pour un découplage maximal.

## 3. Fonctionnalités

* **Évaluation d'expressions** : Support des opérateurs arithmétiques usuels et des parenthèses.
* **Gestion des variables** : Affectation de valeurs à des variables utilisables dans des calculs ultérieurs.
* **Paquets de constantes** : Chargement de constantes immuables depuis des fichiers texte externes (ex: `pi`, `g`).
* **Historique permanent** : Conservation des 20 dernières expressions dans `historique.txt`, persistant après fermeture.
* **Analyse syntaxique** : Commande `analyse` pour le décompte des opérateurs, nombres, variables et constantes utilisés.

## 4. Spécifications du Système de Stockage

* **Variables** : Nom de variable d'au moins un caractère, ne débutant pas par un chiffre et sans espace.
* **Constantes** : Visuellement distinctes des variables (préfixe `[const]`) et non modifiables après chargement.

## 5. Installation et Utilisation

1. **Compilation** : `javac -d bin src/**/*.java`
2. **Exécution** : `java -cp bin Main`
3. **Commandes clés** :
* `calculer <expr>` : Calcul direct.
* `var <nom>=<val>` : Assignation.
* `analyse <expr>` : Statistiques de l'expression.
* `histoire` : Affichage de l'historique.
* `quitter` : Fermeture du programme.

## 6. Limitations connues

* **Rotation de l'historique** : Suppression automatique de la plus vieille expression une fois la limite de 20 atteinte.
* **Priorité des constantes** : En cas de conflit de nom dans les fichiers chargés, la dernière valeur lue est conservée.
