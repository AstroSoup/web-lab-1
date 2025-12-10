package ru.astrosoup.controllers;

import com.fastcgi.FCGIRequest;
import ru.astrosoup.DTOs.HttpResponse;
import ru.astrosoup.exceptions.UnsupportedEndpointException;
import ru.astrosoup.exceptions.UnsupportedHttpMethodException;

import java.util.logging.Logger;

public class BasicController{

    private static Logger logger = Logger.getLogger(BasicController.class.getName());

    public HttpResponse<?> mapRequestEndpoint(FCGIRequest request) throws UnsupportedHttpMethodException, UnsupportedEndpointException {
        return switch (request.params.getProperty("REQUEST_URI").replace("/fcgi", "")) {
            case "/" -> new RootController().mapRequestMethod(request);
            default -> throw new UnsupportedEndpointException("The program does not provide service on endpoint " + request.params.getProperty("REQUEST_URI"));
        };
    }
}
