package model;
/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Luke
 * Funcao...........: Aplicação modelo que inicia a nave Luke
 *************************************************************** */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import controller.mainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

// Luke percorre o percurso 08 no sentido horário
public class Luke extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 20;
	private int posicaoYinicial = 125;
	private ImageView nave;
	private boolean start = true;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	mainController controle = new mainController();

	public Luke(mainController controle, ImageView nave) {
		this.nave = nave;
		this.controle = controle;
	} // fim do construtor

	/*
	 * Método: retornarPosicaoInicial()
	 * Funcao: Retorna a nave para a posição inicial
	 * Parametros: void
	 * Retorno: void
	 */
	public void retornarPosicaoInicial() {
		Platform.runLater(() -> {
			nave.setLayoutX(20);
			nave.setLayoutY(125);
			nave.setRotate(0);
		}); // fim do Platform
	} // fim do método retornarPosicaoInicial

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
	 * Método: isPaused()
	 * Funcao: Verifica se a nave está pausada
	 * Parametros: void
	 * Retorno: boolean - true se a nave está pausada, false caso contrário
	 */
	public boolean isPaused() {
		return pause;
	} // fim do método isPaused

	/*
	 * Método: isStart()
	 * Funcao: Verifica se a nave foi iniciada
	 * Parametros: void
	 * Retorno: boolean - true se a nave foi iniciada, false caso contrário
	 */
	public boolean isStart() {
		return start;
	} // fim do método isStart

	/*
	 * Método: getVelocidade()
	 * Funcao: Retorna a velocidade da nave
	 * Parametros: void
	 * Retorno: int - velocidade da nave
	 */
	public int getVelocidade() {
		return velocidade;
	} // fim do método getVelocidade

	/*
	 * Método: setVelocidade(int velocidade)
	 * Funcao: Define a velocidade da nave
	 * Parametros: velocidade - nova velocidade da nave
	 * Retorno: void
	 */
	public void setVelocidade(int velocidade) {
		if (velocidade > 0) {
			this.velocidade = velocidade;
		} // fim do if
	} // fim do método setVelocidade

	/*
	 * Método: retornarBaseDecolagem()
	 * Funcao: Retorna a nave para a base de decolagem
	 * Parametros: void
	 * Retorno: void
	 */
	public void retornarBaseDecolagem() {
		Platform.runLater(() -> {
			nave.setLayoutX(80);
			nave.setLayoutY(76);
			nave.setRotate(0);
		}); // fim do Platform
	} // fim do método retornarBaseDecolagem

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					Thread.sleep(1000);
					moverNaveLuke();
				} // fim do if
			} // fim do while
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			// e.printStackTrace();
			return;
		} // fim do catch
	} // fim do método run

	/*
	 * Método: moverNaveLuke()
	 * Funcao: Move a nave Luke pelo percurso
	 * Parametros: void
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o sleep
	 */
	public void moverNaveLuke() throws InterruptedException {

		retornarPosicaoInicial();
		controle.semaforoJAX_20.acquire();
		controle.semaforoKAY_21.acquire();
		controle.semaforoACE_22.acquire();
		controle.semaforoFIN_23.acquire();
		controle.semaforoDOC_24.acquire();
		moverDireita(145);
		controle.semaforoIRIS_38.acquire();
		moverDireita(270);
		controle.semaforoIRIS_38.release();
		controle.semaforoLUSH_43.acquire();
		moverDireita(395);
		controle.semaforoLUSH_43.release();
		moverDireita(520);
		controle.semaforoECHO_53.acquire();
		moverDireita(645);
		controle.semaforoECHO_53.release();
		controle.semaforoJAX_20.release();
		controle.semaforoKAY_21.release();
		controle.semaforoACE_22.release();
		controle.semaforoFIN_23.release();
		controle.semaforoDOC_24.release();

		controle.semaforoMIST_58.acquire();
		controle.semaforoPAVE_57.acquire();
		moverBaixo(245);
		moverBaixo(375);
		controle.semaforoMIST_58.release();
		controle.semaforoPAVE_57.release();

		controle.semaforoORB_14.acquire();
		controle.semaforoLUX_13.acquire();
		controle.semaforoJOY_12.acquire();
		controle.semaforoJET_11.acquire();
		controle.semaforoZEN_10.acquire();
		moverEsquerda(520);
		moverEsquerda(395);
		moverEsquerda(270);
		moverEsquerda(145);
		moverEsquerda(20);
		controle.semaforoORB_14.release();
		controle.semaforoLUX_13.release();
		controle.semaforoJOY_12.release();
		controle.semaforoJET_11.release();
		controle.semaforoZEN_10.release();

		controle.semaforoVINE_32.acquire();
		controle.semaforoROSE_33.acquire();
		moverCima(245);
		moverCima(125);
		controle.semaforoVINE_32.release();
		controle.semaforoROSE_33.release();

		System.out.println("Luke finalizou o percurso e está abastecendo");
	} // fim do método moverNaveLuke

	/*
	 * Método: moverEsquerda(double COORD_X)
	 * Funcao: Move a nave para a esquerda até a coordenada X especificada
	 * Parametros: COORD_X - coordenada X de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o sleep
	 */
	public void moverEsquerda(double COORD_X) {
		while (posicaoXinicial != COORD_X) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // fim do try
			Platform.runLater(() -> {
				nave.setRotate(270);
				nave.setLayoutX(posicaoXinicial);
			}); // fim do Platform
			posicaoXinicial--;
		} // fim do while
	} // fim do método moverEsquerda

	/*
	 * Método: moverDireita(double COORD_X)
	 * Funcao: Move a nave para a direita até a coordenada X especificada
	 * Parametros: COORD_X - coordenada X de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o sleep
	 */
	public void moverDireita(double COORD_X) {
		while (posicaoXinicial != COORD_X) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // fim do try
			Platform.runLater(() -> {
				nave.setRotate(90);
				nave.setLayoutX(posicaoXinicial);
			}); // fim do Platform
			posicaoXinicial++;
		} // fim do while
	} // fim do método moverDireita

	/*
	 * Método: moverCima(double COORD_Y)
	 * Funcao: Move a nave para cima até a coordenada Y especificada
	 * Parametros: COORD_Y - coordenada Y de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o sleep
	 */
	public void moverCima(double COORD_Y) {
		while (posicaoYinicial != COORD_Y) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // fim do try
			Platform.runLater(() -> {
				nave.setRotate(0);
				nave.setLayoutY(posicaoYinicial);
			}); // fim do Platform
			posicaoYinicial--;
		} // fim do while
	} // fim do método moverCima

	/*
	 * Método: moverBaixo(double COORD_Y)
	 * Funcao: Move a nave para baixo até a coordenada Y especificada
	 * Parametros: COORD_Y - coordenada Y de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o sleep
	 */
	public void moverBaixo(double COORD_Y) {
		while (posicaoYinicial != COORD_Y) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // fim do try
			Platform.runLater(() -> {
				nave.setRotate(180);
				nave.setLayoutY(posicaoYinicial);
			}); // fim do Platform
			posicaoYinicial++;
		} // fim do while
	} // fim do método moverBaixo

	/*
	 * Método: pauseIfNeeded()
	 * Funcao: Pausa a nave se necessário
	 * Parametros: void
	 * Retorno: void
	 */
	private void pauseIfNeeded() {
		lock.lock();
		try {
			while (pause) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} // fim do catch
			} // fim do while
		} finally {
			lock.unlock();
		} // fim do finally
	} // fim do método pauseIfNeeded
} // fim da classe Luke
