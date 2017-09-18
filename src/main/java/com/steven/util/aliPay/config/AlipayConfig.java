package com.steven.util.aliPay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "";

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = "";

	// 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "";

	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key = "";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// notify_url为服务器通知，支付宝可以保证99.9999%的通知到达率，前提是您的网络通畅。
	// notify_url: 服务器后台通知,这个页面是支付宝服务器端自动调用这个页面的链接地址,这个页面根据支付宝反馈过
	// 来的信息修改网站的定单状态,更新完成后需要返回一个success给支付宝.,不能含有任何其它的字符包括html语言.
	public static String notify_url ="";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// return_url为网页重定向通知，是由客户的浏览器触发的一个通知，若客户去网银支付，也会受银行接口影响，由
	// 于各种影响因素特别多，所以该种类型的通知支付宝不保证其到达率。
	public static String return_url = "";

	// 签名方式
	public static String sign_type = "RSA";

	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";

	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";

	// 支付类型 ，无需修改
	public static String payment_type = "1";

	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
