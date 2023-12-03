package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Yoda percorre o percurso 04 no sentido anti-horário
public class Yoda extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 270;
	private int posicaoYinicial = 375;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	MainController controle = new MainController();

	public Yoda(MainController controle, ImageView nave, Slider slider) {
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
			nave.setLayoutX(205);
			nave.setLayoutY(326);
			nave.setRotate(0);
		});
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					moverNaveYoda();
				}
			}
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			e.printStackTrace();
			return;
		}
	}

	public void moverNaveYoda() throws InterruptedException {

		retornarPosicaoInicial();
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
	public void retornarPosicaoInicial() {
		Platform.runLater(() -> {
		  nave.setLayoutX(270);
		  nave.setLayoutY(375);
		  nave.setRotate(0);
		});
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