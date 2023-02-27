package bin.util;

import bin.entity.Player;
import bin.game.ConnectGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    int[][] clientMap;
    public Player clientPlayer;
    public Player serverPlayer;
    int[][] serverMap;

    public Server(Player clientPlayer,Player serverPlayer){
        this.clientPlayer=clientPlayer;
        this.clientMap= clientPlayer.map;
        this.serverPlayer=serverPlayer;
        this.serverMap= serverPlayer.map;
    }

    @SuppressWarnings("BusyWait")
    public void createServer(int port){
        Thread thread = new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("创建成功！");
                Thread.sleep(1000L);
                System.out.println("等待玩家进入");
                Socket socket = serverSocket.accept();
                System.out.println("连接成功！");
                ConnectGame.hasConnection=true;
                Thread.sleep(100L);
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                while (true){
                    clientPlayer.floor=inputStream.readInt();
                    outputStream.writeInt(serverPlayer.floor);
                    Thread.sleep(100L);
                }

            } catch (SocketException e){
                System.out.println("其它玩家断开连接！");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
