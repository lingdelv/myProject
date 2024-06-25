package com.example.rental.GeneratorCode;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
/**
 * 生成代码的工具类。
 * 该类配置了生成代码所需的各种常量，包括数据库连接信息、输出目录、包名等。
 * 通过指定的表名和前缀，可以从数据库中生成对应的实体类、DAO层代码等。
 */
public class GeneratorCode {

    // 作者名称常量，用于生成代码中的作者信息。
    private static final String AUTHOR = "teacher_shi";

    // JDBC连接URL，用于连接到数据库。
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/auto_rental";

    // JDBC用户名，用于数据库认证。
    private static final String JDBC_USERNAME="root";

    // JDBC密码，用于数据库认证。
    private static final String JDBC_PASSWORD="123456";

    // 生成代码的输出目录。
    private static final String OUT_DIR=".\\src\\main\\java";

    // 生成代码的包名。
    private static final String PACKAGE_NAME="com.example";

    // 项目模块名，用于生成包路径。
    private static final String MODULE_NAME="rental";

    // 需要生成代码的表名数组。
    private static final String[] TABLES={
            "auto_maker","auto_brand","auto_info",
            "sys_user_role","sys_user","sys_role_permission","sys_role","sys_permission","sys_dept",
            "busi_violation","busi_rental_type","busi_order","busi_maintain","busi_customer"
    };

    // 表名前缀数组，用于过滤或指定生成代码的表。
    private static final String[] PREFIX={"busi_","sys_"};
    /**
     * 自动化生成代码的测试方法。
     * 使用FastAutoGenerator工具类，通过配置各项参数，实现根据数据库表结构自动生成对应的Java代码和配置文件。
     * 这里主要配置了全局设置、包配置、策略配置以及模板引擎，最后执行生成操作。
     */
    @Test
    void generatorCode(){
        // 创建FastAutoGenerator实例，初始化数据库连接信息。
        FastAutoGenerator.create(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD)
                // 配置全局属性，包括作者、是否启用Swagger、输出目录等。
                .globalConfig(builder -> {
                    builder.author(AUTHOR)
                            .enableSwagger()
                            .outputDir(OUT_DIR);
                })
                // 配置包信息，包括父包名、模块名，并指定XML文件的输出路径。
                .packageConfig(builder -> {
                    builder.parent(PACKAGE_NAME)
                            .moduleName(MODULE_NAME)
                            .pathInfo(Collections.singletonMap(OutputFile.xml,".\\src\\main\\resources\\mapper"));

                })
                // 配置生成策略，包括需要包含的表、表名前缀，以及实体类和控制器的生成选项。
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES)
                            .addTablePrefix(PREFIX)
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .controllerBuilder()
                            .enableRestStyle()
                            .mapperBuilder();
                })
                // 指定使用的模板引擎为Freemarker。
                .templateEngine(new FreemarkerTemplateEngine())
                // 执行代码生成操作。
                .execute();

    }

}
