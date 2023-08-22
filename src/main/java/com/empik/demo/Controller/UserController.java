package com.empik.demo.Controller;

import com.empik.demo.dto.UserDto;
import com.empik.demo.error.errors.UserDoesNotExistException;
import com.empik.demo.model.LoginInfo;
import com.empik.demo.repo.LoginInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private static final String GET_USER_URL = "https://api.github.com/users/%s";
    private final LoginInfoRepository loginInfoRepository;
    private final ObjectMapper mapper;


    @GetMapping("/{login}")
    public ResponseEntity<UserDto> getBuyerById(@PathVariable String login) {
        UserDto userDto = getResponse(login);
        saveRequestCount(login);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    private void saveRequestCount(String login) {
        Optional<LoginInfo> byId = loginInfoRepository.findById(login);
        LoginInfo loginInfo;
        if (byId.isPresent()) {
            loginInfo = byId.get();
            loginInfo.setRequestCount(loginInfo.getRequestCount() + 1);
        } else {
            loginInfo = LoginInfo.builder()
                    .login(login)
                    .requestCount(1L)
                    .build();
        }
        loginInfoRepository.save(loginInfo);
    }

    private UserDto getResponse(String login) {
        URL url;
        HttpURLConnection connection;
        UserDto userDto;
        try {
            url = new URL(String.format(GET_USER_URL, login));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            int status = connection.getResponseCode();
            if (status > 299) throw new UserDoesNotExistException(login);
            InputStream responseStream = connection.getInputStream();
            userDto = mapper.readValue(responseStream, UserDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }
}
