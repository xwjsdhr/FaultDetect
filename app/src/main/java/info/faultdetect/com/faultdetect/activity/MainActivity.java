package info.faultdetect.com.faultdetect.activity;

import android.widget.TextView;

import com.hw.common.utils.basicUtils.MLogUtil;
import com.hw.common.utils.basicUtils.SystemUtils;
import com.hw.common.web.FastHttp;

import info.faultdetect.com.faultdetect.MyApplication;
import info.faultdetect.com.faultdetect.R;
import info.faultdetect.com.faultdetect.bean.BaseAjaxCallBack;
import info.faultdetect.com.faultdetect.bean.Req_Regist;
import info.faultdetect.com.faultdetect.bean.Res_UserInfo;

public class MainActivity extends BaseActivity {
    private TextView btn_user_nick_name,btn_user_true_name,btn_user_sex,btn_user_pwd,btn_user_company;
    protected void init() {

    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_main);

        btn_user_nick_name = (TextView) this.findViewById(R.id.btn_user_nick_name);
        btn_user_true_name = (TextView) this.findViewById(R.id.btn_user_true_name);
        btn_user_sex = (TextView) this.findViewById(R.id.btn_user_sex);
        btn_user_pwd = (TextView) this.findViewById(R.id.btn_user_pwd);
        btn_user_company = (TextView) this.findViewById(R.id.btn_user_company);
    }

    private void getRegistHelp(){
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + " register/getRegisterHelpInfo.html?rdid="+ SystemUtils.getDeviceID(MyApplication.getApplication().getApplicationContext()), null,new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {
                Res_UserInfo.UserInfo userInfo = t.getData(Res_UserInfo.class).getUserAccountDescriptor();
                MLogUtil.e("userInfo "+userInfo.getUserid());
            }

            public void onFailure(int status, String msg) {

            }
        });
    }

    private void regist(){
        Req_Regist req_regist = new Req_Regist("aaaa","哈哈","aa123a","a123456","","","20001");
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/register.html", req_regist,new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {
                Res_UserInfo.UserInfo userInfo = t.getData(Res_UserInfo.class).getUserAccountDescriptor();
                MLogUtil.e("userInfo "+userInfo.getUserid());
            }

            public void onFailure(int status, String msg) {

            }
        });
    }


    @Override
    protected void loadData() {
//        regist();
        getRegistHelp();
    }

    @Override
    protected void setEvent() {
        hideTitle();
    }
}
