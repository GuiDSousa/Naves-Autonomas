package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Maul percorre o percurso 12 no sentido anti-horário
public class Maul extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 145;
	private int posicaoYinicial = 625;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	MainController controle = new MainController();

	public Maul(MainController controle, ImageView nave, Slider slider) {
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
			nave.setLayoutX(145);
			nave.setLayoutY(625);
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
			nave.setLayoutY(578);
			nave.setRotate(0);
		});
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					moverNaveMaul();
				}
			}
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			e.printStackTrace();
			return;
		}
	}

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
		moverCima(245);
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
		moverBaixo(375);
		moverBaixo(505);
		moverBaixo(625);
		controle.semaforoNOVA_39.release();
		controle.semaforoIRIS_38.release();
		controle.semaforoAQUA_37.release();
		controle.semaforoFUJI_36.release();
		controle.semaforoAIMI_35.release();
		
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
