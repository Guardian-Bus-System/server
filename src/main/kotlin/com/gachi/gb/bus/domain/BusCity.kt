package com.gachi.gb.bus.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

//TODO: 안동시, 구미시, 대구시 등 시, 도 큰 단위의 table
@Entity
class BusCity (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int?,

  @Column(nullable = false, updatable = true, unique = true)
  var cityName: String,

  @OneToMany(mappedBy = "busCity")
  var towns: MutableList<BusTown>?
) {
}