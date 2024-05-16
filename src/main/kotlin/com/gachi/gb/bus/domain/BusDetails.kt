package com.gachi.gb.bus.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class BusDetails (
  @Id
  //구미
  var name: String,

  //옥계, 구미역
  var titleLine: String,

  //상세 설명
  var detailsLine: String? = null
) {
  
}
