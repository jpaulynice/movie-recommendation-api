package com.recommendation.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Movie entity for hibernate database persistence. Movie details can be fetched
 * from the IMDB API with a rest call like this:
 *
 * http://www.omdbapi.com/?t=planes
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@XmlRootElement
@Entity(name = "items")
public class Movie {
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	@XmlElement
	private MovieGenre genre;

	@XmlElement
	@Transient
	private int rank;

	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;

	@XmlElement
	@Column(name = "imdb_id")
	private String imdb_id;

	@XmlElement
	@Column(name = "name")
	private String name;

	@XmlElement
	@Column(name = "img")
	private String img;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "taste_item_similarity", joinColumns = { @JoinColumn(name = "item_id_a") }, inverseJoinColumns = {
			@JoinColumn(name = "item_id_b") })
	private Set<Movie> similarMovies = new HashSet<>();

	/**
	 * @return the genre
	 */
	public MovieGenre getGenre() {
		return genre;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the imdb_id
	 */
	public String getImdb_id() {
		return imdb_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the similarMovies
	 */
	public Set<Movie> getSimilarMovies() {
		return similarMovies;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(final MovieGenre genre) {
		this.genre = genre;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param imdb_id
	 *            the imdb_id to set
	 */
	public void setImdb_id(final String imdb_id) {
		this.imdb_id = imdb_id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imdb_id == null) ? 0 : imdb_id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Movie other = (Movie) obj;
		if (imdb_id == null) {
			if (other.imdb_id != null) {
				return false;
			}
		} else if (!imdb_id.equals(other.imdb_id)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Movie [genre=" + genre + ", id=" + id + ", imdb_id=" + imdb_id + ", name=" + name + ", img=" + img
				+ "]";
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
