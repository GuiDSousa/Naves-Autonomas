package model;

/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Ahsoka
 * Funcao...........: Aplicação modelo que inicia a nave Ahsoka
 *************************************************************** */
import javafx.application.Platform; // Importa a classe Platform
import javafx.scene.image.ImageView; // Importa a classe ImageView
import java.util.concurrent.locks.Condition; // Importa a classe Condition
import java.util.concurrent.locks.Lock; // Importa a classe Lock  
import java.util.concurrent.locks.ReentrantLock; // Importa a classe ReentrantLock
import controller.mainController; // Importa a classe MainController

// Ahsoka percorre o percurso 15 no sentido horário
// Classe Ahsoka herda de Thread
public class Ahsoka extends Thread {
  private int velocidade = 5;
  private int posicaoXinicial = 395;
  private int posicaoYinicial = 0;
  private ImageView nave;
  private boolean start = true;
  private boolean pause = false;
  private final Lock lock = new ReentrantLock();
  private final Condition condition = lock.newCondition();
  mainController controle = new mainController();

  // Construtor
  public Ahsoka(mainController controle, ImageView nave) {
    this.nave = nave;
    this.controle = controle;
  } // Fim do construtor

  /*
   * Método: parar()
   * Funcao: Para a nave utilizando o lock
   * Parametros: void
   * Retorno: void
   */
  public void parar() {
    lock.lock();
    try {
      this.pause = true;
    } finally {
      lock.unlock();
    } // Fim do finally
  } // Fim do método parar

  /*
   * Método: retomar()
   * Funcao: Retoma a nave utilizando o lock
   * Parametros: void
   * Retorno: void
   */

  public void retomar() {
    lock.lock();
    try {
      this.pause = false;
      condition.signal();
    } finally {
      lock.unlock();
    } // Fim do finally
  } // Fim do método retomar

  /*
   * Método: isPaused()
   * Funcao: Retorna o estado da nave
   * Parametros: void
   * Retorno: boolean
   */
  public boolean isPaused() {
    return pause;
  } // Fim do método isPaused

  /*
   * Método: isStart()
   * Funcao: Retorna o estado da nave
   * Parametros: void
   * Retorno: boolean
   */

  public boolean isStart() {
    return start;
  } // Fim do método isStart

  /*
   * Método: getVelocidade()
   * Funcao: Retorna a velocidade da nave
   * Parametros: void
   * Retorno: int
   */
  public int getVelocidade() {
    return velocidade;
  } // Fim do método getVelocidade

  /*
   * Método: setVelocidade()
   * Funcao: Seta a velocidade da nave
   * Parametros: int
   * Retorno: void
   */

  public void setVelocidade(int velocidade) {
    if (velocidade > 0) {
      this.velocidade = velocidade;
    } // Fim do if
  } // Fim do método setVelocidade

  /*
   * Método: retornarBaseDecolagem()
   * Funcao: Retorna a base de decolagem
   * Parametros: void
   * Retorno: void
   */
  public void retornarBaseDecolagem() {
    Platform.runLater(() -> {
      nave.setLayoutX(460);
      nave.setLayoutY(75);
      nave.setRotate(0);
    }); // Fim do Platform
  } // Fim do método retornarBaseDecolagem

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        if (!pause) {
          Thread.sleep(1000);
          moverNaveAhsoka();
        } // Fim do if
      } // Fim do while
    } catch (InterruptedException e) {
      return;
    } // Fim do catch
  } // Fim do método run

  /*
   * Método: moverNaveAhsoka()
   * Funcao: Move a nave Ahsoka
   * Parametros: void
   * Retorno: void
   */
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
    controle.semaforoACE_22.acquire();
    controle.semaforoFIN_23.acquire();
    moverCima(0);
    controle.semaforoACE_22.release();
    controle.semaforoFIN_23.release();
    controle.semaforoHILL_47.release();
    controle.semaforoLUNA_48.release();
    controle.semaforoVIBE_49.release();

    System.out.println("Ahsoka finalizou o percurso e está abastecendo");

  } // Fim do método moverNaveAhsoka


	/*
	 * Método: moverEsquerda()
	 * Funcao: Move a nave para a esquerda
	 * Parametros: double COORD_X
	 * Retorno: void
	 */
	public void moverEsquerda(double COORD_X) {
		while (posicaoXinicial != COORD_X) {
			pauseIfNeeded(); // Pausa a nave se necessário
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(270);
				nave.setLayoutX(posicaoXinicial);
			}); // Fim do Platform
			posicaoXinicial--; // Decrementa a posição X inicial
		} // Fim do while
	} // Fim do método moverEsquerda()

	/*
	 * Método: moverDireita()
	 * Funcao: Move a nave para a direita
	 * Parametros: double COORD_X
	 * Retorno: void
	 */
	public void moverDireita(double COORD_X) {
		while (posicaoXinicial != COORD_X) {
			pauseIfNeeded(); // Pausa a nave se necessário
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(90);
				nave.setLayoutX(posicaoXinicial);
			}); // Fim do Platform
			posicaoXinicial++; // Incrementa a posição X inicial
		} // Fim do while
	} // Fim do método moverDireita()

	/*
	 * Método: moverCima()
	 * Funcao: Move a nave para cima
	 * Parametros: double COORD_Y
	 * Retorno: void
	 */
	public void moverCima(double COORD_Y) {
		while (posicaoYinicial != COORD_Y) {
			pauseIfNeeded(); // Pausa a nave se necessário
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(0);
				nave.setLayoutY(posicaoYinicial);
			}); // Fim do Platform
			posicaoYinicial--; // Decrementa a posição Y inicial
		} // Fim do while
	}

	/*
	 * Método: moverBaixo()
	 * Funcao: Move a nave para baixo
	 * Parametros: double COORD_Y
	 * Retorno: void
	 */
	public void moverBaixo(double COORD_Y) {
		while (posicaoYinicial != COORD_Y) {
			pauseIfNeeded(); // Pausa a nave se necessário
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(180);
				nave.setLayoutY(posicaoYinicial);
			}); // Fim do Platform
			posicaoYinicial++; // Incrementa a posição Y inicial
		} // Fim do while
	} // Fim do método moverBaixo()

	/*
	 * Método: retornarPosicaoInicial()
	 * Funcao: Retorna a nave para a posição inicial
	 * Parametros: void
	 * Retorno: void
	 */
  public void retornarPosicaoInicial() {
    Platform.runLater(() -> {
      nave.setLayoutX(395);
      nave.setLayoutY(0);
      nave.setRotate(0);
    }); // Fim do Platform
  } // Fim do método retornarPosicaoInicial

	/*
	 * Método: pauseIfNeeded()
	 * Funcao: Pausa a nave se necessário
	 * Parametros: void
	 * Retorno: void
	 */
	private void pauseIfNeeded() {
		lock.lock(); // Adquire o lock
		try {
			while (pause) {
				try {
					condition.await(); // Aguarda o sinal do lock
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // Set the interrupt status/flag
				} // Fim do try
			} // Fim do while
		} finally {
			lock.unlock(); // Libera o lock
		} // Fim do try
	} // Fim do método pauseIfNeeded()
} // Fim da classe Ahsoka
