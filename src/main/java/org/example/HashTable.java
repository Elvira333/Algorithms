package org.example;


import org.w3c.dom.Node;

import java.util.HashMap;

/*
Хеш-таблица
Структура данных, представляющая собой ассоциативный массив использующий хеш-функцию
для выполнения операций добавления, удаления и поиска элементов.
В свою очередь ассоциативным массивом называют структуру данных, которая
хранит пары ключей - значение, где ключ каждой пары является уникальным в
пределах всего массива данных.
Важной особенностью хеш-таблиц является, при некотором разумном допущении,
получить сложность каждой из перечисленных операций равной О(1).
Популярные хеш-алгоритмы: MD5, SHA-1, SHA-256, SHA-384, SHA-512
 */
public class HashTable {

    static final int INIT_BASKET_COUNT = 16;
    Basket[] baskets;

    public HashTable(){
        this(INIT_BASKET_COUNT );
    }
    public HashTable(int size){
        baskets = new Basket[size];
    }

    private class Entity{
        int key;
        int value;
    }

    public int calcIndex(int key){
        return key % baskets.length;
    }

    public boolean add(int key, int value){
        int index = calcIndex(key);
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        Basket basket = baskets[index];
        if(basket == null){
            basket = new Basket();
            baskets[index] = basket;
        }

        return basket.add(entity);
    }
    public Integer find(int key){
        int index = calcIndex(key);
        Basket basket = baskets[index];
        if(basket == null){
          return null;
        }
        return basket.find(key);
    }
    public boolean del(int key){
        int index = calcIndex(key);
        Basket basket = baskets[index];
        if(basket == null){
          return false;
        }

        return basket.del(key);
    }

    public void print(){
        for(int i = 0;i < baskets.length; i++){
            if(baskets[i] == null)
                System.out.printf("Empty");
            else
                baskets[i].print();
            System.out.println();
        }
    }
    private class Basket{
        Node head;

        private class Node{
            Entity value;
            Node next;
        }
        public  boolean add(Entity entity) {
            Node node = new Node();
            node.value = entity;
            if (head == null) {
                head = node;
                return true;
            } else {
                Node current = head;
                while (current.next != null) {
                    if (current.value.key == entity.key) {
                        return false;
                    }
                    current = current.next;
                }
                current.next = node;
                return true;
            }
        }

        public  Integer find(int key) {
            if (head != null) {
                Node current = head;
                while (current != null) {
                    if (current.value.key == key) {
                        return current.value.value;
                    }
                    current = current.next;
                }
            }
            return null;
        }
        public  boolean del(int key) {
            if (head == null) {
                return false;
            } else {
                if(head.value.key==key){
                    head = head.next; // удаление(меняем ссылку просто на след объект, классический способ)
                    return true;
                }else{
                    Node current = head;
                    while (current.next != null) {
                        if (current.next.value.key == key) {
                            current.next = current.next.next;
                            return true;
                        }
                        current = current.next;
                    }
                    return false;
                }
            }
        }
            public void print(){
                Node cur = head;
                while(cur != null){
                    System.out.printf("(key: %d, value: %d); ", cur.value.key, cur.value.value);
                    cur = cur.next;
                }
            }

    }
}
