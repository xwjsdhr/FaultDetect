package info.faultdetect.com.faultdetect.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hw.common.ui.SystemBarTintManager;
import com.hw.common.ui.emptyview.VaryViewHelperController;
import com.hw.common.utils.basicUtils.FileUtils;
import com.hw.common.utils.basicUtils.ViewUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import info.faultdetect.com.faultdetect.MyApplication;
import info.faultdetect.com.faultdetect.R;

/**
 * Created by nicai on 2016/7/19.
 * email：930324291@qq.com
 */
@SuppressLint("NewApi")
public abstract class BaseActivity extends FragmentActivity {
    private View title;
    protected BaseActivity mContext;
    protected RelativeLayout content;
    protected LinearLayout btn_base_right, btn_base_back;
    private TextView tv_base_title, tv_base_right, iv_base_nodata, tv_base_back;
    protected VaryViewHelperController mEmptyViewController;
    protected BGARefreshLayout mRefreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加入ApplicationEx,关闭应用的时候用来finish
        MyApplication.activities.add(this);

        // 初始化内部开始--------------------------------------start
        setContentView(R.layout.activity_base);
        mContext = BaseActivity.this;

        btn_base_back = (LinearLayout) this.findViewById(R.id.btn_base_back);
        btn_base_back.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                BaseActivity.this.onBackPressed();
            }
        });
        btn_base_right = (LinearLayout) this.findViewById(R.id.btn_base_right);
        tv_base_title = (TextView) this.findViewById(R.id.tv_base_title);
        tv_base_right = (TextView) this.findViewById(R.id.tv_base_right);
        tv_base_back = (TextView) this.findViewById(R.id.tv_base_back);
        content = (RelativeLayout) this.findViewById(R.id.rl_base_content);
        iv_base_nodata = (TextView) this.findViewById(R.id.iv_base_nodata);
        title = this.findViewById(R.id.rl_base_top);
        restoreSaveInstance(savedInstanceState);
