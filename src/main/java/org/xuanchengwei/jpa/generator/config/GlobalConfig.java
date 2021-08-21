package org.xuanchengwei.jpa.generator.config;


import lombok.Data;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XUAN-CW
 * @date 2021/8/21 - 1:28
 */
@Data
public class GlobalConfig {

    /**
     * 生成文件的输出目录
     */
    private String outputDir;

    /**
     * author
     */
    private String author = "XUAN_CW";

    /**
     * 创建时间
     */
    Date date = new Date();

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

}
