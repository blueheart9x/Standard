package com.elcom.service.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.elcom.common.Messages;
import com.elcom.model.constant.InterviewConstant;
import com.elcom.model.object.ExceptionModelDTO;
import com.elcom.sharedbiz.validation.AuthorizationException;
import com.elcom.sharedbiz.validation.PermissionException;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.JSONConverter;
import com.elcom.util.StringUtils;
import com.sun.jersey.server.impl.model.parameter.multivalued.ExtractorContainerException;

public class ExceptionFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ExceptionFilter.class.getName());

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        ServletContext context = null;
        try {

            context = fConfig.getServletContext();

            context.log("ExceptionFilter initialized!");
            InterviewConstant.ROOT_DIR = context.getRealPath("/");
        } catch (Exception e) {
            context.log("ExceptionFilter.init.Ex: " + e.toString());
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {

            /*--- Gzip filter ---*/
            if (req instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) req;
                request.setCharacterEncoding("UTF-8");
                HttpServletResponse response = (HttpServletResponse) res;
                //Get root url
                String currenUrl = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
                InterviewConstant.ROOT_URL = currenUrl;
                if (currenUrl != null && currenUrl.contains("/service")) {
                    InterviewConstant.ROOT_URL = currenUrl.substring(0, currenUrl.indexOf("/service") + 1);
                }

                String ae = request.getHeader("accept-encoding");
                if (ae != null && ae.contains("gzip")) {
                    GZIPResponseWrapper wrapperResponse = new GZIPResponseWrapper(response);
                    chain.doFilter(req, wrapperResponse);
                    wrapperResponse.finishResponse();
                    return;
                }
            }
            /*---*/

            chain.doFilter(req, res);

        } catch (Exception ex) {

            res.setContentType("application/json;charset=utf-8");

            // Support CORS
            HttpServletResponse httpResponse = (HttpServletResponse) res;
            httpResponse.setHeader("Access-Control-Expose-Headers", "Access-Control-*");
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, vi-identity");
            //httpResponse.setHeader("Access-Control-Allow-Headers", "Access-Control-*, Origin, X-Requested-With, Content-Type, Accept, content-type, accept, authorization, vi-identity");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

            try {

                int status = 0;
                int returnCode = 0;
                String message = StringUtils.Empty;
                Throwable th = ex.getCause();
                Exception tempEx = (Exception) th;

                if (tempEx instanceof ValidationException) {

                    status = ((ValidationException) tempEx).getHttpStatusCode();

                    ValidationException validationEx = (ValidationException) tempEx;
                    if (validationEx != null && !StringUtils.isNullOrEmpty(validationEx.getCode())) {
                        returnCode = Integer.parseInt(validationEx.getCode());
                    }

                    message = ex.getMessage();
                } else if (tempEx instanceof AuthorizationException) {
                    status = ((AuthorizationException) tempEx).getHttpStatusCode();
                    message = ex.getMessage();
                } else if (tempEx instanceof PermissionException) {
                    status = ((PermissionException) tempEx).getHttpStatusCode();
                    message = ex.getMessage();
                } else if (tempEx instanceof ExtractorContainerException) {
                    status = HttpStatus.SC_BAD_REQUEST;
                    message = "Value Extract Error: " + ex.getMessage();
                } else if (tempEx instanceof org.codehaus.jackson.map.exc.UnrecognizedPropertyException) {
                    status = HttpStatus.SC_BAD_REQUEST;
                    //message = "Value Extract Error: "+ ex.getMessage();
                    message = "Field name in payLoad is invalid!";
                } else {
                    status = HttpStatus.SC_INTERNAL_SERVER_ERROR;
                    message = tempEx == null ? "Internal Server Error" : tempEx.getMessage();
                }
                httpResponse.setStatus(status);

                PrintWriter out = null;

                ExceptionModelDTO result = null;

                try {

                    out = res.getWriter();

                    result = new ExceptionModelDTO();

                    if (returnCode == 0) {
                        returnCode = status;
                    }

                    result.setStatus(returnCode);
                    messageProgress(result, message);

                    out.print(JSONConverter.toJSON(result));

                    out.flush();

                } catch (Exception e) {
                    logger.error(
                            e.toString()
                    );
                } finally {

                    if (out != null) {
                        out.close();
                    }
                }

                /*System.out.println(
						tempEx!=null ? tempEx.getMessage() : result.getReturnMes()
				);*/
                logger.error(
                        tempEx != null ? tempEx.getMessage() : result != null ? result.getMessage() : "Unknow error"
                );

            } catch (Exception e) {
                //System.out.println(e.getMessage());
                //System.out.println(e.getStackTrace());

                logger.error(e.getMessage());
                logger.error(e.getStackTrace());

                httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);

                PrintWriter out = null;

                try {

                    out = res.getWriter();

                    ExceptionModelDTO result = new ExceptionModelDTO();
                    result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    messageProgress(result, e.getMessage());

                    out.print(JSONConverter.toJSON(result));

                    out.flush();

                } catch (Exception e1) {
                    logger.error(e1.toString());
                } finally {

                    if (out != null) {
                        out.close();
                    }
                }

            }

        }
    }

    private void messageProgress(ExceptionModelDTO result, String message) {

        message = formatMessage(message);

        result.setMessage(replaceMessage(message));
    }

    private String formatMessage(String message) {

        if (message.indexOf("org.codehaus.jackson.map.JsonMappingException: ") != -1 && message.indexOf("->") != -1) {

            String errEntity = message.substring(message.indexOf("->") + 2);

            if (errEntity.indexOf("[\"") != -1 && errEntity.indexOf("\"]") != -1) {

                String errField = errEntity.substring(errEntity.indexOf("[\"") + 2, errEntity.indexOf("\"]"));
                message = message.replace("org.codehaus.jackson.map.JsonMappingException: ", "");
                message = (!StringUtils.isNullOrEmpty(errField) ? "'" + errField + "': " : "") + message.substring(0, message.indexOf(':'));

                String lblInteger = Messages.getString("validation.json.integer");
                String strFmt = "Can not construct instance of {0} from String value";
                message = message.replace(MessageFormat.format(strFmt, "java.lang.Integer"), lblInteger)
                        .replace(MessageFormat.format(strFmt, "java.lang.Long"), lblInteger)
                        .replace(MessageFormat.format(strFmt, "java.math.BigDecimal"), lblInteger)
                        .replace(MessageFormat.format(strFmt, "java.util.Date"), Messages.getString("validation.json.date"));
            }
        }

        return message;
    }

    private String replaceMessage(String message) {

        return message.replace(
                "com.elcom.sharedbiz.validation.ValidationException: ", "")
                .replace("com.elcom.data.DataException: ", "")
                .replace("com.elcom.data.DataException: ", "")
                .replace("com.elcom.validation.AuthorizationException: ", "")
                .replace("com.elcom.sharedbiz.validation.AuthorizationException: ", "");
    }

    /*@Override
	public void destroy() {
	}*/
}
