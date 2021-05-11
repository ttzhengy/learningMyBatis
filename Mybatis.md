# Mybatis

## 框架概述

### 软件开发常用结构

#### 三层架构

1. 界面层（User Interface Layer）：用于显示数据和接收用户输入的数据，为用户提供一种交互式操作的界面。 实现视图用到的技术有html/css/jsp/js等前端技术
2. 业务逻辑层(Business Logic Layer)：位于三层中的中间层（DAL与UIL中间），起到了数据交换中承上启下的作用，用于对业务逻辑的封装。BLL中的增、删、改、查不再是“原子性”的功能，而是包含了一定的业务逻辑。
3. 数据访问层(Data Access Layer):数据访问层也称为持久层，位于三层中的最下层，用于对数据进行处理。该层中的方法一般都是“原子性”的，即每个方法都不可再分。

### 框架是什么

#### 定义

解决一个开放性问题而设计的具有一定约束性的支撑结构。是整个或部分系统的可重用设计，表现为一组抽象构件及构件实例间交互的方法

### JDBC的缺陷

1. 代码多，效率低
2. 需要关注Connection，Statement，ResultSet对象的创建和销毁
3. 对查询结果ResultSet，需要自己再封装
4. 代码重复部分多
5. 业务代码与数据库操作部分混在一起

### MyBatis概述

本来是apache的一个开源项目，原名iBatis。后移至google code并改名为MyBaits。2013年11月迁移至Github

#### 两大特点

1. sql mapper：sql映射。可以把数据库表中的一行数据映射为一个java对象
2. Data Access Objects(DAOs)：数据访问。对数据库CRUD

#### 实现功能

1. 提供了创建和管理Connection，Statement，ResultSet对象资源的能力
2. 提供了执行sql语句的能力
3. 提供了循环sql，并将结果转换为List的能力

数据库操作简化为：开发人员提供sql语句-》mybatis处理语句并取得数据-》开发人员得到java对象或List集合

## 使用步骤

### 新建数据库，表

demo使用的库名为mybatis，表名为student。表中元素为id，name，email，age

### 加入依赖

#### mybatis依赖

```
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```

#### mysql依赖（5.X版本）

```
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>5.1.49</version>
</dependency>
```

#### 编译 .xml ,properties文件

maven本身不编译配置文件，需要手动添加

在\<build>标签内

```
<resources>
  <resource>
  <!--所在的目录-->
  <directory>src/main/java</directory>
  <includes>
    <include>**/*.properties</include>
    <include>**/*.xml</include>
  </includes>
  </resource>
</resources>
```

\<directory>内的路径就是要编译的文件夹

### 创建数据实体类

- 路径：根目录下com.yat.entity.Student
- 类名建议跟表名一样，便于记忆
- 类的属性跟列名一致，定义所有getter,setter方法，重写override方法

### 创建持久层的dao接口

- 路径：根目录下com.yat.dao.StudentDao
- 接口，定义相应的操作数据库方法

### 配置映射关系文件

- 路径：根目录下com.yat.dao，与StudentDao同目录，命名为StudentDao.xml
- \<!DOCTYPE>是约束文件，固定
- \<mapper>：映射文件的根标签
  - namespace：指向Dao类的全类型名，即：包路径+类名
- \<select>：执行查询语句
  - id：sql语句的唯一标识，对应Dao接口中的方法名
  - parameterType：传入的参数类型
  - resultType：返回的类型T，存放在ResultSet\<T>中，要写java全类名
  - #{任意字符}是占位符，PreparedStatement中的？
- \<update>：执行更改语句
  - 执行增删改之后，需要手动提交事务sqlSession.commit()
- \<insert>：执行插入语句
- \<delete>：执行删除语句
- \<resultMap>：强大功能，待填坑

### 配置mybatis主配置文件

- 路径：在src/main下创建资源文件夹resources，在resources下创建.xml文件
- 一个项目只有主配置文件，作用是提供数据库连接信息和sql映射文件信息
- \<!DOCTYPE>是约束文件，固定
- \<environments>：环境配置的集合，包括数据库的连接信息
  - default：必须跟某个\<environment>的id相同，表示默认使用该环境
- \<environment>：某个单独的环境
  - id：自定义的名称，代表当前环境
  - \<transactionManager>：事务管理器类型
    - type=“JDBC”表示使用jdbc中的Connection对象的commit，rollback做事务处理
    - 使用Spring+Mybatis则没有必要配置事务管理器，因为Spring会使用自带的管理器来覆盖前面的配置
  - \<dataSource>：数据源的配置
    - type="POOLED"表示使用连接池
    - \<property>：配置以下属性
      1. driver：数据库的驱动类名，"com.mysql.jdbc.Driver"
      2. url：数据库的url，"jdbc:mysql://localhost:3306/mybatis"（当前库名为mybatis）
      3. name，password：用户账号密码
- \<mappers>：sql mapper（sql映射文件）的位置
  - \<mapper>：某个映射文件的具体位置
    - resource：相对于类路径的资源引用，是编译后文件的路径

