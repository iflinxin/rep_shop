/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.plugin.unionpayPayment;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.shopxx.Setting;
import net.shopxx.entity.PaymentLog;
import net.shopxx.entity.PluginConfig;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.plugin.abcPayment.AbcPaymentPlugin;
import net.shopxx.plugin.unionpayPayment.sdk.AcpService;
import net.shopxx.plugin.unionpayPayment.sdk.LogUtil;
import net.shopxx.plugin.unionpayPayment.sdk.SDKConfig;
import net.shopxx.plugin.unionpayPayment.sdk.SDKConstants;
import net.shopxx.util.SystemUtils;
import net.shopxx.util.WebUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Component;





@Component("unionpayPaymentPlugin")
public class UnionpayPaymentPlugin extends PaymentPlugin {

	private static final String CURRENCY = "156";
	
	static{
		//String path = SDKConfig.class.getClassLoader().getResource("").getPath()+"net/shopxx/plugin/unionpayPayment/sdk";
		String path = UnionpayPaymentPlugin.class.getResource("").getFile();
		SDKConfig.getConfig().loadPropertiesFromPath(path);
		
	}
	
	@Override
	public String getName() {
		return "银联在线支付";
	}

	@Override
	public String getVersion() {
		return "5.0.0";
	}

	@Override
	public String getAuthor() {
		return "xiaohe";
	}

	@Override
	public String getSiteUrl() {
		return "http://3936242.01p.com/";
	}

	@Override
	public String getInstallUrl() {
		return "unionpay_payment/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "unionpay_payment/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "unionpay_payment/setting.jhtml";
	}

	@Override
	public String getRequestUrl() {
		return "https://101.231.204.80:5000/gateway/api/frontTransReq.do";
	}

	@Override
	public PaymentPlugin.RequestMethod getRequestMethod() {
		return PaymentPlugin.RequestMethod.post;
	}

	@Override
	public String getRequestCharset() {
		return "UTF-8";
	}
	

