<script setup>
import { ref, provide } from 'vue'
import QuadrantCard from '../components/QuadrantCard.vue'
import BaseButton from '../components/ui/BaseButton.vue'

// Global state for selected class
const selectedClass = ref(null)

// Provide the selected class to all child components
provide('selectedClass', selectedClass)

// Function to handle class selection
const selectClass = (classData) => {
selectedClass.value = classData
}

// Provide the select function to child components
provide('selectClass', selectClass)
</script>

<template>
  <div class="app-container">
    <!-- Top Navigation Bar -->
    <header class="navbar navbar-expand-lg navbar-dark bg-secondary">
      <div class="container-fluid">
        <div class="navbar-brand">
          <h1 class="h4 mb-0">topicus</h1>
        </div>
        <nav class="d-flex">
          <BaseButton
              variant="outline-light"
              size="sm"
              class="me-2"
              icon="bi-bell"
              aria-label="Notifications"
          />
          <BaseButton
              variant="outline-light"
              size="sm"
              icon="bi-person-circle"
              aria-label="Profile"
          />
        </nav>
      </div>
    </header>

    <!-- Main Content Area with 4 Quadrants -->
    <main class="container-fluid py-4 bg-light min-vh-100">
      <div class="row g-3">
        <div class="col-md-6">
          <QuadrantCard
              title="Classes"
              add-button-text="Add Class"
              type="classes"
          />
        </div>
        <div class="col-md-6">
          <QuadrantCard
              :title="selectedClass ? `Students - ${selectedClass.name}` : 'Students - Select a class'"
              add-button-text="Add Student"
              type="students"
          />
        </div>
        <div class="col-md-6">
          <QuadrantCard
              :title="selectedClass ? `Documents - ${selectedClass.name}` : 'Documents - Select a class'"
              add-button-text="Add Document"
              type="documents"
          />
        </div>
        <div class="col-md-6">
          <QuadrantCard
              :title="selectedClass ? `Statistics - ${selectedClass.name}` : 'Statistics - Select a class'"
              type="statistics"
          />
        </div>
      </div>
    </main>
  </div>
</template>