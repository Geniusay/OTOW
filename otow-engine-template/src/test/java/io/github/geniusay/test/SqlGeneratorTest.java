package io.github.geniusay.test;

import io.github.geniusay.template.sql.SQLFileGenerator;

public class SqlGeneratorTest {

    public static void main(String[] args) {
        // 定义参数
        String packageName = "io.github.geniusay.pojo";
        String dbName = "test_db";
        String outputFilePath = "D:\\test_db.sql";

        // 生成完整的 SQL 脚本
        String sqlScript = SQLFileGenerator.generateDatabaseSQL(packageName, dbName);

        // 将 SQL 写入文件
        SQLFileGenerator.writeSQLToFile(sqlScript, outputFilePath);

        System.out.println("SQL 文件已生成: " + outputFilePath);
    }
}