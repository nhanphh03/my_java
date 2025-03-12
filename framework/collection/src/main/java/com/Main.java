package com;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println("Task 1 - " + System.currentTimeMillis()));
        Thread thread2 = new Thread(() -> System.out.println("Task 2 - " + System.currentTimeMillis()));
        Thread thread3 = new Thread(() -> System.out.println("Task 3 - " + System.currentTimeMillis()));

        thread1.start();
        thread2.start();
        thread3.start();

        CompletableFuture.runAsync(() -> System.out.println("Task 1  " + System.currentTimeMillis()));
        CompletableFuture.runAsync(() -> System.out.println("Task 2  " + System.currentTimeMillis()));
        CompletableFuture.runAsync(() -> System.out.println("Task 3  " + System.currentTimeMillis()));

        // Đợi các tác vụ hoàn thành (không bắt buộc)
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> System.out.println("Task 11  " + System.currentTimeMillis())),
                CompletableFuture.runAsync(() -> System.out.println("Task 22  " + System.currentTimeMillis())),
                CompletableFuture.runAsync(() -> System.out.println("Task 33  " + System.currentTimeMillis()))
        ).join();
    }

    //Đơn luồng + Đồng bộ
    //Các tác vụ được thực hiện tuần tự trong một luồng.
    //https://chat.deepseek.com/a/chat/s/79cee054-bba8-4fc2-86c0-e47d9f78b567
    public static class SingleThreadSyncExample {
        public static void main(String[] args) {
            task1();
            task2();
            task3();
        }

        static void task1() { System.out.println("Đơn luồng + Đồng bộ Task 1   " + System.currentTimeMillis()); }
        static void task2() { System.out.println("Đơn luồng + Đồng bộ Task 2   " + System.currentTimeMillis()); }
        static void task3() { System.out.println("Đơn luồng + Đồng bộ Task 3   " + System.currentTimeMillis()); }
    }

    //Đơn luồng + Bất đồng bộ
    //Các tác vụ được thực hiện bất đồng bộ trong một luồng (sử dụng event loop hoặc callback).
    public static class SingleThreadAsyncExample {
        public static void main(String[] args) {
            CompletableFuture.runAsync(SingleThreadAsyncExample::task1);
            CompletableFuture.runAsync(SingleThreadAsyncExample::task2);
            CompletableFuture.runAsync(SingleThreadAsyncExample::task3);

            System.out.println("--------------------------------------");
            // Đợi các tác vụ hoàn thành
            CompletableFuture.allOf(
                    CompletableFuture.runAsync(SingleThreadAsyncExample::task1),
                    CompletableFuture.runAsync(SingleThreadAsyncExample::task2),
                    CompletableFuture.runAsync(SingleThreadAsyncExample::task3)
            ).join();
        }

        static void task1() { System.out.println("Đơn luồng + Bất đồng bộ Task 1    " + System.currentTimeMillis()); }
        static void task2() { System.out.println("Đơn luồng + Bất đồng bộ Task 2    " + System.currentTimeMillis()); }
        static void task3() { System.out.println("Đơn luồng + Bất đồng bộ Task 3    " + System.currentTimeMillis()); }
    }

    //Đa luồng + Đồng bộ
    //Các tác vụ được thực hiện tuần tự trong nhiều luồng.
    public static class MultiThreadSyncExample {
        public static void main(String[] args) {
            Thread thread1 = new Thread(MultiThreadSyncExample::task1);
            Thread thread2 = new Thread(MultiThreadSyncExample::task2);
            Thread thread3 = new Thread(MultiThreadSyncExample::task3);


            System.out.println(Runtime.getRuntime().availableProcessors());
            System.out.println(Runtime.getRuntime().freeMemory());
            System.out.println(Runtime.getRuntime().totalMemory());

            thread1.start();
            thread2.start();
            thread3.start();
        }

        static void task1() { System.out.println("Đa luồng + Đồng bộ Task 1     " + System.currentTimeMillis()); }
        static void task2() { System.out.println("Đa luồng + Đồng bộ Task 2     " + System.currentTimeMillis()); }
        static void task3() { System.out.println("Đa luồng + Đồng bộ Task 3     " + System.currentTimeMillis()); }
    }


    //Đa luồng + Bất đồng bộ
    //Các tác vụ được thực hiện bất đồng bộ trong nhiều luồng.
    public static class MultiThreadAsyncExample {
        public static void main(String[] args) {
            CompletableFuture.runAsync(MultiThreadAsyncExample::task1);
            CompletableFuture.runAsync(MultiThreadAsyncExample::task2);
            CompletableFuture.runAsync(MultiThreadAsyncExample::task3);

            System.out.println("ashdashkdv");
            // Đợi các tác vụ hoàn thành
            CompletableFuture.allOf(
                    CompletableFuture.runAsync(MultiThreadAsyncExample::task1),
                    CompletableFuture.runAsync(MultiThreadAsyncExample::task2),
                    CompletableFuture.runAsync(MultiThreadAsyncExample::task3)
            ).join();
        }


        static void task1() { System.out.println("Đa luồng + Bất đồng bộ Task 1    " + System.currentTimeMillis()); }
        static void task2() { System.out.println("Đa luồng + Bất đồng bộ Task 2    " + System.currentTimeMillis()); }
        static void task3() { System.out.println("Đa luồng + Bất đồng bộ Task 3    " + System.currentTimeMillis()); }
    }
}