package com.zjh.dataservice.entity.jpa;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "talent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Talent extends BaseEntity{

    @Column
    private String name;
    @Column
    private Date birthday;
    @Column
    private String gender;
    @Column
    private BigDecimal salary;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "talent")
    private List<Education> educations;
}
