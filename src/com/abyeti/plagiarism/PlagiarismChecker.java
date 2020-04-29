package com.abyeti.plagiarism;

//import main.java.com.abyeti.plagiarism.tokenizer.Tokenizer;
//import JavaKeywords;
import com.abyeti.plagiarism.tokenizer.Tokenizer;
import com.abyeti.plagiarism.tokenizer.languages.JavaKeywords;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Integer.max;

public class PlagiarismChecker {
    static File[] files;
    //    static List<String> filesAsString;
//    static List<List<String>> filesAsTokens;
    static Map<File, List<String>> fileTokensMap;

    public static final String TRAINING_DATA_DIR_PATH = "/home/utkarsh/Documents/SampleInputs/Q1/Train";
    public static final String CANDIDATE_FILE_PATH = "/home/utkarsh/Documents/SampleInputs/Q1/Candidate1.java";

    public static void main(String[] args) throws IOException {
        // write your code here
        System.out.println("hello");
//        = new File(CANDIDATE_FILE_PATH);
//        List<String> candidateAsTokens = getTokensFromFile(candidateFile);
        System.out.println(getReportsForDirectory(TRAINING_DATA_DIR_PATH));
    }
    @SuppressWarnings("unchecked")
	public static JSONObject getReportsForDirectory(String path) throws IOException {
        buildTrainingData(path);
         Map<File,Double> redundancyMap;
        redundancyMap = new HashMap<>();
        for (Map.Entry<File, List<String>> fileListEntry : fileTokensMap.entrySet()) {
            File candidateFile ;
            candidateFile = fileListEntry.getKey();
            redundancyMap.put(candidateFile,getPlagairismPercForCandidate(candidateFile));

        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(redundancyMap);

        return jsonObject;
    }
    private static double getPlagairismPercForCandidate(File candidateFile) {
        int i = 0;
        double total = 0;
        for (Map.Entry<File, List<String>> fileListEntry : fileTokensMap.entrySet()) {
            i++;
            if (candidateFile != fileListEntry.getKey()) {
                double percentage = matchFiles(fileListEntry.getValue(), fileTokensMap.get(candidateFile));
                total += percentage;
                System.out.println(" Percentage for solution " + i + "  is " + percentage);
            }
        }
        return total / (fileTokensMap.size() - 1);
    }

    private static void buildTrainingData(String path) throws IOException {
        File directory = new File(TRAINING_DATA_DIR_PATH);
        System.out.println("@@ directory" + directory.getAbsolutePath());
        if (directory.isDirectory()) {
            files = directory.listFiles();
        }
        fileTokensMap = new TreeMap<>();

        for (File file : files) {
            fileTokensMap.put(file, getTokensFromFile(file));
        }
    }

    private static List<String> getTokensFromFile(File file) throws IOException {
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        Tokenizer tokenizer = new Tokenizer(JavaKeywords.getInstance());
        JavaKeywords java = new JavaKeywords();
        tokenizer.setLanguage(java);
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line).append("\n");
            } else {
                break;
            }
        }
        String fileAsString = sb.toString();
//            System.out.println("@@@ fileAsString" + fileAsString);
        List<String> tokenizedInput = tokenizer.tokenizeInputWithMapping(fileAsString, true, true);
        System.out.println(" file is " + file.getAbsolutePath());
//        System.out.println(".tokenizeInput  " + tokenizedInput);
        System.out.println("@@@tokens size " + tokenizedInput.size());
        System.out.println("@@@@   tokenizer.getTokenTable" + tokenizer.getTokenTable().getTokenMapping());
////            Map<String, Integer> frequency = countTokens(tokenizedInput);
//        Stream<String> tokenStream = tokenizedInput.parallelStream();
//        Map<String, Long> frequency = tokenStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
////        System.out.println(" frequency map = " + frequency);
//        Stream<Map.Entry<String, Long>> abcd = frequency.entrySet().parallelStream().sorted(new Comparator<Map.Entry<String, Long>>() {
//            @Override
//            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
//                return Long.compare(o2.getValue(), o1.getValue());
//            }
//        });//.collect(Collectors.toMap((key,value)->{value},LinkedHashMap::new));
////        System.out.println(" Sorted by frequency  " + Arrays.toString(abcd.toArray()));
//
//        Map<List<String>, Integer> substringCounter = substringCounter(tokenizedInput);
//        Stream<Map.Entry<List<String>, Integer>> sortedSubstringCounter = substringCounter.entrySet().parallelStream().sorted(new Comparator<Map.Entry<List<String>, Integer>>() {
//            @Override
//            public int compare(Map.Entry<List<String>, Integer> first, Map.Entry<List<String>, Integer> second) {
//                int x = Integer.compare(second.getKey().size(), first.getKey().size());
//                if (x == 0) {
//                    x = Integer.compare(second.getValue(), first.getValue());
//                }
//                return x;
//            }
//        }).filter(o -> (o.getValue() > 3));

//            System.out.println(" substringCounter " + Arrays.toString(sortedSubstringCounter.toArray()));
//        filesAsString.add(fileAsString);
        List<String> prepends = new ArrayList<>();
        tokenizedInput.stream().forEach(s -> {
            if (s.contains("p") || s.contains("o") || s.contains("c")) {
                prepends.add(s.substring(0, 2));
            } else {
                prepends.add(s);
            }
        });
        System.out.println(" tokenizeInput  " + prepends);

        return prepends;
    }

    @SuppressWarnings("unused")
	private static Map<List<String>, Integer> substringCounter(List<String> tokenizedInput) {
        HashMap<List<String>, Integer> subStringCountMap = new HashMap<>();
        int substringMaxLength = 20;
        for (int i = tokenizedInput.size() - substringMaxLength - 1; i >= 0; i--) {
            List<String> temp = new ArrayList<>();
            for (int j = i; j < i + substringMaxLength; j++) {
                temp.add(tokenizedInput.get(j));
                if (subStringCountMap.containsKey(temp)) {
                    subStringCountMap.put(new ArrayList<>(temp), subStringCountMap.get(temp) + 1);
                } else {
                    subStringCountMap.put(new ArrayList<>(temp), 1);
                }
            }

        }
        return subStringCountMap;
    }

    static double matchFiles(List<String> trainingData, List<String> candidate) {

        int len1 = trainingData.size();
        int len2 = candidate.size();
        int count = lcsDP(trainingData, candidate, len1, len2);
        return calculateRedundancy(len1, len2, count);
    }

    private static double calculateRedundancy(int len1, int len2, int count) {
        if (len2 == 0) {
            return 0;
        }
        return (100.0 * count / (len1));
    }

    private static int lcsDP(List<String> trainingData, List<String> candidate, int m, int n) {
        System.out.println(" len 1 " + m + " len2 " + n);
        int L[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (trainingData.get(i - 1).compareTo(candidate.get(j - 1)) == 0)
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[m][n];
    }

}
