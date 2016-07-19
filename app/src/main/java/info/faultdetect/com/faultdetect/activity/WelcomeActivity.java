package info.faultdetect.com.faultdetect.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by nicai on 2016/7/19.
 * email：930324291@qq.com
 */
public class WelcomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

}
