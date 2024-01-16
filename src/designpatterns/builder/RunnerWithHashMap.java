package designpatterns.builder;

import java.util.HashMap;
import java.util.Map;

public class RunnerWithHashMap {

	public static void main(String[] args) {
		Map<String, Object> requestParams = new HashMap<>();

		requestParams.put("name", "Gangadhar");
		requestParams.put("age", "33");
		StudentWithHashMap st = new StudentWithHashMap(requestParams);
		System.out.println(st.getName() + " is of age " + st.getAge());
	}
}
