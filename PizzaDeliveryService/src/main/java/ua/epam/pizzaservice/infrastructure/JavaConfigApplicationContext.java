package ua.epam.pizzaservice.infrastructure;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anna on 23.07.2015.
 */
public class JavaConfigApplicationContext implements ApplicationContext {

    private Config config;
    private final Map<String,Object> beans = new HashMap<String,Object>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    public Object getBean(String beanName) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object bean = beans.get(beanName);
        if (bean != null)
        {
            return bean;
        }

        BeanBuilder beanBuilder = new BeanBuilder(beanName);
        beanBuilder.createObject();
        beanBuilder.callInitMethod();
        beanBuilder.createProxy();

        return beanBuilder.getObject();
    }

    class BeanBuilder {

        private Object obj;
        private String beanName;

        public BeanBuilder(String beanName) {
            this.beanName = beanName;
        }

        public void createObject() throws IllegalAccessException, InstantiationException, InvocationTargetException {
            Class<?> clazz = config.getImplementation(beanName);

            Constructor<?> constructor = clazz.getConstructors()[0];
            Class<?>[] parameters = constructor.getParameterTypes();
            if (parameters.length == 0)
            {
                obj = clazz.newInstance();
                beans.put(beanName,obj);
                return;
            }

            StringBuilder name = new StringBuilder();

            Object[] objects = new Object[parameters.length];
            int i = 0;

            for (Class<?> p : parameters)
            {
                name.append(p.getSimpleName());
                name.setCharAt(0, Character.toLowerCase(name.charAt(0)));
                objects[i++] = getBean(name.toString());
                name.setLength(0);
            }
            obj = constructor.newInstance(objects);
            beans.put(beanName,obj);
        }

//        private Object checkAndCreateObjectForBenchmark(Object object) {
//            Class<?> clazz = obj.getClass();
//            for (Method m : clazz.getMethods())
//            {
//                if (m.isAnnotationPresent(Benchmark.class))
//                {
//                    return createProxyObj(object);
//                }
//            }
//            return object;
//        }

        public void createProxy() {
            ProxyForBenchmarkAnnotation proxyForBenchmarkAnnotation = new ProxyForBenchmarkAnnotation();
            obj = proxyForBenchmarkAnnotation.checkAndCreateObjectForBenchmark(obj);
//            Class<?> clazz = obj.getClass();
//            for (Method m : clazz.getMethods())
//            {
//                if (m.isAnnotationPresent(Benchmark.class))
//                {
//                    obj = createProxyObj(obj);
//                }
//            }
        }

//        private Object createProxyObj(final Object obj) {
//            final Class<?> type = obj.getClass();
//            return Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(),
//                    new InvocationHandler() {
//                        @Override
//                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//                            if (type.getMethod(method.getName(),method.getParameterTypes())
//                                    .isAnnotationPresent(Benchmark.class))
//                            {
//
//                                System.out.println("Benchmark start " + method.getName());
//                                long start = System.nanoTime();
//                                Object retVal = method.invoke(obj, args);
//                                long result = System.nanoTime() - start;
//                                System.out.println(result);
//                                System.out.println("Benchmark finish " + method.getName());
//                                return retVal;
//                            }
//                            else
//                            {
//                                return method.invoke(obj,args);
//                            }
//                        }
//                    });
//        }

        public void callInitMethod() throws InvocationTargetException, IllegalAccessException {
            Class<?> clazz = obj.getClass();
            Method method;
            try
            {
                method = clazz.getMethod("init");
            }
            catch (NoSuchMethodException e)
            {
                return;
            }
            method.invoke(obj);
        }

        public Object getObject() {
            return obj;
        }

    }
}
