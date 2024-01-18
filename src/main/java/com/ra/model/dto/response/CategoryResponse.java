package com.ra.model.dto.response;

import com.ra.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Service
public class CategoryResponse  {
    private Long id;
    private String categoryName;
    private Boolean status;
    public  CategoryResponse (Category category)
    {
        this.id=category.getId();
        this.categoryName=category.getCategoryName();
        this.status=category.getStatus();
    }
}
