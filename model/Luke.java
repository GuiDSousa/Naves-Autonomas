package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Luke percorre o percurso 08 no sentido horário
public class Luke extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 20;
	private int posicaoYinicial = 125;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	MainController controle = new MainController();

	public Luke(MainController controle, ImageView nave, Slider slider) {
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

	public void retornarPosicaoInicial() {
		Platform.runLater(() -> {
			nave.setLayoutX(20);
			nave.setLayoutY(125);
			nave.setRotate(0);
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
			nave.setLayoutX(80);
			nave.setLayoutY(76);
			nave.setRotate(0);
		});
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					moverNaveLuke();
				}
			}
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			e.printStackTrace();
			return;
		}
	}

	

	public void moverNaveLuke() throws InterruptedException {

		retornarPosicaoInicial();
		controle.semaforoJAX_20.acquire();
		controle.semaforoKAY_21.acquire();
		controle.semaforoACE_22.acquire();
		controle.semaforoFIN_23.acquire();
		controle.semaforoDOC_24.acquire();
		moverDireita(145);
		moverDireita(270);
		moverDireita(395);
		moverDireita(520);
		moverDireita(645);
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
