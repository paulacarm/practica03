package buzonDistribuido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author Paula.
 * Esta clase tiene un HashMap estatico para guardar los destinatarios de los mensajes junto con un arraylist de sus mensajes.
 * La lista es actualizada por el servidor cuando recibe los datos del cliente
 *
 */
public class Mensajes  implements Serializable {
	

	public static  HashMap<String, ArrayList<Mensaje>> mensajes=new HashMap<String, ArrayList<Mensaje>>();
	 
	

	
}
