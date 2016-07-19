package info.faultdetect.com.faultdetect.activity;

import android.widget.EditText;

import info.faultdetect.com.faultdetect.R;

/**
 * Created by nicai on 2016/7/19.
 * email：930324291@qq.com
 */
public class UpdateUserActivity extends BaseActivity{
    private EditText edt_update_user;
    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        addContentView(R.layout.activity_update_user);

        edt_update_user = (EditText) this.findViewById(R.id.edt_update_user);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setEvent() {

    }
}
