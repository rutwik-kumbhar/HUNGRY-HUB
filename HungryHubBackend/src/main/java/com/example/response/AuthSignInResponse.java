package com.example.response;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthSignInResponse {
	Map<String, String> user = new HashMap<>();
	private String jwt;
	private String message;
}
