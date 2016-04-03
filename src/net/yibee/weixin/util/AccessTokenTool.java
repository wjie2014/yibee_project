package net.yibee.weixin.util;


import com.zmedu.tools.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangjie on 16/2/24.
 */
public class AccessTokenTool {

    private static Logger logger = org.apache.log4j.Logger.getLogger(AccessTokenTool.class);

    public static final String AppId = "wx41051ab7eead0825";
    public static final String AppSecret = "95da48b39b80434266d779c9569ed1ce";
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
            + AppId + "&secret=" + AppSecret;
    public static String access_token = "";

    /**
     * 过期时间7200秒， 因为微信token过期时间为2小时，即7200秒
     */
    private static int expireTime = 7200 * 1000;
    private static long refreshTime;

    /**
     * 获取微信accesstoken
     *
     * @return
     */
    public static synchronized String getAccessToken() {
        return getAccessToken(false);
    }

    public static synchronized String getAccessToken(boolean refresh) {
        if (StringUtils.isBlank(access_token) || (System.currentTimeMillis() - refreshTime) > expireTime || refresh) {
            try {
                access_token = initAccessToken();
            } catch (Exception e) {
                e.printStackTrace();
            }
            refreshTime = System.currentTimeMillis();
        }

        return access_token;
    }

    private static String initAccessToken() throws Exception {
        String responseContent = HttpUtil.getHttpUrl(GET_ACCESS_TOKEN_URL);
        JSONObject object = new JSONObject();
        try {
            object = new JSONObject(responseContent);
            return (String) object.get("access_token");
        } catch (JSONException e) {
            try {
                logger.error("获取token失败 errcode:" + object.get("errcode") + " errmsg:" + object.getString("errmsg"));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
}