package org.campus02.at;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EinwohnerServer {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)){

            while(true){
                System.out.println("Server wartet auf CLient...");
                Socket client = server.accept();

                EinwohnerHandler einwohnerHandler = new EinwohnerHandler(client);
                einwohnerHandler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFileException e) {
            e.printStackTrace();
        }

    }

}
