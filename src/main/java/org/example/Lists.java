package org.example;


/*
Связные списки
 */
public class Lists{
    static Node head;
    static Node tail;

    public static void main(String[] args) {
        Node node = new Node();
        node.add(10);
        node.add(20);
        node.add(30);
        node.add(20);
        node.printList();
        node.revert();
        node.printList();

    }

    /*
    Метод создания двусвязного списка
    */
    public static class Node {
        int value;
        Node next;
        Node previos;


        /*
        Метод заполнения с самого начала списка
        */
        public void add(int value, Node node) {
            Node next = node.next;
            Node newNode = new Node();
            newNode.value = value;
            node.next = newNode;
            newNode.previos = node;
            if (next == null) {
                tail = newNode;
            } else {
                next.previos = newNode;
                newNode.next = next;
            }

        }

        /*
        Метод добавления элементов в список, заполняем с проверкой если начало пустое, чтобы избежать
        в последствии ошибок и дополнительных проверок(не должна быть ситуация, что конец заполнен, а начало нет)
        */
        public void add(int value) {
            Node node = new Node();
            node.value = value;
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.previos = tail;
                tail = node;
            }
        }

        /*
        Метод удаления элемента
         */
        public void delete(Node node) {
            Node previous = node.previos;
            Node next = node.next;
            if (previous == null) {
                node.next.previos = null;
                head = next;
            } else {
                if (next == null) {
                    previous.next = null;
                    tail = previous;
                } else {
                    previous.next = next;
                    next.previos = previous;

                }
            }
        }

        /*
        Метод поиска элемента в списке
         */
        public Node find(int value) {
            Node currentNode = head;
            while (currentNode != null) {
                if (currentNode.value == value) {
                    return currentNode;
                }
                currentNode = currentNode.next;
            }
            return null;
        }

        /*
        Метод разворота
         */
        public void revert() {
            Node currentNode = head;
            while (currentNode != null) {
                Node next = currentNode.next;
                Node previous = currentNode.previos;
                currentNode.next = previous;
                currentNode.previos = next;
                if (previous == null) {
                    tail = currentNode;
                }
                if (next == null) {
                    head = currentNode;
                }
                currentNode = next;
            }

        }
        public void printList() {
            Node currentNode = head;
            while (currentNode != null) {
                System.out.print(currentNode.value + " ");
                currentNode = currentNode.next;
            }
            System.out.println();
        }

    }

}
