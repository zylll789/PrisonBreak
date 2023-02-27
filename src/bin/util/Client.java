package bin.util;

import bin.entity.Player;
import bin.game.ConnectGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    DataOutputStream toServer=null;
    DataInputStream fromServer=null;

    int[][] clientMap;
    public Player clientPlayer;
    public Player serverPlayer;
    int[][] serverMap;

    public Client(Player clientPlayer,Player serverPlayer){
        this.clientPlayer=clientPlayer;
        this.clientMap= clientPlayer.map;
        this.serverPlayer=serverPlayer;
        this.serverMap= serverPlayer.map;
    }

    @SuppressWarnings({"BusyWait"})
    public void createClient(InetAddress ip, int port) {
        Thread thread = new Thread(()->{
            try {
                Socket socket = new Socket(ip,port);
                System.out.println("连接成功！");
                ConnectGame.hasConnection=true;
                fromServer = new DataInputStream(socket.getInputStream());
                toServer = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (true){
                    toServer.writeInt(clientPlayer.floor);
                    toServer.flush();
                    serverPlayer.floor = fromServer.readInt();
                    Thread.sleep(100L);
                }
            } catch (SocketException e){
                System.out.println("断开连接！");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

}
