package com.jyph.wsdapp.mvp.wsdapi;

import com.jyph.wsdapp.common.bean.BaseInfo;
import com.jyph.wsdapp.common.bean.LoginInfo;
import com.jyph.wsdapp.common.bean.UserDataProgress;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sxt on 16/12/19.
 */
public interface MyApi {

    /**
     * 登录短信验证码获取
     */
    @GET(MyApiConstants.API_LOGIN_SMS_CODE)
    Observable<BaseInfo> getSmsCode(
             @Query("mobile") String mobile);

    /**
     * 登录接口
     * */
    @GET(MyApiConstants.API_LOGIN)
    Observable<LoginInfo> getLogin(
            @Query("userMobile") String userMobile,
            @Query("code") String code,
            @Query("source") String source
    );
    /**
     * 检查用户资料完善情况
     * */
    @GET(MyApiConstants.API_CHECK_UESRINFO)
    Observable<UserDataProgress> getUserData(

    );











    /**
     * 首页CMS 最新公告  信息   PUBLICATION
     */
//    interface GetCMSPublication {
//        @GET(MyApiConstants.API_CMS_PUBLICATION)
//        Observable<List<CMSResponse>> getCMSPublication(
//                @Path("publication") String publication);
//    }

    /**
     * 首页标的
     */

//    interface HomePage {
//
//        //首页轮播图
//        @GET(MyApiConstants.API_BANNERS)
//        Observable<List<Banner>> getBannerImg();
//
//        //首页标的
//        @GET(MyApiConstants.API_HOMELOAN)
//        Observable<HomeLoanInfo> getHomeLoan();
//
//        //升级检查
//        @GET(MyApiConstants.VERSION_UPDATE_LINK)
//        Observable<Version> checkUpdate();
//    }
//
//    /**
//     * 检测手机号是否注册
//     */
//    interface CheckNumber {
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_CHECKPHONE)
//        Observable<BaseInfo> checkNumber(@Field("mobile") String mobile);
//    }
//
//    /**
//     * 登录
//     */
//    interface Login {
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_LOGIN)
//        Observable<LoginInfo> gotoLogin(
//                @Field("username") String username,
//                @Field("password") String password,
//                @Field("client_id") String client_id,
//                @Field("client_secret") String client_secret,
//                @Field("grant_type") String grant_type);
//    }
//
//    /**
//     * 注册 短信验证码获取
//     */
//    interface RegisterSmsCode {
//        @GET(MyApiConstants.API_REGISTER_SMS_CODE)
//        Observable<BaseInfo> getSmsCode(
//                @Query("mobile") String mobile);
//    }
//
//    /**
//     * 注册
//     */
//    interface Register {
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_REGISTER)
//        Observable<RegisterInfo> goRegister(
//                @Field("mobile") String mobile,
//                @Field("password") String password,
//                @Field("repassword") String repassword,
//                @Field("mobile_captcha") String mobile_captcha,
//                @Field("source") String source,
//                @Field("agreement") boolean agreement
//        );
//    }
//
//    /**
//     * 忘记密码  短信验证码获取
//     */
//    interface ForgetPwdCode {
//        @GET(MyApiConstants.API_FORGET_PWD_CODE)
//        Observable<BaseInfo> getForgetPwdCode(
//                @Query("mobile") String mobile
//        );
//    }
//
//    /**
//     * 忘记密码 重置密码
//     */
//    interface ForgetPwd {
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_FORGET_PWD)
//        Observable<BaseInfo> forgetPwd(
//                @Field("newPassword") String newPassword,
//                @Field("rePass") String rePass,
//                @Field("mobile") String mobile,
//                @Field("captcha") String captcha,
//                @Field("smsType") String smsType
//        );
//    }
//
//
//    /**
//     * 注册协议
//     */
//    interface Protocol {
//        @GET(MyApiConstants.API_PROTOCOL)
//        Observable<List<CMSResponse>> getProtocol(
//                @Path("protocol") String protocol
//        );
//    }
//
//    /**
//     * 产品列表
//     */
//    interface GetProductList {
//        @GET(MyApiConstants.API_LOANS_LIST)
//        Observable<LoanInfo> getProductList(
//                @Query("pageSize") int pageSize,
//                @Query("status") String status,
//                @Query("maxDuration") int maxDuration,
//                @Query("minDuration") int minDuration,
//                @Query("minRate") int minRate,
//                @Query("maxRate") int maxRate,
//                @Query("currentPage") int currentPage,
//                @Query("product") String product
//        );
//    }
//
//   /**
//    *理财列表 整合后接口
//    * */
//    interface GetLoanList{
//       @GET(MyApiConstants.API_lIST)
//       Observable<LoanListInfo> getList(
//               @Query("pageSize") int pageSize,
//               @Query("currentPage") int currentPage
//       );
//   }
//
//
//    /**
//     * 新手专享
//     * */
//    interface GetProduceXSZX{
//        @GET(MyApiConstants.API_LOANS_XSZX)
//        Observable<LoanInfo> getProductXSZX();
//    }
//
//    /**
//     * 限时特惠
//     * */
//    interface GetProductXSTH{
//        @GET(MyApiConstants.API_LOANS_XSTH)
//        Observable<LoanInfo> getProductXSTH();
//    }
//
//    /**
//     * 产品列表  底部 往期回顾
//     */
//    interface GetLoanReview {
//        @GET(MyApiConstants.API_LOAN_REVIEW)
//        Observable<TotalLoanInvestInfo> getLoanReview();
//    }
//
//
//    /**
//     * 标的详情
//     */
//    interface LoanDetail {
//        @GET(MyApiConstants.API_LOAN_DETAIL)
//        Observable<Loan> getLoanDetail(
//                @Path("loanId") String loanId
//        );
//    }
//
//    /**
//     * 借款人信息
//     */
//    interface UserInfo {
//        @GET(MyApiConstants.API_DETAIL_USER_INFO)
//        Observable<BorrowUserInfo> getBorrowUserInfo(
//                @Path("userId") String userId
//        );
//    }
//
//    /**
//     * 标的的投资记录
//     * */
//    interface LoanInvestRecord{
//        @GET(MyApiConstants.API_LOAN_INVEST)
//        Observable<InvestRecordResponse> getLoanInvestRecord(
//                @Path("id") String id,
//                @Path("page") int page,
//                @Path("pageSize") int pageSize
//        );
//    }
//
//    /**
//     * 标的详情  产品详情图片
//     */
//    interface LoanDetailImg {
//        @GET(MyApiConstants.API_LOAN_DETAIL_IMG)
//        Observable<List<ProofInfo>> getLoanDetailImg(
//                @Path("id") String id
//        );
//    }
//
//    /**
//     * 标的详情  安全保障 img
//     */
//    interface DeatilSafeImg {
//        @GET(MyApiConstants.API_LOAN_SAFE_IMG)
//        Observable<ProofsInfoSafe> getDeatilSafe(
//                @Path("entityId") String entityId
//        );
//    }
//
//    /**
//     * 投资页面  可用红包接口
//     */
//    interface InvestCoupon {
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_INVEST_COUPON)
//        Observable<ChooseCouponListResponse> getCouponList(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("amount") int amount,
//                @Field("loanId") String loanId
//        );
//    }
//
//    /**
//     * 投资页面
//     */
//    interface CheckAuth {
//        @GET(MyApiConstants.API_CHECK_AUTH)
//        Observable<BaseInfo> checkAuth(
//                @Header("Authorization") String Authorization
//        );
//    }
//
//    /**
//     * 用户信息接口
//     */
//    interface GetUserInfo {
//        @GET(MyApiConstants.API_USER_INFO)
//        Observable<com.lmjr.client.commom.bean.UserInfo> getUsetInfo(
//                @Header("Authorization") String Authorization
//        );
//
//        @GET(MyApiConstants.API_ACCOUNT_STATUS)
//        Observable<BaseDataBean<Integer>> getAccountStatus(
//                @Header("Authorization") String Authorization
//        );
//    }
//
//
//    /**
//     * 我的页面  是否有新消息  未读的
//     */
//    interface MyMessage {
//        @GET(MyApiConstants.API_MESSAGE_BY_STATUS)
//        Observable<NotificationMessageInfo> getMyMessageByStatus(
//                @Header("Authorization") String Authorization,
//                @Query("page") int pageIndex,
//                @Query("pageSize") int pageSize,
//                @Query("status") String status
//        );
//    }
//
//    /**
//     * 我的页面  是否有新消息  未读的
//     */
//    interface GetMyAccountInfo {
//        @GET(MyApiConstants.API_NEW_NEWS)
//        Observable<NotificationMessageInfo> getMyAccountInfo(
//                @Header("Authorization") String Authorization
//        );
//    }
//
//    /**
//     * 消息中心
//     */
//    interface GetAllInfos {
//        @GET(MyApiConstants.API_ALL_NEWS)
//        Observable<NotificationMessageInfo> getAllInfos(
//                @Header("Authorization") String Authorization
//        );
//    }
//
//    /**
//     * 读消息  接口
//     */
//    interface ReadInfo {
//        @GET(MyApiConstants.API_READ_INFO)
//        Observable<BaseInfo> readInfo(
//                @Header("Authorization") String Authorization,
//                @Path("id") String id
//        );
//    }
//
//    /**
//     * 邀请好友  邀请码接口
//     * */
//    interface InviteCode{
//       @GET(MyApiConstants.API_INVITE_CODE)
//        Observable<InviteCodeInfo> getInviteCode(
//               @Header("Authorization") String Authorization
//       );
//    }
//
//    /**
//     *邀请好友中  已邀请人数和红包接口
//     * */
//    interface InviteFriend {
//      @GET(MyApiConstants.API_INVITE_FRIEND)
//        Observable<InviteFriendInfo>  getInviteFriendNum(
//              @Header("Authorization") String Authorization
//      );
//    }
//
//    interface InviteContent{
//        @GET(MyApiConstants.API_CMS_ACTIVITY)
//        Observable<List<CMSResponse>> getActivity(
//                @Path("item") String item);
//    }
//
//    //    ===============================  新浪接口 ================================
//
//
//    /**
//     * 第三方支付
//     */
//    interface ThirdPartyPayment {
//        /**
//         * 开户
//         */
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_AUTH)
//        Observable<BaseInfo> goAuth(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("name") String name,
//                @Field("idNumber") String idNumber,
//                @Field("clientIp") String clientIp,
//                @Field("returnUrl") String returnUrl
//        );
//
//        /**
//         * invest  投资
//         */
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_INVEST)
//        Observable<SinaPaymentResponse> goInvest(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("loanId") String loanId,
//                @Field("amount") int amount,
//                @Field("placementId") String placementId,
//                @Field("payMethod") String payMethod,
//                @Field("clientIp") String clientIp,
//                @Field("returnUrl") String returnUrl
//        );
//
//        /**
//         * 查询交易最终结果
//         */
//        @GET(MyApiConstants.API_TRADE_RESULT)
//        Observable<BaseInfo> getTradeResult(
//                @Header("Authorization") String Authorization,
//                @Query("orderId") String orderId,
//                @Query("code") String code
//        );
//
//        //bank card
//        //@FormUrlEncoded
//        @GET(MyApiConstants.API_SINA_BANKCARD)
//        Observable<List<FundAccount>> goBankCard(
//                @Header("Authorization") String Authorization
//        );
//
//        //bind card send sms
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_BIND_SEND_SMS)
//        Observable<SinaPaymentResponse> goBindCardSendSms(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("bankCode") String bankCode,
//                @Field("bankAccountNo") String bankAccountNo,
//                @Field("phoneNo") String phoneNo,
//                @Field("province") String province,
//                @Field("city") String city,
//                @Field("clientIp") String clientIp
//
//        );
//
//        //bind card real binding
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_BIND_CARD)
//        Observable<SinaPaymentResponse> goBindCard(
//                @Header("Authorization") String Authorization,
//                @Field("orderId") String orderId,
//                @Field("validCode") String smsCode,
//                @Field("clientIp") String clientIp
//
//        );
//
//        //unbind send sms
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_UNBIND_SEND_SMS)
//        Observable<SinaPaymentResponse> goUnbindCardSendSms(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("bankAccountNo") String bankAccountNo,
//                @Field("clientIp") String clientIp
//        );
//
//        //unbind card real unbinding
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_UNBIND_CARD)
//        Observable<SinaPaymentResponse> goUnbindCard(
//                @Header("Authorization") String Authorization,
//                @Field("orderId") String orderId,
//                @Field("validCode") String smsCode,
//                @Field("clientIp") String clientIp
//        );
//
//        //recharge
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_RECHARGE)
//        Observable<SinaPaymentResponse> goRecharge(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("accountType") String accountType,
//                @Field("amount") String amount,
//                @Field("clientIp") String clientIp,
//                @Field("retUrl") String returnUrl
//        );
//
//        //withdraw
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_WITHDRAW)
//        Observable<SinaPaymentResponse> goWithdraw(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("amount") String amount,
//                @Field("userIp") String userIp,
//                @Field("withdrawReturnUrl") String returnUrl
//        );
//
//
//        //modify payment pwd
//        //@FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_MODIFY_PAYMENT_PWD)
//        Observable<SinaPaymentResponse> goModifyPaymentPwd(
//                @Header("Authorization") String Authorization
//        );
//
//        //sina account
//        //@FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_ACCOUNT)
//        Observable<SinaPaymentResponse> goSinaAccount(
//                @Header("Authorization") String Authorization
//        );
//
//        //set payment password
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_SET_PAYMENT_PWD)
//        Observable<SinaPaymentResponse> goSetPaymentPwd(
//                @Header("Authorization") String Authorization,
//                @Field("returnUrl") String returnUrl
//        );
//
//        //query payment password
//        //@FormUrlEncoded
//        @POST(MyApiConstants.API_SINA_QUERY_PAYMENT_PWD)
//        Observable<SinaPaymentResponse> goQueryPaymentPwd(
//                @Header("Authorization") String Authorization
//        );
//
//    }
//
////    =============================== ============================================
//
//    //=============================    han
//    //累计收益、预期收益
//    interface MyProperty {
//        @GET(MyApiConstants.API_FUND_STATICS)
//        Observable<StaticsInvest> getMyFundStatics(
//                @Header("Authorization") String Authorization
//        );
//
//        @GET(MyApiConstants.API_FUND_AVAILABLE)
//        Observable<UserFund> getMyFundAvailable(
//                @Header("Authorization") String Authorization
//        );
//    }
//
//    //即将到期
//    interface DueLoan {
//        @GET(MyApiConstants.API_DUE_RECORD_LIST)
//        Observable<BaseDataBean<DueLoanResponse>> getDueLoan(
//                @Header("Authorization") String Authorization,
//                @Path("pageIndex") int pageIndex,
//                @Path("pageSize") int pageSize
//        );
//    }
//    //还款记录
//    interface PaymentRecord {
//        @GET(MyApiConstants.API_PAYMENT_RECORD_LIST)
//        Observable<PaymentRecordResponse> getPaymentRecord(
//                @Header("Authorization") String Authorization,
//                @Query("page") int pageIndex,
//                @Query("pageSize") int pageSize,
//                @Query("type") String type
//        );
//
//        @GET(MyApiConstants.API_PAYMENT_RECORD_DETAIL)
//        Observable<BaseDataBean<PaymentRecordDetailBean>> getPaymentRecordDetail(
//                @Header("Authorization") String Authorization,
//                @Path("loanId") String loanId
//        );
//    }
//
//    //购买记录
//    interface BuyRecord {
//        @GET(MyApiConstants.API_BUY_RECORD_LIST)
//        Observable<BaseResultsBean<BuyRecordBean>> getBuyRecord(
//                @Header("Authorization") String Authorization,
//                @Path("pageIndex") int pageIndex,
//                @Path("pageSize") int pageSize
//        );
//        @GET(MyApiConstants.API_BUY_RECORD_DETAIL)
//        Observable<BuyRecordDetailBean> getBuyRecordDetail(
//                @Header("Authorization") String Authorization,
//                @Path("investId") String investId
//        );
//    }
//
//    //红包
//    interface Coupon {
//        @GET(MyApiConstants.API_COUPON_STATUS)
//        Observable<CouponListResponse> getCouponByStatus(
//                @Header("Authorization") String Authorization,
//                @Query("status") List<String> statusList,
//                @Query("pageNo") int pageNo,
//                @Query("pageSize") int pageSize
//        );
//
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_COUPON_REDEEM)
//        Observable<Boolean> goRedeemCoupon(
//                @Header("Authorization") String Authorization,
//                @Field("placementId") String placementId
//        );
//    }
//
//    //资金明细
//    interface FundDetail {
//        @GET(MyApiConstants.API_FUND_RECORD)
//        Observable<FundDetailRecordResponse> getFundDetailByType(
//                @Header("Authorization") String Authorization,
//                @QueryMap Map<String, Object> params
////                @Query("page") int page,
////                @Query("pageSize") int pageSize,
////                @Query("startDate") long startDate,
////                @Query("endDate") long endDate,
////                @Query("allStatus") boolean allStatus,
////                @Query("allOperation") boolean allOperation
//        );
//    }
//
//    //登录密码
//    interface LoginPassword {
//        @FormUrlEncoded
//        @POST(MyApiConstants.API_PWD_MODIFY)
//        Observable<BaseInfo> loginPwdModify(
//                @Header("Authorization") String Authorization,
//                @Field("userId") String userId,
//                @Field("newPassword") String newPassword,
//                @Field("currentPassword") String oldPassword
//        );
//    }
//
//
//    interface CMS {
//        @GET(MyApiConstants.API_CMS_PUBLICATION)
//        Observable<List<CMSResponse>> getPublication(
//                @Path("item") String item);
//
//        @GET(MyApiConstants.API_CMS_ACTIVITY)
//        Observable<List<CMSResponse>> getActivity(
//                @Path("item") String item);
//
//        @GET(MyApiConstants.API_CMS_HELP)
//        Observable<List<CMSResponse>> getHelp(
//                @Path("item") String item);
//    }
//
//    //预期收益、累积收益接口
//    interface InterestDetail {
//        @GET(MyApiConstants.API_INTEREST_DETAIL)
//        Observable<ExpectOrTotalInfo> getInterestDetail(
//                @Header("Authorization") String Authorization,
//                @Path("userId") String userId,
//                @Query("item") String item,
//                @Query("pageNo") int pageNo,
//                @Query("pageSize") int pageSize
//        );
//    }


}
