import org.apache.spark.SparkConf
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.hive.HiveContext

/**
  * Created by VIKAS on 1/16/2017.
  */
object SparkUtilities {

  def sparkConfig( ) : SparkConf = {
    val sparkConf= new SparkConf().setAppName("tetb").setMaster("local")
    sparkConf
  }

  def getDataFrame(hc:HiveContext, database: String, inputTable: String, outputTable: String) : DataFrame = {
    val dF= hc.sql("select * from finance_store_dev.inputTable")
    dF.registerTempTable(outputTable)
    dF
  }

}
