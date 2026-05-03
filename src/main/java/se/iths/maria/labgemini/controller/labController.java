package se.iths.maria.labgemini.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class labController {

    @GetMapping("/ask")
    public String ask(@RequestParam String prompt) {

        String apiKey = System.getenv("GOOGLE_API_KEY");
        
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        String body = "{"
                + "\"contents\": [{"
                + "\"parts\": [{"
                + "\"text\": \"" + prompt + "\""
                + "}]"
                + "}]"
                + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(url, entity, String.class);
    }
}
