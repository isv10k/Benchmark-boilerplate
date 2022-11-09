package com.isv10k;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class ToBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        @Param({"1000000", "10000000", "100000000"})
        public int listSize;

        public List<Integer> testList;

        @Setup(Level.Trial)
        public void setUp() {
            testList = new Random()
                    .ints()
                    .limit(listSize)
                    .boxed()
                    .collect(Collectors.toList());
        }
    }

    @Benchmark
    public void sumWithStreamReduce(Blackhole blackhole, BenchmarkState state) {
        int sum = state.testList.stream().reduce(0, Integer::sum);
        blackhole.consume(sum);
    }

    @Benchmark
    public void sumWithFor(Blackhole blackhole, BenchmarkState state) {
        int sum = 0;
        for (int num : state.testList) {
            sum += num;
        }
        blackhole.consume(sum);
    }

//    @Benchmark
//    @Threads(Threads.MAX)
//    public void sumWithStreamLongAdder(Blackhole blackhole, BenchmarkState state) {
//        LongAdder a = new LongAdder();
//        state.testList.parallelStream().forEach(a::add);
//        blackhole.consume(a);
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ToBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(3)
                .warmupTime(TimeValue.seconds(3))
                .measurementTime(TimeValue.seconds(3))
                .timeUnit(TimeUnit.MILLISECONDS)
                .mode(Mode.AverageTime)
                .build();

        new Runner(opt).run();
    }

}
