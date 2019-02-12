package com.hymane.emmm;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
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
    public void test2() {
        Father f = new Son();
        f.say();
    }

    static class Father {
        private void say() {
            System.out.println("father: hi");
        }
    }

    static class Son extends Father {
        private void say() {
            System.out.println("Son: hi");
        }
    }

    static interface Sky {
        void light();

        void dark();
    }

    static class HiSky implements Sky {
        public HiSky() {
        }

        @Override
        public void light() {
            System.out.println("light+++++++");
        }

        public void dark() {
            System.out.println("dark+++++++");
        }
    }

    @Test
    public void proxy() {
        Sky sky = (Sky) new SalesInvocationHandler().bind(new HiSky());
        sky.light();

        Sky sky2 = new SalesInvocationHandler().bind(Sky.class);
        sky2.dark();
    }

    public class SalesInvocationHandler implements InvocationHandler {
        //代理类持有一个委托类的对象引用
        private Object delegate;

        /**
         * 绑定委托对象并返回一个代理类 * @param delegate * @return
         */
        public Object bind(Object delegate) {
            this.delegate = delegate;
            return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
        }

        @SuppressWarnings("unchecked")
        public <T> T bind(Class<T> delegate) {
            return (T) Proxy.newProxyInstance(delegate.getClassLoader(), new Class[]{delegate}, (proxy, method, args) -> {
                if (!delegate.isInterface()) {
                    throw new IllegalArgumentException("not a interface");
                }

                Class<?> aClass = Class.forName("com.hymane.emmm.ExampleUnitTest$HiSky");
                Object o = aClass.newInstance();
//                Class<HiSky> hiSkyClass = HiSky.class;
//                HiSky hiSky = hiSkyClass.newInstance();
                return method.invoke(o, args);
            });
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println(proxy.getClass().getInterfaces()[0]);
            System.out.println("Enter method " + method.getName());
            long start = System.currentTimeMillis();

            Object result = method.invoke(delegate, args);

            long end = System.currentTimeMillis();
            System.out.println("Exit method " + method.getName());
            System.out.println("执行时间：" + (end - start));

            return result;
        }
    }

    @Test
    public void TestFan() {
        Class<String> stringClass = getClassFromInterface(new User<String>() {
        });
        System.out.println(stringClass.toString());

        Type type = new User<String>().getClass().getGenericSuperclass();
        System.out.println(type.toString());
    }

    public interface Person<T> {

    }

    public class User<T> {

    }

    /**
     * 获取对象的泛型类型
     *
     * @param callback 带有泛型的类
     * @return callback的泛型类型
     * @author hymane
     */
    @SuppressWarnings("unchecked")
    private <T> Class<T> getClassFromInterface(User<T> callback) {
        try {
            Type[] interfaceTypes = callback.getClass().getGenericInterfaces();
            Type type;
            if (interfaceTypes == null || interfaceTypes.length == 0) {
                //该类未实现任何接口，
                type = callback.getClass().getGenericSuperclass();
            } else { //直接实现ZTCallbackBase
                type = interfaceTypes[0];
            }
            if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                Type item = (((ParameterizedType) type).getActualTypeArguments())[0];
                return ((Class<T>) item);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}