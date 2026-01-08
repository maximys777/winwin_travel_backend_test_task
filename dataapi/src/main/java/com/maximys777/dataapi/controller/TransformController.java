package com.maximys777.dataapi.controller;

import com.maximys777.dataapi.dto.request.TransformRequest;
import com.maximys777.dataapi.dto.response.TransformResponse;
import com.maximys777.dataapi.service.TransformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transform")
@RequiredArgsConstructor
public class TransformController {

    private final TransformService transformService;

    @PostMapping
    public ResponseEntity<TransformResponse> transform(
            @RequestBody TransformRequest request,
            @RequestHeader("X-Internal-Token") String token) {
        String result = transformService.processText(request.text(), token);

        return ResponseEntity.ok(new TransformResponse(result));
    }
}
