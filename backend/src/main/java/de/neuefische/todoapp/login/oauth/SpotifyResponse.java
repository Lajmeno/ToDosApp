package de.neuefische.todoapp.login.oauth;


import com.fasterxml.jackson.annotation.JsonProperty;

public record SpotifyResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("scope") String scope,
        @JsonProperty("token_type") String tokenType
) {

}