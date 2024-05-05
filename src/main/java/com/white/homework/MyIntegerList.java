package com.white.homework;


public class MyIntegerList implements IntegerList<Integer> {

    private int size = 0;
    private int capacity;
    private Integer[] arr;

    public MyIntegerList(int capacity) {
        this.capacity = capacity;
        arr = new Integer[capacity];
    }

    @Override
    public Integer add(Integer item) {
        validation(item);
        if (size >= capacity) grow();
        arr[size] = item;
        size++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validation(index, item);
        if (size + 1 > capacity) grow();
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        size++;
        arr[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validation(index, item);
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validation(item);
        if (!contains(item)) throw new IllegalArgumentException("Item is absent");
        int indexSwap = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                indexSwap = i;
                break;
            }
        }
        for (int i = indexSwap; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return item;
    }

    @Override
    public Integer removeByIndex(int index) {
        validation(index);
        Integer removeItem = arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
            if (i == size - 1) arr[i] = null;
        }
        size--;
        return removeItem;
    }

    @Override
    public boolean contains(Integer item) {
        quickSort(arr, 0, size - 1);
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.intValue() == arr[mid]) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        validation(item);
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validation(item);
        for (int i = size - 1; i > 0; i--) {
            if (arr[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validation(index);
        return arr[index];
    }

    @Override
    public boolean equals(IntegerList<Integer> otherList) {
        if (otherList == null) throw new IllegalArgumentException();
        boolean isEquals = true;
        if (otherList == this) {
            return true;
        }
        if (this.size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(otherList.get(i))) {
                isEquals = false;
                break;
            }
        }
        return isEquals;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return arr[0] == null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] integerArray = new Integer[size];
        System.arraycopy(arr, 0, integerArray, 0, size);
        return integerArray;
    }

    private void grow() {
        capacity = (int) (capacity * 1.5);
        Integer[] tempArr = new Integer[capacity];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);
        arr = tempArr;
    }

    private void validation(Integer item) {
        if (item == null) throw new IllegalArgumentException("Null can't be added");
    }

    private void validation(int index) {
        if (index >= size && index != 0) throw new IndexOutOfBoundsException();
    }

    private void validation(int index, Integer item) {
        if (item == null) throw new IllegalArgumentException("Null can't be added");
        if (index >= size) throw new IndexOutOfBoundsException();
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1) str.append(arr[i]).append(",");
            else str.append(arr[i]);
        }
        str.append("]");
        return str.toString();
    }
}
