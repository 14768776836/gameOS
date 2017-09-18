package com.steven.util.wx;
/**
 *
 *
 * 类描述：微信相关参数配置 <br>
 * 作者：田帅 <br>
 * 创建时间：2017-09-16 <br>
 * 版本：V1.0
 */
public class ConfigUtil {
	/** app应用的ID */
	public final static String APPID = "wx97f93285af090c3e";
	/** app应用的应用密码 */
	public final static String APP_SECRECT = "1f9470d9289615b41e0a6b487bbe5b85";
	public final static String TOKEN = "weixinCourse";// 服务号的配置token
	/** app商户号 */
	public final static String MCH_ID = "1453450802";
	/** API密钥用户自己设置 */
	public final static String API_KEY = "C50E092772003E8BAFAD8FB7D453888C";
	/** 签名加密方式*/
	public final static String SIGN_TYPE = "MD5";
	/** 微信支付证书存放路径地址 */
	public final static String CERT_PATH = "D:/apiclient_cert.p12";
	/** 微信公众号的ID */
	public final static String APPIDH5 = "wx97f93285af090c3e";
	/** 微信公众号的应用密码 */
	public final static String APP_SECRECTH5 = "1f9470d9289615b41e0a6b487bbe5b85";
	/** 微信公众号商户号 */
	public final static String MCH_IDH5 = "1453450802";
	/** API密钥用户自己设置 */
	public final static String API_KEYH5 = "C50E092772003E8BAFAD8FB7D453888C";
	/** 微信支付统一接口的回调d */
	public final static String NOTIFY_URL = "";
	/** 微信支付成功支付后跳转的地址 */
	public final static String SUCCESS_URL = "";
	/** oauth2授权时回调action */
	public final static String REDIRECT_URI = "";
	/**
	 * 微信基础接口地址
	 */
	/** 获取token接口(GET) */
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** oauth2授权接口(GET) */
	public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/** 刷新access_token接口（GET） */
	public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	/**
	 * 微信支付接口地址
	 */
	/** 微信支付统一下单接口(POST) */
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/** 微信退款接口(POST) */
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	/** 订单查询接口(POST) */
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	/** 关闭订单接口(POST) */
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	/** 退款查询接口(POST) */
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	/** 对账单接口(POST) */
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	/** 短链接转换接口(POST) */
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
}
