# Benchmark-boilerplate

Benchmarking with JMH

## With JMH archetype, using command line

Example in **jmharchetype** folder.

[JMH GitHub](https://github.com/openjdk/jmh#preferred-usage-command-line)

1. Setting up the benchmarking project

       $ mvn archetype:generate \
        -DinteractiveMode=false \
        -DarchetypeGroupId=org.openjdk.jmh \
        -DarchetypeArtifactId=jmh-java-benchmark-archetype \
        -DgroupId=com.isv10k \
        -DartifactId=jmharchetype \
        -Dversion=1.0
2. Building benchmark

        $ cd test/
        $ mvn clean verify
3. Running benchmark
        
        $ java -jar target/benchmarks.jar