package controller;

/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: MainController
 * Funcao...........: Aplicação controller que inicia o programa das naves automaticas	
 *************************************************************** */

import java.net.URL; // Importa a classe URL
import java.util.ResourceBundle; // Importa a classe ResourceBundle
import javafx.event.ActionEvent;

import java.util.concurrent.Semaphore; // Importa a classe Semaphore
import javafx.application.Platform; // Importa a classe Platform
import javafx.fxml.FXML; // Importa a classe FXML
import javafx.fxml.Initializable; // Importa a classe Initializable
import javafx.scene.control.Button; // Importa a classe Button
import javafx.scene.control.Label; // Importa a classe Label
import javafx.scene.image.ImageView; // Importa a classe ImageView
import javafx.scene.media.Media; // Importa a classe Media
import javafx.scene.media.MediaPlayer; // Importa a classe MediaPlayer
import javafx.util.Duration; // Importa a classe Duration
import model.Ahsoka; // Importa a classe Ahsoka
import model.Kylo; // Importa a classe Kylo
import model.Luke; // Importa a classe Luke
import model.Maul; // Importa a classe Maul
import model.Obiwan; // Importa a classe Obiwan
import model.Sidius; // Importa a classe Sidius
import model.Vader; // Importa a classe Vader
import model.Yoda; // Importa a classe Yoda

// Classe MainController que implementa Initializable
public class mainController implements Initializable {

	// Elementos da interface gráfica
	// Jedis
	// Yoda
	@FXML
	private ImageView nave_yoda;
	@FXML
	private ImageView percurso_yoda;
	@FXML
	private Button aumentar_velocidade_yoda;
	@FXML
	private Button diminuir_velocidade_yoda;
	@FXML
	private Label velocidade_yoda;
	@FXML
	private Button pausar_yoda;
	@FXML
	private Button ocultar_percurso_yoda;

	// Luke
	@FXML
	private ImageView nave_luke;
	@FXML
	private ImageView percurso_luke;
	@FXML
	private Button aumentar_velocidade_luke;
	@FXML
	private Button diminuir_velocidade_luke;
	@FXML
	private Label velocidade_luke;
	@FXML
	private Button pausar_luke;
	@FXML
	private Button ocultar_percurso_luke;

	// Obi-Wan
	@FXML
	private ImageView nave_obiwan;
	@FXML
	private ImageView percurso_obiwan;
	@FXML
	private Button aumentar_velocidade_obiwan;
	@FXML
	private Button diminuir_velocidade_obiwan;
	@FXML
	private Label velocidade_obiwan;
	@FXML
	private Button pausar_obiwan;
	@FXML
	private Button ocultar_percurso_obiwan;

	// Ahsoka
	@FXML
	private ImageView nave_ahsoka;
	@FXML
	private ImageView percurso_ahsoka;
	@FXML
	private Button aumentar_velocidade_ahsoka;
	@FXML
	private Button diminuir_velocidade_ahsoka;
	@FXML
	private Label velocidade_ahsoka;
	@FXML
	private Button pausar_ahsoka;
	@FXML
	private Button ocultar_percurso_ahsoka;

	// Siths
	// Darth Maul
	@FXML
	private ImageView nave_maul;
	@FXML
	private ImageView percurso_maul;
	@FXML
	private Button aumentar_velocidade_maul;
	@FXML
	private Button diminuir_velocidade_maul;
	@FXML
	private Label velocidade_maul;
	@FXML
	private Button pausar_maul;
	@FXML
	private Button ocultar_percurso_maul;

	// Darth Sidious
	@FXML
	private ImageView nave_sidious;
	@FXML
	private ImageView percurso_sidious;
	@FXML
	private Button aumentar_velocidade_sidious;
	@FXML
	private Button diminuir_velocidade_sidious;
	@FXML
	private Label velocidade_sidious;
	@FXML
	private Button pausar_sidious;
	@FXML
	private Button ocultar_percurso_sidious;

	// Darth Vader
	@FXML
	private ImageView nave_vader;
	@FXML
	private ImageView percurso_vader;
	@FXML
	private Button aumentar_velocidade_vader;
	@FXML
	private Button diminuir_velocidade_vader;
	@FXML
	private Label velocidade_vader;
	@FXML
	private Button pausar_vader;
	@FXML
	private Button ocultar_percurso_vader;

