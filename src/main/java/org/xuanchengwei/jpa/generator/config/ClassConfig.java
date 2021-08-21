package org.xuanchengwei.jpa.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author XUAN-CW
 * @date 2021/8/21 - 0:57
 */
@Data
public class ClassConfig {

    /**
     * Entity 的 Controller 后缀
     */
    private String controller = "Controller";

    /**
     * Entity 的 Service 后缀
     */
    private String service = "Service";

    /**
     * Entity 的 ServiceImpl 后缀
     */
    private String serviceImpl = "ServiceImpl";

    /**
     * Entity 的 Repository 后缀
     */
    private String repository ="Repository";


    /**
     * Entity 的 Entity
     */
    private String entity;


    /**
     * Entity 的 Hyphen 格式
     */
    private String entityInHyphen;

    /**
     * Entity 的 ID 类型
     */
    private Object entityIdType;

}
