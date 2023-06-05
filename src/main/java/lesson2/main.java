package lesson2;

public class main {
    public static void main(String[] args) {
        int[] array = new int[]{2, 7, 5, 1, 4, 65, 43, 67, 9, 3, 8};

        heapSort(array);
        for (int i : array) {
            System.out.println(i);
        }

    }

    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;

            heapify(array, i, 0);
        }
    }

    public static void heapify(int[] array, int heapSize, int root) {
        int max = root;

        int firstChild = 2 * root + 1;
        int secondChild = 2 * root + 2;

        if (firstChild < heapSize && array[firstChild] > array[max]) {
            max = firstChild;
        }

        if (secondChild < heapSize && array[secondChild] > array[max]) {
            max = secondChild;
        }

        if (max != root) {
            int tmp = array[root];
            array[root] = array[max];
            array[max] = tmp;

            heapify(array, heapSize, max);
        }


    }

}
