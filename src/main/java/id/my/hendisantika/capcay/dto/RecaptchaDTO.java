package id.my.hendisantika.capcay.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : capcay
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/07/24
 * Time: 09.50
 * To change this template use File | Settings | File Templates.
 */
public class RecaptchaDTO {
    private boolean success;
    private double score;
    private String action;
    private Date challenge_ts;
    private String hostname;
    private ArrayList<String> error_codes;
}
