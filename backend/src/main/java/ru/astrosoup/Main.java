package ru.astrosoup;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.logging.Logger;

import com.fastcgi.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.astrosoup.DTOs.AreaHitResponse;
import ru.astrosoup.controllers.BasicController;
import ru.astrosoup.DTOs.HttpResponse;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static final String BASE_RESPONSE = """
            Access-Control-Allow-Origin: *
            Connection: keep-alive
            Content-Type: application/json
            Content-Length: %d
                        
            %s
            """;

    public static void main(String[] args) {
        FCGIInterface fcgi = new FCGIInterface();
        BasicController controller = new BasicController();
        while (fcgi.FCGIaccept() >= 0) {
            long startTime = System.currentTimeMillis();
            FCGIRequest request = FCGIInterface.request;
            try {
                HttpResponse<?> httpResponse = controller.mapRequestEndpoint(request);
                httpResponse.setExecutionTime(System.currentTimeMillis() - startTime);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                String json = objectMapper.writeValueAsString(httpResponse);
                System.out.println(formatResponse(json));
            } catch (Exception ex) {
                System.out.println(formatResponse("\"exception\":\""+ ex.getMessage() +"\""));
            }

        }
    }

    private static String formatResponse(String body) {
        return String.format(BASE_RESPONSE, body.getBytes(StandardCharsets.UTF_8).length, body);
    }

}