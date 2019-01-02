package lrucache;

/*
 * Daniel Valencia
 */
public class LRUCacheTester {

    
    public static void main(String[] args) {
        
        LRUCache cache = new LRUCache();
        
        cache.putEntry(0, 1);
        int value = cache.get(0);
        System.out.println("Cache Contents:");
        System.out.println(cache.list());
        test("Get 0 should be 1", value == 1);
        
        cache.putEntry(1,10);
	cache.putEntry(2,20);
	cache.putEntry(3,30);
	cache.putEntry(4,40);
	System.out.println("\nCache Contents:");
	System.out.println(cache.list());
        test("Get 0 should be -1", cache.get(0) == -1);
        test("Get 1 should be 10", cache.get(1) == 10);
        test("Get 2 should be 20", cache.get(2) == 20);
        test("Get 3 should be 30", cache.get(3) == 30);
        
    }
    
    public static void test(String message, boolean test) {

	System.out.print(message + " ..... ");
        
	if(test) {
            System.out.println("Passed");
	}
        else {
            System.out.println("Failed");
	}
		
    }
}
