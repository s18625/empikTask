package com.empik.demo.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private Double calculations;

    @JsonCreator
    public UserDto(@JsonProperty("id") Long id,
                   @JsonProperty("login") String login,
                   @JsonProperty("name") String name,
                   @JsonProperty("type") String type,
                   @JsonProperty("avatar_url") String avatarUrl,
                   @JsonProperty("created_at") String createdAt,
                   @JsonProperty("followers") Long followers,
                   @JsonProperty("public_repos") Long public_repos
    ) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = ((6.0 / followers) * (2.0 + public_repos));
    }
}
