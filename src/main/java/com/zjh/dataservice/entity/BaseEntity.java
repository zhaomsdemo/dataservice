package com.zjh.dataservice.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(nullable = false,columnDefinition = "timestamp default current_timestamp")
    private Date createdDate;

    @Column(nullable = false,columnDefinition = "timestamp default current_timestamp")
    private Date modifiedDate;
}
