package com.test.tcp.domain.dto;

import lombok.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soyun")
public class Test {

    private Long id;

    private String code;

    private LocalDateTime date;
}
