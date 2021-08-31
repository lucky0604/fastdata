package com.fastdata.fileservice.exception;

import com.fastdata.common.core.exception.BaseException;
import com.fastdata.common.core.exception.ErrorType;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 3:47 PM
 * @Version: 1.0
 * @Description:
 **/

public class MinioFileException extends BaseException {

    public MinioFileException() {
        super(MinioFileType.FILE_GET_FAILED);
    }

    public MinioFileException(String msg) {
        super(MinioFileType.FILE_UPLOAD_FAILED, msg);
    }

    public MinioFileException(ErrorType errorType, String msg) {
        super(errorType, msg);
    }
}
