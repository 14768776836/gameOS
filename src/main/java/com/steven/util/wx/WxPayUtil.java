package com.steven.util.wx;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * 
 * 类描述： 微信支付<br>
 * 作者：田帅 <br>
 * 创建时间：2017-09-16 <br>
 * 版本：V1.0
 */
public class WxPayUtil {

	/**
	 * 
	 * 方法描述: 微信支付查询订单<br>
	 * 
	 * @param transaction_id
	 *            微信的订单号，优先使用
	 * @param out_trade_no
	 *            商户系统内部的订单号，当没提供transaction_id时需要传这个。 微信订单号和商户订单号二选一
	 *@param //描述
	 *            网址https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
	 *            该接口提供所有微信支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。 需要调用查询接口的情况： ◆
	 *            当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知； ◆ 调用支付接口后，返回系统错误或未知交易状态情况； ◆
	 *            调用被扫支付API，返回USERPAYING的状态； ◆ 调用关单或撤销接口API之前，需确认支付状态； 接口链接
	 *            https://api.mch.weixin.qq.com/pay/orderquery
	 * @return String xml格式的<br>
	 * 作者：田帅 <br>
	 * 创建时间：2017-09-16 <br>
	 * 版本：V1.0
	 */
	public static String orderQuery(String transaction_id, String out_trade_no) {
		// 请求参数
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		// 应用ID
		parameters.put("appid", ConfigUtil.APPID);
		// 商户号
		parameters.put("mch_id", ConfigUtil.MCH_ID);
		// 随机字符串
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
		if (transaction_id != null && !transaction_id.isEmpty()) {
			// 微信订单号
			parameters.put("transaction_id", transaction_id);
		} else {
			// 商户订单号
			parameters.put("out_trade_no", out_trade_no);
		}
		// 生成签名
		String sign = PayCommonUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		// 将请求参数转换为xml格式的string
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		System.out.println("requestXML=" + requestXML);
		// 发送https请求查询订单
		String result = HttpKit.httpsRequest(ConfigUtil.CHECK_ORDER_URL,
				"POST", requestXML);
		System.out.println("result=" + result);
		return result;
	}

	/**
	 * 
	 * 方法描述:微信支付关闭订单 <br>
	 * 
	 * @param out_trade_no
	 *            商户订单号
	 * @param /描述
	 *            网址：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9
	 *            _3& index=5
	 *            以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付
	 *            ；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
	 *            注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
	 *            接口链接：https://api.mch.weixin.qq.com/pay/closeorder
	 * 作者：田帅 <br>
	 * 创建时间：2017-09-16 <br>
	 * 版本：V1.0
	 */
	public static String closeOrder(String out_trade_no) {
		// 请求参数
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		// 应用ID
		parameters.put("appid", ConfigUtil.APPID);
		// 商户号
		parameters.put("mch_id", ConfigUtil.MCH_ID);
		// 随机字符串
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
		// 商户订单号
		parameters.put("out_trade_no", out_trade_no);
		// 生成签名
		String sign = PayCommonUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		// 将请求参数转换为xml格式的string
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		System.out.println("requestXML=" + requestXML);
		// 发送https请求查询订单
		String result = HttpKit.httpsRequest(ConfigUtil.CLOSE_ORDER_URL,"POST", requestXML);
		System.out.println("result=" + result);
		return result;
	}

//	public static void main(String[] args) {
//		String s = closeOrder("1111111");
//
//		try {
//			// 把xml格式字符串转化为key---value格式
//			Map<Object, Object> map = XMLUtil.doXMLParse(s);
//			// 循环获取key的值，根据key获取value
//			for (Object key : map.keySet()) {
//				// 打印key-----value的值
//				System.out.println(key + "=" + map.get(key));
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
}
