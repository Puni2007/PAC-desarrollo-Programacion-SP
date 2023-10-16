package server;

import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Definimos objeto del servidor
    	Server serv = new Server();
        System.out.println("Iniciando servidor . . .");
        
        //Iniciamos el servidor
        serv.iniciarServer();
        
        //finalizamos el servidor
        serv.finalizarServer();

	}

}
