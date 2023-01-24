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
import java.net.ServerSocket;
import java.net.Socket;

public class servidor{

	public static void main(String[] args) throws IOException {
		
		//Creamos el servidor socket e indicamos el puerto que atendera
		ServerSocket conexion = new ServerSocket(9999);
		
		//Imprimimos mensaje de inicializacion del servidor
		System.out.println("Servidor Iniciado");
				
		try {
		
		//Bucle infinito para que el servidor este activo
		while (true) {
						
			Socket socket = conexion.accept();		
			
			//Variable que lee el mensaje que el cliente ha enviado al buffer.
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//Se carga mensaje en variable
			String solicitud = input.readLine();
			
			//Creamos variable de salida
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
			//Condici�n que comprueba que la solicitud es n�mero entero
			if (isNumeric(solicitud)) {
				
				//Convierte solicitud en entero
				int Nsolicitud = Integer.parseInt(solicitud);
									
					//Condicion para imprimir en buffer el mensaje a razon de solicitud introducida
				switch (Nsolicitud){

				case 1:	out.println("AVISO -> Debe de registrarse para poder ingresar.");
					break;
				case 2:	out.println("AVISO -> Lo sentimos, no se pueden realizar mas registros.");
					break;
				case 3:	out.println("AVISO -> Esta opci�n se encuetra en mantenimiento, disculpe las molestias.");
					break;
				case 4:	out.println("AVISO -> Para darse de baja, escriba un correo a bajas@chat.net.\n");
					break;
				case 5:	out.println("MENU -> 1-Entrar  2-Registrarase  3-Manual  4-Bajas  5-Menu  6-Salir");
					break;
				case 6:	out.println("Hasta pronto");
					break;
				default:
					out.println("Solo se aceptan las solicitudes mostradas, por favor introduzca un n�mero de la lista.");
				}				
			}			
			else {
				out.println("Por favor introduzca la solicitud de forma num�rica.");
			}							
			socket.close();		//Se cierra objeto socket
		}
		}finally {
			conexion.close();	//Se cierra conxi�n como servidor.
		}
		
	}
	
	//Metodo que comprueba que la solicitud es un numero entero 
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

}