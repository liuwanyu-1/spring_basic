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
    private Integer sage;
    private String sgender;
}
