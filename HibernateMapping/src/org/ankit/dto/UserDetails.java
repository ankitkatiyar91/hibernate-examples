package org.ankit.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "test_user_details")
@Table(name = "USER_Details")
public class UserDetails {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int userId;

	// @Column(name="USER_NAME",length=100,precision=1)
	
	private String userName;
	
	
	@OneToMany
	private Set<Address> addresses=new HashSet<Address>();
	
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
