package com.galaxy.bigdata.hadoop

import org.junit.Test

/**
  * @link https://hadoop.apache.org/docs/r2.6.3/hadoop-yarn/hadoop-yarn-site/WritingYarnApplications.html
  * @link https://github.com/apache/samza/blob/master/samza-autoscaling/src/main/java/org/apache/samza/autoscaling/utils/YarnUtil.java
  */
class YarnTest {

  @Test
  def testGetApplicationState: Unit = {
    val yarnUtil = new YarnUtil("http://10.104.114.104", 8088)
    println(yarnUtil.getApplicationState("application_1554712154936_0006"))
  }
}
