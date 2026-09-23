package lwy.study.spring.entity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Teacher {
    private Integer sid;
    private String sname;
    private String sage;
    private String sgender;
    private Address schoolAddress;
}
