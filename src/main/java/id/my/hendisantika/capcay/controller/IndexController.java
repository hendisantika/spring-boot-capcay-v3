package id.my.hendisantika.capcay.controller;

import id.my.hendisantika.capcay.dto.RecaptchaDTO;
import id.my.hendisantika.capcay.service.RecaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * Project : capcay
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/07/24
 * Time: 14.14
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {
    /**
     * reCAPTCHA v3 verification
     * <p>
     * "reCaptchaV3Store" is the storage for reCAPTCHA v3.
     * The annotation "@Parameter" provides a parameter named "SECRET" whose value is the server-side secret key of reCAPTCHA.
     * If this parameter is not specified, the default service secret key will be loaded from the configuration file.
     *
     * @param result After verification, detailed information is passed in
     * @return
     */
//    @GetMapping("/checkV3")
//    @VerifyCode(store = @Store("reCaptchaV3Store"),
//            verifier = @Verifier("reCaptchaVerifier"),
//            parameters = {
//                    @Parameter(name = "SECRET", value = "SECRET_KEY")
//            }
//    )
//    public String v3(@CodeValue ReCaptchaV3Result result) {
//        log.info(result.toString());
//        return String.format("Hello World! <br> (%s)", result);
//    }

    public static final String SECRET_KEY = "<YOUR_SECRET_KEY>";
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private final RecaptchaService recaptchaService;

    @Autowired
    private RestTemplateBuilder builder;

    @PostMapping(value = "/validation")
    public @ResponseBody RecaptchaDTO ajax(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", SECRET_KEY);
        map.add("response", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

//        ResponseEntity<String> response = builder.build().postForEntity(SITE_VERIFY_URL, request, String.class);
        ResponseEntity<RecaptchaDTO> response = builder.build().postForEntity(SITE_VERIFY_URL, request, RecaptchaDTO.class);
        return response.getBody();
    }

    @GetMapping("/v3")
    public String index2() {
        return "index2";
    }

    @PostMapping(value = "v3/robot/token")
    public @ResponseBody RecaptchaDTO ajaxV3(String token) {
        return recaptchaService.token(token);
    }
}
