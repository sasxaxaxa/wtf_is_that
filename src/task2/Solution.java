package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        switch (args[0]) {
            case "-t" -> solution.test();
            default -> solution.input();
        }
    }

    public void test() {
        String str1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, " +
                "faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. " +
                "Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, " +
                "ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Integer vel odio nec mi tempor dignissim.";
        String str2 = "Мама мыла-мыла-мыла раму!";
        String str3 = "AAA aaa aaa99 AAA bbb BBB!!! bbb        BBB aaa AaA aAa AAa AAA bbb; bbb; bbb, Aaa99,";
        String str4 = "1 2 2 3 3 3 4 4 4 4 5 5 5 5 5 6 6 6 6 6 6 7 7 7 7 7 7 7";
        run(str1);
        run(str2);
        run(str3);
        run(str4);
    }

    public void run(String str) {
        System.out.printf("%s%n%n", str);
        frequencyOfWordsStream(str);
        System.out.println();
        frequencyOfWords(str);
    }

    public void frequencyOfWordsStream(String str) {
        String[] words = str.toLowerCase().replaceAll("[^\\p{L}0-9]+", " ").split(" ");

        Map<String, Long> wordsAndCount = Arrays.stream(words)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        wordsAndCount.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue)
                        .reversed().thenComparing(Map.Entry::getKey)).limit(10).
                forEach(etnry -> System.out.println(etnry.getKey()));
    }

    public void frequencyOfWords(String str) {
        printWords(initializeAndSortMap(str), 10);
    }

    public void input() {
        String str = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        run(str);
    }

    public Map<String, Integer> initializeAndSortMap(String str) {
        String[] words = str.toLowerCase().replaceAll("[^\\p{L}0-9]+", " ").split(" ");
        Map<String, Integer> wordsAndCount = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            wordsAndCount.put(currentWord, wordsAndCount.getOrDefault(currentWord, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordsAndCount.entrySet());
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

    public void printWords(Map<String, Integer> map, int count) {
        if (map.size() < count) {
            count = map.size();
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (count == 0) {
                break;
            }
            System.out.println(entry.getKey());
            count--;
        }
        System.out.println();
    }
}
