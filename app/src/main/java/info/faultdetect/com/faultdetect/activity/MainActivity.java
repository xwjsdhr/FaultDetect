package info.faultdetect.com.faultdetect.activity;

import com.hw.common.utils.basicUtils.MLogUtil;
import com.hw.common.utils.basicUtils.SystemUtils;
import com.hw.common.web.FastHttp;

import info.faultdetect.com.faultdetect.MyApplication;
import info.faultdetect.com.faultdetect.R;
import info.faultdetect.com.faultdetect.bean.BaseAjaxCallBack;
import info.faultdetect.com.faultdetect.bean.Req_Login;
import info.faultdetect.com.faultdetect.bean.Req_Regist;
import info.faultdetect.com.faultdetect.bean.Res_UserInfo;

public class MainActivity extends BaseActivity {
    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_main);
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

    private void login(){
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "login.html", new Req_Login("aa123a","a123456"),new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {

            }

            public void onFailure(int status, String msg) {

            }
        });
    }

    @Override
    protected void loadData() {
//        regist();
        getRegistHelp();
        login();
    }

    @Override
    protected void setEvent() {

    }
}
