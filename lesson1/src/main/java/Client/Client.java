package Client;

import java.io.*;
import java.net.*;

/**
 * @author gaoxiong
 * @ClassName Client
 * @Description TODO
 * @date 2019/3/28 22:00
 */
public class Client {
    public static void main ( String[] args ) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 2222), 3000);

        System.out.println("已发起服务器链接,并进入后续流程");
        System.out.println("客户端信息: "+socket.getLocalAddress()+"P:"+socket.getLocalPort());
        System.out.println("服务端信息: " + socket.getInetAddress() + "P:" + socket.getPort());

        try {
            todo(socket);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常关闭");
        }
        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo ( Socket client ) throws IOException {
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        OutputStream os = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(os);

        InputStream clientInputStream = client.getInputStream();
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientInputStream));
        boolean flag = true;
        do {
            //读取键盘输入
            String str = input.readLine();
            //发送到服务器
            socketPrintStream.println(str);

            String echo = clientReader.readLine();
            if (echo.equals("bye")) {
                flag = false;
            } else {
                System.out.println(echo);
            }
        } while (flag);

        //资源释放
        socketPrintStream.close();
        clientReader.close();
    }
}
