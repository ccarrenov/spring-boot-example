package cl.ccarrenov.mvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    private int code;
    private String name;
    private int countryCode;
}