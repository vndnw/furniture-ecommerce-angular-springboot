package com.store.furniture.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginatedResponse<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