	@Override
	public Map<String, Object> getParameterMap(String sn, String description, HttpServletRequest request) {
		Setting setting = SystemUtils.getSetting();
		PluginConfig pluginConfig = getPluginConfig();
		PaymentLog paymentLog = getPaymentLog(sn);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("txnType", "01");
		parameterMap.put("channelType", "07");
		parameterMap.put("currencyCode", CURRENCY);
		parameterMap.put("merId", pluginConfig.getAttribute("partner"));
		parameterMap.put("txnSubType", "01");
		parameterMap.put("txnAmt", paymentLog.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());
		parameterMap.put("version", getVersion());
		parameterMap.put("signMethod", "01");
		parameterMap.put("bizType", "000201");           			  //业务类型，B2C网关支付，手机wap支付
		parameterMap.put("encoding", getRequestCharset());
		parameterMap.put("origQryId", "");
		parameterMap.put("merAbbr", StringUtils.abbreviate(setting.getSiteName().replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 40));
		parameterMap.put("orderId", sn);
		parameterMap.put("txnTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		parameterMap.put("accessType", "0");
		parameterMap.put("orderTimeout", "10080000");
		parameterMap.put("frontUrl", getNotifyUrl(PaymentPlugin.NotifyMethod.sync));
		parameterMap.put("backUrl", "http://wwww.specialurl.com/");//为了统一其他的支付平台，不想让银联通知我，由我主动去查询
		Map<String, String> submitStringData = AcpService.sign(parameterMap,getRequestCharset());  //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		Map<String, Object> submitObjectData = new HashMap<>();
		for(String key :submitStringData.keySet()){
			submitObjectData.put(key, submitStringData.get(key));
		}
		return submitObjectData;
	}

	@Override
	public boolean verifyNotify(PaymentPlugin.NotifyMethod notifyMethod, HttpServletRequest request) {
		
		Map<String, String> respParam = getAllRequestParam(request);
		String encoding = request.getParameter(SDKConstants.param_encoding);
		PluginConfig pluginConfig = getPluginConfig();
		PaymentLog paymentLog = getPaymentLog(request.getParameter("orderId"));
	
		Map<String, String> valideData = null;
		if (null != respParam && !respParam.isEmpty()) {
			Iterator<Entry<String, String>> it = respParam.entrySet()
					.iterator();
			valideData = new HashMap<String, String>(respParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				try {
					value = new String(value.getBytes(encoding), encoding);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				valideData.put(key, value);
			}
		}
		if (!AcpService.validate(valideData, encoding)) {
			LogUtil.writeLog("验证签名结果[失败].");
			return false;
		} else {
			LogUtil.writeLog("验证签名结果[成功].");
			Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put("version", getVersion());
			parameterMap.put("encoding", getRequestCharset());
			parameterMap.put("signMethod", "01");                          //签名方法 目前只支持01-RSA方式证书加密
			parameterMap.put("txnType", "00");                             //交易类型 00-默认
			parameterMap.put("txnSubType", "00");                          //交易子类型  默认00
			parameterMap.put("bizType", "000201");                         //业务类型 B2C网关支付，手机wap支付
			
			parameterMap.put("merId", pluginConfig.getAttribute("partner"));
			parameterMap.put("accessType", "0");                           //接入类型，商户接入固定填0，不需修改
			
			parameterMap.put("orderId", paymentLog.getSn());
			parameterMap.put("txnTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
			
			Map<String, String> reqData = AcpService.sign(parameterMap,getRequestCharset());//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
			String url = SDKConfig.getConfig().getSingleQueryUrl();// 交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.singleQueryUrl
			//这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
			Map<String, String> rspData = AcpService.post(reqData,url,getRequestCharset());
			
			/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
			//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
			if(!rspData.isEmpty()){
				if(AcpService.validate(rspData, getRequestCharset())){
					LogUtil.writeLog("验证签名成功");
					if("00".equals(rspData.get("respCode"))){//如果查询交易成功
						//处理被查询交易的应答码逻辑
						String origRespCode = rspData.get("origRespCode");
						if("00".equals(origRespCode)){
							//交易成功，更新商户订单状态
							return true;
						}else if("03".equals(origRespCode) ||
								 "04".equals(origRespCode) ||
								 "05".equals(origRespCode)){
							//需再次发起交易状态查询交易 
							//TODO
						}else{
							//其他应答码为失败请排查原因
							//TODO
						}
					}else{//查询交易本身失败，或者未查到原交易，检查查询交易报文要素
						//TODO
					}
				}else{
					LogUtil.writeErrorLog("验证签名失败");
					//TODO 检查验证签名失败的原因
				}
			}else{
				//未返回正确的http状态
				LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
				return false;
			}
		}
		
		return false;
		
		
		/*PluginConfig pluginConfig = getPluginConfig();
		PaymentLog paymentLog = getPaymentLog(request.getParameter("orderNumber"));
		if (paymentLog != null && generateSign(request.getParameterMap()).equals(request.getParameter("signature")) && pluginConfig.getAttribute("partner").equals(request.getParameter("merId")) && CURRENCY.equals(request.getParameter("orderCurrency"))
				&& "00".equals(request.getParameter("respCode")) && paymentLog.getAmount().multiply(new BigDecimal(100)).compareTo(new BigDecimal(request.getParameter("orderAmount"))) == 0) {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("version", "1.0.0");
			parameterMap.put("charset", "UTF-8");
			parameterMap.put("transType", "01");
			parameterMap.put("merId", pluginConfig.getAttribute("partner"));
			parameterMap.put("orderNumber", paymentLog.getSn());
			parameterMap.put("orderTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
			parameterMap.put("merReserved", "");
			parameterMap.put("signMethod", "MD5");
			parameterMap.put("signature", generateSign(parameterMap));
			String result = WebUtils.post("https://query.unionpaysecure.com/api/Query.action", parameterMap);
			if (ArrayUtils.contains(StringUtils.split(result, "&"), "respCode=00")) {
				return true;
			}
		}
		return false;*/
	}

	@Override
	public String getSn(HttpServletRequest request) {
		return request.getParameter("orderId");
	}

	@Override
	public String getNotifyMessage(PaymentPlugin.NotifyMethod notifyMethod, HttpServletRequest request) {
		return null;
	}

	private String generateSign(Map<String, ?> parameterMap) {
		PluginConfig pluginConfig = getPluginConfig();
		return DigestUtils.md5Hex(joinKeyValue(new TreeMap<String, Object>(parameterMap), null, "&" + DigestUtils.md5Hex(pluginConfig.getAttribute("key")), "&", false, "signMethod", "signature"));
	}
	
	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				if (res.get(en) == null || "".equals(res.get(en))) {
					// System.out.println("======为空的字段名===="+en);
					res.remove(en);
				}
			}
		}
		return res;
	}

}