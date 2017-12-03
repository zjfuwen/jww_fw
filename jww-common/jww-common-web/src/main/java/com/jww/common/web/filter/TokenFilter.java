package com.jww.common.web.filter;

import com.alibaba.fastjson.JSON;
import com.jww.common.core.Constants;
import com.jww.common.core.model.TokenModel;
import com.jww.common.core.util.TokenUtil;
import com.jww.common.web.util.WebUtil;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShenHuaJie
 * @since 2017年3月19日 上午10:21:59
 */
@Slf4j
public class TokenFilter implements Filter {

    /**
     * 白名单
     */
    private List<String> whiteUrls;

    private int size = 0;

    @Override
    public void init(FilterConfig config) throws ServletException {
        // 读取文件
        String path = CsrfFilter.class.getResource("/").getFile();
        whiteUrls = FileUtil.readUtf8Lines(path + "token-white.txt");
        size = null == whiteUrls ? 0 : whiteUrls.size();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (isWhiteReq(request.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            String uuid = request.getHeader("UUID");
            if (StrUtil.isNotBlank(uuid)) {
                try {
                    TokenModel tokenInfo = TokenUtil.getTokenInfo(uuid);
                    if (tokenInfo != null) {
                        Long now = System.currentTimeMillis();
                        Long millis = 1000 * 60 * 30L;
                        if (now - tokenInfo.getTime() < millis) {
                            String value = tokenInfo.getValue();
                            TokenUtil.setTokenInfo(uuid, value);
                            WebUtil.saveCurrentUser(request, value);
                        }
                    }
                } catch (Exception e) {
                    //TODO 使用消息中间件记录日志
                    log.error("token检查发生异常:", e);
                }
            }
            // 响应
            if (ObjectUtil.length(WebUtil.getCurrentUser(request)) <= 0) {
                response.setContentType("text/html; charset=UTF-8");
                Map<String, Object> modelMap = new LinkedHashMap<>(3);
                modelMap.put("httpCode", Constants.ResultCodeEnum.UNAUTHORIZED.value());
                modelMap.put("msg", Constants.ResultCodeEnum.UNAUTHORIZED.getMessage());
                modelMap.put("timestamp", System.currentTimeMillis());
                PrintWriter out = response.getWriter();
                out.println(JSON.toJSONString(modelMap));
                out.flush();
                out.close();
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 判断是否是白名单
     *
     * @param requestUrl 请求URL
     * @return boolean
     */
    private boolean isWhiteReq(String requestUrl) {
        if (size == 0) {
            return true;
        } else {
            for (String urlTemp : whiteUrls) {
                if (requestUrl.contains(urlTemp.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}
