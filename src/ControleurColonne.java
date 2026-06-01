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
        this.modele.jouerJeton(this.colonne);
        this.vue.majAffichage();
    }
}