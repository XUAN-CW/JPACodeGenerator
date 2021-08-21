package org.xuanchengwei.jpa.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author XUAN-CW
 * @date 2021/8/21 - 13:50
 */

@Data
public class PackageConfig {

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent;

    /**
     * 父包模块名
     */
    private String moduleName;

    /**
     * module 的 Hyphen 格式
     */
    private String moduleNameInHyphen;

    /**
     * Entity 包名
     */
    private String entity = "entity";

    /**
     * Service 包名
     */
    private String service = "service";

    /**
     * Service Impl 包名
     */
    private String serviceImpl = "service.impl";

    /**
     * Controller 包名
     */
    private String controller = "controller";

    /**
     * Repository 包名
     */
    private String repository = "repository";

}