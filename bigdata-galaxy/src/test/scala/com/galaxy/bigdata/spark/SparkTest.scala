package com.galaxy.bigdata.spark

import org.apache.spark.sql.SparkSession
import org.junit.Test

/**
  *
  *
  * @author pengwang
  * @date 2019/10/22
  */
class SparkTest {

  val spark = SparkSession.builder().master("local[1]").appName("SparkByExample").getOrCreate()

  /**
    * Below is an example on how to create a SparkSession using builder pattern method and SparkContext.
    * SparkContext will be created only once for an application; even if you try to create another SparkContext, it still return existing SparkContext.
    */
  @Test
  def sparkSessionTest: Unit = {
    val spark = SparkSession.builder().master("local[1]").appName("SparkByExample").getOrCreate()

    println("First SparkContext:")
    println("APP Name :" + spark.sparkContext.appName)
    println("Deploy Mode :" + spark.sparkContext.deployMode)
    println("Master :" + spark.sparkContext.master)

    val sparkSession2 = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample-test")
      .getOrCreate()

    println("Second SparkContext:")
    println("APP Name :" + sparkSession2.sparkContext.appName)
    println("Deploy Mode :" + sparkSession2.sparkContext.deployMode)
    println("Master :" + sparkSession2.sparkContext.master)
  }

  /**
    * Resilient Distributed Datasets (RDD) is the fundamental data structure of Spark.
    * RDDs are immutable and fault-tolerant in nature. RDD is just the way of representing Dataset distributed across multiple nodes in a cluster, which can be operated in parallel.
    * RDDs are called resilient because they have the ability to always re-compute an RDD when a node failure.
    */
  @Test
  def testRDDTest: Unit = {
    val rdd = spark.sparkContext.parallelize(Seq(("Java", 20000), ("Python", 100000), ("Scala", 3000)))
    rdd.foreach(println)
  }

  /**
    * DataFrame is a distributed collection of data organized into named columns.
    * It is conceptually equivalent to a table in a relational database or a data frame in R/Python, but with richer optimizations under the hood.
    * DataFrames can be constructed from a wide array of sources such as: structured data files, tables in Hive, external databases, or existing RDDs.
    */
  @Test
  def testDataFrame: Unit = {
    import spark.implicits._

    val columns = Seq("language", "users_count")
    val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))
    val rdd = spark.sparkContext.parallelize(data)

    val dfFromRDD1 = rdd.toDF()
    dfFromRDD1.printSchema()

    // takes arguments for custom column names
    val dfFromRDD2 = rdd.toDF("language", "users_count")
    dfFromRDD2.printSchema()

    // Using createDataFrame() from SparkSession is another way to create and it takes rdd object as an argument. and chain with toDF() to specify names to the columns.
    val dfFromRDD3 = spark.createDataFrame(rdd).toDF(columns: _*)

    val dfFromData1 = data.toDF()
    //From Data (USING createDataFrame)
    var dfFromData2 = spark.createDataFrame(data).toDF(columns: _*)
  }

  /**
    * Read and Write Apache Parquet file in Spark
    */
  @Test
  def testParquet: Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    val data = Seq(("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1)
    )

    val columns = Seq("firstname", "middlename", "lastname", "dob", "gender", "salary")
    import spark.sqlContext.implicits._
    val df = data.toDF(columns: _*)

    df.show()
    df.printSchema()

    df.write
      .parquet("/tmp/output/people.parquet")

    val parqDF = spark.read.parquet("/tmp/output/people.parquet")
    parqDF.createOrReplaceTempView("ParquetTable")

    spark.sql("select * from ParquetTable where salary >= 4000").explain()
    val parkSQL = spark.sql("select * from ParquetTable where salary >= 4000 ")

    parkSQL.show()
    parkSQL.printSchema()

    df.write
      .partitionBy("gender", "salary")
      .parquet("/tmp/output/people2.parquet")

    val parqDF2 = spark.read.parquet("/tmp/output/people2.parquet")
    parqDF2.createOrReplaceTempView("ParquetTable2")

    val df3 = spark.sql("select * from ParquetTable2  where gender='M' and salary >= 4000")
    df3.explain()
    df3.printSchema()
    df3.show()

    val parqDF3 = spark.read
      .parquet("/tmp/output/people2.parquet/gender=M")
    parqDF3.show()
  }

  @Test
  def testFlightData: Unit = {
    this.spark.conf.set("spark.sql.shuffle.partitions", "5")
    val flightData2015 = spark.read.option("inferSchema", true).option("header", true).csv("src/test/resources/data/flight-data/csv/2015-summary.csv")
    flightData2015.sort("count").take(2).foreach(println(_))

    flightData2015.createOrReplaceTempView("flight_data_2015")

    val sqlWay = spark.sql(
      """
        SELECT DEST_COUNTRY_NAME, count(1)
        FROM flight_data_2015
        GROUP BY DEST_COUNTRY_NAME
     """)
    val dataFrameWay = flightData2015.groupBy("DEST_COUNTRY_NAME").count()

    sqlWay.explain()
    dataFrameWay.explain()

    this.spark.sql("SELECT max(count) from flight_data_2015").take(1).foreach(println(_))

    import org.apache.spark.sql.functions.max
    flightData2015.select(max("count")).take(1).foreach(println(_))

    val maxSql = spark.sql(
      """
    SELECT DEST_COUNTRY_NAME, sum(count) as destination_total
    FROM flight_data_2015
    GROUP BY DEST_COUNTRY_NAME
    ORDER BY sum(count) DESC
    LIMIT 5
    """)
    maxSql.show()

    import org.apache.spark.sql.functions.desc
    flightData2015
      .groupBy("DEST_COUNTRY_NAME")
      .sum("count")
      .withColumnRenamed("sum(count)", "destination_total")
      .sort(desc("destination_total"))
      .limit(5)
      .explain()

    while (true) {

    }
  }
}