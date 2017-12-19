package sfp.db.benchmark.postgresql;

import sfp.db.benchmark.AbstractDataBaseBenchmark;
import sfp.db.properties.postgresql.PostgreSql;

public class PostgreSqlBenchmark extends AbstractDataBaseBenchmark {

    @Override
    public void prepare() throws Exception {
        super.prepare(new PostgreSql());
    }
}
