import org.apache.spark._
import org.apache.log4j._


object FriendsByName {

  /** A function that splits a line of input into (name, numFriends) tuples. */
  def parseLine(line: String) = {
    val fields = line.split(",")
    val name = fields(1)
    val numFriends = fields(3).toInt
    (name, numFriends)
  }

  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "FriendsByAge")

    // Load each line of the source data into an RDD
    val lines = sc.textFile("fakefriends.csv")

    val rdd = lines.map(parseLine)

    val totalsByName = rdd.mapValues(x => (x, 1)).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))

    val averagesByName = totalsByName.mapValues(x => x._1 / x._2)

    val results = averagesByName.collect()

    results.sorted.foreach(println)

  }

}
