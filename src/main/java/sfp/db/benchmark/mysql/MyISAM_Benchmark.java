package sfp.db.benchmark.mysql;

import sfp.db.benchmark.AbstractDataBaseBenchmark;
import sfp.db.properties.mysql.MyISAM;

public class MyISAM_Benchmark extends AbstractDataBaseBenchmark {

    @Override
    public void prepare() throws Exception {
        createQuery = createQuery + "ENGINE = MyISAM";
        super.prepare(new MyISAM());
    }
}
