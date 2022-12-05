import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HashMapExample {
  
  public static void main(String[] args){

  //Key /value type
  //String , Integer
  HashMap<String, Integer> mymap = new HashMap<String, Integer>();

  //Add a key, value pair to it
  mymap.put("bala", 30);
  mymap.put("ken", 40);

  //.get(key) returns value
  String key = "bala";
  System.out.println("Value for key = " + key + "-->" + mymap.get(key)); 

  //update key
  mymap.put("bala", 31);
  System.out.println("New value for key = " + key + "-->" + mymap.get(key));

  //check if a key exists in map
  System.out.println("check if fred exists: " + mymap.containsKey("fred"));
  
  // usermap.get("bala").remove("item1");

  //HashMap with ArrayList as a value
  HashMap<String, ArrayList<String>> usermap = new HashMap<String, ArrayList<String>>();

  usermap.put("bala", new ArrayList<String>(Arrays.asList("item1", "item2")));
  usermap.put("fred", new ArrayList<String>());
  usermap.put("ken", new ArrayList<String>());
  

  }
}

