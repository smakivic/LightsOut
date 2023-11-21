package org.acme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class SolutionStepEntity extends PanacheEntity {
    private int field_index;
    private int order_number;

    @ManyToOne
    @JoinColumn(name = "solution_id")
    private SolutionEntity solution;

    public SolutionStepEntity(int field_index, int order_number) {
        this.field_index = field_index;
        this.order_number = order_number;
    }

    public SolutionStepEntity() {

    }

    public void setSolution(SolutionEntity solution) {
        this.solution = solution;
    }

    public int getField_index() {
        return field_index;
    }

    public void setField_index(int field_index) {
        this.field_index = field_index;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }
}
