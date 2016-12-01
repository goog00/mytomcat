package ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket是基于http协议的，客户端的http请求，serversocket在接收之后，
 * 通过socket.getInputStream()获取客户端传输的流，通过解析这个流就可以获取传输的信息
 *
 *
 * Created by ST on 2016/12/1.
 */
public class HttpServer {

    /**
     * WEB_ROOT is the directory where our html and other files reside
     * For this package,WEB_ROOT is the "webroot" directory under the working directory.
     * The working directory is the location in the file system from where the java command was invoked
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args){
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await(){

        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //loop waiting for request
        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;


            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //create Request object and parse
                Request request = new Request(input);
                request.parse();

                //create Response object
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                //close the socket
                socket.close();
                //check if the previous URI is a shutdown command
                shutdown = request.getUri() != null && request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }




}
