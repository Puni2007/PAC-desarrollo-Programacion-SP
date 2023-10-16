package client;

import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Creamos objeto de Cliente
        Client cli = new Client();
        System.out.println("Recibiendo mensaje del servidor");
        
        //Iniciamos la conexión
        cli.iniciarCliente();
        
        //Imprimimos mensaje para confirmar que ha finalizado la conexión
        System.out.println("Cerrando cliente");

	}

}
