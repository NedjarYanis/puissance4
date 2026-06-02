import java.util.ArrayList;
import java.util.List;

public class Puissance4 {
    private List<List<Integer>> grille; // 0: Vide, 1: Rouge, 2: Jaune
    private int joueurCourant;
    private int nbJetonsPlaces;

    public Puissance4() {
        this.reinitialiser();
    }

    public void reinitialiser() {
        this.joueurCourant = 1;
        this.nbJetonsPlaces = 0;
        this.grille = new ArrayList<>();
        
        for (int i = 0; i < 6; i++) {
            List<Integer> ligne = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                ligne.add(0); 
            }
            this.grille.add(ligne);
        }
    }

    public boolean jouerJeton(int colonne) {
        // Cherche la case vide en partant du bas
        for (int ligne = 5; ligne >= 0; ligne--) {
            if (this.grille.get(ligne).get(colonne) == 0) {
                this.grille.get(ligne).set(colonne, this.joueurCourant);
                this.nbJetonsPlaces++;
                return true;
            }
        }
        return false; 
    }

    public void changerJoueur() {
        if (this.joueurCourant == 1) {
            this.joueurCourant = 2;
        } else {
            this.joueurCourant = 1;
        }
    }

    public boolean estPlein() {
        return this.nbJetonsPlaces == 42;
    }

    // on vérifie chaque case une par une
    public boolean estGagne() {
        for (int ligne = 0; ligne < 6; ligne++) {
            for (int col = 0; col < 7; col++) {
                int couleur = this.grille.get(ligne).get(col);
                
                // Si la case n'est pas vide, on vérifie si 4 jetons s'alignent
                if (couleur != 0) {
                    
                    // Vérification Horizontale (vers la droite)
                    if (col + 3 < 7) {
                        if (this.grille.get(ligne).get(col + 1) == couleur &&
                            this.grille.get(ligne).get(col + 2) == couleur &&
                            this.grille.get(ligne).get(col + 3) == couleur) {
                            return true;
                        }
                    }
                    
                    // Vérification Verticale (vers le bas)
                    if (ligne + 3 < 6) {
                        if (this.grille.get(ligne + 1).get(col) == couleur &&
                            this.grille.get(ligne + 2).get(col) == couleur &&
                            this.grille.get(ligne + 3).get(col) == couleur) {
                            return true;
                        }
                    }
                    
                    // Vérification Diagonale (vers le bas à droite)
                    if (ligne + 3 < 6 && col + 3 < 7) {
                        if (this.grille.get(ligne + 1).get(col + 1) == couleur &&
                            this.grille.get(ligne + 2).get(col + 2) == couleur &&
                            this.grille.get(ligne + 3).get(col + 3) == couleur) {
                            return true;
                        }
                    }
                    
                    // Vérification Diagonale (vers le haut à droite)
                    if (ligne - 3 >= 0 && col + 3 < 7) {
                        if (this.grille.get(ligne - 1).get(col + 1) == couleur &&
                            this.grille.get(ligne - 2).get(col + 2) == couleur &&
                            this.grille.get(ligne - 3).get(col + 3) == couleur) {
                            return true;
                        }
                    }
                }
            }
        }
        return false; 
    }

    public List<List<Integer>> getGrille() { return this.grille; }
    public int getJoueurCourant() { return this.joueurCourant; }
}