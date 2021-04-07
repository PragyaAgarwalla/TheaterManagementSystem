package com.virtualpairprogrammers.theater.api

import com.virtualpairprogrammers.theater.data.PerformanceRepository
import com.virtualpairprogrammers.theater.domain.Performance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PerformanceApiController {
    @Autowired
    lateinit var performanceRepository: PerformanceRepository

    @GetMapping("/performances")
    fun getPerformances() : List<Performance> =
        performanceRepository.findAll()

    @PostMapping("/performances")
    fun addPerformance(@Validated @RequestBody performance: Performance)  =
        performanceRepository.save(performance)

    @DeleteMapping("/performances/{id}")
    fun deletePerformance(@PathVariable(value = "id") performanceId : Long ) : ResponseEntity<Void> {
        return performanceRepository.findById(performanceId).map { performance ->
            performanceRepository.delete(performance)
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/performances/{id}")
    fun UpdatePerformance(@PathVariable("id")performanceId : Long, @Validated @RequestBody newPerformance : Performance) : ResponseEntity<Performance> {
        return performanceRepository.findById(performanceId).map { existingPerformance -> val updatedPerformance : Performance = existingPerformance
            .copy(title = newPerformance.title)
            ResponseEntity.ok().body(performanceRepository.save(updatedPerformance))
        }.orElse(ResponseEntity.notFound().build())
    }
}