package lwy.study.spring.entity;

import org.junit.Test;

public class TestTeacher {

    @Test
    public void testTeacher() {
        Teacher teacher = new Teacher(
                1, "张三", "12", "男", null
        );
        System.out.println(teacher);

    }
}
