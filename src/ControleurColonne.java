import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurColonne implements EventHandler<MouseEvent> {
    private Puissance4 modele;
    private AppliPuissance4 vue;
    private int colonne;

    public ControleurColonne(Puissance4 modele, AppliPuissance4 vue, int colonne) {
        this.modele = modele;
        this.vue = vue;
        this.colonne = colonne;
    }

    @Override
    public void handle(MouseEvent e) {
        boolean coupValide = this.modele.jouerJeton(this.colonne);
        
        if (coupValide) {
            this.vue.majAffichage();
            
            // On vérifie d'abord si quelqu'un a gagné
            if (this.modele.estGagne()) {
                this.vue.popUpVictoire().showAndWait();
                this.modele.reinitialiser();
                this.vue.majAffichage();
            } 
            // Sinon on vérifie si c'est plein
            else if (this.modele.estPlein()) {
                this.vue.popUpMatchNul().showAndWait();
                this.modele.reinitialiser();
                this.vue.majAffichage();
            } 
            // Sinon on continue le jeu
            else {
                this.modele.changerJoueur();
                this.vue.majAffichage(); 
            }
        }
    }
}