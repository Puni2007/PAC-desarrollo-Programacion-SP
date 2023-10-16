package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import server.Tarea;

public class Client {
	
		//Creamos todos los atributos necesarios
		private final String HOST = "localhost"; 
	    private final int PUERTO = 9876; //Conectamos con el mismo puerto
	    private Socket socket; //Abrimos socket
	    private DataInputStream entrada; 
	    private DataOutputStream salida;
	    private String descripcion, estado, nombre;
	    private int tarea;
    	
    	//Creamos Scanner para poder pedir los datos por consola al usuario
    	static Scanner sc = new Scanner(System.in);
	    
    	//Llamamos al Socket en el localhost con el puerto requerido
	    public Client() throws IOException {
	        socket = new Socket(HOST, PUERTO);
	    }
	    
	    //iniciamos función para iniciar cliente
	    public void iniciarCliente() throws IOException {
	    	
	    	//Iniciamos la entrada de datos
	        entrada = new DataInputStream(socket.getInputStream());
	        
	        System.out.println(entrada.readUTF()); //Mostramos el mensaje del servidor por pantalla
	        
	        //Iniciamos la salida de datos
	        salida = new DataOutputStream(socket.getOutputStream());
	        
	        //Solicitamos el nombre por consola
	        nombre=sc.nextLine();
	        salida.writeUTF(nombre); //se envia al servidor
	        System.out.println("Enviando mensaje al servidor: {"+nombre+"}"); //informamos de lo que se envia
	        System.out.println("Recibiendo mensaje del servidor");
	        
	        System.out.println(entrada.readUTF()); //recibimos del servidor el siguiente mensaje
	        tarea=sc.nextInt(); //Solicitamos al usuario
	        sc.nextLine(); //despues de un nextInt() limpiar buffer
	        salida.writeInt(tarea); //enviamos al servidor
	        System.out.println("Enviando mensaje al servidor: {"+tarea+"}"); //información del envio
	        System.out.println("Leyendo mensaje del servidor");
	        
	        //Bucle acorde con el servidor para entrada y salida peticiones
	        for(int i = 0; i<tarea; i++) {
	        	System.out.println(entrada.readUTF());
	        	System.out.println("Leyendo mensaje del servidor");
	        	System.out.println(entrada.readUTF());
	        	descripcion = sc.nextLine();
	        	System.out.println("Enviando mensaje al servidor: {"+descripcion+"}");
	        	salida.writeUTF(descripcion);
	        	System.out.println("Leyendo mensaje del servidor");
	        	System.out.println(entrada.readUTF());	
	        	estado = sc.nextLine();
	        	System.out.println("Enviando mensaje al servidor: {"+estado+"}");
	        	salida.writeUTF(estado);
	        	System.out.println("Leyendo mensaje del servidor");
	        	
            }
	        
	        
	        
	        //Recibimos del servidor que va a enviar la lista de tareas
	        System.out.println(entrada.readUTF());
	        
	        System.out.println("Leyendo mensaje del servidor");
	        
	      
	        //Creamos bucle para recibir las tareas del servidor
	       for(int i = 0; i<tarea; i++) {
	        	System.out.println(entrada.readUTF());
	        	//Si la tarea es diferente a la ultima posición no me imprimas las frase
	        	//para que quede acorde con el ejemplo del PDF
	        	if(i!=tarea-1) {
	        	System.out.println("Leyendo mensaje del servidor");
	        	}
	        }
	           
	        //Cerramos todos los canales de comunicación
	        salida.close();
	        entrada.close();
	        socket.close();
	        sc.close();
	    }
	    
	    
	    
}
