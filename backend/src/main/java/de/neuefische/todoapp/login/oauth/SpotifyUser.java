package de.neuefische.todoapp.login.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpotifyUser(
        @JsonProperty("id") String id,
        @JsonProperty("login") String login,
        @JsonProperty("email") String email,
        @JsonProperty("name") String name) {
}
