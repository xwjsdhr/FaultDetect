package info.faultdetect.com.faultdetect.activity;

import android.widget.Button;
import android.widget.EditText;

import info.faultdetect.com.faultdetect.R;

/**
 * Created by xiaweijia on 16/7/19.
 */
public class UpdatePwdActivity extends BaseActivity {

    private EditText et_update_pwd_phone, et_update_pwd_verifyCode, et_update_pwd_pwd;
    private Button btn_update_pwd_get_verifyCode, btn_update_pwd_ok;

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_update_pwd);
        et_update_pwd_phone = (EditText) this.findViewById(R.id.et_update_pwd_phone);
        et_update_pwd_verifyCode = (EditText) this.findViewById(R.id.et_update_pwd_verifyCode);
        et_update_pwd_pwd = (EditText) this.findViewById(R.id.et_update_pwd_pwd);
        btn_update_pwd_get_verifyCode = (Button) this.findViewById(R.id.btn_update_pwd_get_verifyCode);
        btn_update_pwd_ok = (Button) this.findViewById(R.id.btn_update_pwd_ok);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setEvent() {

    }
}
