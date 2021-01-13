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
 * Le pregunta si quiere consultar o enviar mensajes y le pide los datos necesarios para almacenar el mensaje o le manda un array con los mensajes que son para el cliente que los solicite
 *
 */
public class HiloServidor extends Thread {
	//socket del cliente
	private Socket socket;
	//canal de salida de datos
	private ObjectOutputStream salida;
	//canal entrada de datos
	private ObjectInputStream entrada ;
	//array donde se guardarán los mensajes exclusivos de un cliente
	private ArrayList <Mensaje> ms;
	//Datos del mensaje
	private String destinatario,mensaje;
	//nombre del cliente para identificarlo y pasarle sus mensajes
	private Mensaje nombreCliente;
	

	
	public HiloServidor(Socket socket) throws IOException {
		this.socket=socket;
		salida=new ObjectOutputStream(socket.getOutputStream());
		entrada  =new ObjectInputStream(socket.getInputStream());
		ms=new ArrayList<Mensaje>();
		
	}
	public void run() {
		try {
			
			while(true) {
				//MANDA UN MENSAJE DE BIENVENIDA AL CLIENTE
				Mensaje m=new Mensaje("Bienvenid@,  ¿Quieres consultar,enviar un mensaje o salir?");
				salida.writeObject(m);
				
				//RECIBO RESPUESTA DE LO QUE QUIERE HACER EL CLIENTE
				Mensaje recibido=(Mensaje) entrada.readObject();
				this.nombreCliente=(Mensaje) entrada.readObject();
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
				//SI EL CLIENTE QUIERE SALIR CIERRO LOS SOCKETS Y EL CANAL DE COMUNICACIÓN Y ME SALGO DEL BUCLE.
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
		Mensaje m2=new Mensaje(this.nombreCliente.getMensaje()+ " ,a quien quieres enviarle un mensaje?");
		salida.writeObject(m2);
		Mensaje destinatario=(Mensaje) entrada.readObject();
		this.destinatario=destinatario.mensaje;	
	}
	
	public void PedirMensaje() throws IOException, ClassNotFoundException {
		//PREGUNTA AL CLIENTE EL MENSAJE QUE QUIERE ENVIAR Y GUARDA SU RESPUESTA
		Mensaje m3=new Mensaje("De acuerdo "+ this.nombreCliente.getMensaje()+", Escriba su mensaje");
		salida.writeObject(m3);
		Mensaje mensaje=(Mensaje) entrada.readObject();
		this.mensaje=mensaje.mensaje;		
	}
	
	public  void enviarElMensaje() throws IOException {
		//boleano para saber si ya existe como clave el destintario del mensaje
		boolean existe=false;
		//MANDO CONFIRMACION AL CLIENTE DE QUE SE ENVIARÁ EL MENSAJE
		Mensaje m3=new Mensaje("Mensaje enviado");
		salida.writeObject(m3);
		
		Mensaje mensaj=new Mensaje(this.nombreCliente.getMensaje(),this.destinatario,this.mensaje);
		
		for (String clave :Mensajes.mensajes.keySet()) {
		    if(clave.contains(this.destinatario)) {
		    	ms = Mensajes.mensajes.get(clave);
		    	ms.add(mensaj);
		    	existe=true;
		    }
		}
		if(!existe) {
			//CREO EL OBJETO MENSAJE Y LO AÑADO AL ARRAY
			ms.add(mensaj);
			//GUARDO EL MENSAJE CON EL DESTINATARIO EN EL HASHMAP Y EL ARRAY DE ESE MENSAJES DE ESE DESTINATARIO
			Mensajes.mensajes.put(this.destinatario,ms);
		}
		
	}
	
	public void ConsultarMensajes() throws IOException, ClassNotFoundException {
		//EL SERVIDOR LE MANDA AL CLIENTE UN ARRAYLIST CON SUS MENSAJES
		ArrayList<Mensaje>mensajescliente=new ArrayList();
		for (String clave :Mensajes.mensajes.keySet()) {
		    if(clave.contains(this.nombreCliente.getMensaje())) {
		    	mensajescliente = Mensajes.mensajes.get(clave);
		    	
		    }
		}
		
		salida.writeObject(mensajescliente);
		
	}
	
		
	
}
