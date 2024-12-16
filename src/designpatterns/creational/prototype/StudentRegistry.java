package designpatterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistry {
	private Map<String, Student> registry = new HashMap<>();

	public Student get(String key) {
		return registry.get(key);
	}

	public void add(String key, Student obj) {
		registry.put(key, obj);
	}
}
