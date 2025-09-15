package ru.astrosoup.controllers;

import com.fastcgi.FCGIRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.astrosoup.DTOs.AreaHitRequest;
import ru.astrosoup.DTOs.AreaHitResponse;
import ru.astrosoup.DTOs.HttpResponse;
import ru.astrosoup.exceptions.UnsupportedHttpMethodException;
import ru.astrosoup.services.AreaHitService;

import java.io.IOException;

public class RootController {

    AreaHitService service = new AreaHitService();

    public HttpResponse<?> mapRequestMethod(FCGIRequest request) throws UnsupportedHttpMethodException {
        String httpMethod = request.params.getProperty("REQUEST_METHOD");
        return switch (httpMethod) {
            case "POST" -> postMapping(request);
            default -> throw new UnsupportedHttpMethodException(httpMethod + " is not supported.");
        };
    }
    private HttpResponse<?> postMapping(FCGIRequest request) {
        try {
            StringBuilder body = new StringBuilder();
            int in_char = request.inStream.read();
            while (in_char != -1) {
                body.append((char) in_char);
                in_char = request.inStream.read();
            }
            String requestBody = body.toString();
            ObjectMapper jsonMapper = new ObjectMapper();
            AreaHitRequest areaHitRequest = jsonMapper.readValue(requestBody, AreaHitRequest.class);

            AreaHitResponse areaHitResponse = new AreaHitResponse();
            areaHitResponse.setX(areaHitRequest.getX());
            areaHitResponse.setY(areaHitRequest.getY());
            areaHitResponse.setR(areaHitRequest.getR());
            areaHitResponse.setHit(service.isAreaHit(areaHitRequest));

            return new HttpResponse<>(areaHitResponse, 200);
        } catch (IOException e) {
            e.printStackTrace(); // TODO: replace with proper handling
            return new HttpResponse<>(null, 400);
        }
    }
}
