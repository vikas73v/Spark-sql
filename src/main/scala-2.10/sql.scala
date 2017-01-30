import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by VIKAS on 1/15/2017.
  */
object sql {
  def main(args: Array[String]) : Unit ={
    val conf= SparkUtilities.sparkConfig()


    //val conf= new SparkConf().setAppName("tetb").setMaster("local")
    val sc=new SparkContext(conf)
    val hc= new HiveContext(sc)


    val tet1= SparkUtilities.getDataFrame(hc, "default", "employee","te")
    //tet1.registerTempTable("te")


    val dept= hc.sql("select * from dept")
    dept.registerTempTable("dept")

    val location= hc.sql("select * from location")
    location.registerTempTable("location")

    val join= hc.sql (s"select A.*,B.*,C.* from ${tet1} A left semi join dept B on(A.id=B.id) left semi join C on (A.id= C.id)")

    join.registerTempTable("join_tables")
  }
}
