package com.aeClub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "id_user", nullable = false)
	private int idUser;

	@Column(name = "name", nullable = false, length = 64)
	private String name;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
//	@Column(name = "age", nullable = false, columnDefinition = "smallint")
//	private int age;
//
//	/**
//	 * The field <i>city</i> get we from text field, not from list of cities
//	 */
//	@Column(name = "city", nullable = false, length = 64)
//	private String city;
//
//	/**
//	 * The field <i>denom</i> get we from list of denomination We store information
//	 * about users denomination in database as a number.
//	 * 
//	 * @see Account#getDenomString()
//	 */
//	@Column(name = "denom", nullable = false, columnDefinition = "smallint")
//	private int denom;
//	/**
//	 * We store information about users gender in database as a number. 0: "Woman"
//	 * 1:"Man"
//	 * 
//	 * @see Account#getGenderString()
//	 */
//	@Column(name = "gender", nullable = false, columnDefinition = "smallint")
//	private int gender;
//
//	/**
//	 * 
//	 */
//
//	@Column(name = "photo_link", nullable = false, length = 64)
//	private String photoLink;
//
//	@Transient
//	private String genderString, denomString;
//
//	@Transient
//	private int tab;
//
//	/**
//	 * The method receives string value from numerical value <i>gender</i>. 0:
//	 * "Woman" 1:"Man"
//	 * 
//	 * @return String
//	 */
//	public String getGenderString() {
//		return (gender == 0) ? "Woman" : "Man";
//	}
//
//	/**
//	 * The method receives string value from numerical value <i>denom</i>. 1:
//	 * "pyatidesyatniki" 2: "baptisty" 3: "harizmaty" 4: "adventisty" 5: "drugie
//	 * protestanty" 6: "ne protestanty" 7: "nothing"
//	 * 
//	 * @return String
//	 */
//	public String getDenomString() {
//		switch (denom) {
//		case 7:
//			return "nothing";
//		case 1:
//			return "pyatidesyatniki";
//		case 2:
//			return "baptisty";
//		case 3:
//			return "harizmaty";
//		case 4:
//			return "adventisty";
//		case 5:
//			return "drugie protestanty";
//		case 6:
//			return "ne protestanty";
//		}
//		return "nothing";
//	}
//
//	public int getTab() {
//		return tab;
//	}
//
//	public void setTab(int tab) {
//		this.tab = tab;
//	}
//
//	public int getIdUser() {
//		return idUser;
//	}
//
//	public void setIdUser(int idUser) {
//		this.idUser = idUser;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public int getDenom() {
//		return denom;
//	}
//
//	public void setDenom(int denom) {
//		this.denom = denom;
//	}
//
//	public int getGender() {
//		return gender;
//	}
//
//	public void setGender(int gender) {
//		this.gender = gender;
//	}
//
//	public String getPhotoName() {
//		return photoLink;
//	}
//
//	public void setPhotoName(String photoName) {
//		this.photoLink = photoName;
//	}
//
//	@Override
//	public String toString() {
//		return "Account [idUser=" + idUser + ", name=" + name + ", age=" + age + ", city=" + city + ", denom=" + denom
//				+ ", gender=" + gender + ", photoName=" + photoLink + ", smallPhotoName=" + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + age;
//		result = prime * result + ((city == null) ? 0 : city.hashCode());
//		result = prime * result + denom;
//		result = prime * result + gender;
//		result = prime * result + idUser;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Account other = (Account) obj;
//		if (age != other.age)
//			return false;
//		if (city == null) {
//			if (other.city != null)
//				return false;
//		} else if (!city.equals(other.city))
//			return false;
//		if (denom != other.denom)
//			return false;
//		if (gender != other.gender)
//			return false;
//		if (idUser != other.idUser)
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}

}