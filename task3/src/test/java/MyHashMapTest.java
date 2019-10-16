import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {

    @Test
    public void isEmpty() {
        MyHashMap<String, String> testHashMap = new MyHashMap<>();
        testHashMap.put("3", "hello");
        Assert.assertFalse(testHashMap.isEmpty());
    }

    @Test
    public void containsKey() {
        MyHashMap<String, String> testHashMap = new MyHashMap<>();
        testHashMap.put("3", "hello");
        Assert.assertTrue(testHashMap.containsKey("3"));
    }

    @Test
    public void containsValue() {
        MyHashMap<String, String> testHashMap = new MyHashMap<>();
        testHashMap.put("3", "hello");
        testHashMap.put("world", "me");
        Assert.assertTrue(testHashMap.containsValue("me"));
    }

    @Test
    public void get() {
        MyHashMap<String, String> testHashMap = new MyHashMap<>();
        testHashMap.put("3", "hello");
        Assert.assertEquals("hello", testHashMap.get("3"));
    }

    @Test
    public void put() {
        MyHashMap<String, String> testHashMap = new MyHashMap<>();
        Assert.assertEquals("hello", testHashMap.put("3", "hello"));
    }
}
