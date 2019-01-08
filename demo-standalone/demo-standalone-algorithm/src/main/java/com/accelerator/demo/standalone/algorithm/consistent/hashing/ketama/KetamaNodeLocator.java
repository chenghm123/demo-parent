package com.accelerator.demo.standalone.algorithm.consistent.hashing.ketama;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public final class KetamaNodeLocator {

    private TreeMap<Long, Node> ketamaNodes;
    private HashAlgorithm hashAlg;

    KetamaNodeLocator(List<Node> nodes, HashAlgorithm alg, int nodeCopies) {
        hashAlg = alg;
        ketamaNodes = new TreeMap<>();
        for (Node node : nodes)
            for (int i = 0; i < nodeCopies / 4; i++) {
                byte[] digest = hashAlg.computeMd5(node.getName() + i);
                for (int h = 0; h < 4; h++) {
                    long m = hashAlg.hash(digest, h);
                    ketamaNodes.put(m, node);
                }
            }
    }

    Node getPrimary(final String k) {
        byte[] digest = hashAlg.computeMd5(k);
        return getNodeForKey(hashAlg.hash(digest, 0));
    }

    private Node getNodeForKey(long hash) {
        final Node rv;
        Long key = hash;
        if (!ketamaNodes.containsKey(key)) {
            SortedMap<Long, Node> tailMap = ketamaNodes.tailMap(key);
            if (tailMap.isEmpty()) {
                key = ketamaNodes.firstKey();
            } else {
                key = tailMap.firstKey();
            }
//          For JDK1.6 version
//			key = ketamaNodes.ceilingKey(key);
//			if (key == null) {
//				key = ketamaNodes.firstKey();
//			}
        }


        rv = ketamaNodes.get(key);
        return rv;
    }
}
