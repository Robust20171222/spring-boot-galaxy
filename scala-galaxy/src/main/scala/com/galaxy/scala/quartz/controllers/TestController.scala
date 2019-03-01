package com.galaxy.scala.quartz.controllers

import com.galaxy.scala.quartz.service.JobsService
import org.quartz.SchedulerException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.collection.mutable.ListBuffer

/**
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 14:51
  */
@RestController
@RequestMapping(value = Array("/quartz"))
class TestController {

  @Autowired
  private var jobsService: JobsService = _

  /**
    * 添加Job
    *
    * @param jobs
    * @throws
    * @return
    */
  @RequestMapping(value = Array("/addJobs"), method = Array(RequestMethod.POST), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
  @throws[SchedulerException]
  def addJobs(@RequestParam(defaultValue = "10", required = false) jobs: Int): ResponseEntity[ListBuffer[String]] = {
    val ids = this.jobsService.addNewJobs(jobs)
    ResponseEntity.status(HttpStatus.OK).body(ids)
  }

  /**
    * 删除Job
    *
    * @param id
    * @throws
    * @return
    */
  @RequestMapping(value = Array("/deleteJob/{id}"), method = Array(RequestMethod.DELETE), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
  @throws[SchedulerException]
  def deleteJob(@PathVariable("id") id: String): ResponseEntity[List[String]] = {
    val result = this.jobsService.deleteJob(id)
    ResponseEntity
      .status(if (result) HttpStatus.OK else HttpStatus.NOT_FOUND)
      .build()
  }

  /**
    * 获取所有任务名称
    *
    * @throws
    * @return
    */
  @RequestMapping(value = Array("/getJobs"), method = Array(RequestMethod.GET), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
  @throws[SchedulerException]
  def getJobs: ResponseEntity[List[String]] = {
    val ids = this.jobsService.getJobs
    ResponseEntity
      .status(HttpStatus.OK)
      .body(ids)
  }

  /**
    * 获取所有任务状态
    *
    * @throws
    * @return
    */
  @RequestMapping(value = Array("/status/jobs"), method = Array(RequestMethod.GET), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
  @throws[SchedulerException]
  def getJobsStatuses: ResponseEntity[ListBuffer[JobStatus]] = {
    val ids = this.jobsService.getJobsStatuses
    ResponseEntity
      .status(HttpStatus.OK)
      .body(ids)
  }
}
