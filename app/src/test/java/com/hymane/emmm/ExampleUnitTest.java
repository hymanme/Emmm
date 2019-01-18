package com.hymane.emmm;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAsyncPost() {
        final ExecutorService executor = Executors.newFixedThreadPool(5);
        final Scheduler scheduler = Schedulers.from(executor);//最高5个并发
        //Schedulers.io()所有任务一起并发执行(8个)
        ///Schedulers.single()一个一个任务顺序执行
        Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8)
                .flatMap((Function<Integer, ObservableSource<Integer>>) integer -> Observable.just(integer)
                        .subscribeOn(scheduler)
                        .map(integer1 -> {
                            System.out.println(System.currentTimeMillis() + " | Task start, id = " + integer1);
                            Thread.sleep(2000);
//                            System.out.println("PPP end " + integer1);
                            return integer1;
                        })).subscribeOn(Schedulers.io())
                .subscribe(integer -> {
                    System.out.println(System.currentTimeMillis() + " | end：id = " + integer);

                });
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRxJava() {
        Maybe.just(0)
                .map(v -> v + 1)
                .filter(v -> v == 1)
                .defaultIfEmpty(1)
                .test()
                .assertResult(2);
    }

    @Test
    public void testCast(){
        A cast = A.class.cast(new B());
        B b = new B();
    }

    class A {}
    class B extends A{

    }
}