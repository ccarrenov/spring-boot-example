package site.carrenov.app.oracle.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class User  {

	@Id
	@Size(min = 1, max = 12, message = "the size must be between 1 and 12")	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	@SequenceGenerator(sequenceName = "user_id_seq", allocationSize = 1, name = "USER_ID_SEQ")
	private Long id;

	@NotEmpty(message = "not empty")
	@Size(min = 1, max = 90, message = "the size must be between 1 and 90")
	@Column(nullable = false)
	private String name;

}
