package sfp.db.benchmark.mysql;

import sfp.db.benchmark.AbstractDataBaseBenchmark;
import sfp.db.properties.mysql.InnoDB;

public class InnoDbBenchmark extends AbstractDataBaseBenchmark {

    @Override
    public void prepare() throws Exception {
        super.prepare(new InnoDB());
    }
}
