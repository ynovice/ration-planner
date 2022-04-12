package com.lambakean.RationPlanner.dto.form;

import com.lambakean.RationPlanner.dto.EntityIdReferenceDto;
import com.lambakean.RationPlanner.dto.PhotoDto;
import com.lambakean.RationPlanner.dto.TimeDto;
import com.lambakean.RationPlanner.model.ProductQuantity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MealCreationForm {

    private String name;
    private String description;
    private String recipe;
    private TimeDto cookingDuration;
    private Set<IngredientInformation> ingredients;
    private PhotoDto photo;

    @Getter
    @Setter
    public static class IngredientInformation {

        private String name;
        private EntityIdReferenceDto product;
        private ProductQuantityInformation productQuantity;

        @Getter
        @Setter
        public static class ProductQuantityInformation {

            private Double amount;
            private EntityIdReferenceDto measurementUnit;
        }
    }
}
