/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;

/**
 *
 * @author 20201014040024
 */
public class Server {
    static int port = 5000;
    private static byte[] mensagem = "Resposta".getBytes();
    
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Servidor em funcionamento. Aguardando conexões...");
            
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            socket.receive(packet);
            
            System.out.println("Dados: " + new String(packet.getData()).trim());
            System.out.println("Porta: " + packet.getPort());
            System.out.println("Endereço: " + packet.getAddress());
            
            DatagramPacket responsePacket = new DatagramPacket(
                    mensagem, 
                    mensagem.length, 
                    packet.getAddress(), 
                    packet.getPort()
            );
            
            socket.send(responsePacket);
            socket.close(); 
        } catch (SocketException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        
        
    }
}
