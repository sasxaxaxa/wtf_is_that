package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {

        //тесты

        frequencyOfWords();
    }

    //через стримы

    public static void frequencyOfWordsStream() {

    }

    //через мои кривые руки

    public static void frequencyOfWords() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = bufferedReader.readLine();

            //стащила регулярку из гпт
            str = str.replaceAll("[^\\p{L}0-9]+", " ");
            List<String> words = Arrays.asList(str.toLowerCase().split(" "));

            Map<String, Integer> wordsAndCount = new HashMap<>();
            int count = 0;
            for (int i = 0; i < words.size(); i++) {
                String currentWord = words.get(i);

                if (!wordsAndCount.containsKey(currentWord)) {
                    count = 1;
                    for (int j = i + 1; j < words.size(); j++) {
                        if (currentWord.equals(words.get(j))) {
                            count++;
                        }
                    }
                    wordsAndCount.put(currentWord, count);
                } else {
                    wordsAndCount.replace(currentWord, count);
                }
            }

            for (Map.Entry<String, Integer> entry : wordsAndCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {

    }
}
