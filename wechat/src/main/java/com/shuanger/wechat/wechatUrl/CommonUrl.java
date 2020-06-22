package com.shuanger.wechat.wechatUrl;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-22 14:39
 * @description:
 */

import com.shuanger.wechat.wechatConfig.WxMpConfigStorage;
import lombok.AllArgsConstructor;

import static com.shuanger.wechat.wechatConfig.WxMpHostConfig.*;

@AllArgsConstructor
public enum CommonUrl implements WxMpApiUrl {

    /**
     * 获取access_token.
     */
    GET_ACCESS_TOKEN_URL(API_DEFAULT_HOST_URL, "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"),
    /**
     * 获得各种类型的ticket.
     */
    GET_TICKET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/ticket/getticket?type="),
    /**
     * 长链接转短链接接口.
     */
    SHORTURL_API_URL(API_DEFAULT_HOST_URL, "/cgi-bin/shorturl"),
    /**
     * 语义查询接口.
     */
    SEMANTIC_SEMPROXY_SEARCH_URL(API_DEFAULT_HOST_URL, "/semantic/semproxy/search"),
    /**
     * 用code换取oauth2的access token.
     */
    OAUTH2_ACCESS_TOKEN_URL(API_DEFAULT_HOST_URL, "/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code"),
    /**
     * 刷新oauth2的access token.
     */
    OAUTH2_REFRESH_TOKEN_URL(API_DEFAULT_HOST_URL, "/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s"),
    /**
     * 用oauth2获取用户信息.
     */
    OAUTH2_USERINFO_URL(API_DEFAULT_HOST_URL, "/sns/userinfo?access_token=%s&openid=%s&lang=%s"),
    /**
     * 验证oauth2的access token是否有效.
     */
    OAUTH2_VALIDATE_TOKEN_URL(API_DEFAULT_HOST_URL, "/sns/auth?access_token=%s&openid=%s"),
    /**
     * 获取微信服务器IP地址.
     */
    GET_CALLBACK_IP_URL(API_DEFAULT_HOST_URL, "/cgi-bin/getcallbackip"),
    /**
     * 网络检测.
     */
    NETCHECK_URL(API_DEFAULT_HOST_URL, "/cgi-bin/callback/check"),
    /**
     * 第三方使用网站应用授权登录的url.
     */
    QRCONNECT_URL(OPEN_DEFAULT_HOST_URL, "/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect"),
    /**
     * oauth2授权的url连接.
     */
    CONNECT_OAUTH2_AUTHORIZE_URL(OPEN_DEFAULT_HOST_URL, "/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect"),
    /**
     * 获取公众号的自动回复规则.
     */
    GET_CURRENT_AUTOREPLY_INFO_URL(API_DEFAULT_HOST_URL, "/cgi-bin/get_current_autoreply_info"),
    /**
     * 公众号调用或第三方平台帮公众号调用对公众号的所有api调用（包括第三方帮其调用）次数进行清零.
     */
    CLEAR_QUOTA_URL(API_DEFAULT_HOST_URL, "/cgi-bin/clear_quota");

    private String prefix;
    private String path;

    public String getUrl(WxMpConfigStorage config) {
        return buildUrl(config.getHostConfig(), prefix, path);
    }
}
