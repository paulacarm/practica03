package buzonDistribuido;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * 
 * @author Paula.
 * La clase cliente recibe una bienvenida del servidor y le responde que operaci�n quiere realizar(consultar o enviar mensajes)
 *
 */
public class Cliente {
	//nombre del cliente para identificarlo y saber qu� mensajes son para �l
	private String nombre;
	//Socket del cliente
	private Socket socket;
	//Scanner para pedir datos
	Scanner s;
	//String para almacenamiento de datos necesarios
	private String destinatario,mens,accion;
	//HashMap donde recibiremos todos los mensajes con sus destinatarios
	private HashMap<String, ArrayList<Mensaje>> mismensajes;
	//Array donde se almacenaran los mensajes que van dirigidos a mi
	private ArrayList<Mensaje> miarraypropio;
	//canal de salida de datos
	private ObjectOutputStream  salida;
	//canal de entrada de datos
	private ObjectInputStream entrada;
	 
	public Cliente(String nombre) throws ClassNotFoundException, IOException  {
		this.nombre=nombre;
		this.socket=new Socket();
		System.out.println("Estableciendo la conexi�n");
		InetSocketAddress addr=new InetSocketAddress("localhost",5555);
		miarraypropio=new ArrayList<Mensaje>();
		this.s=new Scanner(System.in);
	
			socket.connect(addr);
			entrada  =new ObjectInputStream(socket.getInputStream());
			salida=new ObjectOutputStream(socket.getOutputStream());
		
			while(true) {
					recibirBienvenida();
				if(accion.equals("enviar")) {
					DarDestinatario() ;
					DarMensaje();
				}else if(accion.equals("consultar")) {
					ConsultarMensaje() ;
				}else if(accion.equals("salir")) {
					//SI QUIERE SALIR SE CIERRA EL SOCKET Y EL CANAL DE COMUNICACION
					entrada.close();
					salida.close();
					socket.close();
					break;
				}
				else {
					System.out.println("no entiendo la accion ");
				}
			}
		}
				
	
	public void recibirBienvenida() throws ClassNotFoundException, IOException {
		//LEO EL MENSAJE DEL SERVIDOR DE BIENVENIDA
		Mensaje m=(Mensaje) entrada.readObject();
		m.muestraMensaje();
		//LE RESPONDO LA ACCION QUE QUIERO REALIZAR
		accion=s.nextLine();
		Mensaje vuelta= new Mensaje(accion);
		salida.writeObject(vuelta);
	}
	
	public void DarDestinatario() throws ClassNotFoundException, IOException {
		//LEO LA PETICION DEL SERVIDOR DEL DESTINATARIO
		Mensaje m2=(Mensaje) entrada.readObject();
		m2.muestraMensaje();
		//LE RESPONDO EL DESTINATARIO QUE DESEO ENVIAR
		this.destinatario=s.nextLine();
		Mensaje destinatario=new Mensaje(this.destinatario);
		salida.writeObject(destinatario);
	}
	
	public void DarMensaje() throws ClassNotFoundException, IOException {
		//LEO PETICION DE MENSAJE
		Mensaje m2=(Mensaje) entrada.readObject();
		m2.muestraMensaje();
		//LE RESPONDO EL MENSAJE QUE DESEO ENVIAR
		this.mens=s.nextLine();
		Mensaje m=new Mensaje(this.mens);
		salida.writeObject(m);
		//RECIBO RESPUESTA DE CONFIRMACION DEL SERVIDOR
		Mensaje m3=(Mensaje) entrada.readObject();
		m3.muestraMensaje();
		
	}
	
	@SuppressWarnings("unchecked")
	public void ConsultarMensaje() throws ClassNotFoundException, IOException {
		//RECIBO LOS MENSAJES DEL SERVIDOR PARA VER CUALES SON PARA MI
		//guardo los que sean mios en un array y los muestro
		
		mismensajes=(HashMap<String, ArrayList<Mensaje>>) entrada.readObject();
		System.out.println("mensajes para t�, "+this.nombre);
		
		for (String clave :mismensajes.keySet()) {
			miarraypropio = mismensajes.get(clave);
		    if(clave.contains(this.nombre)) {
		    	for (Mensaje mensaje : miarraypropio) {
		    		mensaje.muestraMensaje();
		    	 }
		    }
		   
		}
		
		
	}


}


