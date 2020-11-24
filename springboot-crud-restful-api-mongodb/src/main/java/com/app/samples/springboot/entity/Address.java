package com.app.samples.springboot.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 */

public class Address {
	
	/** The city. */
	private String city;
	
	/** The state. */
	private String state;
	
	/** The country. */
	private String country;
	
	/** The zip code. */
	private long zipCode;
	
	/**
	 * Instantiates a new address.
	 */
	public Address() {
		super();
	}

	/**
	 * Instantiates a new address.
	 *
	 * @param city the city
	 * @param state the state
	 * @param country the country
	 * @param zipCode the zip code
	 */
	public Address(String city, String state, String country, long zipCode) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public long getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode the new zip code
	 */
	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

}
