package com.jww.common.util;

import com.jww.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * @author wanyong
 * @description: Web层辅助类
 * @date 2017/11/16 17:13
 */
@Slf4j
public final class WebUtil {
    private WebUtil() {
    }

    /**
     * 保存当前用户
     */
    public static final void saveCurrentUser(Object user) {
        setSession(Constants.CURRENT_USER, user);
    }

    /**
     * 保存当前用户
     */
    public static final void saveCurrentUser(HttpServletRequest request, Object user) {
        setSession(request, Constants.CURRENT_USER, user);
    }

    /**
     * 获取当前用户
     */
    public static final Long getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            try {
                Session session = currentUser.getSession();
                if (null != session) {
                    return (Long) session.getAttribute(Constants.CURRENT_USER);
                }
            } catch (InvalidSessionException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 获取当前用户
     */
    public static final Object getCurrentUser(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (null != session) {
                return session.getAttribute(Constants.CURRENT_USER);
            }
        } catch (InvalidSessionException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    public static final void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    public static final void setSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        if (null != session) {
            session.setAttribute(key, value);
        }
    }

    /**
     * 移除当前用户
     */
    public static final void removeCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.CURRENT_USER);
    }

    /**
     * 获得国际化信息
     *
     * @param key     键
     * @param request
     * @return
     */
    public static final String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    /**
     * 获取客户端IP
     */
    public static final String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && ip.indexOf(",") > 0) {
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            ip = ip.substring(0, ip.indexOf(","));
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            Enumeration<NetworkInterface> netInterfaces = null;
            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();
                a:
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = netInterfaces.nextElement();
                    log.info("DisplayName:" + ni.getDisplayName());
                    log.info("Name:" + ni.getName());
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = ips.nextElement().getHostAddress();
                        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                            break a;
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        log.debug("getRemoteAddr ip: " + ip);
        return ip;
    }
}
