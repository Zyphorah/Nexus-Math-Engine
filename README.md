# Nexus Math Engine (NME)

## 1. Overview

Nexus Math Engine is a command-line (CLI) mathematical expression interpreter developed in Java. It transforms character strings into recursive syntax trees for immediate evaluation.

## 2. Technical Architecture

The project follows SOLID principles and uses several design patterns to ensure component decoupling:

* **Composite & Interpreter**: Models equations as trees via the `INoeud` and `IExpression` interfaces.
* **Chain of Responsibility**: Manages operator precedence (+, -, *, /) via `ChaineOperateurs` and `OperateurHandler`.
* **Command Pattern**: Encapsulates user actions in classes implementing `ICommande`.
* **Dependency Injection**: Assembles components in the `Main` class for maximum decoupling.

## 3. Features

* **Expression evaluation**: Supports standard arithmetic operators and parentheses.
* **Variable management**: Assign values to variables for use in subsequent calculations.
* **Constant packages**: Load immutable constants from external text files (e.g., `pi`, `g`).
* **Persistent history**: Stores the last 20 expressions in `historique.txt`, persisting after closing.
* **Syntax analysis**: `analyse` command counts operators, numbers, variables, and constants used.

## 4. Storage System Specifications

* **Variables**: Variable names must be at least one character, not start with a digit, and contain no spaces.
* **Constants**: Visually distinct from variables (prefix `[const]`) and immutable after loading.

## 5. Installation and Usage

1. **Compile**: `javac -d bin src/**/*.java`
2. **Run**: `java -cp bin Main`
3. **Key commands**:
* `calculer <expr>`: Direct calculation.
* `var <name>=<val>`: Assignment.
* `analyse <expr>`: Expression statistics.
* `histoire`: Show history.
* `quitter`: Exit the program.

## 6. Known Limitations

* **History rotation**: The oldest expression is automatically deleted once the 20-expression limit is reached.
* **Constant precedence**: If there is a name conflict in loaded files, the last value read is kept.
