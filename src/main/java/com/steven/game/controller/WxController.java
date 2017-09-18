package com.steven.game.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.steven.util.wx.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * 
 * 
 * 类描述：微信授权登录等action <br>
 * 作者：田帅 <br>
 * 创建时间：2017-09-16 <br>
 * 版本：V1.0
 */
@Controller
@RequestMapping(value = "/wxController")
public class WxController {
	/**
	 *
	 *
	 * 类描述：根据code获取用户信息 <br>
	 * 作者：田帅 <br>
	 * 创建时间：2017-09-16 <br>
	 * 版本：V1.0
	 */
	@RequestMapping(value = "/wxLogin")
	public Object wxLogin(String code) {
		Map<String, Object> json = new HashMap<String, Object>();
		/** 请求结果 */
		String result = WxUserInfo.getWxUserInfo(code);
		JSONObject jsonObject = (JSONObject) JSON.parse(result);
		// 微信openid
		String openid = jsonObject.getString("openid");
		String nickname = jsonObject.getString("nickname");
		String headimgurl = jsonObject.getString("headimgurl");
		System.out.println("openid=" + openid);
		System.out.println("nickname=" + nickname);
		System.out.println("headimgurl=" + headimgurl);

		json.put("userId", openid);
		json.put("nickname", nickname);
		json.put("headimgurl", headimgurl);
		json.put("success", true);
		return json;
	}

	/**
	 * 
	 * 方法描述: 微信绑定平台的用户手机号<br>
	 * 作者：田帅 <br>
	 * 创建时间：2017-09-16 <br>
	 * 版本：V1.0
	 */
	@RequestMapping(value = "/wxBound")
	@ResponseBody
	public Object wxBound(String mobile, String openid, String nickname,String headimgurl) {
		Map<String, Object> json = new HashMap<String, Object>();
		System.out.println("mobile=" + mobile);
		System.out.println("openid=" + openid);
		System.out.println("nickname=" + nickname);
		System.out.println("headimgurl=" + headimgurl);
		if (mobile == null || mobile.isEmpty() || openid == null
				|| openid.isEmpty()) {
			json.put("status", "203");
			json.put("message", "参数不完整");
			return json;
		}
		//赋值书手机号
			/* 根据手机号查询账号是否存在 */
			// 该手机号还没有注册
			// 该手机号已注册
			// 先查询该手机号是否已绑定微信openid
		return json;
	}

	/**
	 * 
	 * 方法描述: 跳转到h5微信支付<br>
	 * 作者：田帅 <br>
	 * 创建时间：2017-09-16 <br>
	 * 版本：V1.0
	 */
	@RequestMapping(value = "/toH5WxPay")
	public Object toH5WxPay(String out_trade_no, String total_fee, String body) {
		System.out.println("out_trade_no=" + out_trade_no);
		System.out.println("total_fee=" + total_fee);
		System.out.println("body=" + body);
		String backUri = "";//微信授权后跳转的支付界面
		backUri = backUri + "?out_trade_no=" + out_trade_no + "&total_fee="
				+ total_fee + "&body=" + body;
		// URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri = URLEncoder.encode(backUri);
		// scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="
				+ ConfigUtil.APPIDH5
				+ "&redirect_uri="
				+ backUri
				+ "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		return "redirect:" + url;
	}


	/**
	 *
	 * 方法描述: H5向微信请求支付信息<br>
	 *
	 * @return <br>
	 * @return String <br>
	 * @作者： 张海洋 <br>
	 * @创建时间： 2016-4-5 下午01:03:40
	 */
	@RequestMapping(value = "/wxpay_getWXPayXmlH5")
	@ResponseBody
	public Object getWXPayXmlH5(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "0") int userId, String out_trade_no, String status) {
		Map<String, Object> json = new HashMap<String, Object>();
		String openid = null;
		String total_fee;
		try {
			// 查询用户信息
			System.out.println("openid:"+openid);
			// 查询应交金额
			BigDecimal payment = null;
			if (payment == null) {
				json.put("status", "202");
				json.put("message", "没有根据该订单查询到应交金额");
				return json;
			}

			total_fee = String.valueOf(payment);
		} catch (Exception e) {
			json.put("status", "500");
			json.put("message", "服务器内部异常");
			System.out.println("未知异常：" + e.getLocalizedMessage());
			return json;
		}

		//HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("userId=" + userId);
		System.out.println("out_trade_no=" + out_trade_no);
		System.out.println("total_fee=" + total_fee);
		// System.out.println("body=" + body);
		System.out.println("openid=" + openid);
		// 金额转化为分为单位
		int finalmoney = (int) (Double.valueOf(total_fee) * 100);

		String appid = ConfigUtil.APPID;
		String appsecret = ConfigUtil.APP_SECRECTH5;
		String partner = ConfigUtil.MCH_IDH5;
		String partnerkey = ConfigUtil.API_KEYH5;

		request.getSession();
		// 商户号
		String mch_id = partner;
		// 随机数
		String nonce_str = PayCommonUtil.CreateNoncestr();
		// 商品描述根据情况修改
		String body = "麻将游戏";

		// 订单生成的机器 IP
		String spbill_create_ip = PayCommonUtil.getIpAddress(request);
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = ConfigUtil.NOTIFY_URL;
		System.out.println("wowowo===================="+notify_url);
		System.out.println("=========================================================================================");
		System.out.println("wowowo===================="+notify_url);
		System.out.println("=========================================================================================");
		String trade_type = "JSAPI";
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", finalmoney + "");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		if("1".equals(status)){
			// 附件属性,原样返回
			packageParams.put("attach", "clear");
		}else {
			// 附件属性,原样返回
			packageParams.put("attach", "trade");
		}

		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = PayCommonUtil.createSignature(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" + "<out_trade_no>"
				+ out_trade_no + "</out_trade_no>" + "<total_fee>" + finalmoney
				+ "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>" + openid + "</openid>"
				+ "<attach>"+packageParams.get("attach")+"</attach>"
				+ "</xml>";
		System.out.println("xml=" + xml);
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);

		json.put("appid", appid2);
		json.put("timeStamp", timestamp);
		json.put("nonceStr", nonceStr2);
		json.put("package", packages);
		json.put("paySign", finalsign);
		json.put("status", 200);
		return json;

	}
}
