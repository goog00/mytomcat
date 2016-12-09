package ex03.pyrmont.connector.http;

import org.apache.catalina.util.StringManager;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Convenience implementation of <b>ServletInputStream</b> that works with the standard implementations of <b>Request</b> If the content length has
 * been set on our associated Request ,this implementation will enforce not reading more than that many bytes on the underlying stream
 * Created by ST on 2016/12/9.
 */
public class RequestStream extends ServletInputStream{

    // ----------------------------------------------------------- Constructors

    public RequestStream(HttpRequest request){
        super();

    }


    // ----------------------------------------------------- Instance Variables

    /**
     * Has this stream been closed
     */
    protected boolean closed = false;

    /**
     * The number of bytes which have already been return by this stream
     */
    protected int count = 0;

    /**
     * the content length past which we will not read ,or -1 if there is no defined content length
     */
    protected int length = -1;

    /**
     * The localized strings for this package
     */
    protected static StringManager sm = StringManager.getManager(Constants.Package);


    // the underlying input stream from which we should read data
    protected InputStream stream = null;

    // --------------------------------------------------------- Public Methods


    /**
     * Close this input stream No physical level I-O is performed(执行)，but any further attempt to read  from this stream will throw an IOException.
     * if a content length has been set but not all of the bytes have yet been consumed ,the remaining bytes will be swallowed
     * @throws IOException
     */
    public void close()throws IOException{
        if(closed){
            throw new IOException(sm.getString("reqeustStream.close.closed"));
        }
        if(length > 0){
            while (count < length){
                int b = read();
                if(b < 0){
                    break;
                }
            }
        }
        closed = true;
    }

    public int read() throws IOException{
        //has this stream been closed?
        if(closed){
            throw new IOException(sm.getString("RequestStream.read.closed"));
        }
        //Have we read the specified content length already?
        if((length >= 0) && (count >= length))
            return (-1);

        //Read and count the next byte then return it
        int b = stream.read();
        if(b >= 0){
            count++;
        }
        return (b);
    }

    public int read(byte b[]) throws IOException{
        return ((read(b,0,b.length)));
    }

    /**
     * Read up to
     * @param b
     * @param off
     * @param len
     * @return
     * @throws IOException
     */
    public int read(byte b[],int off,int len) throws IOException{
        int toRead = len;
        if(length > 0){
            if(count >= length){
                return (-1);
            }
            if((count + len) > length){
                toRead = length - count;
            }
        }
        int actuallyRead = super.read(b,off,toRead);
        return (actuallyRead);
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }
}
