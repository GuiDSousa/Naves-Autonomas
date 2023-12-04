package model;

/* ***************************************************************
 * Autor............: Guilherme Dias Sousa
 * Matricula........: 202211033
 * Inicio...........: 24/11/2023    
 * Ultima alteracao.: 04/12/2023
 * Nome.............: Yoda
 * Funcao...........: Aplicação modelo que inicia a nave Yoda
 *************************************************************** */

import java.util.concurrent.locks.Condition; // Importa a classe Condition
import java.util.concurrent.locks.Lock; // Importa a classe Lock
import java.util.concurrent.locks.ReentrantLock; // Importa a classe ReentrantLock
import controller.MainController; // Importa a classe MainController
import javafx.application.Platform; // Importa a classe Platform
import javafx.scene.image.ImageView; // Importa a classe ImageView

// Yoda percorre o percurso 04 no sentido anti-horário
// Classe Yoda que extende de Thread
public class Yoda extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 270;
	private int posicaoYinicial = 375;
	private ImageView nave;
	private boolean start = true;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock(); // Instancia um objeto da classe ReentrantLock
	private final Condition condition = lock.newCondition(); // Instancia um objeto da classe Condition
	MainController controle = new MainController();

	// Construtor
	public Yoda(MainController controle, ImageView nave) {
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
			lock.unlock(); // Libera o lock
		} // Fim do try
	} // Fim do método parar()

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
			condition.signal(); // Sinaliza o lock
		} finally {
			lock.unlock(); // Libera o lock
		} // Fim do try
	} // Fim do método retomar()

	/*
	 * Método: isPaused()
	 * Funcao: Retorna o estado da nave
	 * Parametros: void
	 * Retorno: boolean
	 */
	public boolean isPaused() {
		return pause;
	} // Fim do método isPaused()

	/*
	 * Método: isStart()
	 * Funcao: Retorna o estado da nave
	 * Parametros: void
	 * Retorno: boolean
	 */
	public boolean isStart() {
		return start;
	} // Fim do método isStart()

	/*
	 * Método: getVelocidade()
	 * Funcao: Retorna a velocidade da nave
	 * Parametros: void
	 * Retorno: int
	 */
	public int getVelocidade() {
		return velocidade;
	} // Fim do método getVelocidade()

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
	} // Fim do método setVelocidade()

	/*
	 * Método: retornarBaseDecolagem()
	 * Funcao: Retorna a nave para a base de decolagem
	 * Parametros: void
	 * Retorno: void
	 */
	public void retornarBaseDecolagem() {
		Platform.runLater(() -> {
			nave.setLayoutX(205);
			nave.setLayoutY(326);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarBaseDecolagem()

	/*
	 * Método: run()
	 * Funcao: Inicia a thread
	 * Parametros: void
	 * Retorno: void
	 */
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) { // Enquanto a thread não for interrompida
				if (!pause) {
					Thread.sleep(1000);
					moverNaveYoda();
				} // Fim do if
			} // Fim do while
		} catch (InterruptedException e) {
			return;
		} // Fim do try
	} // Fim do método run()

	/*
	 * Método: moverNaveYoda()
	 * Funcao: Move a nave Yoda através do percurso determinado e adquire e libera
	 * os semáforos para evitar colisões
	 * Parametros: void
	 * Retorno: void
	 */
	public void moverNaveYoda() throws InterruptedException {

		retornarPosicaoInicial(); // Retorna a nave para a posição inicial
		controle.semaforoGAZE_42.acquire();
		controle.semaforoLUSH_43.acquire();
		controle.semaforoDUSK_44.acquire();
		moverCima(245);
		moverCima(125);
		moverCima(0);
		controle.semaforoGAZE_42.release();
		controle.semaforoLUSH_43.release();
		controle.semaforoDUSK_44.release();

		controle.semaforoBUD_26.acquire();
		controle.semaforoWIN_25.acquire();
		moverEsquerda(145);
		moverEsquerda(20);
		controle.semaforoBUD_26.release();
		controle.semaforoWIN_25.release();

		controle.semaforoHALO_34.acquire();
		controle.semaforoROSE_33.acquire();
		controle.semaforoVINE_32.acquire();
		controle.semaforoZORO_31.acquire();
		controle.semaforoAIKO_30.acquire();
		moverBaixo(125);
		moverBaixo(245);
		moverBaixo(365);
		moverBaixo(495);
		moverBaixo(625);
		controle.semaforoHALO_34.release();
		controle.semaforoROSE_33.release();
		controle.semaforoVINE_32.release();
		controle.semaforoZORO_31.release();
		controle.semaforoAIKO_30.release();

		controle.semaforoELM_0.acquire();
		controle.semaforoOAK_1.acquire();
		controle.semaforoANA_2.acquire();
		controle.semaforoASH_3.acquire();
		controle.semaforoBEE_4.acquire();
		moverDireita(145);
		moverDireita(270);
		moverDireita(395);
		moverDireita(520);
		moverDireita(645);
		controle.semaforoELM_0.release();
		controle.semaforoOAK_1.release();
		controle.semaforoANA_2.release();
		controle.semaforoASH_3.release();
		controle.semaforoBEE_4.release();

		controle.semaforoBUGG_55.acquire();
		controle.semaforoHIRO_56.acquire();
		moverCima(505);
		moverCima(375);
		controle.semaforoBUGG_55.release();
		controle.semaforoHIRO_56.release();

		controle.semaforoORB_14.acquire();
		controle.semaforoLUX_13.acquire();
		controle.semaforoJOY_12.acquire();
		moverEsquerda(520);
		moverEsquerda(395);
		moverEsquerda(270);
		controle.semaforoORB_14.release();
		controle.semaforoLUX_13.release();
		controle.semaforoJOY_12.release();
		System.out.println("Yoda finalizou o percurso e está abastecendo");
	} // Fim do método moverNaveYoda()

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
			nave.setLayoutX(270);
			nave.setLayoutY(375);
			nave.setRotate(0);
		}); // Fim do Platform
	} // Fim do método retornarPosicaoInicial()

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
} // Fim da classe Yoda