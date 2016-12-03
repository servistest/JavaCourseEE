package edu.mvc.controller.message;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by ALex on 03.12.2016.
 */
//Класс UrlUtil - это служебный класс, кодирующий URL мя перенаправления.
public class UrlUtil {
    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest){
        String enc=httpServletRequest.getCharacterEncoding();
        if (enc==null){
            enc= WebUtils.DEFAULT_CHARACTER_ENCODING;
        }try {
            pathSegment= UriUtils.encodePathSegment(pathSegment,enc);
        }catch (UnsupportedEncodingException ue){}
        return pathSegment;
    }
}
