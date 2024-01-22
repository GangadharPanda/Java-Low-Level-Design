package designpatterns.structural.adapter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestParameters {
	private long id;
	private String email;
	private String name;
	private Double amount;
}
