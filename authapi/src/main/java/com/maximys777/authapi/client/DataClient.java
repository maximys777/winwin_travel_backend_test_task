package com.maximys777.authapi.client;

import com.maximys777.authapi.client.config.FeignConfig;
import com.maximys777.authapi.dto.request.ProcessRequest;
import com.maximys777.authapi.dto.response.ProcessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "data-api", url = "${app.data-api.url}", configuration = FeignConfig.class)
public interface DataClient {

    @PostMapping("/api/transform")
    ProcessResponse transform(
            @RequestBody ProcessRequest request,
            @RequestHeader("X-Internal-Token") String token
    );

}
