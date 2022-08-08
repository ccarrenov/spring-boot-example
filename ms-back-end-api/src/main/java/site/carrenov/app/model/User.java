package site.carrenov.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import site.carrenov.app.model.annotation.MapperAnnotation;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {

	@MapperAnnotation
	@JsonProperty("id")	
	private Long id;
	
	@MapperAnnotation	
	@JsonProperty("name")		
	private String name;
}
