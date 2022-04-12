package com.lambakean.RationPlanner.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "planned_day_meals")
@Getter
@Setter
public class PlannedDayMeal extends BaseEntity implements Comparable<PlannedDayMeal> {

    @ManyToOne
    @JoinColumn(name = "planned_day_id", nullable = false)
    private PlannedDay plannedDay;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    private LocalTime time;

    public Double getPrice() {
        if(meal == null) {
            return 0.0;
        }

        return meal.getPrice();
    }

    @Override
    public int compareTo(@NonNull PlannedDayMeal another) {
        return time.compareTo(another.getTime());
    }
}
