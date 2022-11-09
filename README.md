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

        $ cd jmharchetype/
        $ mvn clean verify
3. Running benchmark
        
        $ java -jar target/benchmarks.jar


## With using [JMH plugin](https://github.com/artyushov/idea-jmh-plugin#intellij-idea-plugin-for-java-microbenchmark-harness-jmh)

You only need to add these dependencies in your pom file, or have them on the classpath of your module

      <dependencies>
          <dependency>
              <groupId>org.openjdk.jmh</groupId>
              <artifactId>jmh-core</artifactId>
              <version>1.35</version>
          </dependency>
          <dependency>
              <groupId>org.openjdk.jmh</groupId>
              <artifactId>jmh-generator-annprocess</artifactId>
              <version>1.35</version>
              <scope>provided</scope>
          </dependency>
      </dependencies>

If setup is correct you can just press JMH plugin button near class or method declaration, for more info [JMH plugin](https://github.com/artyushov/idea-jmh-plugin#intellij-idea-plugin-for-java-microbenchmark-harness-jmh)

## Running from the `main()` method

You need to have same dependencies from previous point, just add `main()` method, and set up running configuration with `OptionsBuilder` class.
Example in jmhwithmain folder.
