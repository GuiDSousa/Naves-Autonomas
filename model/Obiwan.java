package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Obiwan percorre o percurso 19 no sentido horário
public class Obiwan extends Thread{
  private int velocidade = 5;
	private int posicaoXinicial = 270;
	private int posicaoYinicial = 505;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;
	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	MainController controle = new MainController();

	public Obiwan(MainController controle, ImageView nave, Slider slider) {
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
		  nave.setLayoutX(270);
		  nave.setLayoutY(505);
		  nave.setRotate(0);
		});
	  }

	// Método para parar a nave
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
			nave.setLayoutX(334);
			nave.setLayoutY(451);
			nave.setRotate(0);
		});
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					moverNaveObiwan();
				}
			}
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			e.printStackTrace();
			return;
		}
	}

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
		moverEsquerda(393);
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
	


		controle.semaforoYOKA_46.acquire();
		controle.semaforoNEJI_45.acquire();
		moverBaixo(460);
		moverBaixo(560);
		controle.semaforoYOKA_46.release();
		controle.semaforoNEJI_45.release();
	
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
