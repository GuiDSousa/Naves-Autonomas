package model;

/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Vader
 * Funcao...........: Aplicação modelo que inicia a nave Vader
 *************************************************************** */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import controller.mainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

// Vader percorre o percurso 1 no sentido horário
// Classe Vader herda de Thread
public class Vader extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 395;
	private int posicaoYinicial = 245;
	private ImageView nave;
	private boolean start = true;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	mainController controle = new mainController();

	// Construtor
	public Vader(mainController controle, ImageView nave) {
		this.nave = nave;
		this.controle = controle;
	} // Fim do construtor

	/*
	 * Método: retornarPosicaoInicial()
	 * Funcao: Retorna a nave para a posição inicial
	 * Parametros: void
	 * Retorno: void
	 */
	public void retornarPosicaoInicial() {
		Platform.runLater(() -> {
			nave.setLayoutX(395);
			nave.setLayoutY(245);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarPosicaoInicial

	/*
	 * Método: parar()
	 * Funcao: Pausa a execução da nave
	 * Parametros: void
	 * Retorno: void
	 */
	public void parar() {
		lock.lock();
		try {
			this.pause = true;
		} finally {
			lock.unlock();
		} // fim do finally
	} // fim do método parar

	/*
	 * Método: retomar()
	 * Funcao: Retoma a execução da nave
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
		} // fim do finally
	} // fim do método retomar

	/*
	 * Método: isStart()
	 * Funcao: Verifica se a nave está iniciada
	 * Parametros: void
	 * Retorno: boolean
	 */
	public boolean isStart() {
		return start;
	} // fim do método isStart

	/*
	 * Método: isPaused()
	 * Funcao: Verifica se a nave está pausada
	 * Parametros: void
	 * Retorno: boolean
	 */
	public boolean isPaused() {
		return pause;
	} // fim do método isPaused

	/*
	 * Método: getVelocidade()
	 * Funcao: Retorna a velocidade da nave
	 * Parametros: void
	 * Retorno: int
	 */
	public int getVelocidade() {
		return velocidade;
	} // fim do método getVelocidade

	/*
	 * Método: setVelocidade(int velocidade)
	 * Funcao: Define a velocidade da nave
	 * Parametros: velocidade - a velocidade da nave
	 * Retorno: void
	 */
	public void setVelocidade(int velocidade) {
		if (velocidade > 0) {
			this.velocidade = velocidade;
		} // Fim do if
	} // Fim do método setVelocidade

	/*
	 * Método: retornarBaseDecolagem()
	 * Funcao: Retorna a nave para a base de decolagem
	 * Parametros: void
	 * Retorno: void
	 */
	public void retornarBaseDecolagem() {
		Platform.runLater(() -> {
			nave.setLayoutX(334);
			nave.setLayoutY(201);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarBaseDecolagem

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					Thread.sleep(1000);
					moverNaveVader();
				} // Fim do if
			} // Fim do while
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			// e.printStackTrace();
			return;
		} // Fim do catch
	} // Fim do método run

	/*
	 * Método: moverNaveVader()
	 * Funcao: Move a nave de acordo com o percurso
	 * Parametros: void
	 * Retorno: void
	 */
	public void moverNaveVader() throws InterruptedException {
		retornarPosicaoInicial();
		controle.semaforoINK_17.acquire();
		moverEsquerda(270);
		controle.semaforoINK_17.release();

		controle.semaforoGAZE_42.acquire();
		moverBaixo(375);
		controle.semaforoGAZE_42.release();

		controle.semaforoJET_11.acquire();
		moverEsquerda(145);
		controle.semaforoJET_11.release();

		controle.semaforoFUJI_36.acquire();
		controle.semaforoAIMI_35.acquire();
		moverBaixo(505);
		moverBaixo(625);
		controle.semaforoFUJI_36.release();
		controle.semaforoAIMI_35.release();

		controle.semaforoELM_0.acquire();
		moverEsquerda(20);
		controle.semaforoELM_0.release();

		controle.semaforoAIKO_30.acquire();
		controle.semaforoZORO_31.acquire();
		controle.semaforoVINE_32.acquire();
		controle.semaforoROSE_33.acquire();
		controle.semaforoHALO_34.acquire();
		moverCima(505);
		moverCima(375);
		moverCima(245);
		moverCima(125);
		moverCima(0);
		controle.semaforoAIKO_30.release();
		controle.semaforoZORO_31.release();
		controle.semaforoVINE_32.release();
		controle.semaforoROSE_33.release();
		controle.semaforoHALO_34.release();

		controle.semaforoWIN_25.acquire();
		controle.semaforoBUD_26.acquire();
		moverDireita(145);
		moverDireita(270);
		controle.semaforoWIN_25.release();
		controle.semaforoBUD_26.release();

		controle.semaforoDUSK_44.acquire();
		moverBaixo(125);
		controle.semaforoDUSK_44.release();

		controle.semaforoACE_22.acquire();
		moverDireita(395);
		controle.semaforoACE_22.release();

		controle.semaforoVIBE_49.acquire();
		moverCima(0);
		controle.semaforoVIBE_49.release();

		controle.semaforoZOE_28.acquire();
		controle.semaforoGAL_29.acquire();
		moverDireita(520);
		moverDireita(645);
		controle.semaforoZOE_28.release();
		controle.semaforoGAL_29.release();

		controle.semaforoZENO_59.acquire();
		controle.semaforoMIST_58.acquire();
		controle.semaforoPAVE_57.acquire();
		controle.semaforoHIRO_56.acquire();
		controle.semaforoBUGG_55.acquire();
		moverBaixo(125);
		moverBaixo(245);
		moverBaixo(375);
		moverBaixo(505);
		moverBaixo(625);
		controle.semaforoZENO_59.release();
		controle.semaforoMIST_58.release();
		controle.semaforoPAVE_57.release();
		controle.semaforoHIRO_56.release();
		controle.semaforoBUGG_55.release();

		controle.semaforoBEE_4.acquire();
		moverEsquerda(520);
		controle.semaforoBEE_4.release();

		controle.semaforoPAIN_50.acquire();
		controle.semaforoHANA_51.acquire();
		moverCima(505);
		moverCima(375);
		controle.semaforoPAIN_50.release();
		controle.semaforoHANA_51.release();

		controle.semaforoLUX_13.acquire();
		moverEsquerda(395);
		controle.semaforoLUX_13.release();

		controle.semaforoHILL_47.acquire();
		moverCima(245);
		controle.semaforoHILL_47.release();

		System.out.println("Vader finalizou o percurso e está abastecendo");
	} // Fim do método moverNaveVader

	/*
	 * Método: moverEsquerda(double COORD_X)
	 * Funcao: Move a nave para a esquerda
	 * Parametros: COORD_X - a coordenada X final
	 * Retorno: void
	 */
	public void moverEsquerda(double COORD_X) {
		while (posicaoXinicial != COORD_X) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(270);
				nave.setLayoutX(posicaoXinicial);
			});
			posicaoXinicial--;
		} // Fim do while
	} // Fim do método moverEsquerda

	/*
	 * Método: moverDireita(double COORD_X)
	 * Funcao: Move a nave para a direita
	 * Parametros: COORD_X - a coordenada X final
	 * Retorno: void
	 */
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
		} // Fim do while
	} // Fim do método moverDireita

	/*
	 * Método: moverCima(double COORD_Y)
	 * Funcao: Move a nave para cima
	 * Parametros: COORD_Y - a coordenada Y final
	 * Retorno: void
	 */
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
			}); // Fim do Platform
			posicaoYinicial--;
		} // Fim do while
	} // Fim do método moverCima

	/*
	 * Método: moverBaixo(double COORD_Y)
	 * Funcao: Move a nave para baixo
	 * Parametros: COORD_Y - a coordenada Y final
	 * Retorno: void
	 */
	public void moverBaixo(double COORD_Y) {
		while (posicaoYinicial != COORD_Y) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(180);
				nave.setLayoutY(posicaoYinicial);
			});
			posicaoYinicial++;
		} // Fim do while
	} // Fim do método moverBaixo

	private void pauseIfNeeded() {
		lock.lock();
		try {
			while (pause) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} // Fim do catch
			} // Fim do while
		} finally {
			lock.unlock();
		} // Fim do finally
	} // Fim do método pauseIfNeeded
} // Fim da classe Vader
