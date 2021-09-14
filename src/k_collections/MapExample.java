package k_collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// 1. Проблемы кода
// 2. Атомарный тип
// 3. compute Java-8
public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        map.putIfAbsent("Four", 4);


        Integer oldValue = null;
        Integer newValue = null;
        do {
            oldValue = map.get("Four");
            newValue = oldValue == null? 0 : oldValue + 1;
        } while (!map.replace("Four", oldValue, newValue));


        System.out.println(map);
        computeExample();
    }


    public void atomic(){
        Map<String, AtomicInteger> map = new ConcurrentHashMap<>();
        map.put("One", new AtomicInteger(1));
        map.put("Two", new AtomicInteger(2));
        map.put("Three", new AtomicInteger(3));

        map.get("Three").incrementAndGet();

        System.out.println(map);
    }

    public static void computeExample(){ //Java 8
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        //map.put("Four", 4);

        //map.compute("Four", (k, v) -> v == null ? 0 : v + 1);

        map.merge("Four", 1, Integer::sum);

        System.out.println(map);
    }
}
