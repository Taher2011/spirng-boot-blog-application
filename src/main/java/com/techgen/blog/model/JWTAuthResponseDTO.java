package com.techgen.blog.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTAuthResponseDTO {
	private String accessToken;
	private String tokenType = "Bearer";

	public JWTAuthResponseDTO(String accessToken) {
		this.accessToken = accessToken;
	}
}
