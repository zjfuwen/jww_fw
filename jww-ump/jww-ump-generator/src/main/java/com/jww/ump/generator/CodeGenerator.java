package com.jww.ump.generator;

import com.jww.common.core.util.MybatisPlusGeneratorUtil;

/**
 * 代码生成器
 *
 * @author wanyong
 * @date 2017/11/25 14:05
 */
public class CodeGenerator {

    public static void main(String[] args) {
        MybatisPlusGeneratorUtil mybatisPlusGeneratorUtil = new MybatisPlusGeneratorUtil();
        String propertiesFilePath = "D:\\SOFT\\idea-workspace\\jww\\jww-ump\\jww-ump-generator\\src\\main\\resources\\generator.properties";
        mybatisPlusGeneratorUtil.generator(propertiesFilePath);
    }

}
