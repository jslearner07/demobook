package com.ak.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = Book.FIND_ALL, query = "SELECT b FROM Book b")
})
public class Book implements Serializable {


    public static final String FIND_ALL = "Book.findAll";
    private static final float VAT_RATE = 5.5f;

    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TKSK_SURVEY_GEN")
    @SequenceGenerator(name="SEQ_TKSK_SURVEY_GEN", sequenceName="SEQ_TKSK_SURVEY_ID", allocationSize = 1)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(length = 17,name = "ISBN")
    private String isbn;

    @Column(length = 50,name = "NAME")
    @NotNull
    private String name;

    @Column(length = 300,name = "DESCRIPTION")
    private String description;

    @Column(length = 255,name = "PICTUREURL")
    private String pictureURL;

    @Column(length = 13)
    @Min(0)
    private Float price;

    @Transient
    private Float vat;

    @Column(name = "AUTHOR")
    private String author;
    
    @Column(name = "CREATION_DATE", updatable = false)
    private Date creationDate;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
        if (price != null) {
            vat = price * VAT_RATE / 100;
        }

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Float getVat() {
        return vat;
    }
    
    public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (id != null)
            result += "id: " + id;
        result += ", version: " + version;
        if (isbn != null && !isbn.trim().isEmpty())
            result += ", isbn: " + isbn;
        if (name != null && !name.trim().isEmpty())
            result += ", name: " + name;
        if (description != null && !description.trim().isEmpty())
            result += ", description: " + description;
        if (pictureURL != null && !pictureURL.trim().isEmpty())
            result += ", pictureURL: " + pictureURL;
        if (price != null)
            result += ", price: " + price;
        if (author != null && !author.trim().isEmpty())
            result += ", author: " + author;
        return result;
    }
}