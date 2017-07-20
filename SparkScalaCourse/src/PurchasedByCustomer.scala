import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by sgloutnikov on 7/15/17.
  */
object PurchasedByCustomer {

  def parseLine(line: String) = {
    val lineData = line.split(",")
    val customer = lineData(0).toInt
    val pricePaidForItem = lineData(2).toFloat
    (customer, pricePaidForItem)
  }

  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sparkConfig = new SparkConf().setAppName("PurchasedByCustomer").setMaster("local[*]")
    val sc = new SparkContext(sparkConfig)

    val rawData = sc.textFile("customer-orders.csv")

    val parsedData = rawData.map(parseLine)

    val customerTotals = parsedData.reduceByKey((x, y) => x + y)

    val customerTotalsSorted = customerTotals.map(x => (x._2, x._1)).sortByKey().collect()

    customerTotalsSorted.foreach(println)

  }

}
