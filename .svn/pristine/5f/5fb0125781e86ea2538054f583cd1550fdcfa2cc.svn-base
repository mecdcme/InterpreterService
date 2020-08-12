package it.istat.sintesi.interpreter.domain.survey;

/**
 * Copyright 2017 ISTAT
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence. You may
 * obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * Licence for the specific language governing permissions and limitations under
 * the Licence.
 *
 * @author Francesco Amato <framato @ istat.it>
 * @author Mauro Bruno <mbruno @ istat.it>
 * @author Stefano Macone <stefano.macone @ istat.it>
 * @author Andrea Pagano <andrea.pagano @ istat.it>
 * @author Paolo Pizzo <papizzo @ istat.it>
 * @version 1.0
 */
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "mb_users")
@DynamicUpdate
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ID_SEQ_MB_USERS", sequenceName = "SEQ_MB_USERS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_MB_USERS")
	@Column(name = "userid")
	private Long userid;

	@Column(name = "password", length = 100)
	private String password;

	@Email
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "surname", length = 100)
	private String surname;

	@OneToOne(mappedBy = "user", orphanRemoval = true)
	@JsonManagedReference
	private UserRole role;

	public User() {

	}

	public User(String email, String fullname) {
		this.email = email;

	}

	public User(Long userid, String email, String fullname) {
		this.userid = userid;
		this.email = email;

	}

	public User(User user) {
		this.userid = user.userid;
		this.name = user.name;
		this.surname = user.surname;
		this.email = user.email;
		this.password = user.password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + userid + ", email='" + email + '\'' + ", passwordHash='" + password + '\'' + '}';
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
