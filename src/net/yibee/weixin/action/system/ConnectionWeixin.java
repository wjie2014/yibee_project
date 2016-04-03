package net.yibee.weixin.action.system;

import net.yibee.weixin.entity.ImageArticle;
import net.yibee.weixin.entity.Menu;
import net.yibee.weixin.util.AccessTokenTool;
import net.yibee.weixin.util.SignUtil;
import net.yibee.weixin.util.WeiXinUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证微信
 * Created by wangjie on 16/2/23.
 */

@Controller
@RequestMapping("/system/ConnectionWeixin")
public class ConnectionWeixin {
    private static Logger logger = Logger.getLogger(ConnectionWeixin.class);

    @RequestMapping()
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
        // 暂时空着，在这里可处理用户请求
    }
        @RequestMapping("/getAccessToken")
    public String getAccessToken(HttpServletRequest request, HttpServletResponse response){
        String accessToken = AccessTokenTool.getAccessToken();
        Menu menu = WeiXinUtil.initMenu();
        String result = WeiXinUtil.createMenu(accessToken,menu);
        logger.info("##############################");
        logger.info(result);
        logger.info(accessToken);
        logger.info("##############################");
        return "";
    }

    @RequestMapping("/uploadArticle")
    public String uploadArticle(HttpServletRequest request, HttpServletResponse response){
        String accessToken = AccessTokenTool.getAccessToken();
        ImageArticle imageArticle = WeiXinUtil.initImageArticle();
        String result = WeiXinUtil.uploadNews(accessToken,imageArticle);
        logger.info("##############################");
        logger.info(result);
        logger.info(accessToken);
        logger.info("##############################");
        return "";
    }
}
