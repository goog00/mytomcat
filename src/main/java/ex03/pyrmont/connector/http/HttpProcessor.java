package ex03.pyrmont.connector.http;

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



    public void process(Socket socket){


    }
}
