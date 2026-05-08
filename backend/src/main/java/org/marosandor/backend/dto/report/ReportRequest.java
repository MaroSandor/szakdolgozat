package org.marosandor.backend.dto.report;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.marosandor.backend.enums.Category;
import java.math.BigDecimal;

@Data
public class ReportRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Category category;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    private boolean isAnonymous = false;
}
