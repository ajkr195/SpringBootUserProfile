package spring.boot.rocks.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private Integer userage;
	private Integer userssn;
	private Integer usertin;
	private String userdesignation;
	private String userblog;

	@Lob
	private String useraboutme;

	@Lob
	private Byte[] userpicture;

	@Enumerated(value = EnumType.STRING)
	private Expertise userexpertise;

}
