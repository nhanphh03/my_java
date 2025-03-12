package com;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
/**
 * @Package: com
 * @author: nhanph
 * @date: 3/12/2025 2025
 * @Copyright: @nhanph
 */

public class ForkJoinExample extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10;

    public ForkJoinExample(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            // Xử lý trực tiếp nếu tác vụ đủ nhỏ
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + array[i]);
            }
        } else {
            // Chia tác vụ thành hai phần
            int mid = (start + end) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(array, start, mid);
            ForkJoinExample rightTask = new ForkJoinExample(array, mid, end);

            // Fork: Chia nhỏ tác vụ và thực thi song song
            leftTask.fork();
            rightTask.fork();

            // Join: Đợi các tác vụ con hoàn thành
            leftTask.join();
            rightTask.join();
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ForkJoinExample(array, 0, array.length));
    }
}