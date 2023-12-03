package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Kylo percorre o percurso 18 no sentido horário
public class Kylo extends Thread {

	private int velocidade = 20;
	private int posicaoXinicial = 645;
	private int posicaoYinicial = 245;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	MainController controle = new MainController();

	public Kylo(MainController controle, ImageView nave, Slider slider) {
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
			nave.setLayoutX(645);
			nave.setLayoutY(245);
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
			nave.setLayoutX(584);
			nave.setLayoutY(199);
			nave.setRotate(0);
		});
	}


	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					moverNaveKylo();
				}
			}
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			e.printStackTrace();
			return;
		}
	}

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
		moverEsquerda(145);
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
		moverDireita(395);
		moverDireita(520);
		moverDireita(645);
		controle.semaforoINK_17.release();
		controle.semaforoPOD_18.release();
		controle.semaforoHEX_19.release();

		
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
