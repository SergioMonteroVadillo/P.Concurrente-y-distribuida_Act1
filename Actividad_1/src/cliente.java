/****************************************************
 * Asignatura: Programaci�n concurrente y distribuida
 * Profesor: Jos� Delgado P�rez
 * Alumno: Sergio Montero Vadillo
 * DNI: 53426672-H 
 * Actividad : Actividad 1 / Hilos y Socket
 * �ltima modificaci�n: 23/1/2023
 ****************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class cliente{

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("Bienvenido al chat de la UE");
		//Solicita por consola la IP del Servidor
		String serverAddress = System.console().readLine("\nIntroduce la direcci�n IP del servidor del chat: ");
		System.out.println("");
		
		//Condici�n para no salir de programa
		boolean estado = true;
		
		//Condici�n para mostrar men� al inicio 
		boolean inicio = true;
		
		//Bucle infinito para que no finalice el programa
		while (estado) {
			
			//Creamos el cliente socket con la IP del servidor y el puerto por el que escucha
			Socket socket = new Socket(serverAddress, 9999);
			
			//Creamos la varible de escritura para el buffer de salida socket
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
						
			//Condici�n mostrar menu, entra solo en la primera vuelta
			if (inicio == true) {		
				out.println("5");	//Env�a opcic�n de men�		
				inicio = false;		//Pone condici�n en falso
			}
			else {	//En el resto de vueltas... 

				//Se solicita introducir opcion por consola
				String solicitud = System.console().readLine("\nIntroduzca su solicitud: ");
				System.out.println("");	
				//Enviamos mensaje al servidor
				out.println(solicitud);				
			}	
			//Creamos la lectura para el buffer de entrada del socket
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//Leemos el buffer de entrada
			String answer = input.readLine();						
			//Imprimimos el mensaje recibido por el servidor
			System.out.println(answer);
			
			//Condici�n para finalizar el programa
			if (answer.equals("Hasta pronto")) {				
				estado = false;
			}			
			
			//Eliminamos el socket
			socket.close();			
		}
	}
}