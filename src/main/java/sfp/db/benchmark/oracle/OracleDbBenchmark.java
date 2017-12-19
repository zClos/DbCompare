package sfp.db.benchmark.oracle;

import sfp.db.benchmark.AbstractDataBaseBenchmark;
import sfp.db.properties.oracle.Oracle;

public class OracleDbBenchmark extends AbstractDataBaseBenchmark {
    @Override
    public void prepare() throws Exception {
        dropQuery = "BEGIN EXECUTE IMMEDIATE 'DROP TABLE SERVICE_PROVIDER';" +
                " EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE;" +
                " END IF;" +
                " END;";
        createQuery = "CREATE TABLE SERVICE_PROVIDER(" +
                "ID NUMBER PRIMARY KEY," +
                " NAME VARCHAR2(30)," +
                " CITY VARCHAR2(30)," +
                " STREET VARCHAR2(30)," +
                " BUILDING VARCHAR2(4)," +
                " BLOCK VARCHAR2(3)," +
                " PHONE VARCHAR2(13)," +
                " EMAIL VARCHAR2(30)," +
                " SITE VARCHAR2(30)" +
                ")";
        super.prepare(new Oracle());
    }
}
