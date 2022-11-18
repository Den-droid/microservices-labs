package org.example;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class RequestMultithreading implements Callable<Integer> {
    int requestNumber;
    String url;

    public RequestMultithreading(int requestNumber, String url) {
        this.requestNumber = requestNumber;
        this.url = url;
    }

    @Override
    public Integer call() throws Exception {
        RestTemplate template = new RestTemplate();
        int errors = 0;

        for (int i = 0; i < requestNumber; i++) {
            try {
                template.getForObject(url, String.class);
            } catch (HttpStatusCodeException ex) {
                if (ex.getStatusCode().is4xxClientError() ||
                        ex.getStatusCode().is5xxServerError())
                    errors++;
            }
        }
        return errors;
    }
}
