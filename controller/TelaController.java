package controller;

/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: TelaController
 * Funcao...........: Aplicação que controla a tela inicial do jogo
 *************************************************************** */
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;

public class TelaController implements Initializable {

    @FXML
    private Button entrar;

    @FXML
    private Button sair;

    @FXML
    private Button instrucoes;

    // Referência à janela atual
    private Stage stage;
    private MediaPlayer mediaPlayer;

    // Configura a referência à janela atual
    public void setStage(Stage stage) {
        this.stage = stage;
    } // Fim do método setStage

    /*
     * Método: entrar()
     * Funcao: Iniciar o jogo
     * Parametros: void
     * Retorno: void
     */
    @FXML
    private void entrar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
            Parent root = loader.load();
            mainController tela = new mainController();
            loader.setController(tela);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Episodio V - A Vingança de Darth Marlos");
            stage.setScene(scene);
            stage.show();
            stopAudio();
            // Fecha a janela atual
            entrar.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível abrir a janela");
        } // Fim do try-catch
    } // Fim do método entrar

    // Método para mostrar as instruções em um Alert
    @FXML
    private void mostrarInstrucoes() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instruções");
        alert.setHeaderText(null);
        alert.setContentText(
                "Neste jogo, você deve controlar os jedis e os siths para que eles não colidam uns com os outros.\n\n" +
                        "Para isso, você pode controlar a velocidade de cada um deles, utilizando os sliders.\n\n" +
                        "Você também pode pausar o movimento de cada um deles, utilizando os botões \"Pausar\".\n\n" +
                        "Para ocultar o percurso de cada um deles, utilize os botões \"Ocultar\".\n\n" +
                        "Para resetar o jogo, utilize o botão \"Reset\".");

        alert.showAndWait();
    } // Fim do método mostrarInstrucoes

    @FXML
    private void sair() {
        // Create a confirmation dialog
        Dialog<ButtonType> confirmationDialog = new Dialog<>();
        confirmationDialog.setTitle("Confirmação");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText("Tem certeza que deseja sair do jogo?");

        // Add "Yes" and "No" buttons to the dialog
        confirmationDialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        // Show the dialog and wait for user input
        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                // Close the current window
                sair.getScene().getWindow().hide();
            } // Fim do if
        }); // Fim do if
    } // Fim do método sair

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media sound = new Media(getClass().getResource("starwarstheme.wav").toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        // Associar o evento handleStopMusic ao botão de parar a música quando o mouse
        // for pressionado sobre a ImageView stopMusic
    } // Fim do método initialize

    @FXML
    private void handleStopMusic() {
        stopAudio();
    } // Fim do método handleStopMusic

    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.seek(Duration.ZERO);
        } // Fim do if
    } // Fim do método stopAudio
} // Fim da classe TelaController
