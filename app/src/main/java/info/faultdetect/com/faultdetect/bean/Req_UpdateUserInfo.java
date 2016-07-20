package info.faultdetect.com.faultdetect.bean;

import info.faultdetect.com.faultdetect.MyApplication;

/**
 * Created by nicai on 2016/7/20.
 * email：930324291@qq.com
 */
public class Req_UpdateUserInfo extends Req_BaseBean{
    private String userid = MyApplication.getApplication().getUserId();
    private String userName;
    private String nickName;
    private String xb;
    private String organizationId;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
