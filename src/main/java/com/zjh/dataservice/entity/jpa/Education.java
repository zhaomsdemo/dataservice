package com.zjh.dataservice.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
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

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "talent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Talent talent;
    @Column
    private String schoolName;
    @Column(columnDefinition = "tinyint(2)")
    private Integer degree;
    @Column
    private Date graduateDay;
}
