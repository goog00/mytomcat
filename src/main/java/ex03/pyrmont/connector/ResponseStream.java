package ex03.pyrmont.connector;

import ex03.pyrmont.connector.http.HttpResponse;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Convenience implementation of <b>ServeletOutputStream</b> that works with the standard ResponseBase of <b>Response</b>
 * if the content length has been set on our associated Response ,this implementation will enforce not writing more than that many
 * bytes on the underlying stream
 * Created by ST on 2016/12/15.
 */
public class ResponseStream extends ServletInputStream{

    // ----------------------------------------------------------- Constructors
    public ResponseStream(HttpResponse response){
        super();

    }

    /**
     * Has this stream been closed?
     */
    protected boolean closed = false;

    /**
     * Should we commit the response when we are flushed?
     */
    protected boolean commit = false;

    /**
     * The number of bytes which have already been to this stream
     */
    protected int count = 0;

    /**
     * The content length past which we will not write,or -1 if there is
     * no defined content length
     */
    protected int length = -1;

    /**
     * The Response with which this input stream is associated
     */
    protected HttpResponse response = null;

    /**
     * The underlying output stream to which we should write data
     */
    protected OutputStream stream = null;


    // ------------------------------------------------------------- Properties


    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean getCommit() {
        return commit;
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    public OutputStream getStream() {
        return stream;
    }

    public void setStream(OutputStream stream) {
        this.stream = stream;
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

    @Override
    public int read() throws IOException {
        return 0;
    }
}
