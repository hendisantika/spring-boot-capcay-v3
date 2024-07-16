package id.my.hendisantika.capcay;

import cn.dustlight.captcha.annotations.EnableCaptcha;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaptcha
public class CapcayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapcayApplication.class, args);
    }

}
