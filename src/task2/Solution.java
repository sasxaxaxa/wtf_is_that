package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {

        //тесты
        test();

    }

    public static void test() {
        String str1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, " +
                "faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. " +
                "Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, " +
                "ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Integer vel odio nec mi tempor dignissim.\n";

        String str2 = "Мама мыла-мыла-мыла раму!";

        String str3 = "AAA aaa aaa99 AAA bbb BBB!!! bbb        BBB aaa AaA aAa AAa AAA bbb; bbb; bbb, Aaa99,";

        printMap(sorting(initializeMap(str1)));
        printMap(sorting(initializeMap(str2)));
        printMap(sorting(initializeMap(str3)));

    }

    //через стримы

    public static void frequencyOfWordsStream() {

    }

    //через мои кривые руки

    public static void frequencyOfWords() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = bufferedReader.readLine();

            Map<String, Integer> map = initializeMap(str);

            printMap(sorting(map));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> initializeMap(String str) {
        str = str.replaceAll("[^\\p{L}0-9]+", " ");
        List<String> words = Arrays.asList(str.toLowerCase().split(" "));

        Map<String, Integer> wordsAndCount = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            String currentWord = words.get(i);
            wordsAndCount.put(currentWord, wordsAndCount.getOrDefault(currentWord, 0) + 1);
        }

        return wordsAndCount;
    }

    public static Map<String, Integer> sorting(Map<String, Integer> map) {

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        entryList.sort((o1, o2) -> {
            int comp = o2.getValue().compareTo(o1.getValue());

            if (comp == 0) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return comp;
        });

        Map<String, Integer> sortMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : entryList) {
            sortMap.put(entry.getKey(), entry.getValue());
        }

        return sortMap;
    }

    public static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + "; ");
        }
        System.out.println();
    }
}
