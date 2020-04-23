package com.elcom.service.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

//import java.io.*;
//import java.util.*;
//import javax.servlet.*;
//import javax.servlet.http.*;

/**
 *
 * @author anhdv.ho
 */
public class GZIPResponseWrapper extends HttpServletResponseWrapper {
    
    protected HttpServletResponse origResponse = null;
    protected ServletOutputStream stream = null;
    protected PrintWriter writer = null;
    
    public GZIPResponseWrapper(HttpServletResponse response) {
        super(response);
        origResponse = response;
    }
    
    public ServletOutputStream createOutputStream() throws IOException {
        return new GZIPResponseStream(origResponse);
    }
    
    public void finishResponse() {
        try {
            if( writer!=null ) {
                writer.close();
            }else {
                if( stream!=null ) {
                    stream.close();
                }
            }
        }catch(IOException ex) {
            System.out.println("GZIPResponseWrapper.finishResponse() - IOException: " + ex.toString());
        }
    }
    
    @Override
    public void flushBuffer() throws IOException {
        stream.flush();
    }
    
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if( writer!=null ) {
            throw new IllegalStateException("getWriter() has already been called!");
        }
        if( stream==null ) {
            stream = createOutputStream();
        }
        return stream;
    }
    
    @Override
    public PrintWriter getWriter() throws IOException {
        
    	if( writer!=null )
            return writer;
        
        if( stream!=null ) {
            throw new IllegalStateException("getOutputStream() has already been called!");
        }
        stream = getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));
        return writer;
    }
    
    /*@Override
    public void setContentLength(int length) {
    }*/
    
}
