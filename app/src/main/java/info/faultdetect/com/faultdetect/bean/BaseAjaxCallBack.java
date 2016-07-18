package info.faultdetect.com.faultdetect.bean;

import com.alibaba.fastjson.JSON;
import com.hw.common.ui.dialog.DialogUtil;
import com.hw.common.utils.basicUtils.MLogUtil;
import com.hw.common.utils.basicUtils.StringUtils;
import com.hw.common.web.AjaxCallBack;
import com.hw.common.web.ResponseEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public abstract class BaseAjaxCallBack implements AjaxCallBack {
	@SuppressWarnings("unchecked")
	public void callBack(ResponseEntity status) {
		MLogUtil.e("返回数据：  " + status.toString());
		DialogUtil.dismissLoadingDialog();
		if (status.getStatus() == -1) {
			onFailure(-404, "网络连接失败，请检查网络");
			return;
		}
		
		getResponse(status);

		try {
			JSONObject resultJSON = new JSONObject(status.getContentAsString());

			int statusCode = Integer.valueOf(resultJSON.get("error_code").toString());
			if (statusCode == 0) {
				onSuccess(new Res_BaseBean(resultJSON));
			} else {
				// 自动登录失效，重新登录
				if(statusCode == 2 ){
//					MyApplication.getApplication().setUser(null);
				}
				String message = resultJSON.get("reason").toString();
				onFailure(statusCode, message);
			}

		} catch (Exception e) {
			MLogUtil.e("Exception " + e.toString());
			onFailure(-999, "接收数据异常");
		}
	}
	
	protected void getResponse(ResponseEntity response) {
	}

	public static class Res_BaseBean {
		private JSONObject datas;

		public Res_BaseBean(JSONObject data) {
			this.datas = data;
		}
		
		public String getData(){
			try {
				return datas.get("result").toString();
			} catch (JSONException e) {
				return "";
			}
		}
		
		public <T> List<T> getDataList(Class<T> cls) {
			if (!datas.isNull("result")) {
				try {
					return JSON.parseArray(datas.get("result").toString(), cls);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			return null;
		}

		public <T> List<T> getJsonList(Class<T> cls,String json) {
				try {
					return JSON.parseArray(json, cls);
				} catch (Exception e) {
					e.printStackTrace();
				}
			return null;
		}
		
		public <T> List<T> getDataList(Class<T> cls, String param) {
			if (StringUtils.isEmpty(param)) {
				param = "result";
			}

			if (!datas.isNull(param)) {
				try {
					return JSON.parseArray(datas.get(param).toString(), cls);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			return null;
		}

		public <T> T getData(Class<T> cls, String param) {
			if (StringUtils.isEmpty(param)) {
				param = "result";
			}

			if (!datas.isNull(param)) {
				try {
					return JSON.parseObject(datas.get(param).toString(), cls);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			return null;
		}
		
		public <T> T getData(Class<T> cls) {
			if (!datas.isNull("result")) {
				try {
					return JSON.parseObject(datas.get("result").toString(), cls);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			return null;
		}

		public Res_BaseBean() {
		}
	}

	public boolean stop() {
		return false;
	}

	public abstract void onSuccess(Res_BaseBean t);

	public abstract void onFailure(int status, String msg);
}
