package com.gachi.gb.bus.repository

import com.gachi.gb.bus.domain.BusTown
import org.springframework.data.jpa.repository.JpaRepository

interface BusTownRepository: JpaRepository<BusTown, Int> {
}