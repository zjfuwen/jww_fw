function navBar(strData) {
    var data;
    if (typeof(strData) == "string") {
        var data = JSON.parse(strData); //部分用户解析出来的是字符串，转换一下
    } else {
        data = strData;
    }
    var ulHtml = '<ul class="layui-nav layui-nav-tree">';
    for (var i = 0; i < data.length; i++) {
        if (data[i].expand == 1) {
            ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
        } else {
            ulHtml += '<li class="layui-nav-item">';
        }
        if (data[i].menuBeans != undefined && data[i].menuBeans.length > 0) {
            ulHtml += '<a href="javascript:;">';
            if (data[i].iconcls != undefined && data[i].iconcls != '') {
                if (data[i].iconcls.indexOf("icon-") != -1) {
                    ulHtml += '<i class="iconfont ' + data[i].iconcls + '" data-icon="' + data[i].iconcls + '"></i>';
                } else {
                    ulHtml += '<i class="layui-icon" data-icon="' + data[i].iconcls + '">' + data[i].iconcls + '</i>';
                }
            }
            ulHtml += '<cite>' + data[i].menuName + '</cite>';
            ulHtml += '<span class="layui-nav-more"></span>';
            ulHtml += '</a>';
            ulHtml += '<dl class="layui-nav-child">';
            for (var j = 0; j < data[i].menuBeans.length; j++) {
                if (data[i].menuBeans[j].target == "_blank") {
                    ulHtml += '<dd><a href="javascript:;" data-url="' + data[i].menuBeans[j].request + '" target="' + data[i].menuBeans[j].target + '">';
                } else {
                    ulHtml += '<dd><a href="javascript:;" data-url="' + data[i].menuBeans[j].request + '">';
                }
                if (data[i].menuBeans[j].iconcls != undefined && data[i].menuBeans[j].iconcls != '') {
                    if (data[i].menuBeans[j].iconcls.indexOf("icon-") != -1) {
                        ulHtml += '<i class="iconfont ' + data[i].menuBeans[j].iconcls + '" data-icon="' + data[i].menuBeans[j].iconcls + '"></i>';
                    } else {
                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].menuBeans[j].iconcls + '">' + data[i].menuBeans[j].iconcls + '</i>';
                    }
                }
                ulHtml += '<cite>' + data[i].menuBeans[j].menuName + '</cite></a></dd>';
            }
            ulHtml += "</dl>";
        } else {
            if (data[i].target == "_blank") {
                ulHtml += '<a href="javascript:;" data-url="' + data[i].request + '" target="' + data[i].target + '">';
            } else {
                ulHtml += '<a href="javascript:;" data-url="' + data[i].request + '">';
            }
            if (data[i].iconcls != undefined && data[i].iconcls != '') {
                if (data[i].iconcls.indexOf("icon-") != -1) {
                    ulHtml += '<i class="iconfont ' + data[i].iconcls + '" data-icon="' + data[i].iconcls + '"></i>';
                } else {
                    ulHtml += '<i class="layui-icon" data-icon="' + data[i].iconcls + '">' + data[i].iconcls + '</i>';
                }
            }
            ulHtml += '<cite>' + data[i].menuName + '</cite></a>';
        }
        ulHtml += '</li>';
    }
    ulHtml += '</ul>';
    return ulHtml;
}
