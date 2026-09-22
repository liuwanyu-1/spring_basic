package lwy.study.spring.entity;

import org.junit.Test;

public class TestTeacher {
    @Test
    public void testAllArgs() {
        Teacher teacher = new Teacher(
                1001,
                "张三丰",
                101,
                "男"
        );
        System.out.println(teacher);
    }
}
