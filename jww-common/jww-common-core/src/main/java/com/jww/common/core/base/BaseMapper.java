package com.jww.common.core.base;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * mapper父接口
 *
 * @author wanyong
 * @date 2017/11/12 11:54
 */
public interface BaseMapper<T extends BaseModel> extends com.baomidou.mybatisplus.mapper.BaseMapper<T> {

    List<Long> selectIdPage(@Param("cm") Map<String, Object> params);

    List<Long> selectIdPage(RowBounds rowBounds, @Param("cm") Map<String, Object> params);

}