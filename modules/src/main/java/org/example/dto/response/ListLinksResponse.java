package org.example.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ListLinksResponse(
    @Schema(description = "Links")
    @NotNull
    LinkResponse[] links,
    @Schema(description = "Size", example = "132")
    @NotNull
    Integer size
) {
}
