import controller.TelaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Vias;

public class Principal extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        
        FXMLLoader loader = new FXMLLoader(Principal.class.getResource("controller/telainicial.fxml"));
        Parent root = loader.load();
        TelaController tela = new TelaController();
        loader.setController(tela);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Episodio V - A Batalha dos Bytes");
        stage.show();

        Vias vias = new Vias();
        vias.imprimirArrayRuas();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
