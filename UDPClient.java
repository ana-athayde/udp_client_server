/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpcliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Aninh
 */
public class UDPCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        /*Args teste*/
        
        InetAddress srvIP = null;
        DatagramSocket clientSocket = null;
        
        if(args.length != 2){
            System.err.println("\nInvalid arguments!!\n\t Use: UDPCliente <server_ip> <server_port>");
            System.exit(1);
        }
        
        /*Show a start message*/
        System.out.println("\nStarting UDPCliente..."+
                "\n\tconfigured to send data to "+args[0]+" at port "+args[1]);
        
        /*De onde vamos pegar essa informação? Atraves de um buffer reader, que faz a leitura do teclado.*/
        /*Get buffered reader*/
        BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            //Socket para comunicação UDP
            clientSocket = new DatagramSocket();
        }catch(SocketException e){
            System.err.println("\n\tErrpr: "+e.getMessage());
            System.exit(1);
        }
        
        try {
            //Trasformar o nosso argumento e passar para  um formato ip 
            srvIP = InetAddress.getByName(args[0]);
        }catch(UnknownHostException e){
            System.err.println("\n\tErrpr: "+e.getMessage());
            System.exit(1);
        }
        int servPort = Integer.parseInt(args[1]);
        
        /*Communication Loop*/
        String sendMsg = "";
        
        do {            
            try {
                //Buffer de envio nada mais é que um array de bytes
                //65535 - 8 bytes UDP - 20 bytes IP Header
                byte[] sendData = new byte[65507];

                System.out.print("\nDigite uma mensagem:  ");
                sendMsg = inputBufferedReader.readLine();
                //Agora trasnformamos isso em um array de bytes
                sendData = sendMsg.getBytes();

                //Agora vamos criar nosso pacote, para popular as informações dentro dele
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendMsg.length(), srvIP, servPort);
                System.out.print("\nSending message...");
                ///Envia a mensagem paraa o servidor
                clientSocket.send(sendPacket);

                //Recebe a resposta do servidor, com a mesma porta de comunicação
                System.out.print("\nWaiting for server response...");
                byte[] receivedData = new byte[65507];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                clientSocket.receive(sendPacket);//Se executar essa linha e porque recebeu o pacote
                //Agora temos que ler essa mensagem

                String receivedMsg = new String(receivedPacket.getData(), 0, receivedPacket.getLength(), StandardCharsets.UTF_8);
                System.out.print("\nResposta do servidor: "+ receivedMsg);
                
                } catch (Exception e) {
                    System.err.println("\nError: "+ e.getMessage());
                    System.exit(1);
                }
        } while (!"exit".equals(sendMsg));
            

    }
}
