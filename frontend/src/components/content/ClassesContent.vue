<template>
  <div class="classes-content">
    <!-- Add Class Form -->
    <ClassForm
      v-if="showForm"
      @submit="handleAddClass"
      @cancel="showForm = false"
      class="mb-3"
    />

    <!-- Classes List -->
    <div v-if="classesData.length > 0" class="row g-2">
      <div
        v-for="classItem in classesData"
        :key="classItem.id"
        class="col-md-6"
      >
        <BaseCard
          :clickable="true"
          :selected="selectedClass && selectedClass.id === classItem.id"
          @click="handleClassSelect(classItem)"
        >
          <h6 class="card-title mb-1">{{ classItem.name }}</h6>
          <p class="card-text small text-muted mb-0">{{ classItem.teacher }}</p>
        </BaseCard>
      </div>
    </div>

    <!-- Empty State -->
    <div v-if="classesData.length === 0 && !showForm" class="text-center text-muted py-4">
      <p class="mb-0">No classes added yet.</p>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useClassData } from '../../composables/useClassData.js'
import ClassForm from '../forms/ClassForm.vue'
import BaseCard from '../ui/BaseCard.vue'

const props = defineProps({
  showAddForm: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['form-visibility-change'])

const { classesData, selectedClass, addClass, handleClassSelect } = useClassData()

const showForm = ref(props.showAddForm)

// Watch for prop changes
watch(() => props.showAddForm, (newVal) => {
  showForm.value = newVal
})

// Watch for form visibility changes and emit to parent
watch(showForm, (newVal) => {
  emit('form-visibility-change', newVal)
})

const handleAddClass = async (classData) => {
  try {
    const result = await addClass(classData)
    if (result) {
      showForm.value = false
    }
  } catch (error) {
    console.error('Error adding class:', error)
  }
}
</script>
