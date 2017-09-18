package com.steven.util.wx;

import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 
 * 类描述：获取微信用户的基本信息 <br>
 * 作者：田帅 <br>
 * 创建时间：2017-09-16 <br>
 * 版本：V1.0
 */
public class WxUserInfo {
	private static final Logger logger = Logger.getLogger(WxUserInfo.class);

	/**
	 * 
	 * 方法描述: 获取微信用户的基本信息<br>
	 * 作者：田帅 <br>
	 * 创建时间：2017-09-16 <br>
	 * 版本：V1.0
	 */
	public static String getWxUserInfo(String code) {
		String AppId = ConfigUtil.APPIDH5;
		String AppSecret = ConfigUtil.APP_SECRECTH5;
		/** 请求结果 */
		String result = null;
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ConfigUtil.APPIDH5+"&secret="+ConfigUtil.APP_SECRECTH5+"&code="+code+"&grant_type=authorization_code";
		try {
			result = HttpKit.get(url);
			JSONObject jsonTexts = (JSONObject) JSON.parse(result);
			String openid = "";
			String accessToken = "";
			if (null != (jsonTexts.get("openid"))) {
				openid = jsonTexts.get("openid").toString();
				accessToken = jsonTexts.get("access_token").toString();
			}
			url = "https://api.weixin.qq.com/sns/userinfo?access_token=AccessToken&openid=Openid&lang=zh_CN";
			url = url.replace("AccessToken", accessToken).replace("Openid",
					openid);
			result = HttpKit.get(url);
			logger.info("result=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
