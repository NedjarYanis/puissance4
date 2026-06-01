import java.util.ArrayList;
import java.util.List;

public class Puissance4 {
    private List<List<Integer>> grille; // 0: Vide, 1: Bleu, 2: Rouge
    private int joueurCourant;

    public Puissance4() {
        this.reinitialiser();
    }

    public void reinitialiser() {
        this.joueurCourant = 1;
        this.grille = new ArrayList<>();
        
        for (int i = 0; i < 6; i++) {
            List<Integer> ligne = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                ligne.add(0); // On remplit de zéros (cases vides)
            }
            this.grille.add(ligne);
        }
    }

    public boolean jouerJeton(int colonne) {
        // Cherche la case vide la plus basse
        for (int ligne = 5; ligne >= 0; ligne--) {
            if (this.grille.get(ligne).get(colonne) == 0) {
                this.grille.get(ligne).set(colonne, this.joueurCourant);
                this.joueurCourant = (this.joueurCourant == 1) ? 2 : 1; // Change de joueur
                return true;
            }
        }
        return false; // Colonne pleine
    }

    public List<List<Integer>> getGrille() { return this.grille; }
    public int getJoueurCourant() { return this.joueurCourant; }
}