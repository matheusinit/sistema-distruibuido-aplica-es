/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author 20201014040024
 */
public class Cliente {
    String address = "";
    int port = 1235;
    
    public Cliente() {
        try {
            Socket socket = new Socket(address, port);
            
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[200];
            inputStream.read(data);
            inputStream.close();
            
            System.out.println("Data: " + new String(data).trim());
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Cliente();
    }
    
}
