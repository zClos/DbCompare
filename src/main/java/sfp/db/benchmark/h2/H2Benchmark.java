package sfp.db.benchmark.h2;

import sfp.db.benchmark.AbstractDataBaseBenchmark;
import sfp.db.properties.h2.H2;

public class H2Benchmark extends AbstractDataBaseBenchmark {

    @Override
    public void prepare() throws Exception {
        super.prepare(new H2());
    }
}
