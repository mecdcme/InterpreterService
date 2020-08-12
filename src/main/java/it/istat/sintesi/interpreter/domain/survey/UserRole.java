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
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;

@Entity
@Table(name = "mb_user_roles")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ID_SEQ_MB_USER_ROLES", sequenceName = "SEQ_MB_USER_ROLES")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_MB_USER_ROLES")
	@Column(name = "user_role_id")
	private Long userroleid;

	@OneToOne
	@JoinColumn(name = "userid")
	@JsonBackReference
	private User user;

	@Column(name = "role")
	private String role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(Long userroleid) {
		this.userroleid = userroleid;
	}

}
