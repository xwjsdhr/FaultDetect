package info.faultdetect.com.faultdetect.activity;

import android.view.View;
import android.widget.EditText;

import com.hw.common.ui.dialog.DialogUtil;
import com.hw.common.utils.basicUtils.StringUtils;
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
public class UpdateUserActivity extends BaseActivity{
    private EditText edt_update_user;
    private int activityType = 0;
    private UserInfo userInfo;

    protected void init() {
        activityType = getIntent().getIntExtra("UpdateUserActivity",0);
        if(activityType == 0){
            return;
        }
    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_update_user);

        edt_update_user = (EditText) this.findViewById(R.id.edt_update_user);
    }

    @Override
    protected void loadData() {
        if(activityType == Constant.UPDATE_NICK_NAME){
            setTitle("设置昵称");
            edt_update_user.setHint("请输入昵称");
        }else if(activityType == Constant.UPDATE_TRUE_NAME){
            setTitle("设置真实姓名");
            edt_update_user.setHint("请输入真实姓名");
        }
    }

    private void updateInfo(){
        DialogUtil.showLoadingDialog(this);
        String name = edt_update_user.getText().toString().trim();
        if(StringUtils.isEmpty(name)){
            ToastUtil.showShort("请输入名称");
            return;
        }

        userInfo = MyApplication.getApplication().getUserInfo();
        if(userInfo == null){
            ToastUtil.showShort("暂无用户信息，请重新登录");
            finish();
            return;
        }

        if(activityType == Constant.UPDATE_NICK_NAME){
            userInfo.setNickName(name);
        }else{
            userInfo.setUserName(name);
        }

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
    protected void setEvent() {
        setRightButton("完成", new View.OnClickListener() {
            public void onClick(View v) {
                updateInfo();
            }
        });
    }
}
