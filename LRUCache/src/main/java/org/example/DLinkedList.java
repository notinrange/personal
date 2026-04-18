package org.example;

public class DLinkedList<K,V> {
    private DNode<K,V> head;
    private DNode<K,V> tail;
    private  int size;

    DLinkedList(){
        head = new DNode<>();
        tail = new DNode<>();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    void addToFront(DNode<K,V> node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        size++;
    }
    void remove(DNode<K,V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        size--;
    }

    DNode<K,V> removeLast(){
        if(size == 0) return null;
        DNode<K,V> lru = tail.prev;
        remove(lru);
        return lru;
    }

    void moveToFront(DNode<K,V> node){
        remove(node);
        addToFront(node);
    }
    int getSize(){ return  size;}
}
