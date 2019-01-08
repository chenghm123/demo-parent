package com.accelerator.demo.standalone.algorithm.consistent.hashing.ketama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HashAlgorithmPercentTest {

    /** key's count */
    private static final Integer EXE_TIMES = 100000;

    private static final Integer NODE_COUNT = 50;

    private static final Integer VIRTUAL_NODE_COUNT = 160;

    private static final Random RANDOM = new Random();

    private static final List<String> KEYS = getAllStrings();

    public static void main(String[] args) {

        Map<String, List<Node>> map = generateRecord();

        List<Node> allNodes = getNodes(NODE_COUNT);
        System.out.println("Normal case : nodes count : " + allNodes.size());
        call(allNodes, map);

        allNodes = getNodes(NODE_COUNT + 8);
        System.out.println("Added case : nodes count : " + allNodes.size());
        call(allNodes, map);

        allNodes = getNodes(NODE_COUNT - 10);
        System.out.println("Reduced case : nodes count : " + allNodes.size());
        call(allNodes, map);

        int addCount = 0;
        int reduceCount = 0;
        for (Map.Entry<String, List<Node>> entry : map.entrySet()) {
            List<Node> list = entry.getValue();

            if (list.size() == 3) {
                if (list.get(0).equals(list.get(1))) {
                    addCount++;
                }

                if (list.get(0).equals(list.get(2))) {
                    reduceCount++;
                }
            } else {
                System.out.println("It's wrong size of list, key is " + entry.getKey() + ", size is " + list.size());
            }
        }

        System.out.println(addCount + "   ---   " + reduceCount);

        System.out.println("Same percent in added case : " + (float) addCount * 100 / EXE_TIMES + "%");
        System.out.println("Same percent in reduced case : " + (float) reduceCount * 100 / EXE_TIMES + "%");

    }

    private static void call(List<Node> nodes, Map<String, List<Node>> map) {
        KetamaNodeLocator locator = new KetamaNodeLocator(nodes, HashAlgorithm.KETAMA_HASH, VIRTUAL_NODE_COUNT);

        for (Map.Entry<String, List<Node>> entry : map.entrySet()) {
            Node node = locator.getPrimary(entry.getKey());

            if (node != null) {
                List<Node> list = entry.getValue();
                list.add(node);
            }
        }
    }

    private static Map<String, List<Node>> generateRecord() {
        Map<String, List<Node>> record = new HashMap<>(EXE_TIMES);

        for (String key : KEYS) {
            List<Node> list = record.get(key);
            if (list == null) {
                list = new ArrayList<>();
                record.put(key, list);
            }
        }

        return record;
    }


    /**
     * Gets the mock node by the material parameter
     * @param nodeCount the count of node wanted
     * @return the node list
     */
    private static List<Node> getNodes(int nodeCount) {
        List<Node> nodes = new ArrayList<>();

        for (int k = 1; k <= nodeCount; k++) {
            Node node = new Node("node" + k);
            nodes.add(node);
        }

        return nodes;
    }

    /**
     * All the keys
     */
    private static List<String> getAllStrings() {
        List<String> allStrings = new ArrayList<>(EXE_TIMES);
        for (int i = 0; i < EXE_TIMES; i++) {
            allStrings.add(generateRandomString(RANDOM.nextInt(50)));
        }
        return allStrings;
    }

    /**
     * To generate the random string by the random algorithm
     * <br>
     * The char between 32 and 127 is normal char
     */
    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (RANDOM.nextInt(95) + 32));
        }
        return sb.toString();
    }
}
