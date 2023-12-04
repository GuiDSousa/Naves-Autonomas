/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Principal
 * Funcao...........: Aplicação Main que inicia o programa das naves automaticas	
 *************************************************************** */

import controller.TelaController; // Importa a classe TelaController
import javafx.application.Application; // Importa a classe Application
import javafx.fxml.FXMLLoader; // Importa a classe FXMLLoader
import javafx.scene.Parent;// Importa a classe Parent
import javafx.scene.Scene; // Importa a classe Scene
import javafx.stage.Stage; // Importa a classe Stage

// Classe Principal que herda de Application

public class Principal extends Application {

	/*
	 * Método: start
	 * Função: Iniciar a aplicação
	 * Parametros: Stage primaryStage
	 * Retorno: void
	 */

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
		stage.setTitle("Episodio V - A vingança de Darth Marlos");
		stage.show();

	} // Fim do método start

	/*
	 * Método: main
	 * Função: Iniciar a aplicação
	 * Parametros: String[] args
	 * Retorno: void
	 */
	public static void main(String[] args) {
		launch(args);
	} // Fim do método main
} // Fim da classe Principal
 