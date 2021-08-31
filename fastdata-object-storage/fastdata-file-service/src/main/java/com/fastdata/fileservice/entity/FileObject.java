package com.fastdata.fileservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 3:38 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class FileObject {
    @NotBlank(message = "can't be blank")
    @ApiModelProperty("file name")
    private String fileName;

    @NotBlank(message = "can't be blank")
    @ApiModelProperty("bucket")
    private String bucketName;
}
