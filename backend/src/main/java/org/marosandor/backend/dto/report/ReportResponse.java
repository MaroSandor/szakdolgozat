package org.marosandor.backend.dto.report;

import lombok.*;
import org.marosandor.backend.enums.Category;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReportResponse {
    private Long id;
    private String title;
    private String description;
    private Category category;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String statusLabel;
    private boolean isAnonymous;
    private String username;
    private LocalDateTime createdAt;

    
}
