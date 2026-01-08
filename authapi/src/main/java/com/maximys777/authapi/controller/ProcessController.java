package com.maximys777.authapi.controller;

import com.maximys777.authapi.dto.request.ProcessRequest;
import com.maximys777.authapi.dto.response.ProcessResponse;
import com.maximys777.authapi.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping
    public ProcessResponse process(@RequestBody ProcessRequest request) {
        return processService.process(request);
    }
}
