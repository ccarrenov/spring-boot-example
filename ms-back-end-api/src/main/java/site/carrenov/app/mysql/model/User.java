package site.carrenov.app.mysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Size(min = 1, max = 12, message = "the size must be between 1 and 12")
	private Long id;
	
	@NotEmpty(message = "not empty")
	@Size(min = 1, max = 90, message = "the size must be between 1 and 90")
	@Column(nullable = false)
	private String name;

}
