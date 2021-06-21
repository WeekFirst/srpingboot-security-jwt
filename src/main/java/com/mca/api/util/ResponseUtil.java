package com.mca.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author an Stark
 * @Description: response call back
 * @Date 2021/6/18 14:56
 * @Version 1.0
 */
@Slf4j
public class ResponseUtil {

    public static void responseOut(HttpServletResponse response, Result result) {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = null;
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            writer = response.getWriter();
            mapper.writeValue(writer, result);
            writer.flush();
        }catch (IOException ex){
            log.error(ex.getMessage());
        }finally {
            if (null != writer ) {
                writer.close();
            }
        }

    }
}
