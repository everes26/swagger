package me.dungpt.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class Controller {

    public static Map<Long, User> listUsers = new HashMap<>();

    @Operation(description = "Get information user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get user successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser() {
        List<User> list = new ArrayList<>(listUsers.values());
        log.info("--------------------------------");
        log.info("Get users successfully");
        list.forEach(user -> {
            log.info("UserId " + user.getId());
            log.info("UserName " + user.getName());
            log.info("UserEmail " + user.getEmail());
            log.info("Phone " + user.getPhone());
            log.info("- - - - - - - - - - -");
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(description = "Get information user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create user successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        listUsers.put(user.getId(),user);
        log.info("--------------------------------");
        log.info("create user successfully");
        log.info("UserId " + user.getId());
        log.info("UserName " + user.getName());
        log.info("UserEmail " + user.getEmail());
        log.info("Phone " + user.getPhone());
        return new ResponseEntity<>(listUsers.get(user.getId()), HttpStatus.CREATED);
    }

    @Operation(description = "Get information user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create user successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User response = listUsers.get(id);
        log.info("--------------------------------");
        log.info("Get user successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Delete information user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create user successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        listUsers.remove(id);
        log.info("--------------------------------");
        log.info("Delete user successfully");
        log.info("Id ", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
