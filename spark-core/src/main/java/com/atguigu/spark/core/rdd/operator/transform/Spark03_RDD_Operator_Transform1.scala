package com.atguigu.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @description:
 * @author: bansheng
 * @date: 2023/09/16 9:25
 * */
object Spark03_RDD_Operator_Transform1 {
  def main(args: Array[String]): Unit = {
    //连接环境
    val conf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(conf)
    //TODO 算子
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    //取出各分区的数据
    val makeRDD: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex((index: Int, iter: Iterator[Int]) => {
      iter.map((num: Int) => (index, num))
    })
    makeRDD.collect().foreach(println)
    //关闭环境
    sc.stop()
  }
}
