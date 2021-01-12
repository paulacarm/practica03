package buzonDistribuido;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Paula
 * La clase HiloServidor atiende peticiones de los clientes.
 * Le pregunta si quiere consultar o enviar mensajes y le pide los datos necesarios para almacenar el mensaje o le manda todos los mensajes para que encuentre los suyos.
 *
 */

public class HiloServidor extends Thread {
	private Socket socket;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada ;
	private ArrayList <Mensaje> ms;
	private String destinatario,mensaje;
	Scanner s;
	
	public HiloServidor(Socket socket) throws IOException {
		this.socket=socket;
		s=new Scanner(System.in);
		salida=new ObjectOutputStream(socket.getOutputStream());
		entrada  =new ObjectInputStream(socket.getInputStream());
		ms=new ArrayList<Mensaje>();
		
	}
	public void run() {
		try {
			
			while(true) {
				//MANDA UN MENSAJE DE BIENVENIDA AL CLIENTE
				Mensaje m=new Mensaje("Bienvenid@,  �Quieres consultar,enviar un mensaje o salir?");
				salida.writeObject(m);
				
				//RECIBO RESPUESTA DE LO QUE QUIERE HACER EL CLIENTE
				Mensaje recibido=(Mensaje) entrada.readObject();
				System.out.print("El cliente quiere:");
				recibido.muestraMensaje();
				
				//SI LO QUE QUIERE ES ENVIAR UN MENSAJE A OTRO CLIENTE
				if(recibido.mensaje.contains("enviar")) {
					pedirDestinatario();
					System.out.println("el destinatario es: "+this.destinatario);
					PedirMensaje();
					System.out.println("el mensaje es: "+this.mensaje);
					enviarElMensaje();
					
				}
				if(recibido.mensaje.contains("consultar")) {
					ConsultarMensajes();
				}
				if(recibido.mensaje.contains("salir")) {
				//SI EL CLIENTE QUIERE SALIR CIERRO LOS SOCKETS Y EL CANAL DE COMUNICACI�N Y ME SALGO DEL BUCLE.
					entrada.close();
					salida.close();
					socket.close();
					break;
				}
							
			}	
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			
			}
		
	}
	
	public void pedirDestinatario() throws IOException, ClassNotFoundException {
		//PREGUNTA AL CLIENTE EL DESTINATARIO DEL MENSAJE Y GUARDA SU RESPUESTA
		Mensaje m2=new Mensaje("A quien quieres enviarle un mensaje?");
		salida.writeObject(m2);
		Mensaje destinatario=(Mensaje) entrada.readObject();
		this.destinatario=destinatario.mensaje;	
	}
	
	public void PedirMensaje() throws IOException, ClassNotFoundException {
		//PREGUNTA AL CLIENTE EL MENSAJE QUE QUIERE ENVIAR Y GUARDA SU RESPUESTA
		Mensaje m3=new Mensaje("De acuerdo. Escriba su mensaje");
		salida.writeObject(m3);
		Mensaje mensaje=(Mensaje) entrada.readObject();
		this.mensaje=mensaje.mensaje;		
	}
	
	public synchronized void enviarElMensaje() throws IOException {
		
		//MANDO CONFIRMACION AL CLIENTE DE QUE SE ENVIAR� EL MENSAJE
		Mensaje m3=new Mensaje("Mensaje enviado");
		salida.writeObject(m3);
		//CREO EL OBJETO MENSAJE Y LO A�ADO AL ARRAY
		Mensaje mensaj=new Mensaje(this.destinatario,this.mensaje);
		ms.add(mensaj);
		//GUARDO EL MENSAJE CON EL DESTINATARIO EN EL HASHMAP Y EL ARRAY DE ESE MENSAJES DE ESE DESTINATARIO
		Mensajes.mensajes.put(this.destinatario,ms);
		
	}
	
	public void ConsultarMensajes() throws IOException {
		//MANDO EL HASHMAP AL CLIENTE 
		salida.writeObject(Mensajes.mensajes);
	}
}
