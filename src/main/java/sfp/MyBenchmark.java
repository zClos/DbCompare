/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package sfp;

import java.sql.SQLException;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import sfp.h2.H2;
import sfp.mysql.InnoDB;
import sfp.mysql.MyISAM;

public class MyBenchmark {

	public static String ROOT = "root";
	public static String PASSWORD = "root";
	public static String DROP_QUERY = "DROP TABLE IF EXISTS SERVICE_PROVIDER";
	public static String CREATE_QUERY = "CREATE TABLE SERVICE_PROVIDER(ID BIGINT PRIMARY KEY, NAME VARCHAR(30), CITY VARCHAR(30), STREET VARCHAR(30), BUILDING VARCHAR(4), BLOCK VARCHAR(3), PHONE VARCHAR(13), EMAIL VARCHAR(30), SITE VARCHAR(30))";
	public static int ROW_COUNT = 10000;
	
	public static String insertQuery(int i) {
		return "INSERT INTO SERVICE_PROVIDER(ID, NAME, CITY, STREET, BUILDING, BLOCK, "
				+ "PHONE, EMAIL, SITE) VALUES("	+ i + ", 'NAME" + i + "', 'CITY" + i + "', "
				+ "'STREET" + i + "', 'B', 'BL', 'PHONE" + i + "', 'EMAIL" + i + "', "
				+ "'SITE" + i + "')";
	}
	
	public static void main(String[] args) throws RunnerException, ClassNotFoundException, SQLException {
				
		Options opt = new OptionsBuilder()
                .include(H2.class.getSimpleName())
                .include(InnoDB.class.getSimpleName())
                .include(MyISAM.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
	}
}
