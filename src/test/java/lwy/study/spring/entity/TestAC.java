package lwy.study.spring.entity;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAC {
    ApplicationContext applicationContext;
    @Before
    public void init() {
         applicationContext = new ClassPathXmlApplicationContext
                ("ApplicationContext.xml");
    }
    @Test
    public void testTeacher1() {

        Teacher teacher1 = (Teacher)applicationContext.getBean("teacher1");
        System.out.println(teacher1);

        Teacher teacher2 =applicationContext.getBean("teacher2",Teacher.class);
        System.out.println(teacher2);

        Teacher teacher3 = applicationContext.getBean("teacher5",Teacher.class);
        System.out.println(teacher3);
    }
    @Test
    public void testType() {
        Class<?> teacherType = applicationContext.getType("teacher5");
        System.out.println(teacherType);
    }
    @Test
    public void testContainsBean() {
        boolean b = applicationContext.containsBean("teacher1");
        System.out.println(b);
        String[] ts = applicationContext.getAliases("t");
        for (String t : ts) {
            System.out.println(t);
        }
    }
    @Test
    public void testTeacherOfFactory() {
        Teacher teacherOfFactory = applicationContext.getBean("teacher",Teacher.class);
        System.out.println(teacherOfFactory);
        Teacher teacherOfFactory2 = applicationContext.getBean("teacherOfFactory2",Teacher.class);
        System.out.println(teacherOfFactory2);
    }

}