	// Kylo Ren
	@FXML
	private ImageView nave_kylo;
	@FXML
	private ImageView percurso_kylo;
	@FXML
	private Button aumentar_velocidade_kylo;
	@FXML
	private Button diminuir_velocidade_kylo;
	@FXML
	private Label velocidade_kylo;
	@FXML
	private Button pausar_kylo;
	@FXML
	private Button ocultar_percurso_kylo;

	// Botões de controle
	@FXML
	private Button start;
	@FXML
	private Button reset;

	// Instancia das naves
	// Jedis
	private Ahsoka ahsoka_percurso_15_sh;
	private Kylo kylo_percurso_18_sh;
	private Luke luke_percurso_08_sh;
	private Maul maul_percurso_12_sa;
	private Obiwan obiwan_percurso_19_sh;
	private Sidius sidius_percurso_21_sh;
	private Vader vader_percurso_01_sh;
	private Yoda yoda_percurso_04_sa;
	private MediaPlayer mediaPlayer;
	/*
	 * Os semáforos foram divididos em ruas e avenidas para facilitar a
	 * visualização.
	 * Cada avenida é formada por 5 semáforos, um para cada rua.
	 * Os semáforos horizontais são nomeados com 3 caracteres e um número.
	 * Os semáforos verticais são nomeados com 4 caracteres e um número.
	 */
	// Semaforos avenida horizontal 1
	public Semaphore semaforoELM_0 = new Semaphore(1);
	public Semaphore semaforoOAK_1 = new Semaphore(1);
	public Semaphore semaforoANA_2 = new Semaphore(1);
	public Semaphore semaforoASH_3 = new Semaphore(1);
	public Semaphore semaforoBEE_4 = new Semaphore(1);
	// Semaforos avenida horizontal 2
	public Semaphore semaforoSKY_5 = new Semaphore(1);
	public Semaphore semaforoIVY_6 = new Semaphore(1);
	public Semaphore semaforoSUN_7 = new Semaphore(1);
	public Semaphore semaforoDEW_8 = new Semaphore(1);
	public Semaphore semaforoFOG_9 = new Semaphore(1);
	// Semaforos avenida horizontal 3
	public Semaphore semaforoZEN_10 = new Semaphore(1);
	public Semaphore semaforoJET_11 = new Semaphore(1);
	public Semaphore semaforoJOY_12 = new Semaphore(1);
	public Semaphore semaforoLUX_13 = new Semaphore(1);
	public Semaphore semaforoORB_14 = new Semaphore(1);
	// Semaforos avenida horizontal 4
	public Semaphore semaforoPIP_15 = new Semaphore(1);
	public Semaphore semaforoPET_16 = new Semaphore(1);
	public Semaphore semaforoINK_17 = new Semaphore(1);
	public Semaphore semaforoPOD_18 = new Semaphore(1);
	public Semaphore semaforoHEX_19 = new Semaphore(1);
	// Semaforos avenida horizontal 5
	public Semaphore semaforoJAX_20 = new Semaphore(1);
	public Semaphore semaforoKAY_21 = new Semaphore(1);
	public Semaphore semaforoACE_22 = new Semaphore(1);
	public Semaphore semaforoFIN_23 = new Semaphore(1);
	public Semaphore semaforoDOC_24 = new Semaphore(1);
	// Semaforos avenida horizontal 6
	public Semaphore semaforoWIN_25 = new Semaphore(1);
	public Semaphore semaforoBUD_26 = new Semaphore(1);
	public Semaphore semaforoAXE_27 = new Semaphore(1);
	public Semaphore semaforoZOE_28 = new Semaphore(1);
	public Semaphore semaforoGAL_29 = new Semaphore(1);
	// Semaforos avenida vertical 1
	public Semaphore semaforoAIKO_30 = new Semaphore(1);
	public Semaphore semaforoZORO_31 = new Semaphore(1);
	public Semaphore semaforoVINE_32 = new Semaphore(1);
	public Semaphore semaforoROSE_33 = new Semaphore(1);
	public Semaphore semaforoHALO_34 = new Semaphore(1);
	// Semaforos avenida vertical 2
	public Semaphore semaforoAIMI_35 = new Semaphore(1);
	public Semaphore semaforoFUJI_36 = new Semaphore(1);
	public Semaphore semaforoAQUA_37 = new Semaphore(1);
	public Semaphore semaforoIRIS_38 = new Semaphore(1);
	public Semaphore semaforoNOVA_39 = new Semaphore(1);
	// Semaforos avenida vertical 3
	public Semaphore semaforoCHOU_40 = new Semaphore(1);
	public Semaphore semaforoEREN_41 = new Semaphore(1);
	public Semaphore semaforoGAZE_42 = new Semaphore(1);
	public Semaphore semaforoLUSH_43 = new Semaphore(1);
	public Semaphore semaforoDUSK_44 = new Semaphore(1);
	// Semaforos avenida vertical 4
	public Semaphore semaforoNEJI_45 = new Semaphore(1);
	public Semaphore semaforoYOKA_46 = new Semaphore(1);
	public Semaphore semaforoHILL_47 = new Semaphore(1);
	public Semaphore semaforoLUNA_48 = new Semaphore(1);
	public Semaphore semaforoVIBE_49 = new Semaphore(1);
	// Semaforos avenida vertical 5
	public Semaphore semaforoPAIN_50 = new Semaphore(1);
	public Semaphore semaforoHANA_51 = new Semaphore(1);
	public Semaphore semaforoSILK_52 = new Semaphore(1);
	public Semaphore semaforoECHO_53 = new Semaphore(1);
	public Semaphore semaforoZEST_54 = new Semaphore(1);
	// Semaforos avenida vertical 6
	public Semaphore semaforoBUGG_55 = new Semaphore(1);
	public Semaphore semaforoHIRO_56 = new Semaphore(1);
	public Semaphore semaforoPAVE_57 = new Semaphore(1);
	public Semaphore semaforoMIST_58 = new Semaphore(1);
	public Semaphore semaforoZENO_59 = new Semaphore(1);

