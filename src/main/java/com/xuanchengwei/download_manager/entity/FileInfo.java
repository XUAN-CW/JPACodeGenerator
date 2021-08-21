package com.xuanchengwei.download_manager.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author XUAN_CW
 * @since 2021-08-20
 */
@Data
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 下载来源，见download_info.id
     */
    private Integer downloadFrom;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件大小(B)
     */
    private Double length;

    /**
     * MD5
     */
    private String md5;


}
