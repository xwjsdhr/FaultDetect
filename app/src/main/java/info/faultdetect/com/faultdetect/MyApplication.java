package info.faultdetect.com.faultdetect;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.hw.common.db.DbManage;
import com.hw.common.utils.basicUtils.CommonUtil;
import com.hw.common.utils.basicUtils.FileUtils;
import com.hw.common.utils.basicUtils.SharedPreferenceUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    public static String FILE_TEMP,PIC_TEMP,CACHE_TEMP,OSS_TEMP;
    public static String SERVER_URL;
    public static List<Activity> activities;
    private DbManage db;

    public void onCreate() {
        super.onCreate();
        MyApplication.FILE_TEMP = Environment.getExternalStorageDirectory() + "/faultDetect/";
        MyApplication.PIC_TEMP = Environment.getExternalStorageDirectory() + "/faultDetect/pic/";
        MyApplication.CACHE_TEMP = Environment.getExternalStorageDirectory() + "/faultDetect/cache/";
        MyApplication.OSS_TEMP = Environment.getExternalStorageDirectory() + "/faultDetect/oss/";

        activities = new ArrayList<Activity>();
        myApplication = this;
        //创建文件夹
        createDirs();
        //设置开发环境
        setIsDebug(false);
        // 初始化数据库
//        DbManage.setDbAdapter(new WitParkDbAdapter());
        //初始化图片控件
        initImageLoader(getApplicationContext());
        //初始化阿里云
//        OssHelper.set(getApplicationContext());
    }

    private void setIsDebug(Boolean debug) {
            SERVER_URL = "http://121.40.142.156:8080/failcoop/";
    }

    private static MyApplication myApplication;

    public static MyApplication getApplication() {
        return myApplication;
    }

    public String getCookies() {
        return SharedPreferenceUtil.getSharedPreString(getApplicationContext(), "Cookies");
    }

    // 获取DB
    public DbManage getDb(){
        if(db == null) db = DbManage.getDbManage(getApplicationContext());
        return db;
    }


    private void initImageLoader(Context context) {
//        File cacheDir = new File(MyApplication.PIC_TEMP);
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPoolSize(3)// 线程池内加载的数量
//                .threadPriority(Thread.NORM_PRIORITY - 2).discCache(new UnlimitedDiskCache(cacheDir)) // 自定义缓存路径
//                .memoryCache(new WeakMemoryCache()).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()) // 将保存的时候的URI名称用MD5
//                .tasksProcessingOrder(QueueProcessingType.LIFO).defaultDisplayImageOptions(ImageLoaderConfig.getSquareImage()).build();
//        ImageLoader.getInstance().init(config);
    }

    /**
     * 创建文件夹（存放文档）
     */
    private void createDirs() {
        if (!CommonUtil.IsCanUseSdCard()) {
            MyApplication.FILE_TEMP = getApplicationContext().getFilesDir().getPath() + "/faultDetect/";
            MyApplication.PIC_TEMP = getApplicationContext().getFilesDir().getPath() + "/faultDetect/pic/";
            MyApplication.CACHE_TEMP = getApplicationContext().getFilesDir().getPath() + "/faultDetect/cache/";
            MyApplication.OSS_TEMP = getApplicationContext().getFilesDir().getPath() + "/faultDetect/oss/";
        }
        FileUtils.mkdirs(new File(MyApplication.FILE_TEMP));
        FileUtils.mkdirs(new File(MyApplication.PIC_TEMP));
        FileUtils.mkdirs(new File(MyApplication.CACHE_TEMP));
        FileUtils.mkdirs(new File(MyApplication.OSS_TEMP));
    }


    public void exit() {
        for (Activity a : activities) {
            a.finish();
        }
        System.exit(0);
    }
}
