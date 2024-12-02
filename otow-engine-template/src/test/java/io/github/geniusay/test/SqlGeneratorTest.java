package io.github.geniusay.test;

import io.github.geniusay.template.sql.SQLExecutor;
import io.github.geniusay.template.sql.SQLFileGenerator;
import io.github.geniusay.template.sql.SQLManager;
import org.junit.Test;

import static io.github.geniusay.common.constant.DatabaseConstant.DB_NAME_PREFIX;

public class SqlGeneratorTest {

    @Test
    public void generateAndImportSql() {
        // 配置参数
        String packageName = "io.github.geniusay.pojo"; // 实体类包名
        String dbName = DB_NAME_PREFIX + "test_db"; // 数据库名称
        String dbUrl = "jdbc:mysql://127.0.0.1:3306?useSSL=false&serverTimezone=UTC"; // 数据库连接 URL
        String username = "root"; // 数据库用户名
        String password = "369202865"; // 数据库密码
        String outputFilePath = "D:\\test_db.sql"; // SQL 文件路径

        SQLManager.generateAndImportSQL(packageName, dbName, dbUrl, username, password, outputFilePath);
    }

    @Test
    public void generateSqlFile() {
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

    @Test
    public void importSqlFile() {
        // 数据库连接信息
        String dbUrl = "jdbc:mysql://127.0.0.1:3306?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "369202865";

        // SQL 文件路径
        String sqlFilePath = "D:\\test_db.sql";

        // 执行 SQL 文件
        try {
            SQLExecutor.executeSQLFile(dbUrl, username, password, sqlFilePath);
            System.out.println("SQL 文件已成功执行！");
        } catch (Exception e) {
            System.err.println("执行 SQL 文件失败: " + e.getMessage());
        }
    }
}
