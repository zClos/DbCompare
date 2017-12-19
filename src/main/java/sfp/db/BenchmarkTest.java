package sfp.db;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkTest {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder().build();

        new Runner(opt).run();
    }
}
