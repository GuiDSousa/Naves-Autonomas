package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Vader percorre o percurso 1 no sentido horário
public class Vader extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 395;
	private int posicaoYinicial = 245;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	MainController controle = new MainController();

	public Vader(MainController controle, ImageView nave, Slider slider) {
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
			nave.setLayoutX(395);
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

	public boolean isStart() {
		return start;
	}

	public boolean isPaused() {
		return pause;
	}

	public void retornarBaseDecolagem() {
		Platform.runLater(() -> {
			nave.setLayoutX(334);
			nave.setLayoutY(201);
			nave.setRotate(0);
		});
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					moverNaveVader();
				}
			}
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			e.printStackTrace();
			return;
		}
	}

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
