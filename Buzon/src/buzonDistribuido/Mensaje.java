package buzonDistribuido;

import java.io.Serializable;
/**
 * 
 * @author Paula
 * Esta clase la utilizo para mandar mensajes del cliente al servidor
 *
 */

@SuppressWarnings("serial")
public class Mensaje implements Serializable{


	String remitente,destinatario,mensaje;
	
	public Mensaje(String remitente,String destinatario, String mensaje) {
		this.remitente=remitente;
		this.destinatario=destinatario;
		this.mensaje=mensaje;
	}
	
	public Mensaje(String mensaje) {
		this.mensaje=mensaje;
	}

	public Mensaje(String destinatario, String mensaje) {
		this.destinatario=destinatario;
		this.mensaje=mensaje;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void muestraMensaje() {
		System.out.println( this.mensaje);
	}
	
	public void MuestraTodo() {
		System.out.println("Mensaje de "+ this.remitente +" para " +this.destinatario+" :" + this.mensaje);
	}
}
