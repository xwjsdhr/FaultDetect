package info.faultdetect.com.faultdetect.bean;

import com.hw.common.utils.basicUtils.SharedPreferenceUtil;
import com.hw.common.utils.basicUtils.SystemUtils;

import info.faultdetect.com.faultdetect.MyApplication;

/**
 * Created by nicai on 2016/7/18.
 */
public class Req_BaseBean {
    private String rdid = SystemUtils.getDeviceID(MyApplication.getApplication().getApplicationContext());
    private String rsid = SharedPreferenceUtil.getSharedPreString(MyApplication.getApplication().getApplicationContext(),"rsid");

    public String getRsid() {
        return rsid;
    }

    public void setRsid(String rsid) {
        this.rsid = rsid;
    }

    public String getRdid() {
        return rdid;
    }

    public void setRdid(String rdid) {
        this.rdid = rdid;
    }
}
