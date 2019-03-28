package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author gaoxiong
 * @ClassName Server
 * @Description TODO
 * @date 2019/3/28 22:00
 */
public class Server {
    public static void main ( String[] args ) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);

        System.out.println("服务器准备就绪");
        System.out.println("服务器信息: "+serverSocket.getInetAddress()+"P:"+serverSocket.getLocalPort());
        //等待客户端链接
        for (; ; ) {
            Socket client = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(client);
            clientHandler.start();
        }
    }

    private static void todo ( Socket client ) {

    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = true;

        public ClientHandler ( Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run () {
            super.run();
            System.out.println("新客户端链接: " + socket.getInetAddress() + "P: " + socket.getPort());
            try {
                //
                PrintStream socketOp = new PrintStream(socket.getOutputStream());
                //
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                do {
                    String str = socketInput.readLine();
                    if ("bye".equals(str)) {
                        flag = false;
                        socketOp.println("bye");
                    } else {
                        System.out.println(str);
                        socketOp.println("回送:" + str.length());
                    }
                } while (flag);

                socketInput.close();
                socketOp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已退出:"+socket.getInetAddress()+"P:"+socket.getPort());
        }
    }

}
