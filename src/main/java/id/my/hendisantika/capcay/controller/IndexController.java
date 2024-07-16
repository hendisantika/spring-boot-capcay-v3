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
     * reCAPTCHA v3 验证
     * <p>
     * "reCaptchaV3Store" 为 reCAPTCHA v3 的存储器。
     * 注解 ”@Parameter“ 提供了名为 "SECRET" 的参数，值为 reCAPTCHA 的服务端密钥。未指定此参数时将从配置文件加载默认的服务的密钥。
     *
     * @param result 验证通过后，传入详细信息
     * @return
     */
//    @GetMapping("/checkV3")
//    @VerifyCode(store = @Store("reCaptchaV3Store"),
//            verifier = @Verifier("reCaptchaVerifier"),
//            parameters = {
//                    @Parameter(name = "SECRET", value = "6LdV3iMpAAAAAMP0dCeWqByWdP3IN5ll0AT6kzSt")
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
    public @ResponseBody String ajax(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", SECRET_KEY);
        map.add("response", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = builder.build().postForEntity(SITE_VERIFY_URL, request, String.class);

        return response.getBody();
    }

    @GetMapping("/v3")
    public String index2() {
        return "index2";
    }

    @GetMapping(value = "/robot")
    public @ResponseBody RecaptchaDTO ajaxV3(String token) {
        return recaptchaService.token(token);
    }
}
