package org.example.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record RemoveLinkRequest(
    @Schema(description = "Link to delete", example = "https://api.github.com")
    @NotNull
    String link
) {
}
