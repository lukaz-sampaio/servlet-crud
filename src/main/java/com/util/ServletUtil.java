package com.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
public class ServletUtil {
    
    private ServletUtil(){}

    public static void render(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!response.isCommitted()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
