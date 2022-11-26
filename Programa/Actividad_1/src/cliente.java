/****************************************************
 * Asignatura: Programación concurrente y distribuida
 * Profesor: José Delgado Pérez
 * Alumno: Sergio Montero Vadillo
 * DNI: 53426672-H 
 * Actividad : Actividad 1 / Hilos y Socket
 * Última modificación: 26/11/2022
 ****************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("Bienvenido al chat de la UE");
		//Solicita por consola la IP del Servidor
		String serverAddress = System.console().readLine("\nIntroduce la dirección IP del servidor del chat: ");
		System.out.println("");
		
		//Condición para no salir de programa
		boolean estado = true;
		
		//Condición para recivir mensaje de bienvenida
		boolean inicio = true;
		
		//Bucle infinito para mantenerse en programa
		while (estado) {
			
			//Creamos el cliente socket con la IP del servidor y el puerto por el que escucha
			Socket socket = new Socket(serverAddress, 9999);
			
			//Creamos la varible de escritura para el buffer de salida socket
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			//Condición para mensaje de bienvenida, entra solo primera vuelta
			if (inicio == true) {		
				out.println("5");
				//Creamos la lectura para el buffer de entrada del socket
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//Leemos el buffer de entrada
				String answer = input.readLine();				
				//Imprimimos el mensaje recibido por el servidor
				System.out.println(answer);						
				inicio = false;
			}
			else {

				//Se solicita introducir opcion por consola
				String solicitud = System.console().readLine("\nIntroduzca su solicitud: ");
				System.out.println("");	
				//Enviamos mensaje al servidor
				out.println(solicitud);

				//Creamos la lectura para el buffer de entrada del socket
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//Leemos el buffer de entrada
				String answer = input.readLine();						
				//Imprimimos el mensaje recibido por el servidor
				System.out.println(answer);

				//Condición para finalizar el programa
				if (answer.equals("Hasta pronto")) {				
					estado = false;
				}			
			}			
			//Eliminamos el socket
			socket.close();			
		}
	}
}