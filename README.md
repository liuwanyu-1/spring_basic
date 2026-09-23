# spring_basic —— Spring 入门（练习9 + 练习10）

> **练习9**：Spring 概念与入门——bean 的定义、set/构造注入、容器加载与获取。
> **练习10**：bean 的实例化——工厂方式（实例工厂/静态工厂）+ 单例模式（呼应 singleton 作用域）。
> Spring 版本：7.1.0-M1（课堂指定）· lombok 1.18.48 · JDK 17 · Maven war 工程 · 包名 `lwy.study.spring`

## 项目结构

```
src/main/java/lwy/study/spring/entity/
├── Teacher.java        实体：sid/sname/sage(String)/sgender/schoolAddress，lombok 六件套
├── Address.java        地址实体：city/street（被 Teacher 引用，演示 ref 注入）
├── TeacherFactory.java 实例工厂：getInstance() 为普通方法，需先 new 工厂再调
├── TeacherFactory2.java 静态工厂：getInstance() 为 static，类名直接调
├── SingleTon.java      单例（synchronized 懒汉式）：呼应 bean 默认 singleton 作用域
src/main/resources/ApplicationContext.xml
src/test/java/lwy/study/spring/entity/
├── TestAC.java         容器测试：按 id/name+类型获取、getType、containsBean/getAliases、工厂产品
├── TestTeacher.java    lombok 全参构造
└── TestTeacherFactory.java  两种工厂的纯 Java 调用对比
```

## 知识点

### 1. set 注入 与 构造注入

```xml
<!-- set 注入：属性必须有 set 方法（lombok @Setter 生成） -->
<bean id="teacher1" name="teacher3" class="lwy.study.spring.entity.Teacher">
    <property name="sid" value="1"/>
    <property name="sname" value="张三"/>
</bean>

<!-- 构造注入：参数个数、类型、顺序必须与构造方法对应 -->
<bean id="teacher5" class="lwy.study.spring.entity.Teacher">
    <constructor-arg name="sid" value="5"/>
    <constructor-arg name="sname" value="王五"/>
    <constructor-arg name="schoolAddress" ref="address"/>
</bean>
```

- **value**：简单类型赋值；**ref**：引用另一个 bean（引用类型）——不要把 ref 写成 value
- `name="teacher3"` 可给 bean 起多个别名（逗号隔开）；`<alias name="teacher2" alias="t"/>` 单独定义别名

### 2. 获取 bean 与作用域

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
Teacher t1 = (Teacher) ctx.getBean("teacher1");          // 按 id，需强转
Teacher t2 = ctx.getBean("teacher2", Teacher.class);      // 按 name+类型，无需强转
Class<?> c = ctx.getType("teacher5");                     // 取 bean 的 Class
ctx.containsBean("teacher1");                             // 是否存在
ctx.getAliases("t");                                      // 查别名
```

- 默认作用域 **singleton**（单例，容器里一份）；`scope="prototype"` 则每次获取都新建
- 按**类型**获取时容器里该类型 bean 必须唯一，否则报错

### 3. bean 的实例化：工厂方式

```xml
<!-- 实例工厂：先配工厂实例，产品 bean 用 factory-bean + factory-method -->
<bean id="teacherOfFactory" class="lwy.study.spring.entity.TeacherFactory"/>
<bean id="teacher" factory-bean="teacherOfFactory" factory-method="getInstance">
    <property name="sname" value="张实例"/>
</bean>

<!-- 静态工厂：无需工厂实例，只要 factory-method -->
<bean id="teacherOfFactory2" class="lwy.study.spring.entity.TeacherFactory2" factory-method="getInstance">
    <property name="sname" value="张静态"/>
</bean>
```

- 实例工厂的 `getInstance()` 是普通方法 → 必须 `factory-bean` + `factory-method` 两个属性
- 静态工厂的 `getInstance()` 是 static → 只需 `factory-method`（class 直接写工厂类）
- 工厂产出对象后，依然可以用 `<property>` 继续注入属性

### 4. 单例模式（SingleTon）

`synchronized` 懒汉式：私有构造 + 静态方法内判空创建。Spring 容器中的 bean 默认就是单例——多次 `getBean` 拿到同一对象。

## 运行方式

1. IDEA 打开项目（Open 选 pom.xml），Maven 同步
2. `TestAC`：4 个测试（获取/类型/别名/工厂产品）
3. `TestTeacherFactory`：实例工厂 vs 静态工厂
4. `TestTeacher`：lombok 全参构造
