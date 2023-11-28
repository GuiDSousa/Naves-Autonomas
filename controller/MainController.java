package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Ahsoka;
import model.Kylo;
import model.Luke;
import model.Maul;
import model.Obiwan;
import model.Sidius;
import model.Vader;
import model.Vias;
import model.Yoda;

public class MainController implements Initializable {

	// Elementos da interface gráfica
	// Jedis
	// Yoda
	@FXML
	private ImageView nave_yoda;
	@FXML
	private Slider slider_yoda;
	@FXML
	private Button pausar_yoda;
	@FXML
	private Button ocultar_percurso_yoda;

	// Luke
	@FXML
	private ImageView nave_luke;
	@FXML
	private Slider slider_luke;
	@FXML
	private Button pausar_luke;
	@FXML
	private Button ocultar_percurso_luke;

	// Obi-Wan
	@FXML
	private ImageView nave_obiwan;
	@FXML
	private Slider slider_obiwan;
	@FXML
	private Button pausar_obiwan;
	@FXML
	private Button ocultar_percurso_obiwan;

	// Ahsoka
	@FXML
	private ImageView nave_ahsoka;
	@FXML
	private Slider slider_ahsoka;
	@FXML
	private Button pausar_ahsoka;
	@FXML
	private Button ocultar_percurso_ahsoka;

	// Siths
	// Darth Maul
	@FXML
	private ImageView nave_maul;
	@FXML
	private Slider slider_maul;
	@FXML
	private Button pausar_maul;
	@FXML
	private Button ocultar_percurso_maul;

	// Darth Sidious
	@FXML
	private ImageView nave_sidious;
	@FXML
	private Slider slider_sidious;
	@FXML
	private Button pausar_sidious;
	@FXML
	private Button ocultar_percurso_sidious;

	// Darth Vader
	@FXML
	private ImageView nave_vader;
	@FXML
	private Slider slider_vader;
	@FXML
	private Button pausar_vader;
	@FXML
	private Button ocultar_percurso_vader;

	// Kylo Ren
	@FXML
	private ImageView nave_kylo;
	@FXML
	private Slider slider_kylo;
	@FXML
	private Button pausar_kylo;
	@FXML
	private Button ocultar_percurso_kylo;

	// Declaracoes das Threads
	// Jedis
	private Ahsoka ahsoka_percurso_15_sh;
	private Kylo kylo_percurso_18_sh;
	private Luke luke_percurso_08_sh;
	private Maul maul_percurso_12_sa;
	private Obiwan obiwan_percurso_19_sh;
	private Sidius sidius_percurso_21_sh;
	private Vader vader_percurso_01_sh;
	private Yoda yoda_percurso_04_sa;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Inicialização da Thread da nave da Ahsoka
		ahsoka_percurso_15_sh = new Ahsoka(this, nave_ahsoka, slider_ahsoka);
		ahsoka_percurso_15_sh.start();
		ahsoka_percurso_15_sh.setName("Ahsoka");
		ahsoka_percurso_15_sh.setDaemon(true);
		// Inicialização da Thread da nave do Kylo
		kylo_percurso_18_sh = new Kylo(this, nave_kylo, slider_kylo);
		kylo_percurso_18_sh.start();
		kylo_percurso_18_sh.setName("Kylo");
		kylo_percurso_18_sh.setDaemon(true);
		// Inicialização da Thread da nave do Luke
		luke_percurso_08_sh = new Luke(this, nave_luke, slider_luke);
		luke_percurso_08_sh.start();
		luke_percurso_08_sh.setName("Luke");
		luke_percurso_08_sh.setDaemon(true);
		// Inicialização da Thread da nave do Maul
		maul_percurso_12_sa = new Maul(this, nave_maul, slider_maul);
		maul_percurso_12_sa.start();
		maul_percurso_12_sa.setName("Maul");
		maul_percurso_12_sa.setDaemon(true);
		// Inicialização da Thread da nave do Obiwan
		obiwan_percurso_19_sh = new Obiwan(this, nave_obiwan, slider_obiwan);
		obiwan_percurso_19_sh.start();
		obiwan_percurso_19_sh.setName("Obi Wan");
		obiwan_percurso_19_sh.setDaemon(true);
		// Inicialização da Thread da nave do Sidius
		sidius_percurso_21_sh = new Sidius(this, nave_sidious, slider_sidious);
		sidius_percurso_21_sh.start();
		sidius_percurso_21_sh.setName("Sidius");
		sidius_percurso_21_sh.setDaemon(true);
		vader_percurso_01_sh = new Vader(this, nave_vader, slider_vader);
		yoda_percurso_04_sa = new Yoda(this, nave_yoda, slider_yoda);
		sidius_percurso_21_sh.start();
		vader_percurso_01_sh.start();
		yoda_percurso_04_sa.start();
	}

}
