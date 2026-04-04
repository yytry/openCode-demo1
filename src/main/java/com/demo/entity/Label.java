package com.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "label")
public class Label {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Integer labelId;
    
    @Column(name = "label_name", nullable = false, length = 45)
    private String labelName;
    
    @Column(name = "label_status", nullable = false, length = 5)
    private String labelStatus = "1";

    public Label() {}

    public Label(String labelName) {
        this.labelName = labelName;
        this.labelStatus = "1";
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelStatus() {
        return labelStatus;
    }

    public void setLabelStatus(String labelStatus) {
        this.labelStatus = labelStatus;
    }
}
