package com.example.rootservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/api/root")
public class MainController {

    @GetMapping
    public ResponseEntity<String> getRoot() {
        RestTemplate template = new RestTemplate();
        try {
            String userUrl = "http://user-service-service/api/users";
            String users = template.getForObject(userUrl, String.class);
            String foodUrl = "http://food-service-service/api/food";
            String food = template.getForObject(foodUrl, String.class);
            return ResponseEntity.ok(users + "\n" + food);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode().is4xxClientError())
                return ResponseEntity.notFound().build();
            else if (ex.getStatusCode().is5xxServerError())
                return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