	/*
	 * Métodos Pausar
	 * Função: Pausar e retomar as threads além de desabilitar os botões para evitar
	 * erros
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */

	// Método para pausar as naves
	// Pausar Yoda
	public void pausarYoda() {
		if (this.yoda_percurso_04_sa.isPaused()) {
			yoda_percurso_04_sa.retomar();
			aumentar_velocidade_yoda.setDisable(false);
			diminuir_velocidade_yoda.setDisable(false);
			ocultar_percurso_yoda.setDisable(false);
		} else {
			yoda_percurso_04_sa.parar();
			aumentar_velocidade_yoda.setDisable(true);
			diminuir_velocidade_yoda.setDisable(true);
			ocultar_percurso_yoda.setDisable(true);
		} // Fim do if
	} // Fim do método pausarYoda

	// Pausar Ahsoka
	public void pausarAhsoka() {
		if (this.ahsoka_percurso_15_sh.isPaused()) {
			ahsoka_percurso_15_sh.retomar();
			aumentar_velocidade_ahsoka.setDisable(false);
			diminuir_velocidade_ahsoka.setDisable(false);
			ocultar_percurso_ahsoka.setDisable(false);
		} else {
			ahsoka_percurso_15_sh.parar();
			aumentar_velocidade_ahsoka.setDisable(true);
			diminuir_velocidade_ahsoka.setDisable(true);
			ocultar_percurso_ahsoka.setDisable(true);
		} // Fim do if
	} // Fim do método pausarAhsoka

	// Pausar Kylo
	public void pausarKylo() {
		if (this.kylo_percurso_18_sh.isPaused()) {
			kylo_percurso_18_sh.retomar();
			aumentar_velocidade_kylo.setDisable(false);
			diminuir_velocidade_kylo.setDisable(false);
			ocultar_percurso_kylo.setDisable(false);
		} else {
			kylo_percurso_18_sh.parar();
			aumentar_velocidade_kylo.setDisable(true);
			diminuir_velocidade_kylo.setDisable(true);
			ocultar_percurso_kylo.setDisable(true);
		} // Fim do if
	} // Fim do método pausarKylo

	// Pausar Luke
	public void pausarLuke() {
		if (this.luke_percurso_08_sh.isPaused()) {
			luke_percurso_08_sh.retomar();
			aumentar_velocidade_luke.setDisable(false);
			diminuir_velocidade_luke.setDisable(false);
			ocultar_percurso_luke.setDisable(false);
		} else {
			luke_percurso_08_sh.parar();
			aumentar_velocidade_luke.setDisable(true);
			diminuir_velocidade_luke.setDisable(true);
			ocultar_percurso_luke.setDisable(true);
		} // Fim do if
	} // Fim do método pausarLuke

