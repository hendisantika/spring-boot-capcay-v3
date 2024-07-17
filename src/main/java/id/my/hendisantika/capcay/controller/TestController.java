package id.my.hendisantika.capcay.controller;

import cn.dustlight.captcha.annotations.CodeValue;
import cn.dustlight.captcha.annotations.Parameter;
import cn.dustlight.captcha.annotations.Store;
import cn.dustlight.captcha.annotations.Verifier;
import cn.dustlight.captcha.annotations.VerifyCode;
import cn.dustlight.captcha.recaptcha.ReCaptchaResult;
import cn.dustlight.captcha.recaptcha.ReCaptchaV3Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : capcay
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/07/24
 * Time: 14.18
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
public class TestController {

    /**
     * reCAPTCHA v2 verification
     * <p>
     * Specify the storage and verifier by configuring the parameters store and verifier in the @VerifyCode annotation.
     * "reCaptchaStore" is the storage for reCAPTCHA v2.
     *
     * @param result After verification, pass in detailed information
     * @return
     */
    @GetMapping("/checkV2")
    @VerifyCode(store = @Store("reCaptchaStore"), verifier = @Verifier("reCaptchaVerifier"))
    public String index(@CodeValue ReCaptchaResult result) {
        log.info(result.toString());
        return String.format("Hello World! <br> (%s)", result);
    }

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
    @GetMapping("/checkV3")
    @VerifyCode(store = @Store("reCaptchaV3Store"),
            verifier = @Verifier("reCaptchaVerifier"),
            parameters = {
                    @Parameter(name = "SECRET", value = "secret-key")
            }
    )
    public String v3(@CodeValue ReCaptchaV3Result result) {
        log.info(result.toString());
        return String.format("Hello World! <br> (%s)", result);
    }

}
