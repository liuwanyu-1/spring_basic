package lwy.study.spring.entity;

public class SingleTon {
    private static SingleTon singleTon;
    private SingleTon() {
    }

    public static synchronized SingleTon getSingleTon() {
        if (singleTon == null) {
            singleTon = new SingleTon();
        }
        return singleTon;
    }

}
