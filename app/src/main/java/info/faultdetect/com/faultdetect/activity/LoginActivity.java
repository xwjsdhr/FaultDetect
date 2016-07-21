package info.faultdetect.com.faultdetect.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hw.common.ui.dialog.DialogUtil;
import com.hw.common.utils.basicUtils.MLogUtil;
import com.hw.common.utils.basicUtils.SharedPreferenceUtil;
import com.hw.common.utils.basicUtils.StringUtils;
import com.hw.common.web.FastHttp;

import info.faultdetect.com.faultdetect.MyApplication;
import info.faultdetect.com.faultdetect.R;
import info.faultdetect.com.faultdetect.bean.BaseAjaxCallBack;
import info.faultdetect.com.faultdetect.bean.Req_Login;
import info.faultdetect.com.faultdetect.bean.Res_Rsid;
import info.faultdetect.com.faultdetect.bean.UserInfo;
import info.faultdetect.com.faultdetect.utils.ToastUtil;

/**
 * Created by nicai on 2016/7/19.
 * email：930324291@qq.com
 */
public class LoginActivity  extends BaseActivity implements View.OnClickListener{
    private EditText edt_login_name,edt_login_pwd;
    private Button btn_login;
    private TextView btn_login_forget_pwd,btn_login_regist;

    protected void init() {

    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_login);

        edt_login_name = (EditText) this.findViewById(R.id.edt_login_name);
        edt_login_pwd = (EditText) this.findViewById(R.id.edt_login_pwd);
        btn_login = (Button) this.findViewById(R.id.btn_login);
        btn_login_forget_pwd = (TextView) this.findViewById(R.id.btn_login_forget_pwd);
        btn_login_regist = (TextView) this.findViewById(R.id.btn_login_regist);

        hideTitle();
    }

    // 登录
    private void login(){
        String name = edt_login_name.getText().toString().trim();
        String pwd =  edt_login_pwd.getText().toString().trim();
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)){
            ToastUtil.showShort("请输入用户名或密码");
            return;
        }

        DialogUtil.showLoadingDialog(this);
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "login.html", new Req_Login(name,pwd),new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {
                Res_Rsid res_rsid = t.getData(Res_Rsid.class);
                MLogUtil.e(res_rsid.getRsid());
                SharedPreferenceUtil.saveSharedPreString(mContext,"rsid",res_rsid.getRsid());

                MyApplication.getApplication().setUserInfo(t.getData(UserInfo.class,"user"));

                UserInfo userInfo= MyApplication.getApplication().getUserInfo();
                MLogUtil.e("userInfo "+userInfo.getUserid());

                startActivity(MainActivity.class);
                finish();
            }

            public void onFailure(int status, String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setEvent() {
        btn_login.setOnClickListener(this);
        btn_login_regist.setOnClickListener(this);
        btn_login_forget_pwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_login_forget_pwd:
                startActivity(ForgetPwdActivity.class);
                break;
            case R.id.btn_login_regist:
                startActivity(RegistActivity.class);
                break;
            default:
                break;
        }
    }
}
