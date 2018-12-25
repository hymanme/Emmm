package com.hymane.emmm;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
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

        final ExecutorService executor = Executors.newFixedThreadPool(3);
        final Scheduler scheduler = Schedulers.from(executor);
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return Observable.just(integer)
                                .subscribeOn(Schedulers.single())
                                .map(new Function<Integer, Integer>() {
                                    @Override
                                    public Integer apply(Integer integer) throws Exception {
                                        System.out.println("PPP start " + integer);
                                        Thread.sleep(1000);
                                        System.out.println("PPP end " + integer);
                                        return integer;
                                    }
                                });
                    }
                }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(4, 2 + 2);
    }
}