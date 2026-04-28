<template>
  <div class="statistics-content">
    <!-- Statistics Table -->
    <div v-if="selectedClass && filteredStatistics.subjects.length > 0">
      <table class="table table-striped table-sm">
        <thead class="table-dark">
          <tr>
            <th scope="col"></th>
            <th scope="col" v-for="subject in filteredStatistics.subjects" :key="subject">
              {{ subject }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row" class="text-muted">Class Average</th>
            <td v-for="(avg, index) in filteredStatistics.classAverage" :key="index">
              {{ avg }}
            </td>
          </tr>
          <tr>
            <th scope="row" class="text-muted">Reached Norm</th>
            <td v-for="(norm, index) in filteredStatistics.reachedNorm" :key="index">
              <span :class="norm === 'yes' ? 'badge bg-success' : 'badge bg-danger'">
                {{ norm }}
              </span>
            </td>
          </tr>
          <tr>
            <th scope="row" class="text-muted">Passing %</th>
            <td v-for="(perc, index) in filteredStatistics.passingPerc" :key="index">
              {{ perc }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Empty States -->
    <div v-if="!selectedClass" class="text-center text-muted py-4">
      <p class="mb-0">Please select a class first to view statistics.</p>
    </div>

    <div v-else-if="selectedClass && filteredStatistics.subjects.length === 0" class="text-center text-muted py-4">
      <p class="mb-0">No statistics data available for {{ selectedClass.name }} yet.</p>
    </div>
  </div>
</template>

<script setup>
import { useClassData } from '../../composables/useClassData.js'

const { filteredStatistics, selectedClass } = useClassData()
</script>
