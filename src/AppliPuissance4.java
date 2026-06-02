import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class AppliPuissance4 extends Application {

    private Puissance4 modele;
    private List<List<Circle>> grilleCercles;
    
    private Label lblJ1;
    private Label lblJ2;

    @Override
    public void init() {
        this.modele = new Puissance4();
        this.grilleCercles = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #222831;"); 

        //ZONE DU HAUT
        BorderPane zoneHaut = new BorderPane();
        
        Label titre = new Label("Puissance 4");
        titre.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        HBox hbBoutons = new HBox(15);
        hbBoutons.setAlignment(Pos.CENTER_RIGHT);
        
        Button btnRejouer = new Button("Rejouer");
        btnRejouer.setStyle("-fx-background-color: #00ADB5; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 8; -fx-padding: 8 15;");
        
        Button btnQuitter = new Button("Quitter");
        btnQuitter.setStyle("-fx-background-color: #EEEEEE; -fx-text-fill: black; -fx-font-size: 16px; -fx-background-radius: 8; -fx-padding: 8 15;");
        
        btnRejouer.setOnAction(new ControleurBoutonRejouer(this.modele, this));
        btnQuitter.setOnAction(new ControleurBoutonQuitter());
        
        hbBoutons.getChildren().addAll(btnRejouer, btnQuitter);
        
        zoneHaut.setLeft(titre);
        zoneHaut.setRight(hbBoutons);

        // La Grille
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(8); 
        gridPane.setVgap(8);
        gridPane.setStyle("-fx-background-color: #1E90FF; -fx-padding: 15; -fx-background-radius: 15;");

        for (int ligne = 0; ligne < 6; ligne++) {
            List<Circle> ligneCercles = new ArrayList<>();
            for (int col = 0; col < 7; col++) {
                Circle cercle = new Circle(30, Color.BLACK);
                cercle.setOnMouseClicked(new ControleurColonne(this.modele, this, col));
                ligneCercles.add(cercle); 
                gridPane.add(cercle, col, ligne); 
            }
            this.grilleCercles.add(ligneCercles); 
        }

        //ZONE DU BAS
        HBox zoneBas = new HBox(50);
        zoneBas.setAlignment(Pos.CENTER);

        this.lblJ1 = new Label("Joueur 1");
        this.lblJ2 = new Label("Joueur 2");

        zoneBas.getChildren().addAll(this.lblJ1, this.lblJ2);

        root.getChildren().addAll(zoneHaut, gridPane, zoneBas);

        Scene scene = new Scene(root);
        stage.setTitle("Puissance 4");
        stage.setScene(scene);
        stage.show();
        
        this.majAffichage(); 
    }

    public void majAffichage() {
        List<List<Integer>> etatGrille = this.modele.getGrille();
        
        
        for (int ligne = 0; ligne < 6; ligne++) {
            for (int col = 0; col < 7; col++) {
                int valeurCase = etatGrille.get(ligne).get(col);
                Circle cercleVue = this.grilleCercles.get(ligne).get(col);
                
                if (valeurCase == 0) {
                    cercleVue.setFill(Color.web("#222831")); 
                } else if (valeurCase == 1) {
                    cercleVue.setFill(Color.RED);
                } else {
                    cercleVue.setFill(Color.YELLOW);
                }
            }
        }
        
        
        String styleActif = "-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 25; ";
        String styleInactif = "-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10 40; -fx-background-radius: 25; -fx-border-radius: 25; -fx-border-width: 3; -fx-background-color: transparent; ";

        if (this.modele.getJoueurCourant() == 1) {
            this.lblJ1.setStyle(styleActif + "-fx-background-color: RED; -fx-text-fill: WHITE;");
            this.lblJ2.setStyle(styleInactif + "-fx-border-color: YELLOW; -fx-text-fill: YELLOW;");
        } else {
            this.lblJ1.setStyle(styleInactif + "-fx-border-color: RED; -fx-text-fill: RED;");
            this.lblJ2.setStyle(styleActif + "-fx-background-color: YELLOW; -fx-text-fill: BLACK;");
        }
    }

    public Alert popUpVictoire() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText("Victoire !");
        Image imageVictoire = new Image("file:src/logo_VS.png");

        ImageView imageView = new ImageView(imageVictoire);

        imageView.setFitHeight(60); 
        imageView.setFitWidth(60);
        imageView.setPreserveRatio(true);

        alert.setGraphic(imageView);

        String couleurGagnant = (this.modele.getJoueurCourant() == 1) ? "Rouge" : "Jaune";
        alert.setContentText("Le joueur " + couleurGagnant + " a gagner ");
        return alert;
    }

    public Alert popUpMatchNul() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText("tu a perdu");
        alert.setContentText("dommage");
        return alert;
    }
}