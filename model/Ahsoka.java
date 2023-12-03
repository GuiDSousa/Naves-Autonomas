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
  private int posicaoXinicial = 395;
  private int posicaoYinicial = 0;

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

  public void retornarBaseDecolagem() {
		Platform.runLater(() -> {
			nave.setLayoutX(460);
			nave.setLayoutY(75);
			nave.setRotate(0);
		});
	}

  @Override
  public void run() {
      try {
          while (!Thread.currentThread().isInterrupted()) {
              if (!pause) {
                  moverNaveAhsoka();
              }
          }
      } catch (InterruptedException e) {
          // Thread was interrupted while waiting or sleeping
          // Log the exception and return from the method
          e.printStackTrace();
          return;
      }
  }

  public void moverNaveAhsoka() throws InterruptedException {


    retornarPosicaoInicial();
    controle.semaforoAXE_27.acquire();
    controle.semaforoBUD_26.acquire();
    controle.semaforoWIN_25.acquire();
    moverEsquerda(270);
    moverEsquerda(145);
    moverEsquerda(20);

      controle.semaforoAXE_27.release();
      controle.semaforoBUD_26.release();
      controle.semaforoWIN_25.release();




    controle.semaforoHALO_34.acquire();
    controle.semaforoROSE_33.acquire();
    controle.semaforoVINE_32.acquire();
    moverBaixo(125);
    moverBaixo(245);
    moverBaixo(375);
      controle.semaforoHALO_34.release();
      controle.semaforoROSE_33.release();
      controle.semaforoVINE_32.release();

    controle.semaforoZEN_10.acquire();
    controle.semaforoJET_11.acquire();
    controle.semaforoJOY_12.acquire();
    moverDireita(145);
    moverDireita(270);
    moverDireita(395);

      controle.semaforoZEN_10.release();
      controle.semaforoJET_11.release();
      controle.semaforoJOY_12.release();

    controle.semaforoHILL_47.acquire();
    controle.semaforoLUNA_48.acquire();
    controle.semaforoVIBE_49.acquire();
    moverCima(245);
    moverCima(125);
    moverCima(0);
      controle.semaforoHILL_47.release();
      controle.semaforoLUNA_48.release();
      controle.semaforoVIBE_49.release();

  } 

  public void moverEsquerda(double COORD_X) {
    while (posicaoXinicial != COORD_X) {
        pauseIfNeeded();
        try {
            sleep(500 / (velocidade * 5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Set the interrupt status/flag
            return; // Stop the method
        }
        Platform.runLater(() -> {
            nave.setRotate(270);
            nave.setLayoutX(posicaoXinicial);
        });
        posicaoXinicial--;
    }
}

public void moverDireita(double COORD_X) {
    while (posicaoXinicial != COORD_X) {
        pauseIfNeeded();
        try {
            sleep(500 / (velocidade * 5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Set the interrupt status/flag
            return; // Stop the method
        }
        Platform.runLater(() -> {
            nave.setRotate(90);
            nave.setLayoutX(posicaoXinicial);
        });
        posicaoXinicial++;
    }
}

public void moverCima(double COORD_Y) {
    while (posicaoYinicial != COORD_Y) {
        pauseIfNeeded();
        try {
            sleep(500 / (velocidade * 5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Set the interrupt status/flag
            return; // Stop the method
        }
        Platform.runLater(() -> {
            nave.setRotate(0);
            nave.setLayoutY(posicaoYinicial);
        });
        posicaoYinicial--;
    }
}

public void moverBaixo(double COORD_Y) {
    while (posicaoYinicial != COORD_Y) {
        pauseIfNeeded();
        try {
            sleep(500 / (velocidade * 5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Set the interrupt status/flag
            return; // Stop the method
        }
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
      nave.setLayoutX(395);
      nave.setLayoutY(0);
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
