package org.daniels.spring.resttemplate.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return HttpStatus.Series.SERVER_ERROR ==  httpResponse.getStatusCode().series()
               || HttpStatus.Series.CLIENT_ERROR == httpResponse.getStatusCode().series();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (HttpStatus.Series.SERVER_ERROR == httpResponse.getStatusCode().series()) {
            log.error("Server error");
        } else if (HttpStatus.Series.CLIENT_ERROR == httpResponse.getStatusCode().series()) {
            log.error("Client error");
            if (HttpStatus.NOT_FOUND == httpResponse.getStatusCode()) {
                log.error("Client error - not found");
            }
        }
    }
}
