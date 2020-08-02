package kaavya;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

// LRU Cache
// Design and implement a data structure for a Least Recently Used (LRU) cache

// API
// get(key) - Get the value of the key if the key exists in the cache, otherwise return null. Consider this node "used"
// set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should
//                   remove the least recently used item before inserting a new item. If key is already present, consider this node used

class LRUAlgorithm{
  HashMap<String, Item> mapper = new HashMap<String, Item> (); 
  int capacity; 
  
//  public HashMap<String, Item> getMapper() {
//	return mapper;
//}
//
//public void setMapper(HashMap<String, Item> mapper) {
//	this.mapper = mapper;
//}
//
//public int getCapacity() {
//	return capacity;
//}
//
//public void setCapacity(int capacity) {
//	this.capacity = capacity;
//}

Item getItem(String k){
	  System.out.println("Get called");
    //check if found
    Item i = mapper.get(k);
    if ( i != null) 
      i.date = LocalDateTime.now();
    
    //get the item  
    return i;
    
  }
  
  void putItem(String k, Item v){
    //check if capacity is reached
	System.out.println("Capacity is: " + capacity);
    if (mapper.size() >= capacity) {
              
      //find the oldest item
        Item oldItem = v;
        LocalDateTime old = LocalDateTime.now();  

        Collection<Item> valuesColl= mapper.values();
        for(Item item : valuesColl) {
          if(item.date.isBefore(old)) {
              old = item.date;
              oldItem = item;
          }
        } //end for
      
      //evict the LRU 
      mapper.remove(oldItem.key);
    }
    
    //insert
    v.date = LocalDateTime.now();
    mapper.put(k, v);
    
  }
  

  public static void main(String[] args) {
      
	  LRUAlgorithm s = new LRUAlgorithm();
      s.capacity = 3;
      
      //HashMap<String, Item> map = s.map;
      s.putItem("a", s.new Item("a", "apple"));
      s.putItem("b", s.new Item("b", "bbb"));
      s.putItem("c", s.new Item("c", "ccc"));

      System.out.println("Map contents: ");
      Collection<Item> valuesColl= s.mapper.values();
        for(Item item : valuesColl) {
        	System.out.println(s.getItem(item.key));
        }
    
      s.putItem("d", s.new Item("d", "ddd"));
      System.out.println("Map contents after eviction: ");
      System.out.println(s.mapper.values());
     
    
  }
  
   class Item{
    String key;
    String value;
    LocalDateTime date;
    
    Item(String key, String value){
      this.key = key;
      this.value = value;
    }
    public String toString(){
      return ("Key: " + this.key + " Value: " + this.value + " timestamp: " + date);
      
    }
  }
  
}

