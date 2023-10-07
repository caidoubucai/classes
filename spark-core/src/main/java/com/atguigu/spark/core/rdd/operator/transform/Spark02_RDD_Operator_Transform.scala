package com.atguigu.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @description:
 * @author: bansheng
 * @date: 2023/09/16 9:25
 * */
object Spark02_RDD_Operator_Transform {
  def main(args: Array[String]): Unit = {
    //连接环境
    val conf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(conf)
    //TODO 算子 -mapPartitions
    //以分区为单位，但是不会释放资源，容易导致内存溢出
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    val makeRDD: RDD[Int] = rdd.mapPartitions((iter: Iterator[Int]) => {
      println(">>>>>>>>>>>>>>>>>>>>")
      iter.map(_ * 2)
    })
    makeRDD.collect().foreach(println)
    //关闭环境
    sc.stop()
  }
}
