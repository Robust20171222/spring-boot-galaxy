package com.galaxy.dao;

import com.galaxy.model.XxlJobLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * job log
 * @author xuxueli 2016-1-12 18:03:06
 */
public interface XxlJobLogDao {
	
	public List<XxlJobLog> pageList(@Param("offset") int offset,
									@Param("pagesize") int pagesize,
									@Param("jobGroup") int jobGroup,
									@Param("jobId") int jobId,
									@Param("triggerTimeStart") Date triggerTimeStart,
									@Param("triggerTimeEnd") Date triggerTimeEnd,
									@Param("logStatus") int logStatus);
	public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("jobGroup") int jobGroup,
                             @Param("jobId") int jobId,
                             @Param("triggerTimeStart") Date triggerTimeStart,
                             @Param("triggerTimeEnd") Date triggerTimeEnd,
                             @Param("logStatus") int logStatus);

}
