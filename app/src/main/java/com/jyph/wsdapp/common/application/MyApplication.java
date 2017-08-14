package com.jyph.wsdapp.common.application;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;


/**
 * Created by creditcloud on 7/26/16.
 */
public class MyApplication extends TinkerApplication {

    /**
     * 参数解析
     * 参数1：tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
     * 参数2：delegateClassName Application代理类 这里填写你自定义的ApplicationLike
     * 参数3：loaderClassName Tinker的加载器，使用默认即可
     * 参数4：tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false
     * */
    protected  MyApplication(){
        super(ShareConstants.TINKER_ENABLE_ALL, "com.jyph.wsdapp.common.application.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);;
    }

    protected MyApplication(int tinkerFlags) {
        super(tinkerFlags);
    }

    protected MyApplication(int tinkerFlags, String delegateClassName, String loaderClassName, boolean tinkerLoadVerifyFlag) {
        super(tinkerFlags, delegateClassName, loaderClassName, tinkerLoadVerifyFlag);
    }

    protected MyApplication(int tinkerFlags, String delegateClassName) {
        super(tinkerFlags, delegateClassName);
    }
}
