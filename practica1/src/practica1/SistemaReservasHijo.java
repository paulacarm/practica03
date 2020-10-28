package practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class SistemaReservasHijo {

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	

		System.out.println("Bievenidos, en qué puedo ayudarle?");
		Scanner s=new Scanner(System.in);
		String t;
		t= s.nextLine();
	
		
		while(!t.equals("fin")) {
			if(t.equals("adopcion de mascota")) {
				System.out.println("Perfecto, tenemos gatos y perros disponibles para adoptar¿Cual desea?");
				t=s.nextLine();
				break;
				
					
			}else {
				System.out.println("No entiendo el mensaje.Has probado con 'adopcion de mascota'?");
				t=s.nextLine();
				
				
				}
			}
		
		 while(!t.equals("fin")) {
			if (t.equals("perro") || t.equals("gato")) {
				System.out.println("Fantastico! va a adoptar usted un " +t+" Introduzca la fecha de recogida");
				t=s.nextLine();
				if(!t.equals("")){
					System.out.println("De acuerdo! Adopcion reservada");
					break;
				}
				
			
			}else {
				System.out.println("No entiendo el mensaje.Has probado con 'perro' o 'gato'?");
				t=s.nextLine();
				
			}
		
		 }
		
	}

}
