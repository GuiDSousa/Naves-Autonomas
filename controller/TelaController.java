package controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaController {
    
    @FXML
    private Button entrar;

    @FXML
    private Button sair;

    @FXML
    private Button instrucoes;

    // Referência à janela atual
    private Stage stage;

    // Configura a referência à janela atual
    public void setStage(Stage stage) {
        this.stage = stage;

    }

@FXML
private void entrar(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Episodio V - A Batalha dos Bytes");
        stage.setScene(scene);
        stage.show();

        // Fecha a janela atual
        entrar.getScene().getWindow().hide();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    // Método para mostrar as instruções em um Alert
    @FXML
    private void mostrarInstrucoes() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instruções");
        alert.setHeaderText(null);
        alert.setContentText("Neste jogo, você deve controlar os jedis e os siths para que eles não colidam uns com os outros.\n\n" +
                "Para isso, você pode controlar a velocidade de cada um deles, utilizando os sliders.\n\n" +
                "Você também pode pausar o movimento de cada um deles, utilizando os botões \"Pausar\".\n\n" +
                "Para ocultar o percurso de cada um deles, utilize os botões \"Ocultar\".\n\n" +
                "Para resetar o jogo, utilize o botão \"Reset\".");

        alert.showAndWait();
    }

    // Método para fechar o programa
    @FXML
    private void sair() {
        // Fecha a janela atual
        stage.close();
    }
}
