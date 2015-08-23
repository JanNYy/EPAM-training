package ua.epam.pizzaservice.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Anna on 01.08.2015.
 */
public class BenchmarkBeanPostProcessor implements BeanPostProcessor
{
    @Override
    public Object postProcessBeforeInitialization(final Object obj, String s) throws BeansException
    {
        final Class<?> clazz = obj.getClass();
        if (clazz.isAnnotationPresent(Benchmark.class))
        {
            final Object proxy = Proxy.newProxyInstance(
                    clazz.getClassLoader(),
                    clazz.getInterfaces(),
                    new InvocationHandler()
                    {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                        {
                            System.out.println("Benchmark start " + method.getName());
                            long start = System.nanoTime();
                            Object retVal = method.invoke(obj, args);
                            long result = System.nanoTime() - start;
                            System.out.println(result);
                            System.out.println("Benchmark finish " + method.getName());
                            return retVal;
                        }
                    }
            );
            return proxy;
        }
        return obj;
    }

    @Override
    public Object postProcessAfterInitialization(Object obj, String s) throws BeansException
    {
        return obj;
    }
}