package com.spring.files.csv.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tutorials")
public class Outlier {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private Date date;

  private Double price;

  public Outlier(Date date, Double price) {
    this.date = date;
    this.price = price;
  }

  public Outlier() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Tutorial{" +
            "id=" + id +
            ", date=" + date +
            ", price=" + price +
            '}';
  }
}
