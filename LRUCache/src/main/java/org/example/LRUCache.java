package org.example;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private final int capacity;
    private final Map<K, DNode<K,V>> map;
    private final DLinkedList<K,V> dll;

    public LRUCache(int capacity){
        if(capacity <=0) throw  new IllegalArgumentException("Capacity must be > 0");
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DLinkedList<>();
    }

    public synchronized V get(K key){
        DNode<K,V> node = map.get(key);
        if(node==null) return null;
        dll.moveToFront(node);
        return node.value;
    }

    public synchronized void put(K key, V value){
        DNode<K,V> existing = map.get(key);
        // case 1 pehle se node exist karta h
        if(existing != null){
            existing.value = value;
            dll.moveToFront(existing);
            return;
        }

        // case 2 capacity full ho gyi LRU ki
        if(map.size() >= capacity){
            DNode<K,V> lru = dll.removeLast();
            if(lru != null){
                map.remove(lru.key);
                System.out.println(" [evict] key=" + lru.key);
            }
        }

        // insert new node at front (MRU)
        DNode<K,V> newNode = new DNode<>(key,value);
        dll.addToFront(newNode);
        map.put(key,newNode);
    }
    public synchronized int size(){ return  map.size();}
    public synchronized boolean containsKey(K key){ return map.containsKey(key);}

    public synchronized void printState(){
        System.out.println(" Cache [MRU->LRU]");
        System.out.println("Size= " + size() + "/" + capacity);
    }
}
