package info.faultdetect.com.faultdetect.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hw.common.ui.dialog.DialogUtil;
import com.hw.common.utils.basicUtils.SystemUtils;
import com.hw.common.web.FastHttp;

import info.faultdetect.com.faultdetect.MyApplication;
import info.faultdetect.com.faultdetect.R;
import info.faultdetect.com.faultdetect.bean.BaseAjaxCallBack;
import info.faultdetect.com.faultdetect.bean.UserInfo;
import info.faultdetect.com.faultdetect.utils.Constant;
import info.faultdetect.com.faultdetect.utils.ToastUtil;

/**
 * Created by nicai on 2016/7/19.
 * email：930324291@qq.com
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{
    private TextView btn_user_nick_name,btn_user_true_name,btn_user_sex,btn_user_pwd,btn_user_company;
    private RadioGroup rb_sex;
    private Dialog sexDialog;

    protected void init() {
        initDialog();
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

    private void initDialog(){
        View contentView = View.inflate(this, R.layout.dialog_update_sex, null);
        rb_sex = (RadioGroup) contentView.findViewById(R.id.rb_sex);

        sexDialog = new Dialog(this);
        sexDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 0, 0, 0);
        sexDialog.setContentView(contentView, layoutParams);
        sexDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void getRegistHelp() {
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/getRegisterHelpInfo.html?rdid=" + SystemUtils.getDeviceID(MyApplication.getApplication().getApplicationContext()), null, new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {

            }

            public void onFailure(int status, String msg) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        UserInfo userInfo= MyApplication.getApplication().getUserInfo();
        if(userInfo != null){
            btn_user_nick_name.setText(userInfo.getNickName());
            btn_user_true_name.setText(userInfo.getUserName());
            btn_user_sex.setText(userInfo.getXb());
            if(userInfo.getBelongedOrganizations() != null){
                btn_user_company.setText(userInfo.getBelongedOrganizations().getName());
            }
        }

//        DialogUtil.showLoadingDialog(this);
//        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/GetRegisterInfomation.html?userid=" + MyApplication.getApplication().getUserId(), null, new BaseAjaxCallBack() {
//            public void onSuccess(Res_BaseBean t) {
//
//            }
//
//            public void onFailure(int status, String msg) {
//
//            }
//        });

    }

    // 修改用户信息
    private void setUserInfo(final UserInfo userInfo){
        DialogUtil.showLoadingDialog(this);
        FastHttp.ajaxGetByBean(MyApplication.SERVER_URL + "register/modify.html", userInfo, new BaseAjaxCallBack() {
            public void onSuccess(Res_BaseBean t) {
                MyApplication.getApplication().setUserInfo(userInfo);
                ToastUtil.showShort("恭喜您，修改成功");
                finish();
            }

            public void onFailure(int status, String msg) {
                ToastUtil.showShort(msg);
            }
        });

    }

    @Override
    protected void loadData() {
        getRegistHelp();
    }

    @Override
    protected void setEvent() {
        hideTitle();

        rb_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_sex_man:
                        btn_user_sex.setText("男");
                        break;
                    case R.id.rb_sex_woman:
                        btn_user_sex.setText("女");
                        break;
                    default:
                        break;
                }
                sexDialog.dismiss();
                UserInfo userInfo = MyApplication.getApplication().getUserInfo();
                if(userInfo == null){
                    ToastUtil.showShort("暂无用户信息，请重新登录");
                    return;
                }
                userInfo.setXb(btn_user_sex.getText().toString().trim());
                setUserInfo(userInfo);
            }
        });

        btn_user_nick_name.setOnClickListener(this);
        btn_user_true_name.setOnClickListener(this);
        btn_user_sex.setOnClickListener(this);
        btn_user_pwd.setOnClickListener(this);
        btn_user_company.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_user_nick_name:
                Bundle bundle = new Bundle();
                bundle.putInt("UpdateUserActivity", Constant.UPDATE_NICK_NAME);
                startActivity(UpdateUserActivity.class,bundle);
                break;
            case R.id.btn_user_true_name:
                Bundle bde = new Bundle();
                bde.putInt("UpdateUserActivity",Constant.UPDATE_TRUE_NAME);
                startActivity(UpdateUserActivity.class,bde);
                break;
            case R.id.btn_user_sex:
                sexDialog.show();
                break;
            case R.id.btn_user_pwd:
                startActivity(ForgetPwdActivity.class);
                break;
            case R.id.btn_user_company:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch(resultCode){
//            case Constant.UPDATE_NICK_NAME:
//                break;
//            case Constant.UPDATE_TRUE_NAME:
//                break;
//            default:
//                break;
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
