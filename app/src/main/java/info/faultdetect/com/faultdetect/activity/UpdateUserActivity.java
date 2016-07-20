package info.faultdetect.com.faultdetect.activity;

import android.view.View;
import android.widget.EditText;

import info.faultdetect.com.faultdetect.R;
import info.faultdetect.com.faultdetect.utils.Constant;

/**
 * Created by nicai on 2016/7/19.
 * email：930324291@qq.com
 */
public class UpdateUserActivity extends BaseActivity{
    private EditText edt_update_user;
    private int activityType = 0;

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

    @Override
    protected void setEvent() {
        setRightButton("完成", new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}
