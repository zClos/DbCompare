package sfp.db.benchmark.mariadb;

import sfp.db.benchmark.AbstractDataBaseBenchmark;
import sfp.db.properties.mariadb.MariaDB;

public class MariaDbBenchmark extends AbstractDataBaseBenchmark {

    @Override
    public void prepare() throws Exception {
        super.prepare(new MariaDB());
    }
}
