package org.example;

import java.util.ArrayList;
import java.util.List;

public class Trees {
    Node root;

    /*
   Обход в глубину (наиболее часто применим)
    */
    public  boolean exist(int value){
        if(root != null) {
            Node node = find(root, value);
            if (node != null) {
                return true;
            }
        }
        return false;
    }
    public Node find(Node node, int value){
        if(node.value==value)
            return node;
        else{
            for (Node child: node.children) {
                Node result = find(child, value);
                if(result!= null){
                    return result;
                }

            }
        }
        return null;

    }
    /*
    Обход в ширину (чаще применим для анализа, но минусы нужно помнить все элементы и затратно по памяти)
     */
    private Node find2(int value){
        List<Node> line = new ArrayList<>();
        line.add(root);
        while(line.size()>0){
            List<Node> nextLine = new ArrayList<>();
            for(Node node : line){
                if(node.value == value){
                    return node;
                }
                nextLine.addAll(node.children);
            }
            line = nextLine;
        }
        return null;
    }

    /*
    Бинарное дерево это частный случай дерева, где все элементы обязательно строго уникальны,
    каждый родитель имеет не более 2 детей, при этом левый ребенок всегда меньше родителя,
    а правый - больше.
     */
    /*
    Сбалансированное дерево частный случай бинарного дерева, у которого выполняется следующее требование:
    для любого узла дерева высота его правого поддерева отличается от высоты левого поддерева
    не более чем на единицу.
    Сбалансированное дерево дает нам идеальную структуру для бинарного поиска - корень такого дерева -
    это его центральный элемент - количество элементов справа и слева от него различается не более чем на
    единицу, что характерно для выбора стартовой позиции в бинарном поиске. Таким образом, сложность поиска
    по сбалансированному дереву составляет O(log n), что даёт очень высокую производительность.
     */
    public class Node{
        int value;
        List<Node> children;
    }
}
