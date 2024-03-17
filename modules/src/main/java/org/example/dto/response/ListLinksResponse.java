package org.example.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ListLinksResponse(
    @Schema(description = "Links")
    @NotNull
    List<LinkResponse> links,
    @Schema(description = "Size", example = "132")
    @NotNull
    Integer size
) {
}
