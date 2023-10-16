package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	//Creación de atributos que vamos a necesitar
	private final int PUERTO = 9876; //conectamos con el puerto
    private ServerSocket serverSocket; //canal de conexion
    private Socket socket; //socket para conectar
    private DataOutputStream salida; 
    private DataInputStream entrada; 
    private String mensaje, descripcion, estado;
    private ArrayList <Tarea> listaTareas = new ArrayList<Tarea>(); //ArrayList para guardar las tareas
    private int tarea;
    
    
    //Definimos el constructor
    public Server() throws IOException {
        serverSocket = new ServerSocket(PUERTO); //Definimos la conexion
        socket = new Socket(); //Iniciamos el cliente
    }
    
    //Función para iniciar la conexión
    public void iniciarServer() throws IOException {
        
    	//Vamos a aceptar los datos que llegarán del cliente
    	
            System.out.println("Esperando la conexion del 1r cliente");
            socket = serverSocket.accept(); //guardamos la petición que llega de cliente  en socket
            //Va a quedar a la espera de recibir peticiones
            
            //Cuando llega petición, iniciamos la conexión
            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
            //Enviamos los mensajes al cliente
            //Primera petición
            salida.writeUTF("Hola, ¿Cual es tu nombre");
            mensaje=entrada.readUTF(); //leemos la respuesta del cliente y guardamos en variable
            System.out.println("Encantado de verte, "+mensaje); //mostramos mensaje
            //Segunda petición
            salida.writeUTF("¿Cuantas tareas quieres realizar?");
            tarea= entrada.readInt();
            System.out.println("Las tareas a realizar son: "+tarea);
            
            //Creamos bucle para enviar y recibir peticiones acorde con a clase client
            for(int i = 0; i<tarea; i++) {
            	 salida.writeUTF("Esta es la tarea: "+ (i+1));
            	 salida.writeUTF("Por favor, introduce la descripción de la tarea " +(i+1));
            	 descripcion=entrada.readUTF();
            	 System.out.println("Descripción recibida: "+descripcion);
            	 salida.writeUTF("Por favor, introduce el estado de la tarea " + (i+1) );
            	 estado=entrada.readUTF();
            	 System.out.println("Estado recibido: "+estado);
            	 
            	 //Guardamos en el arrayList las entradas de las Tareas
            	 listaTareas.add(new Tarea(descripcion,estado));
            	
            }
            
            System.out.println("Listado de todas las tareas");
            salida.writeUTF("Listado de todas las tareas"); //informamos del envio de las tareas
            
            
            //con un bucle recorremos el arrayList y se lo enviamos a Client
            for(Tarea tarea:listaTareas) {
            	salida.writeUTF(tarea.toString());
            }
            
            //cerramos todos los canales de comunicación
            salida.close();
            entrada.close();
            socket.close();
            
    }
    
    //Cerramos el servidor
    public void finalizarServer() throws IOException {
        serverSocket.close();
    }

}
