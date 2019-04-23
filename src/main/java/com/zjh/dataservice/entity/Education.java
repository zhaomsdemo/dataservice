package com.zjh.dataservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "education")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Education extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Talent talent;
    @Column
    private String schoolName;
    @Column(columnDefinition = "tinyint(2)")
    private Integer degree;
    @Column
    private Date graduateDay;
}
