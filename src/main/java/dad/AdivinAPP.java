package dad;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class AdivinAPP extends Application {

    private TextField introducirNumero;
    private Label etiquetaNumero;
    private Button checkButton;
    private Alert mensajeAviso;
    private int numeroSecreto;

    @Override
    public void start(Stage stage) throws Exception {

        // Número aleatorio
        Random random = new Random();
        numeroSecreto = random.nextInt(100);

        // Establecer los parámetros de los atributos
        introducirNumero = new TextField();
        introducirNumero.setPrefColumnCount(20);

        // Etiqueta
        etiquetaNumero = new Label();
        etiquetaNumero.setText("Introducir un número del 1-100");

        // Botón para las acciones y los errores
        checkButton = new Button("Comprobar");
        checkButton.setDefaultButton(true);
        checkButton.setOnAction(this::onCheckAction);

        // Configurar el pane
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #ffd5cc");
        root.setPadding(new Insets(50));
        root.setSpacing(10);
        root.setFillWidth(false);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(etiquetaNumero, introducirNumero, checkButton);

        // Alerta
        mensajeAviso = new Alert(Alert.AlertType.NONE);

        Scene scene = new Scene(root, 350, 300);
        stage.setTitle("AdivinAPP");
        stage.setScene(scene);
        stage.show();

    }
    private void onCheckAction (ActionEvent e){
        try {

            int guess = Integer.parseInt(introducirNumero.getText());

            if (guess < 1 || guess > 100) {
                mensajeAviso.setAlertType(Alert.AlertType.WARNING);
                mensajeAviso.setHeaderText("Número fuera de rango");
                mensajeAviso.setContentText("Introduce un número entre 1 y 100.");
                mensajeAviso.show();

            } else if (guess < numeroSecreto) {
                mensajeAviso.setAlertType(Alert.AlertType.INFORMATION);
                mensajeAviso.setHeaderText("Demasiado bajo");
                mensajeAviso.setContentText("¡Intenta con un número mayor!");
                mensajeAviso.show();

            } else if (guess > numeroSecreto) {
                mensajeAviso.setAlertType(Alert.AlertType.INFORMATION);
                mensajeAviso.setHeaderText("Demasiado alto");
                mensajeAviso.setContentText("¡Intenta con un número menor!");
                mensajeAviso.show();

            } else {
                mensajeAviso.setAlertType(Alert.AlertType.CONFIRMATION);
                mensajeAviso.setHeaderText("¡Correcto!");
                mensajeAviso.setContentText("¡Felicidades! Has adivinado el número secreto.");
                mensajeAviso.show();

                numeroSecreto = new Random().nextInt(100) + 1;
            }
        } catch (NumberFormatException ex) {
                mensajeAviso.setAlertType(Alert.AlertType.ERROR);
                mensajeAviso.setHeaderText("Entrada inválida");
                mensajeAviso.setContentText("Por favor, introduce un número válido.");
                mensajeAviso.show();
        }
    }
}


