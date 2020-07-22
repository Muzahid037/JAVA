package com.muzaaahid;

import java.util.*;

public class DisjointSet {

    /*
     * Disjoint sets using path compression and union by rank
     * There are 3 operations
     * 1) makeSet
     * 2) union
     * 3) findSet
     */
    private Map<Long, Node> map = new HashMap();

    class Node {
        long data;
        Node parent;
        int rank;
    }

    /**
     * Creating a set with only one element.
     */
    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    /*
     * Combines two sets together to one.
     * Does union by rank
     * return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(long data1, long data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }

        //increment rank only if both sets have same rank
        //else whoever's rank is higher becomes parent of other
        if (parent1.rank == parent2.rank) {
            parent2.parent = parent1.parent;
            parent1.rank++;
        } else if (parent1.rank > parent2.rank) {
            parent2.parent = parent1.parent;
        } else if (parent1.rank < parent2.rank) {
            parent1.parent = parent2.parent;
        }
        return true;
    }

    /**
     * Finds the representative of this set
     */
    public long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }


    public static void main(String[] args) {

        //creating obj of scanner class for Input
        Scanner sc = new Scanner(System.in);

        //creating obj of disjoint class
        DisjointSet obj = new DisjointSet();

        //making set
        System.out.println("How many make_set you want:");
        int noOfMakeSet = sc.nextInt();
        for (int i = 0; i < noOfMakeSet; i++) {
            System.out.println("Put a number to make a set with only itself:");
            int dataToInsert = sc.nextInt();
            obj.makeSet(dataToInsert);
            System.out.println("set " + dataToInsert + " is created!");
        }

        //merging two set
        System.out.println("How many union you want:");
        int noOfUnion = sc.nextInt();
        for (int i = 0; i < noOfUnion; i++) {
            System.out.println("Put two set member to join them:");
            int dataToInsert1 = sc.nextInt();
            int dataToInsert2 = sc.nextInt();
            obj.union(dataToInsert1, dataToInsert2);
            System.out.println("Set contains <" + dataToInsert1 + " ans " + dataToInsert2 + "> are joined!");
        }

        //finding representative
        System.out.println("How many find_set/representative you want to see:");
        int noOfFindSet = sc.nextInt();
        for (int i = 0; i < noOfFindSet; i++) {
            System.out.println("Whose representative do you want to find:");
            int dataToInsert = sc.nextInt();
            System.out.println(obj.findSet(dataToInsert));
        }
    }
}
