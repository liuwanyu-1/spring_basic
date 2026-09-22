# 练习9 Spring概念与入门 —— spring_basic

> 练习内容：完成课上练习（Spring 第一课）。核心是 **set 注入实现简单登录验证** + Spring 入门案例（bean 的定义与获取）。
> Spring 版本：7.1.0-M1（课堂指定）· lombok 1.18.48 · JDK 17 · Maven war 工程

## 项目结构

```
src/main/java/lwy/study/spring/
├── entity/Teacher.java          实体类：lombok 六件套（无参/全参构造、getter/setter、toString）
├── dao/UserDao.java             数据访问层接口：boolean login(name, password)
├── dao/impl/UserDaoImpl.java    DAO 实现：校验账号（张三/123）
├── service/UserService.java     业务层接口
├── service/impl/UserServiceImpl.java  业务层实现：持有 UserDao，由 Spring 注入
src/main/resources/ApplicationContext.xml   bean 配置（set 注入 + 构造注入 + ref 引用）
src/test/java/.../TestSpring.java           main 方法：加载容器 → 登录验证 → 登陆成功
src/test/java/.../TestAC.java               按 id / 按 name+类型 获取 bean、单例比较
src/test/java/.../TestTeacher.java          lombok 全参构造测试
```

## 知识点

### 1. 两种注入方式

**set 方法注入**（要求属性必须有 set 方法，方法名 = set + 属性首字母大写）：

```xml
<bean id="teacher1" name="teacher2" class="lwy.study.spring.entity.Teacher">
    <property name="sid" value="1002"/>
    <property name="sname" value="李四"/>
</bean>
```

**构造方法注入**（一定要符合构造方法参数的个数和类型、顺序）：

```xml
<bean id="teacher4" class="lwy.study.spring.entity.Teacher">
    <constructor-arg index="0" type="java.lang.Integer" value="1004"/>
    <constructor-arg name="sname" value="秦始皇"/>
    <constructor-arg name="sage" value="50"/>
    <constructor-arg name="sgender" value="男"/>
</bean>
```

### 2. value 与 ref 的区别（易错点）

- `value`：给**简单类型**（String、int 等）赋值
- `ref`：引用**另一个 bean**（引用类型），例如 Service 依赖 DAO：

```xml
<bean id="userDao" class="lwy.study.spring.dao.impl.UserDaoImpl"/>
<bean id="userService" class="lwy.study.spring.service.impl.UserServiceImpl">
    <property name="userDao" ref="userDao"/>
</bean>
```

### 3. 加载容器与获取 bean

```java
ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("ApplicationContext.xml");

// 按 id 获取（需要强转）
Teacher teacher1 = (Teacher) applicationContext.getBean("teacher1");

// 按 name + 类型 获取（无需强转）
Teacher teacher3 = applicationContext.getBean("teacher3", Teacher.class);
```

- 默认作用域是 **singleton**（单例）：`getBean("teacher1") == getBean("teacher2")` 为 true
- 按**类型**获取时，容器里该类型必须只有一个 bean，否则报错

### 4. 登录验证链路

```
TestSpring(main) → UserService(getBean) → UserDao → 校验 张三/123 → true
```

Service 只声明 `UserDao userDao` 属性 + `setUserDao`，对象由 Spring 容器注入——这就是控制反转（IoC）：对象的创建和装配交给容器。

### 5. lombok

`@NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString` 六件套生成构造器与读写方法（IDEA 需开启 annotation processing）。

## 运行方式

1. IDEA 打开项目（Open 选 pom.xml），等 Maven 同步
2. 跑 `TestAC` / `TestTeacher`（JUnit）
3. 跑 `TestSpring` 的 main：控制台输出 `登陆成功`
