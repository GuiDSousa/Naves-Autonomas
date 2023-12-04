package model;


/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Kylo
 * Funcao...........: Aplicação modelo que inicia a nave Kylo
 *************************************************************** */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import controller.mainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

// Kylo percorre o percurso 18 no sentido horário
// Classe Kylo que herda de Thread
public class Kylo extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 645;
	private int posicaoYinicial = 245;
	private ImageView nave;
	private boolean start = true;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	mainController controle = new mainController();

	// Construtor da classe Kylo
	public Kylo(mainController controle, ImageView nave) {
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
			nave.setLayoutX(645);
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
			nave.setLayoutX(584);
			nave.setLayoutY(199);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarBaseDecolagem

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					Thread.sleep(1000);
					moverNaveKylo();
				} // Fim do if
			} // Fim do while
		} catch (InterruptedException e) {
			return;
		} // Fim do catch
	} // Fim do método run

	/*
	 * Método: moverNaveKylo()
	 * Funcao: Move a nave de acordo com o percurso de Kylo
	 * Parametros: void
	 * Retorno: void
	 */
	public void moverNaveKylo() throws InterruptedException {
		retornarPosicaoInicial();
		controle.semaforoPAVE_57.acquire();
		controle.semaforoHIRO_56.acquire();
		controle.semaforoBUGG_55.acquire();
		moverBaixo(375);
		moverBaixo(505);
		moverBaixo(625);
		controle.semaforoPAVE_57.release();
		controle.semaforoHIRO_56.release();
		controle.semaforoBUGG_55.release();

		controle.semaforoBEE_4.acquire();
		controle.semaforoASH_3.acquire();
		controle.semaforoANA_2.acquire();
		moverEsquerda(520);
		moverEsquerda(395);
		moverEsquerda(270);
		controle.semaforoBEE_4.release();
		controle.semaforoASH_3.release();
		controle.semaforoANA_2.release();

		controle.semaforoCHOU_40.acquire();
		controle.semaforoEREN_41.acquire();
		controle.semaforoGAZE_42.acquire();
		moverCima(505);
		moverCima(375);
		controle.semaforoJOY_12.acquire();
		moverCima(245);
		controle.semaforoJOY_12.release();
		controle.semaforoCHOU_40.release();
		controle.semaforoEREN_41.release();
		controle.semaforoGAZE_42.release();

		controle.semaforoINK_17.acquire();
		controle.semaforoPOD_18.acquire();
		controle.semaforoHEX_19.acquire();
		controle.semaforoLUNA_48.acquire();
		controle.semaforoHILL_47.acquire();
		moverDireita(395);
		controle.semaforoLUNA_48.release();
		controle.semaforoHILL_47.release();
		moverDireita(520);
		moverDireita(645);
		controle.semaforoINK_17.release();
		controle.semaforoPOD_18.release();
		controle.semaforoHEX_19.release();

		System.out.println("Kylo finalizou o percurso e está abastecendo");
	} // Fim do método moverNaveKylo

	/*
	 * Método: moverEsquerda(double COORD_X)
	 * Funcao: Move a nave para a esquerda até a coordenada X especificada
	 * Parametros: COORD_X - coordenada X de destino
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
			} // Fim do catch
			Platform.runLater(() -> {
				nave.setRotate(270);
				nave.setLayoutX(posicaoXinicial);
			}); // Fim do Platform
			posicaoXinicial--;
		} // Fim do while
	} // Fim do método moverEsquerda
	/*
	 * Método: moverDireita(double COORD_X)
	 * Funcao: Move a nave para a direita até a coordenada X especificada
	 * Parametros: COORD_X - coordenada X de destino
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
			}); // Fim do Platform
			posicaoYinicial--;
		} // Fim do while
	} // Fim do método moverCima
	/*
	 * Método: moverBaixo(double COORD_Y)
	 * Funcao: Move a nave para baixo até a coordenada Y especificada
	 * Parametros: COORD_Y - coordenada Y de destino
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
} // Fim da classe Kylo
