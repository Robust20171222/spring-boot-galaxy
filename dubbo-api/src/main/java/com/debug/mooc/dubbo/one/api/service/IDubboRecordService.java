package com.debug.mooc.dubbo.one.api.service;

import com.debug.mooc.dubbo.one.api.request.PushOrderDto;
import com.debug.mooc.dubbo.one.api.response.BaseResponse;

/**
 * Created by Administrator on 2019/1/14.
 */
public interface IDubboRecordService {

    public BaseResponse pushOrder(PushOrderDto dto);

}
