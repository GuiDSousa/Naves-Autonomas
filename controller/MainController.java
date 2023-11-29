package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
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

	// Botões de controle
	@FXML
	private Button start;
	@FXML
	private Button reset;

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

	private MediaPlayer mediaPlayer;

	// Botões de pause
	public void pausarYoda() {
		if (this.yoda_percurso_04_sa.isStart()) {
			yoda_percurso_04_sa.parar();
			slider_yoda.setDisable(true);
			ocultar_percurso_yoda.setDisable(true);
		} else {
			yoda_percurso_04_sa.retomar();
			slider_yoda.setDisable(false);
			ocultar_percurso_yoda.setDisable(false);
		}
	}

	public void pausarAhsoka() {
		if (this.ahsoka_percurso_15_sh.isStart()) {
			ahsoka_percurso_15_sh.parar();
			slider_ahsoka.setDisable(true);
			ocultar_percurso_ahsoka.setDisable(true);
		} else {
			ahsoka_percurso_15_sh.retomar();
			slider_ahsoka.setDisable(false);
			ocultar_percurso_ahsoka.setDisable(false);
		}
	}

	public void pausarKylo() {
		if (this.kylo_percurso_18_sh.isStart()) {
			kylo_percurso_18_sh.parar();
			slider_kylo.setDisable(true);
			ocultar_percurso_kylo.setDisable(true);
		} else {
			kylo_percurso_18_sh.retomar();
			slider_kylo.setDisable(false);
			ocultar_percurso_kylo.setDisable(false);
		}
	}

	public void pausarLuke() {
		if (this.luke_percurso_08_sh.isStart()) {
			luke_percurso_08_sh.parar();
			slider_luke.setDisable(true);
			ocultar_percurso_luke.setDisable(true); 
		} else {
			luke_percurso_08_sh.retomar();
			slider_luke.setDisable(false);
			ocultar_percurso_luke.setDisable(false);
		}
	}

	public void pausarMaul() {
		if (this.maul_percurso_12_sa.isStart()) {
			maul_percurso_12_sa.parar();
			slider_maul.setDisable(true);
			ocultar_percurso_maul.setDisable(true);
		} else {
			maul_percurso_12_sa.retomar();
			slider_maul.setDisable(false);
			ocultar_percurso_maul.setDisable(false);
		}
	}

	public void pausarObiwan() {
		if (this.obiwan_percurso_19_sh.isStart()) {
			obiwan_percurso_19_sh.parar();
			slider_obiwan.setDisable(true);
			ocultar_percurso_obiwan.setDisable(true);
		} else {
			obiwan_percurso_19_sh.retomar();
			slider_obiwan.setDisable(false);
			ocultar_percurso_obiwan.setDisable(false);
		}
	}

	public void pausarSidius() {
		if (this.sidius_percurso_21_sh.isStart()) {
			sidius_percurso_21_sh.parar();
			slider_sidious.setDisable(true);
			ocultar_percurso_sidious.setDisable(true);
		} else {
			sidius_percurso_21_sh.retomar();
			slider_sidious.setDisable(false);
			ocultar_percurso_sidious.setDisable(false);
		}
	}

	public void pausarVader() {
		if (this.vader_percurso_01_sh.isStart()) {
			vader_percurso_01_sh.parar();
			slider_vader.setDisable(true);
			ocultar_percurso_vader.setDisable(true);
		} else {
			vader_percurso_01_sh.retomar();
			slider_vader.setDisable(false);
			ocultar_percurso_vader.setDisable(false);
		}
	}

	public void interromperThreads() {
		// Interrompe as Threads
		if (this.yoda_percurso_04_sa.isStart()) {
			yoda_percurso_04_sa.parar();
		} 
		this.yoda_percurso_04_sa.interrupt();
		if (this.ahsoka_percurso_15_sh.isStart()) {
			ahsoka_percurso_15_sh.parar();
		} 
		this.ahsoka_percurso_15_sh.interrupt(); 
		if (this.kylo_percurso_18_sh.isStart()) {
			kylo_percurso_18_sh.parar();
		} 
		this.kylo_percurso_18_sh.interrupt(); 
		if (this.luke_percurso_08_sh.isStart()) {
			luke_percurso_08_sh.parar();
		} 
		this.luke_percurso_08_sh.interrupt(); 
		if (this.maul_percurso_12_sa.isStart()) {
			maul_percurso_12_sa.parar();
		} 
		this.maul_percurso_12_sa.interrupt();
		if (this.obiwan_percurso_19_sh.isStart()) {
			obiwan_percurso_19_sh.parar();
		}
		this.obiwan_percurso_19_sh.interrupt();
		if (this.sidius_percurso_21_sh.isStart()) {
			sidius_percurso_21_sh.parar();
		}
		this.sidius_percurso_21_sh.interrupt();
		if (this.vader_percurso_01_sh.isStart()) {
			vader_percurso_01_sh.parar();
		}
		this.vader_percurso_01_sh.interrupt();

	}
	

	public void reset() {
		Platform.runLater(() -> {
			interromperThreads();
			reiniciarNaves();
			desabiliarBotoesControle();
			stopAudio();
		  });
		start.setDisable(false);
	}
	public void reiniciarNaves() {
		ahsoka_percurso_15_sh.retornarPosicaoInicial();
		kylo_percurso_18_sh.retornarPosicaoInicial();
		luke_percurso_08_sh.retornarPosicaoInicial();
		maul_percurso_12_sa.retornarPosicaoInicial();
		obiwan_percurso_19_sh.retornarPosicaoInicial();
		sidius_percurso_21_sh.retornarPosicaoInicial();
		vader_percurso_01_sh.retornarPosicaoInicial();
		yoda_percurso_04_sa.retornarPosicaoInicial();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		desabiliarBotoesControle();
	}

	public void desabiliarBotoesControle() {
				// Desabilitar botões de controle das naves
		// Botões do Yoda
		slider_yoda.setDisable(true);
		pausar_yoda.setDisable(true);
		ocultar_percurso_yoda.setDisable(true);
		// Botões da Ahsoka
		slider_ahsoka.setDisable(true);
		pausar_ahsoka.setDisable(true);
		ocultar_percurso_ahsoka.setDisable(true);
		// Botões do Kylo
		slider_kylo.setDisable(true);
		pausar_kylo.setDisable(true);
		ocultar_percurso_kylo.setDisable(true);
		// Botões do Luke
		slider_luke.setDisable(true);
		pausar_luke.setDisable(true);
		ocultar_percurso_luke.setDisable(true);
		// Botões do Maul
		slider_maul.setDisable(true);
		pausar_maul.setDisable(true);
		ocultar_percurso_maul.setDisable(true);
		// Botões do Obiwan
		slider_obiwan.setDisable(true);
		pausar_obiwan.setDisable(true);
		ocultar_percurso_obiwan.setDisable(true);
		// Botões do Sidius
		slider_sidious.setDisable(true);
		pausar_sidious.setDisable(true);
		ocultar_percurso_sidious.setDisable(true);
		// Botões do Vader
		slider_vader.setDisable(true);
		pausar_vader.setDisable(true);
		ocultar_percurso_vader.setDisable(true);
		// Botão de reset
		reset.setDisable(true);
	}

	public void iniciarMusica() {

		Media sound = new Media(getClass().getResource("starwarsthrone.wav").toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        // Associar o evento handleStopMusic ao botão de parar a música quando o mouse for pressionado sobre a ImageView stopMusic
}

    @FXML private void handleStopMusic() {
        stopAudio();
      } // Fim do método handleStopMusic

    private void stopAudio() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.seek(Duration.ZERO);
    } // Fim do if
  }

	public void botaoIniciar() {
		// Inicialização da Thread da nave da Ahsoka
		ahsoka_percurso_15_sh = new Ahsoka(this, nave_ahsoka, slider_ahsoka);
		ahsoka_percurso_15_sh.setName("Ahsoka");
		ahsoka_percurso_15_sh.setDaemon(true);
		// Inicialização da Thread da nave do Kylo
		kylo_percurso_18_sh = new Kylo(this, nave_kylo, slider_kylo);
		kylo_percurso_18_sh.setName("Kylo");
		kylo_percurso_18_sh.setDaemon(true);
		// Inicialização da Thread da nave do Luke
		luke_percurso_08_sh = new Luke(this, nave_luke, slider_luke);
		luke_percurso_08_sh.setName("Luke");
		luke_percurso_08_sh.setDaemon(true);
		// Inicialização da Thread da nave do Maul
		maul_percurso_12_sa = new Maul(this, nave_maul, slider_maul);
		maul_percurso_12_sa.setName("Maul");
		maul_percurso_12_sa.setDaemon(true);
		// Inicialização da Thread da nave do Obiwan
		obiwan_percurso_19_sh = new Obiwan(this, nave_obiwan, slider_obiwan);
		obiwan_percurso_19_sh.setName("Obi Wan");
		obiwan_percurso_19_sh.setDaemon(true);
		// Inicialização da Thread da nave do Sidius
		sidius_percurso_21_sh = new Sidius(this, nave_sidious, slider_sidious);
		sidius_percurso_21_sh.setName("Sidius");
		sidius_percurso_21_sh.setDaemon(true);
		// Inicialização da Thread do Vader
		vader_percurso_01_sh = new Vader(this, nave_vader, slider_vader);
		vader_percurso_01_sh.setName("Vader");
		vader_percurso_01_sh.setDaemon(true);
		// Inicialização da Thread do Yoda
		yoda_percurso_04_sa = new Yoda(this, nave_yoda, slider_yoda);
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
		slider_yoda.setDisable(false);
		pausar_yoda.setDisable(false);
		ocultar_percurso_yoda.setDisable(false);
		// Botões da Ahsoka
		slider_ahsoka.setDisable(false);
		pausar_ahsoka.setDisable(false);
		ocultar_percurso_ahsoka.setDisable(false);
		// Botões do Kylo
		slider_kylo.setDisable(false);
		pausar_kylo.setDisable(false);
		ocultar_percurso_kylo.setDisable(false);
		// Botões do Luke
		slider_luke.setDisable(false);
		pausar_luke.setDisable(false);
		ocultar_percurso_luke.setDisable(false);
		// Botões do Maul
		slider_maul.setDisable(false);
		pausar_maul.setDisable(false);
		ocultar_percurso_maul.setDisable(false);
		// Botões do Obiwan
		slider_obiwan.setDisable(false);
		pausar_obiwan.setDisable(false);
		ocultar_percurso_obiwan.setDisable(false);
		// Botões do Sidius
		slider_sidious.setDisable(false);
		pausar_sidious.setDisable(false);
		ocultar_percurso_sidious.setDisable(false);
		// Botões do Vader
		slider_vader.setDisable(false);
		pausar_vader.setDisable(false);
		ocultar_percurso_vader.setDisable(false);
		// Botão de reset
		reset.setDisable(false);
		start.setDisable(true);
		iniciarMusica();
	}

}
