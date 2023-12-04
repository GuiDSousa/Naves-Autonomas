package model;

/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Obiwan
 * Funcao...........: Aplicação modelo que inicia a nave Yoda
 *************************************************************** */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import controller.mainController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

// Obiwan percorre o percurso 19 no sentido horário
// Classe Obiwan que herda de Thread
public class Obiwan extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 270;
	private int posicaoYinicial = 505;
	private ImageView nave;
	private boolean start = true;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	mainController controle = new mainController();

	public Obiwan(mainController controle, ImageView nave) {
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
			nave.setLayoutX(270);
			nave.setLayoutY(505);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarPosicaoInicial

	/*
	 * Método: parar()
	 * Funcao: Para a nave
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
	 * Funcao: Retoma a nave
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
			nave.setLayoutX(334);
			nave.setLayoutY(451);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarBaseDecolagem

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					Thread.sleep(1000);
					moverNaveObiwan();
				} // Fim do if
			} // Fim do while
		} catch (InterruptedException e) {
			return;
		} // Fim do catch
	} // Fim do método run

	/*
	 * Método: moverNaveObiwan()
	 * Funcao: Move a nave Obiwan pelo percurso
	 * Parametros: void
	 * Retorno: void
	 */
	public void moverNaveObiwan() throws InterruptedException {
		retornarPosicaoInicial();
		controle.semaforoEREN_41.acquire();
		moverCima(375);
		controle.semaforoEREN_41.release();

		controle.semaforoJET_11.acquire();
		controle.semaforoZEN_10.acquire();
		moverEsquerda(145);
		moverEsquerda(20);
		controle.semaforoJET_11.release();
		controle.semaforoZEN_10.release();

		controle.semaforoVINE_32.acquire();
		moverCima(245);
		controle.semaforoVINE_32.release();

		controle.semaforoPIP_15.acquire();
		controle.semaforoPET_16.acquire();
		moverDireita(145);
		moverDireita(270);
		controle.semaforoPIP_15.release();
		controle.semaforoPET_16.release();

		controle.semaforoLUSH_43.acquire();
		controle.semaforoDUSK_44.acquire();
		moverCima(125);
		moverCima(0);
		controle.semaforoLUSH_43.release();
		controle.semaforoDUSK_44.release();

		controle.semaforoAXE_27.acquire();
		moverDireita(395);
		controle.semaforoAXE_27.release();

		controle.semaforoVIBE_49.acquire();
		controle.semaforoLUNA_48.acquire();
		moverBaixo(125);
		moverBaixo(245);
		controle.semaforoVIBE_49.release();
		controle.semaforoLUNA_48.release();

		controle.semaforoPOD_18.acquire();
		controle.semaforoHEX_19.acquire();
		moverDireita(520);
		moverDireita(645);
		controle.semaforoPOD_18.release();
		controle.semaforoHEX_19.release();

		controle.semaforoPAVE_57.acquire();
		moverBaixo(375);
		controle.semaforoPAVE_57.release();

		controle.semaforoORB_14.acquire();
		controle.semaforoLUX_13.acquire();
		moverEsquerda(520);
		controle.semaforoJOY_12.acquire();
		moverEsquerda(393);
		controle.semaforoJOY_12.release();
		controle.semaforoORB_14.release();
		controle.semaforoLUX_13.release();

		controle.semaforoYOKA_46.acquire();
		controle.semaforoNEJI_45.acquire();
		moverBaixo(505);
		moverBaixo(625);
		controle.semaforoYOKA_46.release();
		controle.semaforoNEJI_45.release();

		controle.semaforoANA_2.acquire();
		moverEsquerda(270);
		controle.semaforoANA_2.release();

		controle.semaforoCHOU_40.acquire();
		moverCima(505);
		controle.semaforoCHOU_40.release();

		System.out.println("Obiwan finalizou o percurso e está abastecendo");
	} // Fim do método moverNaveObiwan

	/*
	 * Método: moverEsquerda(double COORD_X)
	 * Funcao: Move a nave para a esquerda
	 * Parametros: COORD_X - coordenada X final
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
	 * Funcao: Move a nave para a direita
	 * Parametros: COORD_X - coordenada X final
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
	 * Funcao: Move a nave para cima
	 * Parametros: COORD_Y - coordenada Y final
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
	 * Funcao: Move a nave para baixo
	 * Parametros: COORD_Y - coordenada Y final
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
} // Fim da classe Obiwan
