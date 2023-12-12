package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Benchmark)
public class ReflectionBenchmark {
    private final static int WARMUP_TIME = 5;
    private final static int MEASUREMENT_TIME = 15;
    private final static String METHOD_NAME_BEING_TESTED = "name";
    private Method method;
    private Student student;
    private MethodHandles.Lookup lookup;
    private MethodType methodType;
    private MethodHandle invokerHandle;
    private RecordMethodInvoker invokerLambda;

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args)
        throws Throwable {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(WARMUP_TIME))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(MEASUREMENT_TIME))
            .build();
        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander");
        lookup = MethodHandles.lookup();
        method = Student.class.getDeclaredMethod(METHOD_NAME_BEING_TESTED);
        methodType = MethodType.methodType(String.class);
        invokerHandle = lookup.findVirtual(Student.class, METHOD_NAME_BEING_TESTED, methodType);
        invokerLambda = (RecordMethodInvoker) LambdaMetafactory.metafactory(
            lookup,
            "invoke",
            MethodType.methodType(RecordMethodInvoker.class),
            invokerHandle.type().changeReturnType(String.class),
            invokerHandle,
            invokerHandle.type()
        ).getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        Object name = method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        Object name = invokerHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void metafactory(Blackhole bh) {
        Object name = invokerLambda.invoke(student);
        bh.consume(name);
    }
}
