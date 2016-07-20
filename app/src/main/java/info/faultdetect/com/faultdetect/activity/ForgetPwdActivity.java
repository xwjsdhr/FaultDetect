package info.faultdetect.com.faultdetect.activity;

import android.widget.Button;
import android.widget.EditText;

import info.faultdetect.com.faultdetect.R;

/**
 * 忘记密码
 * Created by xiaweijia on 16/7/19.
 */
public class ForgetPwdActivity extends BaseActivity {
    private EditText et_forget_pwd_phone, et_forget_pwd_verifyCode, et_forget_pwd_pwd;
    private Button btn_forget_pwd_get_verifyCode, btn_forget_pwd_ok;

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_forget_pwd);
        et_forget_pwd_phone = (EditText) this.findViewById(R.id.et_forget_pwd_phone);
        et_forget_pwd_verifyCode = (EditText) this.findViewById(R.id.et_forget_pwd_verifyCode);
        et_forget_pwd_pwd = (EditText) this.findViewById(R.id.et_forget_pwd_pwd);
        btn_forget_pwd_get_verifyCode = (Button) this.findViewById(R.id.btn_forget_pwd_get_verifyCode);
        btn_forget_pwd_ok = (Button) this.findViewById(R.id.btn_forget_pwd_ok);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setEvent() {

    }
}
