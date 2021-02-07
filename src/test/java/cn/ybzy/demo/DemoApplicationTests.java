package cn.ybzy.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.*;

@SpringBootTest
class DemoApplicationTests {

    static String toHex(int n) {
        Deque<String> stack = new LinkedList<>();
        int a = 12500;
        while (a / 16 > 0 || a / 16 == 0) {
            String value = String.valueOf(a % 16 == 13 ? "D" : a % 16);
            stack.push(value);
            if (a / 16 > 0) {
                a = a / 16;
            } else {
                break;
            }
        }
        StringBuilder str = new StringBuilder();
        while (stack.peek() != null) {
            str.append(stack.pop());
        }
        return str.toString();
    }

    @Test
    public void getTestx() {
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }
    }

    @Test
    public void test() {
        // ZonedDateTime -> long:
        ZonedDateTime zdt = ZonedDateTime.now();
        long ts = zdt.toEpochSecond() * 1000;

        // long -> Date:
        Date date = new Date(ts);
        // long -> Calendar:
        Calendar calendar = Calendar.getInstance();

        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
        calendar.setTimeInMillis(zdt.toEpochSecond() * 1000);
        System.out.println(calendar.getTime());
    }


    @Test
    public void test1() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread("thread1");
            thread.start();
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    sb.append("a");
                }
            }).start();
        }
        // 睡眠确保所有线程都执行完
        Thread.sleep(1000);
        System.out.println(sb.length());
    }

    @Test
    public void test2() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            myThread myThread = new myThread(sb);
            myThread.start();
        }
        // 睡眠确保所有线程都执行完
        Thread.sleep(1000);
        System.out.println(sb.length());
    }

    public class myThread extends Thread {
        StringBuilder sb;

        public myThread(StringBuilder str) {
            this.sb = str;
        }

        public myThread() {

        }

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                sb.append("a");
            }
        }
    }


}
