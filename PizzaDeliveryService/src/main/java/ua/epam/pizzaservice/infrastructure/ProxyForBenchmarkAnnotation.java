package ua.epam.pizzaservice.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Anna on 27.07.2015.
 */
public class ProxyForBenchmarkAnnotation {

    public Object checkAndCreateObjectForBenchmark(Object object) {
        Class<?> clazz = object.getClass();
        for (Method m : clazz.getMethods())
        {
            if (m.isAnnotationPresent(Benchmark.class))
            {
                return createProxyObj(object);
            }
        }
        return object;
    }

    private Object createProxyObj(final Object obj) {
        final Class<?> type = obj.getClass();
        return Proxy.newProxyInstance(
                type.getClassLoader(),
                type.getInterfaces(),
                new InvocationHandler()
                {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        if (type.getMethod(method.getName(), method.getParameterTypes())
                                .isAnnotationPresent(Benchmark.class))
                        {

                            System.out.println("Benchmark start " + method.getName());
                            long start = System.nanoTime();
                            Object retVal = method.invoke(obj, args);
                            long result = System.nanoTime() - start;
                            System.out.println(result);
                            System.out.println("Benchmark finish " + method.getName());
                            return retVal;
                        }
                        else
                        {
                            return method.invoke(obj, args);
                        }
                    }
                }
        );
    }

}
