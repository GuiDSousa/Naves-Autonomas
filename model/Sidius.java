package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.MainController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

// Sidius percorre o percurso 21 no sentido horário
public class Sidius extends Thread {
	private int velocidade = 5;
	private int posicaoXinicial = 520;
	private int posicaoYinicial = 505;
	private ImageView nave;
	private boolean start = true;
	private Slider slider;
  	private boolean pause = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	MainController controle = new MainController();

	public Sidius(MainController controle, ImageView nave, Slider slider) {
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
			  nave.setLayoutX(520);
			  nave.setLayoutY(505);
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
			nave.setLayoutX(585);
			nave.setLayoutY(450);
			nave.setRotate(0);
		});
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!pause) {
					moverNaveSidius();
				}
			}
		} catch (InterruptedException e) {
			// Thread was interrupted while waiting or sleeping
			// Log the exception and return from the method
			e.printStackTrace();
			return;
		}
	}

	public void moverNaveSidius () throws InterruptedException {


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
