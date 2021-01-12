package buzonDistribuido;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Paula
 *
 */

public class Servidor {

	public static void main(String[] args) throws IOException {
		int numeroPuerto = 5555;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		
	
		System.out.println("Esperando al cliente.....");
		while(true) {
			//CREA EL SOCKET CLIENTE Y LE PASA LA PETICION DEL CLIENTE A UN HILO SERVIDOR PARA QUE LA ATIENDA
			Socket cliente = new Socket();
			cliente = servidor.accept();
			HiloServidor hilo = new HiloServidor(cliente);
			hilo.start();
		}
	}

	}


