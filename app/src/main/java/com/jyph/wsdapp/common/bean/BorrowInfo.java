package com.jyph.wsdapp.common.bean;

/**
 * Created by sxt_0 on 2017/8/18.
 */

public class BorrowInfo extends BaseInfo {

    private Emergency emergency;
    private UserCardInfo userCardInfo;
    private OtherInfo otherInfo;
    private Undetermined undetermined;

    public Emergency getEmergency() {
        return emergency;
    }

    public void setEmergency(Emergency emergency) {
        this.emergency = emergency;
    }

    public UserCardInfo getUserCardInfo() {
        return userCardInfo;
    }

    public void setUserCardInfo(UserCardInfo userCardInfo) {
        this.userCardInfo = userCardInfo;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Undetermined getUndetermined() {
        return undetermined;
    }

    public void setUndetermined(Undetermined undetermined) {
        this.undetermined = undetermined;
    }

    public static class Emergency{
        private String name;
        private String phone_number;
        private String relation;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }
    }

    public static class UserCardInfo{
        private String education;
        private String hukou;
        private String job;
        private String marriage;

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getHukou() {
            return hukou;
        }

        public void setHukou(String hukou) {
            this.hukou = hukou;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getMarriage() {
            return marriage;
        }

        public void setMarriage(String marriage) {
            this.marriage = marriage;
        }
    }

    public static class OtherInfo{}

    public static class Undetermined{}



}
