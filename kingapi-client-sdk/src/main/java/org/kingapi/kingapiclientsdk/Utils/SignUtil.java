package org.kingapi.kingapiclientsdk.Utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * ClassName:SignUtil
 * Description:
 *
 * @Author:kinglearn
 * @Create2024/8/2 20:38
 * @version1.0
 */
public class SignUtil {
    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String getSign(String body, String secretKey){
        Digester SHA256 = new Digester(DigestAlgorithm.SHA256);
        String content = body+"."+secretKey;
        return SHA256.digestHex(content);
    }
}
