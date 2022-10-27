package com.example.fashionblog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Bae Fashion Blog",
                version = "1.0.0",
                description = "A fashion blog API for interacting with users, basically for comments and likes"
        ),
        servers = {
                @Server(url = "http://localhost:8080/api/v1"),
                @Server(url = "http://localhost:9090/api/v1")
        },
        tags = {
                @Tag(name = "Users", description = "These are the endpoints relating to users"),
                @Tag(name = "Product", description = "These are the endpoints relating to products"),
                @Tag(name = "Post", description = "These are the endpoints relating to posts"),
                @Tag(name = "Comment", description = "These are the endpoints relating to comments"),
                @Tag(name = "Likes", description = "These are the endpoints relating to post likes")
        }
)
public class BeaBlog {

    public static void main(String[] args) {
        SpringApplication.run(BeaBlog.class, args);
    }

}
