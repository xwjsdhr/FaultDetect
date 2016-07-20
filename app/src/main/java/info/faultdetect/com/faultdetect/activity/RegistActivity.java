package info.faultdetect.com.faultdetect.activity;

import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hw.common.ui.dialog.DialogUtil;
import com.hw.common.utils.basicUtils.CheckUtils;
import com.hw.common.utils.basicUtils.StringUtils;
import com.hw.common.utils.basicUtils.SystemUtils;
import com.hw.common.web.FastHttp;

import info.faultdetect.com.faultdetect.MyApplication;
import info.faultdetect.com.faultdetect.R;
import info.faultdetect.com.faultdetect.bean.BaseAjaxCallBack;
import info.faultdetect.com.faultdetect.bean.Req_Regist;
import info.faultdetect.com.faultdetect.bean.Res_UserInfo;
import info.faultdetect.com.faultdetect.bean.UserInfo;
import info.faultdetect.com.faultdetect.utils.TimeCount;
import info.faultdetect.com.faultdetect.utils.ToastUtil;

/**
 * Created by nicai on 2016/7/19.
 * email：930324291@qq.com
 */
public class RegistActivity extends BaseActivity {
    private EditText et_register_phone, et_register_verifyCode, et_register_pwd, et_register_pwd2;
    private Button btn_register_ok;
    private TextView btn_register_get_verifyCode;
    private TimeCount timeCount;

    @Override
    protected void init() {
        setTitle("注册");
    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_register);
        et_register_phone = (EditText) this.findViewById(R.id.et_register_phone);
        et_register_verifyCode = (EditText) this.findViewById(R.id.et_register_verifyCode);
        et_register_pwd = (EditText) this.findViewById(R.id.et_register_pwd);
        et_register_pwd2 = (EditText) this.findViewById(R.id.et_register_pwd2);
        btn_register_get_verifyCode = (TextView) this.findViewById(R.id.btn_register_get_verifyCode);
        btn_register_ok = (Button) this.findViewById(R.id.btn_register_ok);

        timeCount = new TimeCount(60000, 1000, btn_register_get_verifyCode, et_register_verifyCode);

    }

    private void regist() {
        String tel = et_register_phone.getText().toString().trim();
        String code = et_register_verifyCode.getText().toString().trim();
        String pwd = et_register_pwd.getText().toString().trim();
        String pwd2 = et_register_pwd2.getText().toString().trim();

        if(!CheckUtils.isMobile(tel)){
            ToastUtil.showShort("请输入正确的手机号");
            return;
        }

        if(StringUtils.isEmpty(code)){
            ToastUtil.showShort("请输入验证码");
            return;
        }

        if(StringUtils.isEmpty(pwd) || StringUtils.isEmpty(pwd2) || !pwd.equals(pwd2)){
            ToastUtil.showShort("请输入正确的密码");
            return;
        }

        DialogUtil.showLoadingDialog(this);
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/register.html", new Req_Regist(tel,pwd,code), new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {
                ToastUtil.showShort("恭喜您注册成功");
                UserInfo userInfo = t.getData(Res_UserInfo.class).getUserAccountDescriptor();
                finish();
            }

            public void onFailure(int status, String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    // 发送验证码
    private void sendVerCode() {
        String tel = et_register_phone.getText().toString().trim();
        if (!CheckUtils.isMobile(tel)) {
            ToastUtil.showShort("请输入正确的手机号");
            return;
        }

        timeCount.start();
        DialogUtil.showLoadingDialog(this, "发送中..");
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/sendCode.html?tel="+tel, null, new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {
                try {
                    SystemUtils.SmsContent content = new SystemUtils.SmsContent(RegistActivity.this, new Handler(), et_register_verifyCode, "云短信", new SystemUtils.onSmsListener() {
                        public void onSmsSuccess() {
                            timeCount.cancel();
                            timeCount.onFinish();
                        }

                        public void onSmsonFailure() {

                        }
                    });
                    mContext.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
                } catch (Exception e) {
                    timeCount.cancel();
                    timeCount.onFinish();
                }
            }

            public void onFailure(int status, String msg) {
                ToastUtil.showShort(msg);
                timeCount.cancel();
                timeCount.onFinish();
            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setEvent() {
        btn_register_get_verifyCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendVerCode();
            }
        });

        btn_register_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                regist();
            }
        });
    }
}
