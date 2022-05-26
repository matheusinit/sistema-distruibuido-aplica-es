/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author 20201014040024
 */
public class Client {
    // Max size of packet  = 65535 (udp)
    static byte[] mensagem = "SOLICITAÇÃO DO CLIENTE".getBytes();
    static int port = 3000;
    
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

//            DatagramPacket packet = new DatagramPacket(
//                    mensagem, 
//                    mensagem.length, 
//                    InetAddress.getLocalHost(), 
//                    port
//            );
            

            DatagramPacket packet = new DatagramPacket(
                    mensagem, 
                    mensagem.length, 
                    InetAddress.getByName("10.25.1.132"), 
                    port
            );
            
            socket.send(packet);

            // Message is sent and received by the server but not entirely (in case of timeout failure)
            socket.setSoTimeout(40);

            socket.receive(packet);
            
            System.out.println("Dados: " + new String(packet.getData()).trim());
            System.out.println("Porta: " + packet.getPort());
            System.out.println("Endereço: " + packet.getAddress());
            
            socket.close();
        } catch (SocketException exception) {
            exception.printStackTrace();
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        
        
    }
}
