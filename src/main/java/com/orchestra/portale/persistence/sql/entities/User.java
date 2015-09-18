package com.orchestra.portale.persistence.sql.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="user")
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	private String fbUser;
	private String fbEmail;
        
        private Integer pin;
        
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

        /**
         * @return the role
         */
        public List<Role> getRoles() {
            return roles;
        }

        /**
         * @param role the role to set
         */
        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        /**
         * @return the fbUser
         */
        public String getFbUser() {
            return fbUser;
        }

        /**
         * @param fbUser the fbUser to set
         */
        public void setFbUser(String fbUser) {
            this.fbUser = fbUser;
        }

        /**
         * @return the fbEmail
         */
        public String getFbEmail() {
            return fbEmail;
        }

        /**
         * @param fbEmail the fbEmail to set
         */
        public void setFbEmail(String fbEmail) {
            this.fbEmail = fbEmail;
        }

        /**
         * @return the pin
         */
        public Integer getPin() {
            return pin;
        }

        /**
         * @param pin the pin to set
         */
        public void setPin(Integer pin) {
            this.pin = pin;
        }


}
