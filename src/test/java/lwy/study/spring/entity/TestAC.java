package lwy.study.spring.entity;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAC {
    @Test
    public void testTeacher1() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Teacher teacher1 = (Teacher) applicationContext.getBean("teacher1");
        System.out.println(teacher1);
        Teacher teacher3 = applicationContext.getBean("teacher3", Teacher.class);
        System.out.println(teacher3);
        System.out.println(teacher3 == teacher1);
    }
}
