package model;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import controller.MainController;

// Ahsoka percorre o percurso 15 no sentido horário
public class Ahsoka extends Thread {

  private int velocidade = 5;
  private int posicaoXinicial = 393;
  private int posicaoYinicial = 360;

  private ImageView nave;
  private boolean start = true;
  private Slider slider;

  MainController controle = new MainController();

  // Construtor
  public Ahsoka(MainController controle, ImageView nave, Slider slider) {
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

  // Lógica de movimentação da nave no percurso anti-horário
  @Override
  public void run() {
    while (start) {
      try {
        moverNaveAhsoka();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void moverNaveAhsoka() throws InterruptedException {
    moverEsquerda(296);
    moverEsquerda(200);
    moverEsquerda(100);
    moverCima(260);
    moverCima(160);
    moverCima(60);
    moverDireita(200);
    moverDireita(296);
    moverDireita(393);
    moverBaixo(160);
    moverBaixo(260);
    moverBaixo(360);
  }

  // Método para mover a esquerda, diminuindo o valor de X
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
