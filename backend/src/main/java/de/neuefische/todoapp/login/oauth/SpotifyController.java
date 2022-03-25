package de.neuefische.todoapp.login.oauth;

import de.neuefische.todoapp.login.service.JwtService;
import de.neuefische.todoapp.login.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;



@RestController
@RequestMapping("/callback")
public class SpotifyController {
    private static final String ACCESS_TOKEN_URL = "https://accounts.spotify.com/api/token";

    private final RestTemplate restTemplate;
    private final String spotifyClientId;
    private final String spotifyAuthSecret;
    private final UserService userService;
    private final JwtService jwtUtils;

    SpotifyController(RestTemplate restTemplate, @Value("${spotify.client.id}") String spotifyClientId, @Value("${spotify.client.secret}") String spotifyAuthSecret, UserService userService, JwtService jwtUtils) {
        this.restTemplate = restTemplate;
        this.spotifyClientId = spotifyClientId;
        this.spotifyAuthSecret = spotifyAuthSecret;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public void callbackUrl(@RequestParam String code, HttpServletResponse response) throws ServletException, IOException {
        //ResponseEntity<SpotifyResponse> accessTokenResponse = restTemplate.postForEntity(ACCESS_TOKEN_URL + "?client_id=" + spotifyClientId + "&client_secret=" + spotifyAuthSecret + "&code=" + code, null, SpotifyResponse.class);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", "http://localhost:5000/callback");
        HttpHeaders headers = createGetTokenHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<SpotifyResponse> accessTokenResponse = restTemplate.exchange(
                ACCESS_TOKEN_URL,
                HttpMethod.POST,
                request,
                SpotifyResponse.class
        );



        ResponseEntity<SpotifyUser> userResponse = restTemplate.exchange(
                "https://api.spotify.com/v1/me",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(accessTokenResponse.getBody().accessToken())),
                SpotifyUser.class
        );



        //userService.saveUser(new UserDocument(userResponse.getBody().email(), null, null, userResponse.getBody().id(), "USER"));

        //response.sendRedirect("/courses?jwt=" + jwtUtils.createToken(new HashMap<>(), userResponse.getBody().email()));

    }

    /*

    private String getFirstname(String name) {
        if (name == null) {
            return null;
        }

        String[] nameComponents = name.split(" ");
        if (nameComponents.length > 1) {
            return Arrays.stream(nameComponents).limit(nameComponents.length-1).collect(Collectors.joining(" "));
        }
        return name;
    }

    private String getLastname(String name) {
        if (name == null) {
            return null;
        }

        String[] nameComponents = name.split(" ");
        if (nameComponents.length > 1) {
            return nameComponents[nameComponents.length-1];
        }
        return name;
    }
    */




    HttpHeaders createHeaders(String token){
        return new HttpHeaders() {{
            String authHeader = "Bearer " + token;
            set( "Authorization", authHeader );
        }};
    }

    HttpHeaders createGetTokenHeaders(){
        return new HttpHeaders() {{
            String forHeader = spotifyClientId+":"+spotifyAuthSecret;
            String encodedString = Base64.getEncoder().encodeToString(forHeader.getBytes());
            set( "Authorization", "Basic " + encodedString );
            setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }};
    }

}
