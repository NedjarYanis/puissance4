import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class AppliPuissance4 extends Application {
    private Puissance4 modele = new Puissance4();
    private List<List<Circle>> cercles = new ArrayList<>();
    private Circle cJ1 = new Circle(15, Color.BLUE);
    private Circle cJ2 = new Circle(15, Color.TRANSPARENT);

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);

        HBox infoTour = new HBox(10, cJ1, new ImageView(), cJ2);
        infoTour.setAlignment(Pos.CENTER);

        GridPane grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setStyle("-fx-background-color: blue; -fx-padding: 10;");


        for (int ligne = 0; ligne < 6; ligne++) {
            List<Circle> ligneCercle = new ArrayList<>();
            for (int col = 0; col < 7; col++) {
                Circle c = new Circle(25, Color.WHITE);
                c.setOnMouseClicked(new ControleurColonne(this.modele, this, col)); // Le clic
                ligneCercle.add(c);
                grille.add(c, col, ligne);
            }
            this.cercles.add(ligneCercle);
        }

        Button btnRejouer = new Button("Rejouer");
        btnRejouer.setOnAction(new ControleurBoutonRejouer(this.modele, this));

        Button btnQuitter = new Button("Quitter");
        btnQuitter.setOnAction(new ControleurBoutonQuitter()); // Plus besoin de passer la vue ici !

        HBox boutons = new HBox(15, btnRejouer, btnQuitter);
        boutons.setAlignment(Pos.CENTER);

        root.getChildren().addAll(new Label("Puissance 4"), infoTour, grille, boutons);
        stage.setScene(new Scene(root, 500, 600));
        stage.show();
        
        this.majAffichage();
    }

    public void majAffichage() {
        // Met à jour les couleurs des cercles
        for (int ligne = 0; ligne < 6; ligne++) {
            for (int col = 0; col < 7; col++) {
                int valeur = this.modele.getGrille().get(ligne).get(col);
                if (valeur == 0) this.cercles.get(ligne).get(col).setFill(Color.WHITE);
                else if (valeur == 1) this.cercles.get(ligne).get(col).setFill(Color.BLUE);
                else this.cercles.get(ligne).get(col).setFill(Color.RED);
            }
        }
        // Met à jour l'indicateur de joueur
        this.cJ1.setFill(this.modele.getJoueurCourant() == 1 ? Color.BLUE : Color.TRANSPARENT);
        this.cJ2.setFill(this.modele.getJoueurCourant() == 2 ? Color.RED : Color.TRANSPARENT);
    }
}