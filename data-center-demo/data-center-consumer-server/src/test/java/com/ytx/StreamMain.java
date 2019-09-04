package com.ytx;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMain {
    /*private static Cache<String,Object> cache = CacheBuilder.newBuilder()
            .maximumSize(500)
            .expireAfterWrite(1, TimeUnit.MICROSECONDS)
            .build();*/

    //数值流的构造
    private static void do5(){
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        System.out.println("---------------------");
        IntStream.range(1, 3).forEach(System.out::println);
        System.out.println("---------------------");
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
        System.out.println("---------------------");
    }

    //流转换为其它数据结构
    private static void do6(){

//        // 1. Array
//        String[] strArray1 = stream.toArray(String[]::new);
//        // 2. Collection
//        List<String> list1 = stream.collect(Collectors.toList());
//        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
//        Set set1 = stream.collect(Collectors.toSet());
//        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
//        // 3. String
//        String str = stream.collect(Collectors.joining()).toString();
    }
    private static void do13(){
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    private static void ex(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.api861861.com/lottery/getList.do?name=&lotCode=&pageNo=&pageSize=100";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String responseStr = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(responseStr, JsonObject.class);

        JsonArray asJsonArray = json.get("result").getAsJsonObject()
                .get("data").getAsJsonArray();

        Iterator<JsonElement> iterator = asJsonArray.iterator();
        while(iterator.hasNext()) {
            JsonObject next = iterator.next().getAsJsonObject();
            System.out.println(next.get("name").getAsString());
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        int sum = list.stream().reduce(10,Integer::sum);
        List<String> l = Arrays.asList("1", "2");
        System.out.println(sum);
    }
}
