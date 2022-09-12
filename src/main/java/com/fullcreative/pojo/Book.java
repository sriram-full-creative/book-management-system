package com.fullcreative.pojo;

import java.util.LinkedList;

public class Book {
	private LinkedList<String> authors;
	private LinkedList<String> publication;
	private String title;
	private String language;
	private Integer pages;
	private Integer releaseYear;
	private String country;
	private String coverImage;
	private String bookLink;
	private Integer rating;

	public LinkedList<String> getAuthors() {
		return authors;
	}
	public void setAuthors(LinkedList<String> authors) {
		this.authors = authors;
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
	public String getBookLink() {
		return bookLink;
	}
	public void setBookLink(String bookLink) {
		this.bookLink = bookLink;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}


}
