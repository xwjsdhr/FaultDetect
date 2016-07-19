package info.faultdetect.com.faultdetect.utils;

import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;

public class TimeCount extends CountDownTimer {
	private EditText et_phone;
	private TextView btn;

	public TimeCount(long paramLong1, long paramLong2, TextView paramButton, EditText paramEditText) {
		super(paramLong1, paramLong2);
		this.btn = paramButton;
		this.et_phone = paramEditText;
	}

	public void onFinish() {
		this.btn.setText("重新验证");
		this.btn.setEnabled(true);
		this.et_phone.setEnabled(true);
	}

	public void onTick(long paramLong) {
		this.btn.setEnabled(false);
		this.btn.setText(paramLong / 1000L + "秒");
	}
}