	// Pausar Maul
	public void pausarMaul() {
		if (this.maul_percurso_12_sa.isPaused()) {
			maul_percurso_12_sa.retomar();
			;
			aumentar_velocidade_maul.setDisable(false);
			diminuir_velocidade_maul.setDisable(false);
			ocultar_percurso_maul.setDisable(false);
		} else {
			maul_percurso_12_sa.parar();
			aumentar_velocidade_maul.setDisable(true);
			diminuir_velocidade_maul.setDisable(true);
			ocultar_percurso_maul.setDisable(true);
		} // Fim do if
	} // Fim do método pausarMaul

	// Pausar Obiwan
	public void pausarObiwan() {
		if (this.obiwan_percurso_19_sh.isPaused()) {
			obiwan_percurso_19_sh.retomar();
			aumentar_velocidade_obiwan.setDisable(false);
			diminuir_velocidade_obiwan.setDisable(false);
			ocultar_percurso_obiwan.setDisable(false);
		} else {
			obiwan_percurso_19_sh.parar();
			aumentar_velocidade_obiwan.setDisable(true);
			diminuir_velocidade_obiwan.setDisable(true);
			ocultar_percurso_obiwan.setDisable(true);
		} // Fim do if
	} // Fim do método pausarObiwan

	// Pausar Sidius
	public void pausarSidius() {
		if (this.sidius_percurso_21_sh.isPaused()) {
			sidius_percurso_21_sh.retomar();
			aumentar_velocidade_sidious.setDisable(false);
			diminuir_velocidade_sidious.setDisable(false);
			ocultar_percurso_sidious.setDisable(false);
		} else {
			sidius_percurso_21_sh.parar();
			aumentar_velocidade_sidious.setDisable(true);
			diminuir_velocidade_sidious.setDisable(true);
			ocultar_percurso_sidious.setDisable(true);
		} // Fim do if
	} // Fim do método pausarSidius

	// Pausar Vader
	public void pausarVader() {
		if (vader_percurso_01_sh.isPaused()) {
			vader_percurso_01_sh.retomar();
			aumentar_velocidade_vader.setDisable(false);
			diminuir_velocidade_vader.setDisable(false);
			ocultar_percurso_vader.setDisable(false);
		} else {
			vader_percurso_01_sh.parar();
			aumentar_velocidade_vader.setDisable(true);
			diminuir_velocidade_vader.setDisable(true);
			ocultar_percurso_vader.setDisable(true);
		} // Fim do if
	} // Fim do método pausarVader

	/*
	 * Método: interromperThreads()
	 * Função: Interromper as threads para o reset
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */

	public void interromperThreads() {
		// Interrompe as Thread
		yoda_percurso_04_sa.interrupt();
		logThreadInterrompida(yoda_percurso_04_sa.getName());
		ahsoka_percurso_15_sh.interrupt();
		logThreadInterrompida(ahsoka_percurso_15_sh.getName());
		kylo_percurso_18_sh.interrupt();
		logThreadInterrompida(kylo_percurso_18_sh.getName());
		luke_percurso_08_sh.interrupt();
		logThreadInterrompida(luke_percurso_08_sh.getName());
		maul_percurso_12_sa.interrupt();
		logThreadInterrompida(maul_percurso_12_sa.getName());
		obiwan_percurso_19_sh.interrupt();
		logThreadInterrompida(obiwan_percurso_19_sh.getName());
		sidius_percurso_21_sh.interrupt();
		logThreadInterrompida(sidius_percurso_21_sh.getName());
		vader_percurso_01_sh.interrupt();
		logThreadInterrompida(vader_percurso_01_sh.getName());
	} // Fim do método interromperThreads

	/*
	 * Método: logThreadInterrompida(String nome)
	 * Função: Mostrar no console que a thread foi interrompida
	 * Parâmetros: String nome
	 * Retorno: Nenhum
	 */

	public void logThreadInterrompida(String nome) {
		System.out.println("Thread " + nome + " interrompida");
	} // Fim do método logThreadInterrompida

	/*
	 * Método: reset()
	 * Função: Reiniciar as naves e as velocidades
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */
	public void reset() {
		Platform.runLater(() -> {
			interromperThreads(); // Interrompe as threads
			reiniciarNaves(); // Reinicia as naves
			reiniciarVelocidades(); // Reinicia as velocidades
			desabilitarBotoesControle(); // Desabilita os botões de controle
			stopAudio(); // Para o audio
		}); // Fim do Platform.runLater
		start.setDisable(false);
	} // Fim do método reset

	// Métodos para ocultar os percursos
	/*
	 * Métodos: ocultarPercurso()
	 * Função: Ocultar os percursos das naves
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */

