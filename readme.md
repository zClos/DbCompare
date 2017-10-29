# DBCompare

This project measure query-time to different databases. Database represent 'SERVICE_PROVIDER' table. Query is 'select where name = ...'. Query-time measured in microseconds.

For packing project use command: mvn clean install.

To change test-table size edit ROW_COUNT value in MyBenchmark.class.

Check port number, root and password for each DB.

If you want to vary using databases, remove unnecessary 'Options.include' row in MyBenchmark.class.

To test oracle 11g r2 download and install <a href="http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html">ojdbc6</a> manually.

#My results

#Search in 100 rows:
Run complete. Total time: 00:02:41

Benchmark                           Mode  Cnt    Score     Error  Units
s.h2.H2.testMethod                  avgt   10  124,041 ±  35,250  us/op
s.mariadb.MariaDB.testMethod        avgt   10  487,720 ±  58,176  us/op
s.mysql.InnoDB.testMethod           avgt   10  545,770 ± 150,058  us/op
s.mysql.MyISAM.testMethod           avgt   10  387,613 ± 103,625  us/op
s.oracle.Oracle.testMethod          avgt   10  499,774 ± 162,499  us/op
s.postgresql.PostgreSql.testMethod  avgt   10  435,367 ± 103,225  us/op

#Search in 1 000 rows:
Run complete. Total time: 00:03:40

Benchmark                           Mode  Cnt     Score     Error  Units
s.h2.H2.testMethod                  avgt   10   708,488 ±  11,037  us/op
s.mariadb.MariaDB.testMethod        avgt   10  2634,031 ± 103,300  us/op
s.mysql.InnoDB.testMethod           avgt   10  2611,083 ±  69,075  us/op
s.mysql.MyISAM.testMethod           avgt   10  1441,750 ± 227,368  us/op
s.oracle.Oracle.testMethod          avgt   10   559,887 ± 139,556  us/op
s.postgresql.PostgreSql.testMethod  avgt   10   714,510 ±  50,891  us/op

#Search in 10 000 rows:
Run complete. Total time: 00:18:06

Benchmark                           Mode  Cnt      Score      Error  Units
s.h2.H2.testMethod                  avgt   10   9433,653 ± 1936,562  us/op
s.mariadb.MariaDB.testMethod        avgt   10  26056,574 ± 4577,577  us/op
s.mysql.InnoDB.testMethod           avgt   10  25008,635 ± 1300,183  us/op
s.mysql.MyISAM.testMethod           avgt   10  10359,910 ±  408,784  us/op
s.oracle.Oracle.testMethod          avgt   10   8842,546 ±  625,088  us/op
s.postgresql.PostgreSql.testMethod  avgt   10   4236,861 ±  524,400  us/op

#Search in 100 000 rows:
Run complete. Total time: 00:08:29

Benchmark                           Mode  Cnt        Score        Error  Units
s.h2.H2.testMethod                  avgt   10  2357105,910 ± 662404,787  us/op
s.mysql.MyISAM.testMethod           avgt   10    99816,738 ±   2475,073  us/op
s.oracle.Oracle.testMethod          avgt   10    18965,460 ±    353,728  us/op
s.postgresql.PostgreSql.testMethod  avgt   10    36947,172 ±    592,627  us/op

MariaDB and InnoDB didn't pass the test at this table size.

#In total:

1-2. Oracle
1-2. PostgreSQL
3.   H2
4.   MyISAM
5-6. MariaDB
5-6. InnoDB