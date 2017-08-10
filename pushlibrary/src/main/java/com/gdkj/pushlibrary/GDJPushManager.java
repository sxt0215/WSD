package com.gdkj.pushlibrary;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by sxt on 2017/8/8.
 */

public class GDJPushManager {

    private String TAG = getClass().getSimpleName();
    private static GDJPushManager mInstance;
    private Context mContext;
    private static final int MSG_SET_ALIAS = 1001;

    public static GDJPushManager getInstance() {
        if (mInstance == null) {
            mInstance = new GDJPushManager();
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * @param context 上下文对象
     * @param debug   是否是debug模式
     */
    public GDJPushManager init(Context context, boolean debug) {
        mContext = context;
        JPushInterface.setDebugMode(debug);
        JPushInterface.init(context);
        Log.e(TAG,"--------------------"+JPushInterface.isPushStopped(context));
        return mInstance;
    }

    /**
     * 设置push接收的别名
     *
     * @param alias
     */
    public void setAlias(String alias) {
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    /**
     * 停止接收Push
     */
    public void stopPush() {
        JPushInterface.stopPush(mContext);
    }


    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(mContext,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
                    if (code == 6012) {//处理之前停止过push，再起启动只能通过resumePush开启push
                        if (JPushInterface.isPushStopped(mContext)) {
                            JPushInterface.resumePush(mContext);//恢复push连接
                            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 100 * 6);//设置别名
                        }
                    }
            }
        }
    };
}
