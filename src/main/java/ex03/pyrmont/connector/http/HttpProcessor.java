package ex03.pyrmont.connector.http;

import org.apache.catalina.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * this class used to be called HttpServer
 * Created by ST on 2016/12/7.
 */
public class HttpProcessor {

    public HttpProcessor(HttpConnector connector){
        this.connector = connector;
    }

    private HttpConnector connector;
    private HttpRequest request;
    private HttpRequestLine requestLine = new HttpRequestLine();
    private HttpResponse response;

    protected String method = null;
    protected String queryString = null;


    public void process(Socket socket){
        SocketInputStream input = null;
        OutputStream output = null;

        try {
            input = new SocketInputStream(socket.getInputStream(),2048);
            output = socket.getOutputStream();

            //create HttpRequest object and parse
            request = new HttpRequest(input);

            //create HttpResponse object


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
