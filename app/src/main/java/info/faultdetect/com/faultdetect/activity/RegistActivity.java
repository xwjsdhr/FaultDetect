package info.faultdetect.com.faultdetect.activity;

import android.widget.Button;
import android.widget.EditText;

import info.faultdetect.com.faultdetect.R;

/**
 * Created by nicai on 2016/7/18.
 */
public class RegistActivity extends BaseActivity {
    private EditText et_register_phone, et_register_verifyCode, et_register_pwd, et_register_pwd2;
    private Button btn_register_get_verifyCode, btn_register_ok;

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_register);
        et_register_phone = (EditText) this.findViewById(R.id.et_register_phone);
        et_register_verifyCode = (EditText) this.findViewById(R.id.et_register_verifyCode);
        et_register_pwd = (EditText) this.findViewById(R.id.et_register_pwd);
        et_register_pwd2 = (EditText) this.findViewById(R.id.et_register_pwd2);
        btn_register_get_verifyCode = (Button) this.findViewById(R.id.btn_register_get_verifyCode);
        btn_register_ok = (Button) this.findViewById(R.id.btn_register_ok);

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setEvent() {

    }
}
