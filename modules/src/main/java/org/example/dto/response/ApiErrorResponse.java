package org.example.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record ApiErrorResponse(
    @Schema(description = "Error description", example = "Server Error")
    @NotNull
    String description,
    @Schema(description = "Error code", example = "500")
    String code,
    @Schema(description = "Error class", example = "Exception")
    String exceptionName,
    @Schema(description = "Error message", example = "Illegal type")
    String exceptionMessage,
    String[] stackTrace
) {
}
