//package com.mineirinho.mercado.view.controller;
//
//import com.mineirinho.mercado.dto.UserDTO;
//import com.mineirinho.mercado.services.UserService;
//import com.mineirinho.mercado.view.model.UserRequest;
//import com.mineirinho.mercado.view.model.UserResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/v1/user")
//@Tag(name = "Users", description = "Endpoints for Managing Users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    @Operation(summary = "Finds all users", description = "Finds all users", tags = "Users", responses = {
//            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json",
//                    array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))
//            }),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
//    })
//    public ResponseEntity<List<UserResponse>> obterTodos() {
//        List<UserDTO> users = userService.obterTodos();
//        List<UserResponse> usersResponse = users.stream().map(user -> new ModelMapper().map(user, UserResponse.class)).collect(Collectors.toList());
//
//        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    @Operation(summary = "Find a User", description = "Finds a User", tags = "Users", responses = {
//            @ApiResponse(description = "Success", responseCode = "200",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
//            ),
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
//    })
//    public ResponseEntity<Optional<UserResponse>> obterPorId(@PathVariable UUID id) {
//        Optional<UserDTO> userDto = userService.obterPorId(id);
//
//        return new ResponseEntity<>(Optional.of(new ModelMapper().map(userDto.get(), UserResponse.class)), HttpStatus.OK);
//    }
//
//    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    @Operation(summary = "Add a new user", description = "Add a new user by passing in a JSON or XML", tags = "Users", responses = {
//            @ApiResponse(description = "Created", responseCode = "201",
//                    content = @Content(schema = @Schema(implementation = UserResponse.class))
//            ),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
//    })
//    public ResponseEntity<UserResponse> adicionar(@RequestBody UserRequest user) {
//        UserDTO userDto = new UserDTO();
//        ModelMapper mapper = new ModelMapper();
//        userDto = mapper.map(user, UserDTO.class);
//        userDto = userService.adicionar(userDto);
//
//        return new ResponseEntity(mapper.map(userDto, UserResponse.class), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    @Operation(summary = "Deletes a User", description = "Deletes a User by passing in a JSON or XML", tags = "Users", responses = {
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
//    })
//    public ResponseEntity<?> deletar(@PathVariable UUID id){
//        userService.deletar(id);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    @Operation(summary = "Updates a User", description = "Updates a User by passing in a JSON or XML", tags = "Users", responses = {
//            @ApiResponse(description = "Success", responseCode = "200",
//                    content = @Content(schema = @Schema(implementation = UserResponse.class))
//            ),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
//    })
//    public ResponseEntity<UserResponse> atualizar(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
//        UserDTO userDto = new UserDTO();
//        ModelMapper mapper = new ModelMapper();
//        userDto = userService.atualizar(id, mapper.map(userRequest, UserDTO.class));
//
//        return new ResponseEntity<>(mapper.map(userDto, UserResponse.class), HttpStatus.OK);
//    }
//
//}