### 创建Mybatis的SqlSession对象，执行sql语句

1. 从xml文件中构建SqlSessionFactory对象（例子），读取的文件是编译后，以target/classes为根目录的路径

   ```
   String resource = "org/mybatis/example/mybatis-config.xml";
   InputStream inputStream = Resources.getResourceAsStream(resource);
   SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
   ```

2. 从工厂对象中获取SqlSession

   ```
   SqlSession sqlSession = factory.openSession()
   ```

3. 执行sql语句

### 打开日志

添加以下到主配置文件的根标签中，日志输出到控制台

```
<settings>
  <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

## Mybatis中主要类的介绍

### Resources

工具类。主要功能：读主配置文件

### SqlSessionFactoryBuilder

建造者。主要功能：创建SqlSessionFactory对象

### SqlSessionFactory

- 重量级对象，创建对象耗费资源多，花费时间长。整个项目中只需要创建一个就够了
- 本身是一个接口，实现类：DefaultSqlSessionFactory
- 作用：获取SqlSession。使用factory.openSession()实现
  - openSession()：无参数，获取非自动提交的SqlSession
  - openSession(boolean autoCommit)：参数为true，设置为自动提交
  - 还可以设置数据库连接（Connection）和语句执行对象

### SqlSession

- 它包含了所有执行语句、提交或回滚事务以及获取映射器实例的方法
- 是个接口，默认实现类：DefaultSqlSession
- 使用要求：SqlSession不是线程安全的，需要在方法内部执行。执行语句之前，用openSession()获取SqlSession对象，执行语句后，用close()关闭

## MyBatis框架Dao代理

### Dao动态代理实现数据库操作

- MyBatis在内部实现了**动态代理**，只需要传入Dao接口的类名，就能实现相应的数据库查询的操作

- 使用方式：

  - 获取SqlSession对象
  - 使用SqlSession.getMapper()方法，传入某个dao接口的类型
  - 返回值是一个已实现的dao接口类，可以直接调用方法，执行sql语句

- 使用要求

  - dao接口和mapper文件在同一个目录下，文件名一致
  - mapper文件中的namespace是dao接口的全类名
  - mapper文件中\<select>\<insert>\<update>\<delete>标签中的id与接口方法名一致
  - dao接口中不要使用重载方法
  
  ```
  StudentDao dao = sqlSession.getMapper(StudentDao.class);
  ```

### 深入理解参数

#### parameterType

映射文件中语句标签的属性，表示dao接口中方法参数的数据类型，是全类名或者mybatis中规定的别名。实际上不写也行

#### 传入一个简单参数

- 在sql语句中用#{任意字符}充当占位符，从接口方法传入参数
- 实际上就是封装了JDBC中的PreparedStatement的操作

#### 传入多个参数

1. 使用@Param注解

   - 在接口的传入参数前面，使用@Param("参数名")

     ```
     List<Student> selectMulitParam(@Param("name")String name, @Param("age")int age);
     ```

   - 此时sql语句中，#{}中间的名称要与@Param注解中的名称对应

2. 使用对象传入

   - 新建一个类，类的属性是要查询的参数
   - 此时占位符完整语法：#{类属性名,javaType=类名,jdbcType=类名}
   - 简化方式：#{类属性名}

3. 按位置传入参数

   - 接口方法中定义n个传入参数
   - 占位符#{arg0},#{arg1}...,#{argn-1}
   - mybatis3.3版本之前，是用#{0},#{1}...,#{n-1}的方式

4. 使用Map传值

   - Map<ket,value>对应要查询的字段和对应参数值
   - 将Map对象传入接口方法

#### #和$

1. #占位符，告诉mybatis使用实际的参数值代替，并使用PreparedStatement执行sql语句，更迅速。能避免sql注入，更安全。
2. $执行的是拼接，使用Statement对象执行sql语句，效率和安全性较低。传入的参数如果带有带操作性的字符，可能损害数据库
3. $可以替换表名或列名。能保证数据是安全的情况下，可以使用$

### 封装MyBatis输出结果

#### resultType

- 结果类型，指sql语句执行完毕后，数据转为的java对象
- 处理方法：
  1. mybatis执行sql，然后mybatis调用实体类的无参构造器
  2. mybatis吧ResultType的指定列值赋给同名的属性

#### Map

- 返回Map<>：列名是map的key，列值是map的value
- 只能接受单行的数据，接受多行结果会报错
- 要用map方式接收多行，就要用List嵌套map

#### resultMap

- 结果映射，指定列名和java对象的属性对应关系
- 自定义列值要赋给哪个属性名
- 当列名与属性名不一致时，必须用此方法

#### 实体类属性名与列名不同的处理

1. 用resultMap做列名到属性名的映射
2. mybatis中赋值是根据列名，因此可以在sql语句中，使用as关键字给列起别名，达到赋值给相应属性名的目的

### 模糊like

1. 在java中定义模糊查询的字段，将字段传入方法
2. 在mapper中拼接like

# TODO：多表、关联、缓存