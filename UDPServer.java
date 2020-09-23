/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Aninh
 */
public class UDPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        @SuppressWarnings("UnusedAssingment")
        int serverPort = 0;
        
        /*Args test*/
        //O Server só precisa receber como argumento qual a porta que vai rodar
        if(args.length !=1){
            System.err.print("\nInvalid paramenters!!\n\tUse UDPServer <server_port> ");
            System.exit(1);
        }else{
            
            serverPort = Integer.parseInt(args[0]);
            if((serverPort<1) || serverPort>65535){
                System.err.print("\nParameter out of range!!\n\tserver-port: 1-65535 ");
                System.exit(2);
            }
        }
        
        System.out.print("\nUDPServer running at port "+ args[0]);
        
        //Criar o socker (nossa porta de comunicação)
        DatagramSocket serverSocket = new DatagramSocket(serverPort);
        
        //Criar buffers de recebimento e envio
        byte[] receivedData = new byte[65507];
        byte[] sendData = new byte[65507];
        
        /*Fica sempre parado esperando receber mensagem
        ou  seja tem uma caracterista de um looping infitino*/
        while(true){
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            System.out.print("\nWaiting message...");
            
            //Recebe mensagem
            serverSocket.receive(receivedPacket);
            
            //Pega o ip do cliente que enviou a mensagem
            InetAddress sourceIpAddress = receivedPacket.getAddress();
            //Pega a porta que foi utiliziada pelo cliente 
            int sourcePort = receivedPacket.getPort();
            //Pega os dados enviados pelo clietne
            receivedData = receivedPacket.getData();
            //Agora vamos trasformar esse array de bytes em uma String
            String msg = new String(receivedData, 0 , receivedPacket.getLength(), StandardCharsets.UTF_8);
            
            System.out.print("\nMessage received...");
            System.out.print("\n\tSource IP address: " + sourceIpAddress);
            System.out.print("\n\tSource  port: " + sourcePort);
            System.out.print("\n\tSource payload lenght: " + receivedPacket.getLength());
            System.out.print("\n\tPayload: " + msg);
            
            //Avisa o cliente que a mensagem foi recebida com sucesso
            String serverMsg = "Message successfully received by server.";
            //Trasformar em um array de bytes, e envia de volta para o cliente
            sendData = serverMsg.getBytes();
            //Insere os dados no pacote de envio
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, sourceIpAddress, sourcePort);
            //Envia o pacote pelo socket
            serverSocket.send(sendPacket);
        }
        
        
    }
    
}
