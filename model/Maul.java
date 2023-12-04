package model;

/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Maul
 * Funcao...........: Aplicação modelo que inicia a nave Maul
 *************************************************************** */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import controller.mainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

// Maul percorre o percurso 12 no sentido anti-horário
// Classe Maul que herda de Thread
public class Maul extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 145;
	private int posicaoYinicial = 625;
	private ImageView nave;
	private boolean start = true;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	mainController controle = new mainController();

	// Construtor da classe Maul
	public Maul(mainController controle, ImageView nave) {
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
			nave.setLayoutX(145);
			nave.setLayoutY(625);
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
		} // Fim do finally
	} // Fim do método parar

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
		} // Fim do finally
	} // Fim do método retomar

	/*
	 * Método: isPaused()
	 * Funcao: Verifica se a nave está pausada
	 * Parametros: void
	 * Retorno: boolean - true se a nave está pausada, false caso contrário
	 */
	public boolean isPaused() {
		return pause;
	} // Fim do método isPaused

	/*
	 * Método: isStart()
	 * Funcao: Verifica se a nave está iniciada
	 * Parametros: void
	 * Retorno: boolean - true se a nave está iniciada, false caso contrário
	 */
	public boolean isStart() {
		return start;
	} // Fim do método isStart

	/*
	 * Método: getVelocidade()
	 * Funcao: Retorna a velocidade da nave
	 * Parametros: void
	 * Retorno: int - velocidade da nave
	 */
	public int getVelocidade() {
		return velocidade;
	} // Fim do método getVelocidade

	/*
	 * Método: setVelocidade(int velocidade)
	 * Funcao: Define a velocidade da nave
	 * Parametros: velocidade - nova velocidade da nave
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
			nave.setLayoutX(80);
			nave.setLayoutY(578);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarBaseDecolagem

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					Thread.sleep(1000);
					moverNaveMaul();
				} // Fim do if
			} // Fim do while
		} catch (InterruptedException e) {
			return;
		} // Fim do catch
	} // Fim do método run

	/*
	 * Método: moverNaveMaul()
	 * Funcao: Move a nave Maul pelo percurso
	 * Parametros: void
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o
	 * sleep
	 */
	public void moverNaveMaul() throws InterruptedException {

		retornarPosicaoInicial();
		controle.semaforoOAK_1.acquire();
		controle.semaforoANA_2.acquire();
		moverDireita(270);
		moverDireita(395);
		controle.semaforoOAK_1.release();
		controle.semaforoANA_2.release();

		controle.semaforoNEJI_45.acquire();
		controle.semaforoYOKA_46.acquire();
		controle.semaforoHILL_47.acquire();
		controle.semaforoLUNA_48.acquire();
		controle.semaforoVIBE_49.acquire();
		moverCima(505);
		moverCima(375);
		controle.semaforoJOY_12.acquire();
		moverCima(245);
		controle.semaforoJOY_12.release();
		moverCima(125);
		moverCima(0);

		controle.semaforoNEJI_45.release();
		controle.semaforoYOKA_46.release();
		controle.semaforoHILL_47.release();
		controle.semaforoLUNA_48.release();
		controle.semaforoVIBE_49.release();

		controle.semaforoAXE_27.acquire();
		controle.semaforoBUD_26.acquire();
		moverEsquerda(270);
		moverEsquerda(145);
		controle.semaforoAXE_27.release();
		controle.semaforoBUD_26.release();

		controle.semaforoNOVA_39.acquire();
		controle.semaforoIRIS_38.acquire();
		controle.semaforoAQUA_37.acquire();
		controle.semaforoFUJI_36.acquire();
		controle.semaforoAIMI_35.acquire();
		moverBaixo(125);
		moverBaixo(245);
		controle.semaforoPIP_15.acquire();
		moverBaixo(375);
		controle.semaforoPIP_15.release();
		controle.semaforoZEN_10.acquire();
		controle.semaforoJET_11.acquire();
		moverBaixo(505);
		controle.semaforoZEN_10.release();
		controle.semaforoJET_11.release();
		moverBaixo(625);
		controle.semaforoNOVA_39.release();
		controle.semaforoIRIS_38.release();
		controle.semaforoAQUA_37.release();
		controle.semaforoFUJI_36.release();
		controle.semaforoAIMI_35.release();

		System.out.println("Maul finalizou o percurso e está abastecendo");
	} // Fim do método moverNaveMaul

	/*
	 * Método: moverEsquerda(double COORD_X)
	 * Funcao: Move a nave para a esquerda até a coordenada X especificada
	 * Parametros: COORD_X - coordenada X de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o
	 * sleep
	 */
	public void moverEsquerda(double COORD_X) {
		while (posicaoXinicial != COORD_X) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do catch
			Platform.runLater(() -> {
				nave.setRotate(270);
				nave.setLayoutX(posicaoXinicial);
			});
			posicaoXinicial--;
		} // Fim do while
	} // Fim do método moverEsquerda

	/*
	 * Método: moverDireita(double COORD_X)
	 * Funcao: Move a nave para a direita até a coordenada X especificada
	 * Parametros: COORD_X - coordenada X de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o
	 * sleep
	 */
	public void moverDireita(double COORD_X) {
		while (posicaoXinicial != COORD_X) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do catch
			Platform.runLater(() -> {
				nave.setRotate(90);
				nave.setLayoutX(posicaoXinicial);
			}); // Fim do Platform
			posicaoXinicial++;
		} // Fim do while
	} // Fim do método moverDireita

	/*
	 * Método: moverCima(double COORD_Y)
	 * Funcao: Move a nave para cima até a coordenada Y especificada
	 * Parametros: COORD_Y - coordenada Y de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o
	 * sleep
	 */
	public void moverCima(double COORD_Y) {
		while (posicaoYinicial != COORD_Y) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do catch
			Platform.runLater(() -> {
				nave.setRotate(0);
				nave.setLayoutY(posicaoYinicial);
			});
			posicaoYinicial--;
		} // Fim do while
	} // Fim do método moverCima

	/*
	 * Método: moverBaixo(double COORD_Y)
	 * Funcao: Move a nave para baixo até a coordenada Y especificada
	 * Parametros: COORD_Y - coordenada Y de destino
	 * Retorno: void
	 * Exceção: InterruptedException - caso a thread seja interrompida durante o
	 * sleep
	 */
	public void moverBaixo(double COORD_Y) {
		while (posicaoYinicial != COORD_Y) {
			pauseIfNeeded();
			try {
				sleep(500 / (velocidade * 5));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Set the interrupt status/flag
				return; // Stop the method
			} // Fim do catch
			Platform.runLater(() -> {
				nave.setRotate(180);
				nave.setLayoutY(posicaoYinicial);
			}); // Fim do Platform
			posicaoYinicial++;
		} // Fim do while
	} // Fim do método moverBaixo

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
				} // Fim do catch
			} // Fim do while
		} finally {
			lock.unlock();
		} // Fim do finally
	} // Fim do método pauseIfNeeded
} // Fim da classe Maul
