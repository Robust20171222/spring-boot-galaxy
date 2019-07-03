package com.galaxy.bigdata.spark.hdfs

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Parsing an XML of the FsImage HDFS type.
  *
  * @author pengwang
  * @date 2019/07/04
  */
case class FsImage(sparkSession: SparkSession) extends Serializable {
  def compute(pathFsImage: String): DataFrame = {
    val df = sparkSession.sqlContext.read
      .format("com.databricks.spark.xml")
      .option("rowTag", "inode")
      .option("nullValue", "")
      .load(pathFsImage)

    //val h = df.select($"replication".isNotNull cast "double").rdd.map(x => x.getDouble(0)).histogram(5)
    df.toDF(df.columns map (_.toLowerCase): _*)
  }
}