//        setTransparentStatusBar(R.color.cl_main);
        mEmptyViewController = new VaryViewHelperController(content);
        // 初始化内部结束--------------------------------------end

        // 为了规范而使用的函数
        init();
        initView();
        loadData();
        setEvent();
    }

    // 初始化下拉刷新控件
    protected BGAMoocStyleRefreshViewHolder getRefreshViewHolder(){
        // 设置下拉刷新和上拉加载更多的风格
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(this, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.drawable.ic_launcher);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.cl_main);
        return moocStyleRefreshViewHolder;
    }

    // 检测是否登录，未登录跳登录界面
    protected void checkLogin(){
//        if(MyApplication.getApplication().getUser() == null){
//            startActivity(LoginActivity.class);
//            ToastUtil.showShort("请先登录");
//            return;
//        }
    }


    // AutoLayout布局开始--------------------------------------start
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }
    // AutoLayout布局结束--------------------------------------end


    // 空函数,只是为了规范
    protected abstract void init();

    // 空函数,只是为了规范
    protected abstract void initView();

    // 空函数,只是为了规范
    protected abstract void loadData();

    // 空函数,只是为了规范
    protected abstract void setEvent();

    // 设置透明状态栏
    protected void setTransparentStatusBar(int id) {
        if (Build.VERSION.SDK_INT == 20 || Build.VERSION.SDK_INT == 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setTintColor(ViewUtils.getColor(mContext, id));
        } else if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ViewUtils.getColor(mContext, id));
        }
    }

    protected void restoreSaveInstance(Bundle savedInstanceState) {
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }

    /**
     * 设置标题文字
     **/
    protected void setTitle(String Name) {
        tv_base_title.setText(Name);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        // overridePendingTransition(android.R.anim.fade_in,
        // android.R.anim.fade_out);
    }

    protected void startActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 销毁方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从ApplicationEx移除,因为已经销毁所以关闭的时候不用关闭了
        MyApplication.activities.remove(this);
    }

    /**
     * 返回方法
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 一个简单设置右边按钮的方法
     *
     * @param listener
     */
    protected void setRightButton(String str, OnClickListener listener) {
        tv_base_right.setText(str);
        btn_base_right.setOnClickListener(listener);
        btn_base_right.setVisibility(View.VISIBLE);
        ViewUtils.setBackgroundDrawable(tv_base_right, null);
    }

    /**
     * 设定按钮文字颜色
     */
    protected void setRightButtonColor(String text, int id) {
        tv_base_right.setText(text);
        if (id != -1) tv_base_right.setTextColor(ViewUtils.getColor(mContext,id));
    }

    protected void setLeftButtonColor(String text, int id) {
        tv_base_back.setText(text);
        if (id != -1) tv_base_back.setTextColor(ViewUtils.getColor(mContext,id));
    }

    protected void setLeftButtonDrawable(int res, OnClickListener listener) {
        ViewUtils.setBackgroundDrawable(tv_base_back, getResources().getDrawable(res));
        btn_base_back.setOnClickListener(listener);
        btn_base_back.setVisibility(View.VISIBLE);
    }

    protected void setRightButtonDrawable(int res, OnClickListener listener) {
        ViewUtils.setBackgroundDrawable(tv_base_right, getResources().getDrawable(res));
        btn_base_right.setOnClickListener(listener);
        btn_base_right.setVisibility(View.VISIBLE);
    }

    protected void hideTitle() {
        title.setVisibility(View.GONE);
    }

    protected void showTitle() {
        title.setVisibility(View.VISIBLE);
    }

    /**
     * 显示返回按钮
     */
    protected void showBackButton() {
        btn_base_back.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏返回按钮
     */
    protected void hideBackButton() {
        btn_base_back.setVisibility(View.GONE);
    }

    /**
     * 隐藏右边按钮
     */
    protected void hideRightButton() {
        btn_base_right.setVisibility(View.GONE);
    }

    /**
     * 添加主体布局文件
     *
     * @param resId
     */
    protected void addContentView(int resId) {
        View view = LayoutInflater.from(this).inflate(resId, null);
        content.addView(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * 弹出一个信息
     *
     * @param msg
     */
    protected void showTip(final String msg) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 显示进度条对话框
     */
    protected void showProgressDialog() {
        mEmptyViewController.showLoading(null);
    }

    /**
     * 隐藏进度条对话框
     */
    protected void dismissProgressDialog() {
        showContent();
    }

    /**
     * 显示没有数据的提示
     */
    protected void showNoData() {
        showNoData(getString(com.hw.common.R.string.common_empty_msg));
    }

    protected void showNoData(String msg) {
        mEmptyViewController.showEmpty(msg, new OnClickListener() {
            public void onClick(View v) {
                loadData();
            }
        });
    }

    /**
     * 显示没有数据的提示
     */
    protected void hideNoData() {
        showContent();
    }

    /**
     * 显示数据错误的提示
     */
    protected void showError(String errorMsg) {
        mEmptyViewController.showError(errorMsg, new OnClickListener() {
            public void onClick(View v) {
                loadData();
            }
        });
    }

    protected void showError(String errorMsg,OnClickListener onClickListener) {
        mEmptyViewController.showError(errorMsg, onClickListener);
    }

    protected void showError(int status, String errorMsg) {
        if (status == -404) {
            showNetError();
        } else {
            mEmptyViewController.showError(errorMsg, new OnClickListener() {
                public void onClick(View v) {
                    loadData();
                }
            });
        }
    }

    /**
     * 显示网络连接错误的提示
     */
    protected void showNetError() {
        mEmptyViewController.showNetworkError(new OnClickListener() {
            public void onClick(View v) {
                loadData();
            }
        });
    }

    /**
     * 显示文本内容
     */
    protected void showContent() {
        mEmptyViewController.restore();
    }

    /**
     * 应用程序退出
     */
    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            moveTaskToBack(true);
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && this instanceof MainActivity) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                FileUtils.deleteFile(MyApplication.CACHE_TEMP);
//                MyApplication.getApplication().getDb().clearAll(Res_ParkingLot.class);
                ((MyApplication) getApplication()).exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}