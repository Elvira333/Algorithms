package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // System.out.println(sum(500_000_000));
        // simple(100);
    }
    public static void sort(int[] array){
        // Построение кучи(перегруппируем массив)
        for (int i = array.length/2-1;i>=0;i--){
            heapify(array,array.length,i);
        }

        // Один за другим извлекаем элементы из кучи
        for(int i = array.length-1;i>=0;i--){
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0]=array[i];
            array[i]=temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(array,i,0);
        }

    }

    /*
    O(N) - функция оценки истинной работы программы(асимптотика)
     */
    public static int sum(int N) {
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result += i;
        }
        return result;
    }

    /*
    Нахождение простых чисел
     */
    public static void simple(int N) {
        for (int i = 2; i <= N; i++) {
            boolean ok = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println(i);
            }
        }
    }

    public static void cubs(int K, int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    for (int l = 1; l <= N; l++) {
                        System.out.printf("%d %d %d%n", i, j, k, l);
                    }
                }
            }
        }
    }

    public static void rec_cubs(int K, int N, int id, int C) {
        if (id > K) {
            System.out.printf("%d%n", C);
        }
        for (int i = 1; i <= N; i++) {
            C = C * 10 + i;
            rec_cubs(K, N, id + 1, C);
            C /= 10;
        }
    }

    public static int rec_fib(int N) {
        if (N <= 2) return 1;
        return rec_fib(N - 1) + rec_fib(N - 2);

    }

    /*
    Сортировка выбором
     */
    public static void directSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                }
            }
            if (i != minPosition) {
                int temp = array[i];
                array[i] = array[minPosition];
                array[minPosition] = temp;
            }
        }
    }

    /*
    Сортировка вставками
     */
    public static void insertSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /*
    Алгоритм поиска №1
     */
    public static int find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1; // классический возврат если ничего не найдено
    }

    /*
   Алгоритм поиска №2 (бинарный поиск)
   Необходимо взять центральный элемент отсортированного массива и сравнить с искомым значением,
   далее смотрим по значению куда нам двигаться и берем снова от отрезка середину.
   "Рзаделяй и властвуй"
   Этот алгоритм куда лучше, чем обычный поиск.
   */
    public static int binarySearch(int[] array, int value, int min, int max) {
        int midpoint;
        if (max < min) {
            return -1;
        } else {
            midpoint = (max - min) / 2 + min; // поиск середины массива
        }
        if (array[midpoint] > value) {
            return binarySearch(array, value, midpoint + 1, max);
        } else {
            if (array[midpoint] < value) {
                return binarySearch(array, value, min, midpoint - 1);
            } else {
                return midpoint;
            }
        }
    }
        /*
        Быстрая сортировка
        Разделяй и властвуй - парадигма разработки алгоритмов, заключающаяся в рекурсивном разбиении
        решаемой задачи на две или более подзадачи того же типа, но меньшего размера, и комбинировании
        их решений для получения ответа к исходной задаче; разбиения выполняются до тех пор, пока
        все подзадачи не окажутся элементарными.
        Пивот(от англ. pivot - поворот) - Элемент, служащий точкой сравнения элементов и их поворота, в случае необходимости.
         */
        public static void sort ( int[] array, int startPosition, int endPosition){
            int leftPosition = startPosition;
            int rightPosition = endPosition;
            int pivot = array[(startPosition + endPosition) / 2];
            do {
                while (array[leftPosition] < pivot) {
                    leftPosition++;
                }
                while (array[rightPosition] > pivot) {
                    rightPosition--;
                }
                if (leftPosition <= rightPosition) {
                    if (leftPosition < rightPosition) {
                        int temp = array[leftPosition];
                        array[leftPosition] = array[rightPosition];
                        array[rightPosition] = temp;
                    }
                    leftPosition++;
                    rightPosition--;
                }

            } while (leftPosition <= rightPosition);
            if (leftPosition < endPosition) {
                sort(array, leftPosition, endPosition);
            }
            if (startPosition < rightPosition) {
                sort(array, startPosition, rightPosition);
            }


        }
        /*
        Пирамидальная сортировка(сортировка кучей)
        Бинарная куча. Если принять элемент с индексом i за родителя, то индексы его дочерних элементов
        будут 2*i+1 и 2*i +2
         */
    private static void heapify(int[] array, int heapSize, int rootIndex){
        int largest = rootIndex; // инициализируем наибольший элемент как корень
        int leftChild = 2*rootIndex + 1; // левый = 2*rootIndex + 1
        int rightChild = 2*rootIndex +2; // правый = 2*rootIndex +2

        // Если левый дочерний элемент больше корня
        if(leftChild < heapSize && array[leftChild] > array[largest]){
            largest = leftChild;
        }

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if(rightChild< heapSize  && array[rightChild] > array[largest]){
            largest=rightChild;
        }

        // Если самый большой элемент не корень
        if(largest != rootIndex){
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(array,heapSize,largest);
        }

    }

}