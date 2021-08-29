package com.fastdata.common.core.exception;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/28/21 10:41 PM
 * @Version: 1.0
 * @Description:
 **/

public interface ErrorType {

    /**
     * return code
     * @return
     */
    String getCode();

    /**
     * return msg
     * @return
     */
    String getMsg();
}
