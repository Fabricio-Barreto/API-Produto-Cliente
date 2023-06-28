package com.mineirinho.mercado.view.controller;

import com.mineirinho.mercado.dto.UserDTO;
import com.mineirinho.mercado.services.UserService;
import com.mineirinho.mercado.view.model.ProdutoResponse;
import com.mineirinho.mercado.view.model.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Users", description = "Endpoints for Managing Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all users", description = "Finds all users", tags = "User", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ProdutoResponse.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<UserResponse>> obterTodos() {
        List<UserDTO> users = userService.obterTodos();
        List<UserResponse> usersResponse = users.stream().map(user -> new ModelMapper().map(user, UserResponse.class)).collect(Collectors.toList());

        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }
}
