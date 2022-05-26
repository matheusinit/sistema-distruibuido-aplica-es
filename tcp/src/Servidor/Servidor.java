/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author 20201014040024
 */
public class Servidor {
    public int port = 1235;
    public Date date = new Date();
    
    public Servidor() {
        try {
            ;
            ServerSocket serverSocket = new ServerSocket(port);    
            
            System.out.println("Servidor em funcionamento...");
            
            while (true) {
                Socket socket = serverSocket.accept();
            
                System.out.println("Nova conexão!");
            
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(date.toString().getBytes());
                outputStream.close();
            }
            
            
            
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Servidor();
    }
    
}
