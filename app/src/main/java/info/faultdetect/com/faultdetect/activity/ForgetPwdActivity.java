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
import info.faultdetect.com.faultdetect.bean.Req_UpdatePwd;
import info.faultdetect.com.faultdetect.bean.Res_UserInfo;
import info.faultdetect.com.faultdetect.bean.UserInfo;
import info.faultdetect.com.faultdetect.utils.TimeCount;
import info.faultdetect.com.faultdetect.utils.ToastUtil;

/**
 * 忘记密码
 */
public class ForgetPwdActivity extends BaseActivity {
    private EditText et_forget_pwd_phone, et_forget_pwd_verifyCode, et_forget_pwd_pwd;
    private Button btn_forget_pwd_ok;
    private TextView btn_forget_pwd_get_verifyCode;
    private TimeCount timeCount;

    @Override
    protected void init() {
        setTitle("修改密码");
    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_forget_pwd);
        et_forget_pwd_phone = (EditText) this.findViewById(R.id.et_forget_pwd_phone);
        et_forget_pwd_verifyCode = (EditText) this.findViewById(R.id.et_forget_pwd_verifyCode);
        et_forget_pwd_pwd = (EditText) this.findViewById(R.id.et_forget_pwd_pwd);
        btn_forget_pwd_get_verifyCode = (Button) this.findViewById(R.id.btn_forget_pwd_get_verifyCode);
        btn_forget_pwd_ok = (Button) this.findViewById(R.id.btn_forget_pwd_ok);

        timeCount = new TimeCount(60000, 1000, btn_forget_pwd_get_verifyCode, et_forget_pwd_verifyCode);
    }

    private void updatePwd() {
        String tel = et_forget_pwd_phone.getText().toString().trim();
        String code = et_forget_pwd_verifyCode.getText().toString().trim();
        String pwd = et_forget_pwd_pwd.getText().toString().trim();

        if(!CheckUtils.isMobile(tel)){
            ToastUtil.showShort("请输入正确的手机号");
            return;
        }

        if(StringUtils.isEmpty(code)){
            ToastUtil.showShort("请输入验证码");
            return;
        }

        if(StringUtils.isEmpty(pwd)){
            ToastUtil.showShort("请输入正确的密码");
            return;
        }

        DialogUtil.showLoadingDialog(this);
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/modifyPassword.html", new Req_UpdatePwd(tel,pwd,code), new BaseAjaxCallBack() {
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
        String tel = et_forget_pwd_phone.getText().toString().trim();
        if (!CheckUtils.isMobile(tel)) {
            ToastUtil.showShort("请输入正确的手机号");
            return;
        }

        timeCount.start();
        DialogUtil.showLoadingDialog(this, "发送中..");
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/sendCode.html?tel="+tel, null, new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {
                try {
                    SystemUtils.SmsContent content = new SystemUtils.SmsContent(ForgetPwdActivity.this, new Handler(), et_forget_pwd_verifyCode, "云短信", new SystemUtils.onSmsListener() {
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
        btn_forget_pwd_get_verifyCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendVerCode();
            }
        });

        btn_forget_pwd_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updatePwd();
            }
        });
    }
}
