package org.ankit.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "test_user_details")
@Table(name = "USER_Details")
public class UserDetails {

	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "USER_ID")
	private int userId;

	// @Column(name="USER_NAME",length=100,precision=1)
	
	private String userName;

	@Temporal(TemporalType.DATE)
	private Date joinedDate;

	
	private String description;

	@ElementCollection
	@GenericGenerator(name="myGen",strategy="hilo")
	@CollectionId(columns = { @Column(name="address_id") }, generator = "myGen", type = @Type(type="long"))
	private Collection<Address> addresses=new HashSet<Address>();
	
	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName
				+ ", joinedDate=" + joinedDate + ", description=" + description
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((joinedDate == null) ? 0 : joinedDate.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		System.out.println("UserDetails.hashCode()  "+result);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("UserDetails.equals() "+obj);
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetails other = (UserDetails) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (joinedDate == null) {
			if (other.joinedDate != null)
				return false;
		} else if (!joinedDate.equals(other.joinedDate))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
