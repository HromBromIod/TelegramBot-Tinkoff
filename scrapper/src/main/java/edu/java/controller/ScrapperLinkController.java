package edu.java.controller;

import edu.java.errors.NotFoundException;
import edu.java.service.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.AllArgsConstructor;
import org.example.dto.request.AddLinkRequest;
import org.example.dto.response.ApiErrorResponse;
import org.example.dto.response.LinkResponse;
import org.example.dto.response.ListLinksResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/links")
@AllArgsConstructor
public class ScrapperLinkController {
    private final LinkService linkService;
    @Operation(summary = "Get tracked links")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Links have been received",
            content = {@Content(
                mediaType = "application/json",
                schema = @Schema(implementation
                    = ListLinksResponse.class))}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Incorrect request parameters",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation
                        = ApiErrorResponse.class))
            })
    })

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListLinksResponse getLinks(@RequestParam @Positive Long tgChatId) {
        return linkService.listAll(tgChatId);
    }

    @Operation(summary = "Add tracking to link")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Links has been added",
            content = {@Content(
                mediaType = "application/json",
                schema = @Schema(implementation
                    = LinkResponse.class))}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Incorrect request parameters",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation
                        = ApiErrorResponse.class))
            })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LinkResponse setLink(
        @RequestParam @Positive Long tgChatId,
        @RequestBody @Valid AddLinkRequest addLinkRequest
    ) throws URISyntaxException {
        linkService.add(tgChatId, addLinkRequest.link());
        return new LinkResponse(tgChatId, addLinkRequest.link());
    }

    @Operation(summary = "Delete tracking to link")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Link has been deleted",
            content = {@Content(
                mediaType = "application/json",
                schema = @Schema(implementation
                    = LinkResponse.class))}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Incorrect request parameters",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation
                        = ApiErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Link is not found",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation
                        = ApiErrorResponse.class))
            })
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public LinkResponse deleteLink(
        @RequestParam @Positive Long tgChatId,
        @RequestBody @Valid AddLinkRequest addLinkRequest
    )
        throws NotFoundException, URISyntaxException {
        if (false) {
            throw new NotFoundException("Not Found");
        }
        return new LinkResponse(tgChatId, addLinkRequest.link());
    }
}
