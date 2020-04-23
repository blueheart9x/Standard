package com.elcom.service.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.io.*;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
//import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anhdv.ho
 */
public class GZIPResponseStream extends ServletOutputStream {
    
    protected ByteArrayOutputStream baos = null;
    protected GZIPOutputStream gzipstream = null;
    protected boolean closed = false;
    protected HttpServletResponse response = null;
    protected ServletOutputStream output = null;
    
    public GZIPResponseStream(HttpServletResponse response) throws IOException {
        super();
        closed = false;
        this.response = response;
        this.output = response.getOutputStream();
        baos = new ByteArrayOutputStream();
        gzipstream = new GZIPOutputStream(baos);
    }
    
    @Override
    public void close() throws IOException {
//        if( closed ) {
//            throw new IOException("This output stream has already been closed");
//        }
        gzipstream.finish();
        
        byte[] bytes = baos.toByteArray();
        
        response.addHeader("Content-Length", Integer.toString(bytes.length));
        response.addHeader("Content-Encoding", "gzip");
        output.write(bytes);
        output.flush();
        output.close();
        closed = true;
    }
    
    @Override
    public void flush() throws IOException {
//        if( closed ) {
//            throw new IOException("Cannot flush a closed output stream");
//        }
        gzipstream.flush();
    }
    
    @Override
    public void write(int b) throws IOException {
//        if( closed ) {
//            throw new IOException("Cannot write to a closed output stream");
//        }
        gzipstream.write((byte)b);
    }
    
    @Override
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }
    
    @Override
    public void write(byte b[], int off, int len) throws IOException {
        //System.out.println("writing...");
//        if( closed ) {
//            throw new IOException("Cannot write to a closed output stream");
//        }
        gzipstream.write(b, off, len);
    }
    
    public boolean closed() {
        return this.closed;
    }
    
    /*public void reset() {
    }*/

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
