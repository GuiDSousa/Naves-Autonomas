package model;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Obiwan percorre o percurso 19 no sentido horário
public class Obiwan extends Thread{
  private int velocidade = 5;
	private int posicaoXinicial = 393;
	private int posicaoYinicial = 560;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;

	MainController controle = new MainController();

	public Obiwan(MainController controle, ImageView nave, Slider slider) {
		this.posicaoXinicial = posicaoXinicial;
		this.posicaoYinicial = posicaoYinicial;
		this.nave = nave;
		this.controle = controle;
		this.slider = slider;
		modificarValor(slider);
	}
	// Método para modificar o valor do slider
	private void modificarValor(Slider slider) {
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			// Atualizar a velocidade quando o valor do slider muda
			int novaVelocidade = newValue.intValue();
		});
	}

	// Método para parar a nave
	public void parar() {
		this.start = false;
	}

	@Override
	public void run() {
		while (start) {
			try {
				moverNaveObiwan();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void moverNaveObiwan() throws InterruptedException {
		moverEsquerda(296);
		moverCima(460);
		moverCima(360);
		moverEsquerda(200);
		moverEsquerda(100);
		moverCima(260);
		moverDireita(200);
		moverDireita(296);
		moverCima(160);
		moverCima(60);
		moverDireita(393);
		moverBaixo(160);
		moverBaixo(260);
		moverDireita(490);
		moverDireita(586);
		moverBaixo(360);
		moverEsquerda(490);
		moverEsquerda(393);
		moverBaixo(460);
		moverBaixo(560);
	}

		public void moverEsquerda(double COORD_X) throws InterruptedException {
    while (posicaoXinicial != COORD_X) {
      sleep(500 / (velocidade * 5));
      Platform.runLater(() -> {
        nave.setRotate(270);
        nave.setLayoutX(posicaoXinicial);
      });
      posicaoXinicial--;
    }
  }

  // Método para mover a direita, aumentando o valor de X
  public void moverDireita(double COORD_X) throws InterruptedException {
    while (posicaoXinicial != COORD_X) {
      sleep(500 / (velocidade * 5));
      Platform.runLater(() -> {
        nave.setRotate(90);
        nave.setLayoutX(posicaoXinicial);
      });
      posicaoXinicial++;
    }
  }

  // Método para mover para cima, diminuindo o valor de Y
  public void moverCima(double COORD_Y) throws InterruptedException {
    while (posicaoYinicial != COORD_Y) {
      sleep(500 / (velocidade * 5));
      Platform.runLater(() -> {
        nave.setRotate(0);
        nave.setLayoutY(posicaoYinicial);
      });
      posicaoYinicial--;
    }
  }

  // Método para mover para baixo, aumentando o valor de Y
  public void moverBaixo(double COORD_Y) throws InterruptedException {
    while (posicaoYinicial != COORD_Y) {
      sleep(500 / (velocidade * 5));
      Platform.runLater(() -> {
        nave.setRotate(180);
        nave.setLayoutY(posicaoYinicial);
      });
      posicaoYinicial++;
    }
  }


}
