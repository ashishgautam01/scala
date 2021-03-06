import scala.util._
import scala.collection.mutable.Stack
import scala.io.StdIn.readInt

object dijkstra {
  def main(args: Array[String]): Unit = {
  
    val graph: Array[Array[Int]] = Array(Array(0,1,4,0),
    	                                   Array(1,0,2,0),
                                         Array(4,2,0,2),
                                         Array(0,0,2,0))
    
    val V = graph.size
    var MAX = Integer.MAX_VALUE
    val obj = new diji(V,MAX)
    obj.calc(graph, 0)
  }
}
class diji(V: Int,MAX: Int) {	    
    
    def minDis(dist: Array[Int], visitlist: Array[Boolean]): Int = {
   		var min: Int = MAX
   		var min_count: Int = 0
    	for (v <- 0 until V; if (visitlist(v) == false && dist(v) <= min)) 
    		 {
      			min = dist(v)
     			min_count = v
    		}
    	min_count
  	}

 	 def printSolution(dist: Array[Int]): Unit = {
 	   println("Vertex \t      Distance from Source")
 	   for (i <- 0 until V) println(i + " \t      " + dist(i))
	 }

  def calc(graph: Array[Array[Int]], src: Int): Unit = {
    val dist: Array[Int] = Array.ofDim[Int](V)
    val visitlist: Array[Boolean] = Array.ofDim[Boolean](V) //false if not included in final result
    for (i <- 0 until V) {
      dist(i) = MAX
      visitlist(i) = false
    }
    dist(src) = 0
    for (count <- 0 until V - 1) {
      val u: Int = minDis(dist, visitlist)
            visitlist(u) = true      
      for (v <- 0 until V; if (!visitlist(v) && graph(u)(v) != 0 ))           
	        if( dist(u) != MAX && dist(u) + graph(u)(v) < dist(v)) 
    			   dist(v) = dist(u) + graph(u)(v)
     }
    printSolution(dist) 	 	 	
  }

}