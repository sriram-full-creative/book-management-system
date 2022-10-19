package com.fullcreative.bms.models;

import java.util.LinkedList;

import com.google.gson.annotations.Expose;

public class Book {

	@Expose(serialize = true, deserialize = true)
	private String id;
	@Expose(serialize = true, deserialize = true)
	private LinkedList<String> author;
	@Expose(serialize = true, deserialize = true)
	private LinkedList<String> publication;
	@Expose(serialize = true, deserialize = true)
	private String title;
	@Expose(serialize = true, deserialize = true)
	private String language;
	@Expose(serialize = true, deserialize = true)
	private Integer pages;
	@Expose(serialize = true, deserialize = true)
	private Integer releaseYear;
	@Expose(serialize = true, deserialize = true)
	private String country;
	@Expose(serialize = true, deserialize = true)
	private String coverImage;
	@Expose(serialize = true, deserialize = true)
	private Integer rating;

	/** Getters and Setters **/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LinkedList<String> getAuthor() {
		return author;
	}

	public void setAuthor(LinkedList<String> string) {
		this.author = string;
	}

	public LinkedList<String> getPublication() {
		return publication;
	}

	public void setPublication(LinkedList<String> publication) {
		this.publication = publication;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", publication=" + publication + ", title=" + title
				+ ", language=" + language + ", pages=" + pages + ", releaseYear=" + releaseYear + ", country="
				+ country + ", coverImage=" + coverImage + ", rating=" + rating + "]";
	}


}
