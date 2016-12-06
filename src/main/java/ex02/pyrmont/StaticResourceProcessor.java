package ex02.pyrmont;

import java.io.IOException;

/**
 * Created by ST on 2016/12/6.
 */
public class StaticResourceProcessor {
    public void process(Request request,Response response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
