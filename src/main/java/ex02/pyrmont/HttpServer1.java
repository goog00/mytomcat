package ex02.pyrmont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ST on 2016/12/2.
 */
public class HttpServer1 {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args){
        HttpServer1 httpServer1 = new HttpServer1();
        httpServer1.await();
    }
    public void await(){
        System.out.println(System.getProperty("user.dir"));
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Loop waiting for request
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

                //check if this is a request for a servlet or a static resource
                //a request for a servlet begins with "/servlet/"
                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 processor1 = new ServletProcessor1();
                    processor1.process(request,response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request,response);
                }


                //check if the previous URI is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);


            } catch (IOException e) {
                e.printStackTrace();
//                System.exit(1);
                continue;
            } finally {
                //close the socket
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
