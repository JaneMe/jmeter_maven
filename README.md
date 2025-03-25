# JMeter Maven 接口自动化测试项目

## 项目简介

这是一个基于Apache JMeter和Maven的自动化性能测试项目，用于进行Web应用的接口测试。该项目利用Maven构建工具来管理JMeter测试脚本的执行，并自动生成测试报告。


## 项目环境

确保您的系统已安装：
- Java JDK 8或更高版本
- Apache JMeter 5.6.3
- Apache Maven 3.9.9

## 项目结构

```
jmeter_maven/
├── src/
│   ├── main/                   # 主要源代码目录
│   └── test/
│       ├── jmeter/
│       │   ├── logs/           # JMeter日志目录
│       │   ├── properties/     # JMeter属性配置文件目录
│       │   ├── report/         # 测试报告目录
│       │   │   ├── html/       # HTML格式测试报告
│       │   │   └── jtl/        # JTL格式测试结果
│       │   └── script/         # JMeter测试脚本(.jmx)目录
│       │       ├── 网易搜索.jmx
│       │       ├── BeanShell 取样器测试.jmx
│       │       └── test_auto.py
│       └── resources/          # 测试资源文件
├── target/                     # Maven构建输出目录
├── pom.xml                     # Maven项目配置文件
└── README.md                   # 项目说明文档
```

## 技术栈

- Apache JMeter 5.6.3
- Apache Maven 3.9.9
- JUnit 4.13.2
- Log4j 2.17.1


## 功能特性

- 通过Maven自动化执行JMeter测试脚本
- 自动生成HTML和JTL格式的测试报告
- 可配置的测试参数和环境变量
- 测试报告包含时间戳，方便追踪历史记录
- 支持多种类型的JMeter测试场景

## 使用方法

### 执行测试

1. 克隆或下载项目到本地

2. 进入项目根目录
```bash
cd /path/to/jmeter_maven
```

3. 执行Maven命令运行测试
```bash
mvn clean verify
```
或特定阶段：
```bash
mvn integration-test
```

4. 查看测试报告

测试完成后，可以在以下目录查看测试报告：
- HTML报告：`src/test/jmeter/report/html/`
- JTL报告：`src/test/jmeter/report/jtl/`

### 添加新的测试脚本

1. 使用JMeter GUI创建新的测试脚本(.jmx文件)
2. 将脚本文件放入`src/test/jmeter/script/`目录
3. 执行Maven命令运行测试

### 自定义配置

可以通过修改`pom.xml`中的属性来自定义测试配置：
- 修改报告目录
- 调整JMeter执行参数
- 配置测试报告格式

## 注意事项

- 确保JMeter脚本(.jmx文件)使用相对路径引用资源文件
- 测试结果会自动带有时间戳，便于区分不同时间的测试结果
- 如需忽略测试失败，可查看`pom.xml`中的`ignoreResultFailures`配置

## 故障排除

如果遇到测试执行问题，请检查：
1. `src/test/jmeter/logs/`目录中的日志信息
2. 确认JMeter脚本的正确性和有效性
3. 确认系统环境变量和Java版本配置