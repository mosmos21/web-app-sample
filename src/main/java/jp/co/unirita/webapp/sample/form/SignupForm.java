package jp.co.unirita.webapp.sample.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupForm {
    private String userName;
    private String password1;
    private String password2;
}
