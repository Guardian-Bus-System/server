package com.gachi.gb.bus.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

//구미의 인동과 같이 TODO: 작은 동, 읍, 면 단위의 table
@Entity
class BusTown (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int?,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bus_city_id")
  @JsonIgnore
  var busCity: BusCity,

  @Column(nullable = false, updatable = true, unique = false)
  var townName: String,

  @OneToMany(mappedBy = "busTown")
  var busVillages: MutableList<BusVillage>?
) {
}