import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurBoutonRejouer implements EventHandler<ActionEvent> {
    private Puissance4 modele;
    private AppliPuissance4 vue;

    public ControleurBoutonRejouer(Puissance4 modele, AppliPuissance4 vue) {
        this.modele = modele;
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        this.modele.reinitialiser();
        this.vue.majAffichage();
    }
}