package rs.apps.nn.recipes.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS", schema = "recipes")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "user_name")
	@Length(min = 5, message = "*Your user name must have at least 5 characters")
	@NotEmpty(message = "*Please provide a user name")
	private String userName;

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "role")
	private String role;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	@Column(name = "enabled")
	private Boolean enabled;

//	@ManyToMany(cascade = CascadeType.MERGE)
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles;

}

//
//public class User {
//	@Id
//	@Column(name = "USERNAME")
//	private String username;
//
//	@Column(nullable = false, length = 64)
//	private String password;
//
//	@Column(name = "ENABLED", nullable = false)
//	private boolean enabled;
//
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//	private Set<Authority> authorities = new HashSet<>();
//
//	@Column(name = "first_name", nullable = false, length = 20)
//	private String firstName;
//
//	@Column(name = "last_name", nullable = false, length = 20)
//	private String lastName;
//	// Getter and Setter methods
//
//}

//
//
//package net.codejava;
//
//import javax.persistence.*;
// 
//@Entity
//@Table(name = "users")
//public class User {
//     
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//     
//    @Column(nullable = false, unique = true, length = 45)
//    private String email;
//     
//    @Column(nullable = false, length = 64)
//    private String password;
//     
//    @Column(name = "first_name", nullable = false, length = 20)
//    private String firstName;
//     
//    @Column(name = "last_name", nullable = false, length = 20)
//    private String lastName;
//     
//    // getters and setters are not shown   
//}