	// Métodos para ocultar o percurso do Yoda
	public void ocultarPercursoYoda() {
		if (percurso_yoda.isVisible()) {
			percurso_yoda.setVisible(false);
		} else {
			percurso_yoda.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoYoda

	// Métodos para ocultar o percurso da Ahsoka
	public void ocultarPercursoAhsoka() {
		if (percurso_ahsoka.isVisible()) {
			percurso_ahsoka.setVisible(false);
		} else {
			percurso_ahsoka.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoAhsoka

	// Métodos para ocultar o percurso do Kylo
	public void ocultarPercursoKylo() {
		if (percurso_kylo.isVisible()) {
			percurso_kylo.setVisible(false);
		} else {
			percurso_kylo.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoKylo

	// Métodos para ocultar o percurso do Luke
	public void ocultarPercursoLuke() {
		if (percurso_luke.isVisible()) {
			percurso_luke.setVisible(false);
		} else {
			percurso_luke.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoLuke

	// Métodos para ocultar o percurso do Maul
	public void ocultarPercursoMaul() {
		if (percurso_maul.isVisible()) {
			percurso_maul.setVisible(false);
		} else {
			percurso_maul.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoMaul

	// Métodos para ocultar o percurso do Obiwan
	public void ocultarPercursoObiwan() {
		if (percurso_obiwan.isVisible()) {
			percurso_obiwan.setVisible(false);
		} else {
			percurso_obiwan.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoObiwan

	// Métodos para oculta o percurso do Sidius
	public void ocultarPercursoSidius() {
		if (percurso_sidious.isVisible()) {
			percurso_sidious.setVisible(false);
		} else {
			percurso_sidious.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoSidius

	// Método para ocultar o percurso do Vader
	public void ocultarPercursoVader() {
		if (percurso_vader.isVisible()) {
			percurso_vader.setVisible(false);
		} else {
			percurso_vader.setVisible(true);
		} // Fim do if
	} // Fim do método ocultarPercursoVader

	/*
	 * Método: reiniciarNaves()
	 * Função: Leva as naves de volta a base de decolagem
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */

	public void reiniciarNaves() {
		ahsoka_percurso_15_sh.retornarBaseDecolagem();
		kylo_percurso_18_sh.retornarBaseDecolagem();
		luke_percurso_08_sh.retornarBaseDecolagem();
		maul_percurso_12_sa.retornarBaseDecolagem();
		obiwan_percurso_19_sh.retornarBaseDecolagem();
		sidius_percurso_21_sh.retornarBaseDecolagem();
		vader_percurso_01_sh.retornarBaseDecolagem();
		yoda_percurso_04_sa.retornarBaseDecolagem();
	} // Fim do método reiniciarNaves

	/*
	 * Método: reiniciarVelocidades()
	 * Função: Reiniciar as velocidades das naves
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */
	public void reiniciarVelocidades() {
		ahsoka_percurso_15_sh.setVelocidade(5);
		velocidade_ahsoka.setText(Integer.toString(ahsoka_percurso_15_sh.getVelocidade()));
		kylo_percurso_18_sh.setVelocidade(5);
		velocidade_kylo.setText(Integer.toString(kylo_percurso_18_sh.getVelocidade()));
		luke_percurso_08_sh.setVelocidade(5);
		velocidade_luke.setText(Integer.toString(luke_percurso_08_sh.getVelocidade()));
		maul_percurso_12_sa.setVelocidade(5);
		velocidade_maul.setText(Integer.toString(maul_percurso_12_sa.getVelocidade()));
		obiwan_percurso_19_sh.setVelocidade(5);
		velocidade_obiwan.setText(Integer.toString(obiwan_percurso_19_sh.getVelocidade()));
		sidius_percurso_21_sh.setVelocidade(5);
		velocidade_sidious.setText(Integer.toString(sidius_percurso_21_sh.getVelocidade()));
		vader_percurso_01_sh.setVelocidade(5);
		velocidade_vader.setText(Integer.toString(vader_percurso_01_sh.getVelocidade()));
		yoda_percurso_04_sa.setVelocidade(5);
		velocidade_yoda.setText(Integer.toString(yoda_percurso_04_sa.getVelocidade()));
	} // Fim do método reiniciarVelocidades

	/*
	 * Métodos: aumentarVelocidade() e diminuirVelocidade()
	 * Função: Aumentar e diminuir a velocidade das naves
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Yoda
	public void aumentarVelocidadeYoda() {
		if (yoda_percurso_04_sa.getVelocidade() < 10) {
			yoda_percurso_04_sa.setVelocidade(yoda_percurso_04_sa.getVelocidade() + 1);
			velocidade_yoda.setText(Integer.toString(yoda_percurso_04_sa.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeYoda

	public void diminuirVelocidadeYoda() {
		if (yoda_percurso_04_sa.getVelocidade() > 1) {
			yoda_percurso_04_sa.setVelocidade(yoda_percurso_04_sa.getVelocidade() - 1);
			velocidade_yoda.setText(Integer.toString(yoda_percurso_04_sa.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeYoda

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Ahsoka
	public void aumentarVelocidadeAhsoka() {
		if (ahsoka_percurso_15_sh.getVelocidade() < 10) {
			ahsoka_percurso_15_sh.setVelocidade(ahsoka_percurso_15_sh.getVelocidade() + 1);
			velocidade_ahsoka.setText(Integer.toString(ahsoka_percurso_15_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeAhsoka

	public void diminuirVelocidadeAhsoka() {
		if (ahsoka_percurso_15_sh.getVelocidade() > 1) {
			ahsoka_percurso_15_sh.setVelocidade(ahsoka_percurso_15_sh.getVelocidade() - 1);
			velocidade_ahsoka.setText(Integer.toString(ahsoka_percurso_15_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeAhsoka

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Kylo
	public void aumentarVelocidadeKylo() {
		if (kylo_percurso_18_sh.getVelocidade() < 10) {
			kylo_percurso_18_sh.setVelocidade(kylo_percurso_18_sh.getVelocidade() + 1);
			velocidade_kylo.setText(Integer.toString(kylo_percurso_18_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeKylo

	public void diminuirVelocidadeKylo() {
		if (kylo_percurso_18_sh.getVelocidade() > 1) {
			kylo_percurso_18_sh.setVelocidade(kylo_percurso_18_sh.getVelocidade() - 1);
			velocidade_kylo.setText(Integer.toString(kylo_percurso_18_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeKylo

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Luke
	public void aumentarVelocidadeLuke() {
		if (luke_percurso_08_sh.getVelocidade() < 10) {
			luke_percurso_08_sh.setVelocidade(luke_percurso_08_sh.getVelocidade() + 1);
			velocidade_luke.setText(Integer.toString(luke_percurso_08_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeLuke

	public void diminuirVelocidadeLuke() {
		if (luke_percurso_08_sh.getVelocidade() > 1) {
			luke_percurso_08_sh.setVelocidade(luke_percurso_08_sh.getVelocidade() - 1);
			velocidade_luke.setText(Integer.toString(luke_percurso_08_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeLuke

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Maul
	public void aumentarVelocidadeMaul() {
		if (maul_percurso_12_sa.getVelocidade() < 10) {
			maul_percurso_12_sa.setVelocidade(maul_percurso_12_sa.getVelocidade() + 1);
			velocidade_maul.setText(Integer.toString(maul_percurso_12_sa.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeMaul

	public void diminuirVelocidadeMaul() {
		if (maul_percurso_12_sa.getVelocidade() > 1) {
			maul_percurso_12_sa.setVelocidade(maul_percurso_12_sa.getVelocidade() - 1);
			velocidade_maul.setText(Integer.toString(maul_percurso_12_sa.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeMaul

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Obiwan
	public void aumentarVelocidadeObiwan() {
		if (obiwan_percurso_19_sh.getVelocidade() < 10) {
			obiwan_percurso_19_sh.setVelocidade(obiwan_percurso_19_sh.getVelocidade() + 1);
			velocidade_obiwan.setText(Integer.toString(obiwan_percurso_19_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeObiwan

	public void diminuirVelocidadeObiwan() {
		if (obiwan_percurso_19_sh.getVelocidade() > 1) {
			obiwan_percurso_19_sh.setVelocidade(obiwan_percurso_19_sh.getVelocidade() - 1);
			velocidade_obiwan.setText(Integer.toString(obiwan_percurso_19_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeObiwan

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Sidius
	public void aumentarVelocidadeSidius() {
		if (sidius_percurso_21_sh.getVelocidade() < 10) {
			sidius_percurso_21_sh.setVelocidade(sidius_percurso_21_sh.getVelocidade() + 1);
			velocidade_sidious.setText(Integer.toString(sidius_percurso_21_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeSidius

	public void diminuirVelocidadeSidius() {
		if (sidius_percurso_21_sh.getVelocidade() > 1) {

			sidius_percurso_21_sh.setVelocidade(sidius_percurso_21_sh.getVelocidade() - 1);
			velocidade_sidious.setText(Integer.toString(sidius_percurso_21_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeSidius

	// Métodos para aumentar e diminuir a velocidade das naves e atualizar o label
	// Vader
	public void aumentarVelocidadeVader() {
		if (vader_percurso_01_sh.getVelocidade() < 10) {
			vader_percurso_01_sh.setVelocidade(vader_percurso_01_sh.getVelocidade() + 1);
			velocidade_vader.setText(Integer.toString(vader_percurso_01_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método aumentarVelocidadeVader

	public void diminuirVelocidadeVader() {
		if (vader_percurso_01_sh.getVelocidade() > 1) {
			vader_percurso_01_sh.setVelocidade(vader_percurso_01_sh.getVelocidade() - 1);
			velocidade_vader.setText(Integer.toString(vader_percurso_01_sh.getVelocidade()));
		} // Fim do if
	} // Fim do método diminuirVelocidadeVader

	/*
	 * Método: Initialize()
	 * Função: desabilita os botoes de controle
	 * Parâmetros: URL location, ResourceBundle resources
	 * Retorno: Nenhum
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		desabilitarBotoesControle();
	} // Fim do método initialize

	/*
	 * Método: desabilitarBotoesControle()
	 * Função: Desabilitar os botões de controle das naves
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */
	public void desabilitarBotoesControle() {
		// Desabilitar botões de controle das naves
		// Botões do Yoda
		aumentar_velocidade_yoda.setDisable(true);
		diminuir_velocidade_yoda.setDisable(true);
		pausar_yoda.setDisable(true);
		ocultar_percurso_yoda.setDisable(true);
		// Botões da Ahsoka
		aumentar_velocidade_ahsoka.setDisable(true);
		diminuir_velocidade_ahsoka.setDisable(true);
		pausar_ahsoka.setDisable(true);
		ocultar_percurso_ahsoka.setDisable(true);
		// Botões do Kylo
		aumentar_velocidade_kylo.setDisable(true);
		diminuir_velocidade_kylo.setDisable(true);
		pausar_kylo.setDisable(true);
		ocultar_percurso_kylo.setDisable(true);
		// Botões do Luke
		aumentar_velocidade_luke.setDisable(true);
		diminuir_velocidade_luke.setDisable(true);
		pausar_luke.setDisable(true);
		ocultar_percurso_luke.setDisable(true);
		// Botões do Maul
		aumentar_velocidade_maul.setDisable(true);
		diminuir_velocidade_maul.setDisable(true);
		pausar_maul.setDisable(true);
		ocultar_percurso_maul.setDisable(true);
		// Botões do Obiwan
		aumentar_velocidade_obiwan.setDisable(true);
		diminuir_velocidade_obiwan.setDisable(true);
		pausar_obiwan.setDisable(true);
		ocultar_percurso_obiwan.setDisable(true);
		// Botões do Sidius
		aumentar_velocidade_sidious.setDisable(true);
		diminuir_velocidade_sidious.setDisable(true);
		pausar_sidious.setDisable(true);
		ocultar_percurso_sidious.setDisable(true);
		// Botões do Vader
		aumentar_velocidade_vader.setDisable(true);
		diminuir_velocidade_vader.setDisable(true);
		pausar_vader.setDisable(true);
		ocultar_percurso_vader.setDisable(true);
		// Botão de reset
		reset.setDisable(true);
	} // Fim do método desabilitarBotoesControle

	/*
	 * Método: iniciarMusica()
	 * Função: Iniciar a música
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */
	public void iniciarMusica() {

		Media sound = new Media(getClass().getResource("starwarsthrone.wav").toExternalForm());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		mediaPlayer.setVolume(0.1);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		// Associar o evento handleStopMusic ao botão de parar a música quando o mouse
		// for pressionado sobre a ImageView stopMusic
	} // Fim do método iniciarMusica

	/*
	 * Método: handleStopMusic()
	 * Função: Parar a música
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */
	@FXML
	private void handleStopMusic() {
		stopAudio();
	} // Fim do método handleStopMusic

	/*
	 * Método: stopAudio()
	 * Função: Parar a música
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */
	private void stopAudio() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.seek(Duration.ZERO);
		} // Fim do if
	}

	/*
	 * Método: botaoIniciar()
	 * Função: Iniciar as threads e habilitar os botões de controle
	 * Parâmetros: Nenhum
	 * Retorno: Nenhum
	 */
	public void botaoIniciar() {

		// Inicialização da Thread da nave da Ahsoka
		ahsoka_percurso_15_sh = new Ahsoka(this, nave_ahsoka);
		ahsoka_percurso_15_sh.setName("Ahsoka");
		ahsoka_percurso_15_sh.setDaemon(true);
		// Inicialização da Thread da nave do Kylo
		kylo_percurso_18_sh = new Kylo(this, nave_kylo);
		kylo_percurso_18_sh.setName("Kylo");
		kylo_percurso_18_sh.setDaemon(true);
		// Inicialização da Thread da nave do Luke
		luke_percurso_08_sh = new Luke(this, nave_luke);
		luke_percurso_08_sh.setName("Luke");
		luke_percurso_08_sh.setDaemon(true);
		// Inicialização da Thread da nave do Maul
		maul_percurso_12_sa = new Maul(this, nave_maul);
		maul_percurso_12_sa.setName("Maul");
		maul_percurso_12_sa.setDaemon(true);
		// Inicialização da Thread da nave do Obiwan
		obiwan_percurso_19_sh = new Obiwan(this, nave_obiwan);
		obiwan_percurso_19_sh.setName("Obi Wan");
		obiwan_percurso_19_sh.setDaemon(true);
		// Inicialização da Thread da nave do Sidius
		sidius_percurso_21_sh = new Sidius(this, nave_sidious);
		sidius_percurso_21_sh.setName("Sidius");
		sidius_percurso_21_sh.setDaemon(true);
		// Inicialização da Thread do Vader
		vader_percurso_01_sh = new Vader(this, nave_vader);
		vader_percurso_01_sh.setName("Vader");
		vader_percurso_01_sh.setDaemon(true);
		// Inicialização da Thread do Yoda
		yoda_percurso_04_sa = new Yoda(this, nave_yoda);
		yoda_percurso_04_sa.setName("Yoda");
		yoda_percurso_04_sa.setDaemon(true);
		// Inicia as Threads
		ahsoka_percurso_15_sh.start();
		kylo_percurso_18_sh.start();
		luke_percurso_08_sh.start();
		maul_percurso_12_sa.start();
		obiwan_percurso_19_sh.start();
		sidius_percurso_21_sh.start();
		vader_percurso_01_sh.start();
		yoda_percurso_04_sa.start();

		// Habilitação dos botões de controle da tela
		// Botões do Yoda
		aumentar_velocidade_yoda.setDisable(false);
		diminuir_velocidade_yoda.setDisable(false);
		pausar_yoda.setDisable(false);
		ocultar_percurso_yoda.setDisable(false);
		// Botões da Ahsoka
		aumentar_velocidade_ahsoka.setDisable(false);
		diminuir_velocidade_ahsoka.setDisable(false);
		pausar_ahsoka.setDisable(false);
		ocultar_percurso_ahsoka.setDisable(false);
		// Botões do Kylo
		aumentar_velocidade_kylo.setDisable(false);
		diminuir_velocidade_kylo.setDisable(false);
		pausar_kylo.setDisable(false);
		ocultar_percurso_kylo.setDisable(false);
		// Botões do Luke
		aumentar_velocidade_luke.setDisable(false);
		diminuir_velocidade_luke.setDisable(false);
		pausar_luke.setDisable(false);
		ocultar_percurso_luke.setDisable(false);
		// Botões do Maul
		aumentar_velocidade_maul.setDisable(false);
		diminuir_velocidade_maul.setDisable(false);
		pausar_maul.setDisable(false);
		ocultar_percurso_maul.setDisable(false);
		// Botões do Obiwan
		aumentar_velocidade_obiwan.setDisable(false);
		diminuir_velocidade_obiwan.setDisable(false);
		pausar_obiwan.setDisable(false);
		ocultar_percurso_obiwan.setDisable(false);
		// Botões do Sidius
		aumentar_velocidade_sidious.setDisable(false);
		diminuir_velocidade_sidious.setDisable(false);
		pausar_sidious.setDisable(false);
		ocultar_percurso_sidious.setDisable(false);
		// Botões do Vader
		aumentar_velocidade_vader.setDisable(false);
		diminuir_velocidade_vader.setDisable(false);
		pausar_vader.setDisable(false);
		ocultar_percurso_vader.setDisable(false);
		// Botão de reset
		reset.setDisable(false);
		start.setDisable(true);

		iniciarMusica();
	} // Fim do método botaoIniciar

} // Fim da classe Controller
