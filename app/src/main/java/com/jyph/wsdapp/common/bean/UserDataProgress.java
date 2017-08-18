package com.jyph.wsdapp.common.bean;

/**
 * Created by sxt_0 on 2017/8/18.
 */

public class UserDataProgress extends BaseInfo {
    private boolean emergency;//紧急联系人
    private boolean identity;//身份信息
    private boolean otherInfo;//其他信息
    private boolean undetermined;//待定

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }

    public boolean isOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(boolean otherInfo) {
        this.otherInfo = otherInfo;
    }

    public boolean isUndetermined() {
        return undetermined;
    }

    public void setUndetermined(boolean undetermined) {
        this.undetermined = undetermined;
    }
}
