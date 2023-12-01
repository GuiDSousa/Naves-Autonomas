package model;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;

// Ahsoka percorre o percurso 15 no sentido horário
public class Ahsoka extends Thread {

  private int velocidade = 10;
  private int posicaoXinicial = 393;
  private int posicaoYinicial = 360;

  private ImageView nave;
  private boolean start = true;
  private Slider slider;
  private boolean pause = false;
  private final Lock lock = new ReentrantLock();
  private final Condition condition = lock.newCondition();
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

  public void parar() {
    lock.lock();
    try {
      this.pause = true;
    } finally {
      lock.unlock();
    }
  }

  public void retomar() {
    lock.lock();
    try {
      this.pause = false;
      condition.signal();
    } finally {
      lock.unlock();
    }
  }

  public boolean isPaused() {
    return pause;
  }

  public boolean isStart() {
    return start;
  }

  @Override
  public void run() {
    try {
      while (true) {
        if (!pause) { // Check the pause variable instead of the start variable
          moverNaveAhsoka();
        } else {
          Thread.sleep(500);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void moverNaveAhsoka() throws InterruptedException {
    controle.semaforoJOY_12.acquire();
    controle.semaforoJET_11.acquire();
    controle.semaforoZEN_10.acquire();
    moverEsquerda(296);
    moverEsquerda(200);
    moverEsquerda(100);
    controle.semaforoJOY_12.release();
    controle.semaforoJET_11.release();
    controle.semaforoZEN_10.release();
    controle.semaforoVINE_32.acquire();
    controle.semaforoROSE_33.acquire();
    controle.semaforoHALO_34.acquire();
    controle.semaforoJAX_20.acquire();
    moverCima(260);
    moverCima(160);
    moverCima(60);
    controle.semaforoVINE_32.release();
    controle.semaforoROSE_33.release();
    controle.semaforoHALO_34.release();
    controle.semaforoJAX_20.release();  
    controle.semaforoWIN_25.acquire();
    controle.semaforoBUD_26.acquire();
    controle.semaforoAXE_27.acquire();
    moverDireita(200);
    moverDireita(296);
    moverDireita(393);
    controle.semaforoWIN_25.release();
    controle.semaforoBUD_26.release();
    controle.semaforoAXE_27.release();
    controle.semaforoHILL_47.acquire();
    controle.semaforoLUNA_48.acquire();
    controle.semaforoVIBE_49.acquire();
    moverBaixo(160);
    moverBaixo(260);
    moverBaixo(360);
    controle.semaforoHILL_47.release();
    controle.semaforoLUNA_48.release();
    controle.semaforoVIBE_49.release();
  }

  // Método para mover a esquerda, diminuindo o valor de X
  public void moverEsquerda(double COORD_X) throws InterruptedException {
    while (posicaoXinicial != COORD_X) {
      pauseIfNeeded();
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
      pauseIfNeeded();
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
      pauseIfNeeded();
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
      pauseIfNeeded();
      sleep(500 / (velocidade * 5));
      Platform.runLater(() -> {
        nave.setRotate(180);
        nave.setLayoutY(posicaoYinicial);
      });
      posicaoYinicial++;
    }
  }

  // Método para retornar a posição inicial da nave
  public void retornarPosicaoInicial() {
    Platform.runLater(() -> {
      nave.setLayoutX(393);
      nave.setLayoutY(360);
      nave.setRotate(0);
    });
  }

  private void pauseIfNeeded() {
    lock.lock();
    try {
      while (pause) {
        try {
          condition.await();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    } finally {
      lock.unlock();
    }
  }
}
