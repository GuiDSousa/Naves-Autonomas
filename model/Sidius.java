package model;
/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Sidius
 * Funcao...........: Aplicação modelo que inicia a nave do Sidius
 *************************************************************** */

import java.util.concurrent.locks.Condition; // Importa a classe Condition
import java.util.concurrent.locks.Lock; // Importa a classe Lock
import java.util.concurrent.locks.ReentrantLock; // Importa a classe ReentrantLock
import controller.MainController; // Importa a classe MainController
import javafx.application.Platform; // Importa a classe Platform
import javafx.scene.image.ImageView; // Importa a classe ImageView

// Sidius percorre o percurso 21 no sentido horário
// Classe Sidius herda de Thread
public class Sidius extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 520;
	private int posicaoYinicial = 505;
	private ImageView nave;
	private boolean start = true;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	MainController controle = new MainController();

	// Construtor
	public Sidius(MainController controle, ImageView nave) {
		this.nave = nave;
		this.controle = controle;
	} // Fim do construtor

	/*
	 * Método: retornarPosicaoInicial
	 * Funcao: Retorna a posição inicial da nave
	 * Retorno: void
	 * Parametros: void
	 */
	public void retornarPosicaoInicial() {
		Platform.runLater(() -> {
			nave.setLayoutX(520);
			nave.setLayoutY(505);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarPosicaoInicial

	/*
	 * Método: parar
	 * Funcao: Para a nave
	 * Retorno: void
	 * Parametros: void
	 */
	public void parar() {
		lock.lock();
		try {
			this.pause = true;
		} finally {
			lock.unlock();
		} // Fim do try
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
		} // Fim do try
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
	 * Método: retornarBaseDecolagem()
	 * Funcao: Retorna a base de decolagem
	 * Parametros: void
	 * Retorno: void
	 */

	public void retornarBaseDecolagem() {
		Platform.runLater(() -> {
			nave.setLayoutX(585);
			nave.setLayoutY(450);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarBaseDecolagem

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
	 * Parametros: int velocidade
	 * Retorno: void
	 */

	public void setVelocidade(int velocidade) {
		if (velocidade > 0) {
			this.velocidade = velocidade;
		} // Fim do if
	} // Fim do método setVelocidade

		/*
	 * Método: run()
	 * Funcao: Inicia a thread
	 * Parametros: void
	 * Retorno: void
	 */
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					Thread.sleep(1000);
					moverNaveSidius();
				} // Fim do if
			} // Fim do while
		} catch (InterruptedException e) {
			return;
		} // Fim do try
	} // Fim do método run

		/*
	 * Método: moverNaveSidius()
	 * Funcao: Move a nave do Sidius
	 * Parametros: void
	 * Retorno: void
	 */

	public void moverNaveSidius() throws InterruptedException {

		retornarPosicaoInicial();
		controle.semaforoPAIN_50.acquire();
		moverBaixo(625);
		controle.semaforoPAIN_50.release();

		controle.semaforoASH_3.acquire();
		controle.semaforoANA_2.acquire();
		controle.semaforoOAK_1.acquire();
		moverEsquerda(395);
		moverEsquerda(270);
		moverEsquerda(145);
		controle.semaforoASH_3.release();
		controle.semaforoANA_2.release();
		controle.semaforoOAK_1.release();

		controle.semaforoAIMI_35.acquire();
		controle.semaforoFUJI_36.acquire();
		moverCima(505);
		moverCima(375);
		controle.semaforoAIMI_35.release();
		controle.semaforoFUJI_36.release();

		controle.semaforoZEN_10.acquire();
		moverEsquerda(20);
		controle.semaforoZEN_10.release();

		controle.semaforoVINE_32.acquire();
		moverCima(245);
		controle.semaforoVINE_32.release();

		controle.semaforoPIP_15.acquire();
		moverDireita(145);
		controle.semaforoPIP_15.release();

		controle.semaforoIRIS_38.acquire();
		controle.semaforoNOVA_39.acquire();
		moverCima(125);
		moverCima(0);
		controle.semaforoIRIS_38.release();
		controle.semaforoNOVA_39.release();

		controle.semaforoBUD_26.acquire();
		controle.semaforoAXE_27.acquire();
		controle.semaforoZOE_28.acquire();
		moverDireita(270);
		moverDireita(395);
		moverDireita(520);
		controle.semaforoBUD_26.release();
		controle.semaforoAXE_27.release();
		controle.semaforoZOE_28.release();

		controle.semaforoZEST_54.acquire();
		controle.semaforoECHO_53.acquire();
		moverBaixo(125);
		moverBaixo(245);
		controle.semaforoZEST_54.release();
		controle.semaforoECHO_53.release();

		controle.semaforoHEX_19.acquire();
		moverDireita(645);
		controle.semaforoHEX_19.release();

		controle.semaforoPAVE_57.acquire();
		moverBaixo(375);
		controle.semaforoPAVE_57.release();

		controle.semaforoORB_14.acquire();
		moverEsquerda(520);
		controle.semaforoORB_14.release();

		controle.semaforoHANA_51.acquire();
		moverBaixo(505);
		controle.semaforoHANA_51.release();

		System.out.println("Sidius finalizou o percurso e está abastecendo");
	} // Fim do método moverNaveSidius

		/*
	 * Método: moverEsquerda()
	 * Funcao: Move a nave para a esquerda
	 * Parametros: double COORD_X
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
			}
			Platform.runLater(() -> {
				nave.setRotate(270);
				nave.setLayoutX(posicaoXinicial);
			}); // Fim do Platform
			posicaoXinicial--;
		} // Fim do while
	} // Fim do método moverEsquerda

		/*
	 * Método: moverDireita()
	 * Funcao: Move a nave para a direita
	 * Parametros: double COORD_X
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
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(90);
				nave.setLayoutX(posicaoXinicial);
			}); // Fim do Platform
			posicaoXinicial++;
		} // Fim do while
	} // Fim do método moverDireita

		/*
	 * Método: moverCima()
	 * Funcao: Move a nave para cima
	 * Parametros: double COORD_Y
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
			} // Fim do try
			Platform.runLater(() -> {
				nave.setRotate(0);
				nave.setLayoutY(posicaoYinicial);
			}); // Fim do Platform
			posicaoYinicial--;
		} // Fim do while
	} // Fim do método moverCima

		/*
	 * Método: moverBaixo()
	 * Funcao: Move a nave para baixo
	 * Parametros: double COORD_Y
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
				} // Fim do try
			} // Fim do while
		} finally {
			lock.unlock();
		} // Fim do try
	} // Fim do método pauseIfNeeded
} // Fim da classe Sidius
