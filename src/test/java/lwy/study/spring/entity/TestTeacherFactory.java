package lwy.study.spring.entity;

import org.junit.Test;

public class TestTeacherFactory {
    @Test
    public void testTeacherFactory(){
        //实例工厂
        TeacherFactory teacherFactory = new TeacherFactory();
        Teacher teacher = teacherFactory.getInstance();
        System.out.println(teacher);
    }
    @Test
    public void testTeacherFactory2(){
        //静态工厂
        Teacher teacher = TeacherFactory2.getInstance();
        System.out.println(teacher);
    }
}
