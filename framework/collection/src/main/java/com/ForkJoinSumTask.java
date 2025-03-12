package com;



import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *  @Package: com
 *  @author: nhanph
 *  @date: 3/12/2025 2025
 *  @Copyright: @nhanph
 */

public class ForkJoinSumTask extends RecursiveTask<Long> {
    private final int[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10;

    public ForkJoinSumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Tính tổng trực tiếp nếu tác vụ đủ nhỏ
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Chia tác vụ thành hai phần
            int mid = (start + end) / 2;
            ForkJoinSumTask leftTask = new ForkJoinSumTask(array, start, mid);
            ForkJoinSumTask rightTask = new ForkJoinSumTask(array, mid, end);

            // Fork: Chia nhỏ tác vụ và thực thi song song
            leftTask.fork();
            rightTask.fork();

            // Join: Kết hợp kết quả từ các tác vụ con
            return leftTask.join() + rightTask.join();
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new ForkJoinSumTask(array, 0, array.length));
        System.out.println("Tổng: " + result); // Kết quả: 5050
    }
}