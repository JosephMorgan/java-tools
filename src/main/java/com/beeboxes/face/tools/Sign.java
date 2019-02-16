package com.beeboxes.face.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 为保证系统的数据安全，所有的接口访问均需要认证访问者，访问者需对接口调用进行签名，接口访问签名规则 md5(url + signatureKey + timestamp)
 * url: api地址,过滤掉schme， eg:对于request http://172.16.3.5/i18n/hello?lang=zh-CN 参与签名的url为172.16.3.5/i18n/hello?lang=zh-CN
 * signatureKey: 签名密钥，设备激活时获取签名秘钥。
 * timestamp：接口调用时当前时间，服务器会验证请求的有效性（10分钟内有效），以降低攻击者仿造请求的危险。
 * 在所有的终端接口调用中，终端需要在请求的header中添加timestamp 和 sign 两个字段。
 * 注：终端需将user-agent统一设置为 bb-device
 * 终端调用o-service需要统一添加名为sn的header（业务需求）
 */
public class Sign {
	public Map<String,String> getHttpHeader(String url, String signatureKey){
        StringBuffer mySign = new StringBuffer();
        long t = System.currentTimeMillis();
        appSign(mySign,url+signatureKey+t);
        Map<String,String> map = new HashMap<>(2);
        map.put("Timestamp", t+"");
        map.put("Sign", mySign.toString());
        return map;
    }

    public int appSign(StringBuffer mySign,String text) {
        mySign.append(MD5.stringToMD5(text));
        return 0;
    }

    public static void main(String[] args) throws IOException {
        Sign test1 = new Sign();
        System.out.println(test1.getHttpHeader(
                "10.20.20.12/bbox-service/api/devapi/recognitionRecords",
                "45673776-8754-4873-95bf-c59f14da3951"));

    }

}
