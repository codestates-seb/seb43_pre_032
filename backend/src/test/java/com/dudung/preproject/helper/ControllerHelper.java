package com.dudung.preproject.helper;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

public interface ControllerHelper<T> {
    default RequestBuilder postRequestBuilder(String url, String content) {
        return  post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
    }

    default RequestBuilder patchRequestBuilder(String url, long resourceId, String content) {
        return patch(url, resourceId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
    }

    default RequestBuilder getRequestBuilder(String url, long resourceId) {
        return get(url, resourceId)
                .accept(MediaType.APPLICATION_JSON);
    }

    default RequestBuilder deleteRequestBuilder(String url, long resourceId) {
        return delete(url, resourceId);
    }

    default String toJsonContent(T t) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String content = gson.toJson(t);
        return content;
    }
}
