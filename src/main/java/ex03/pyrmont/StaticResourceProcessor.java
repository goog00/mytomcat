package ex03.pyrmont;

import ex03.pyrmont.connector.http.HttpRequest;
import ex03.pyrmont.connector.http.HttpResponse;

import java.io.IOException;

/**
 * Created by ST on 2016/12/20.
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request,HttpResponse response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
