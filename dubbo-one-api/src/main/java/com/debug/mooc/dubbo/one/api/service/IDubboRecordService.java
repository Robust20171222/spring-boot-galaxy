package com.debug.mooc.dubbo.one.api.service;

import com.debug.mooc.dubbo.one.api.request.PushOrderDto;
import com.debug.mooc.dubbo.one.api.response.BaseResponse;

/**
 * 提供下单服务业务逻辑操作
 */
public interface IDubboRecordService {

    public BaseResponse pushOrder(PushOrderDto dto);

}
