package id.my.hendisantika.capcay.service;

import id.my.hendisantika.capcay.dto.RecaptchaDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : capcay
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/07/24
 * Time: 09.46
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RecaptchaService {

    public RecaptchaDTO token(String token) {
        String url = "https://www.google.com/recaptcha/api/siteverify";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", "secret-key");
        map.add("response", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RecaptchaDTO response = restTemplate.postForObject(url, request, RecaptchaDTO.class);

        return response;
    }

}
