package model;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Luke percorre o percurso 08 no sentido horário
public class Luke extends Thread{
	private int velocidade = 5;
	private int posicaoXinicial = 586;
	private int posicaoYinicial = 360;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;

	MainController controle = new MainController();

	public Luke(MainController controle, ImageView nave, Slider slider) {
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
	public void retornarPosicaoInicial() {
		Platform.runLater(() -> {
		  nave.setLayoutX(586);
		  nave.setLayoutY(360);
		  nave.setRotate(0);
		});
	  }

	// Método para parar a nave
	public void parar() {
		this.suspend();
		start = false;
	}

	public void retomar() {
		this.resume();
		start = true;
	}

	public boolean isStart() {
		return start;
	}

	@Override
	public void run() {
		while (start) {
			try {
				moverNaveLuke();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void moverNaveLuke() throws InterruptedException {
		moverEsquerda(490);
		moverEsquerda(393);
		moverEsquerda(296);
		moverEsquerda(200);
		moverEsquerda(100);
		moverCima(260);
		moverCima(160);
		moverDireita(200);
		moverDireita(296);
		moverDireita(393);
		moverDireita(490);
		moverDireita(586);
		moverBaixo(260);
		moverBaixo(360);
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
