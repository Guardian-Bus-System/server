package com.gachi.gb.bus.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

//구미 인동의 천생초앞, 인동육교와 같이 TODO:마을 또는 포인트 단위의 table
@Entity
class BusVillage (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int?,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bus_Town")
  @JsonIgnore
  var busTown: BusTown,

  //구미
  @Column(nullable = false, updatable = true, unique = false)
  var villageName: String
) {

}
