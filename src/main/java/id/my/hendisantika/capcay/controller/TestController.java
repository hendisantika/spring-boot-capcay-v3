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
     * reCAPTCHA v2 验证
     * <p>
     * 通过配置注解 @VerifyCode 中的参数 store 和 verifier 来指定储存器和验证器。
     * "reCaptchaStore" 为 reCAPTCHA v2 的存储器。
     *
     * @param result 验证通过后，传入详细信息
     * @return
     */
    @GetMapping("/checkV2")
    @VerifyCode(store = @Store("reCaptchaStore"), verifier = @Verifier("reCaptchaVerifier"))
    public String index(@CodeValue ReCaptchaResult result) {
        log.info(result.toString());
        return String.format("Hello World! <br> (%s)", result);
    }

    /**
     * reCAPTCHA v3 验证
     * <p>
     * "reCaptchaV3Store" 为 reCAPTCHA v3 的存储器。
     * 注解 ”@Parameter“ 提供了名为 "SECRET" 的参数，值为 reCAPTCHA 的服务端密钥。未指定此参数时将从配置文件加载默认的服务的密钥。
     *
     * @param result 验证通过后，传入详细信息
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
