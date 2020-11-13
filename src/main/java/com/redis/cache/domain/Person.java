package com.redis.cache.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person implements Serializable {
	private int id;
	private String name;
	private String city;
	private int age;
}
