package com.gachi.gb.bus.repository

import com.gachi.gb.bus.domain.BusVillage
import org.springframework.data.jpa.repository.JpaRepository

interface BusVillageRepository: JpaRepository<BusVillage, Int> {
}