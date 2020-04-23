package spring.boot.rocks.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.boot.rocks.domain.Expertise;

@Getter
@Setter
@NoArgsConstructor
public class AppUserCommand {

	private Long id;
	private String username;
	private Integer userage;
	private Integer userssn;
	private Integer usertin;
	private String userdesignation;
	private String userblog;
	private String useraboutme;
	private Byte[] userpicture;
	private Expertise userexpertise;

}
