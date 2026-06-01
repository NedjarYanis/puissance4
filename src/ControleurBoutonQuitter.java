import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ControleurBoutonQuitter implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        Platform.exit();
    }
}