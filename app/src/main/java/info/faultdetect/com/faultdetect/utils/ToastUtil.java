package info.faultdetect.com.faultdetect.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import info.faultdetect.com.faultdetect.MyApplication;


/**
 * Toast统一管理类
 *
 * @author way
 */
public class ToastUtil {
    // Toast
    private static Toast toast;

    public static void showImgToast(String message,int res){
        if (null == toast) {
            toast = Toast.makeText(MyApplication.getApplication(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(MyApplication.getApplication());
        imageCodeProject.setImageResource(res);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }
    /**
     * 短时间显示Toast
     * @param message
     */
    public static void showShort(String message) {
        if (null == toast) {
            toast = Toast.makeText(MyApplication.getApplication(), message, Toast.LENGTH_SHORT);
//             toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
    
    /**
     * 短时间显示资源文件Toast
     *
     * @param context
     */
    public static void show(Context context, int paramInt) {
        if (null == toast) {
            toast = Toast.makeText(context, MyApplication.getApplication().getResources().getString(paramInt), Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(MyApplication.getApplication().getResources().getString(paramInt));
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(String message) {
        if (null == toast) {
            toast = Toast.makeText(MyApplication.getApplication(), message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(String message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(MyApplication.getApplication(), message, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }


    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param duration
     */
    public static void show(Context context, int paramInt, int duration) {
        if (null == toast) {
            toast = Toast.makeText(context, MyApplication.getApplication().getResources().getString(paramInt), duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(MyApplication.getApplication().getResources().getString(paramInt));
        }
        toast.show();
    }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}

//public class ToastUtil {
//	public static void showLongToast(int paramInt) {
//		Toast.makeText(MyApplication.getApplication(), MyApplication.getApplication().getResources().getString(paramInt), Toast.LENGTH_LONG).show();
//	}
//
//	public static void showLongToast(String paramString) {
//		Toast.makeText(MyApplication.getApplication(), paramString, Toast.LENGTH_LONG).show();
//	}
//
//	public static void showShortToast(int paramInt) {
//		Toast.makeText(MyApplication.getApplication(), MyApplication.getApplication().getResources().getString(paramInt), Toast.LENGTH_SHORT).show();
//	}
//
//	public static void showShortToast(String paramString) {
//		Toast.makeText(MyApplication.getApplication(), paramString, Toast.LENGTH_SHORT).show();
//	}
//}

