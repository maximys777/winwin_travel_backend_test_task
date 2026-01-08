package com.maximys777.dataapi.service;

import com.maximys777.dataapi.dto.response.TransformResponse;
import com.maximys777.dataapi.exception.exceptions.IllegalArgumentException;
import com.maximys777.dataapi.exception.exceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransformService {

    @Value("${app.internal-token}")
    private String internalToken;

    public TransformResponse processText(String text, String tokenHeader) {

        if (!internalToken.equals(tokenHeader)) {
            throw new InvalidTokenException("Invalid token");
        }

        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        String reversed = new StringBuilder(text).reverse().toString();
        
        return new TransformResponse(reversed);
    }
}
