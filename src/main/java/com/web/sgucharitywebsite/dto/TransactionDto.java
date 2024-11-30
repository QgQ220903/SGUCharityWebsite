package com.web.sgucharitywebsite.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

  private Long id;
  private String vnpOrderInfo;
  private LocalDateTime vnpPayDate;
  private String vnpOrderStatus;
  private String vnpTransactionNo;
  private String vnpAmount;
  private String vnpProjectId;
  // Include project details if needed for the DTO (optional)
  private ProjectDto project; // Consider adding relevant project details
}
