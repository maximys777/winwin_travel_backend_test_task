package com.maximys777.authapi.service;

import com.maximys777.authapi.client.DataClient;
import com.maximys777.authapi.dto.request.ProcessRequest;
import com.maximys777.authapi.dto.response.ProcessResponse;
import com.maximys777.authapi.entity.ProcessingLogEntity;
import com.maximys777.authapi.entity.UserEntity;
import com.maximys777.authapi.repository.ProcessingLogRepository;
import com.maximys777.authapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final DataClient dataClient;
    private final ProcessingLogRepository logRepository;
    private final UserRepository userRepository;

    @Value("${app.internal-token}")
    private String internalToken;

    public ProcessResponse process(ProcessRequest request) {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ProcessResponse response = dataClient.transform(request, internalToken);

        UserEntity user = userRepository.findById(authUser.id()).orElseThrow();

        ProcessingLogEntity log = ProcessingLogEntity.builder()
                .user(user)
                .inputText(request.text())
                .outputText(response.text())
                .build();
        logRepository.save(log);

        return response;
    }
}
