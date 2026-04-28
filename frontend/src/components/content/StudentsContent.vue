<template>
  <div class="students-content">
    <!-- Add Student Form -->
    <StudentForm 
      v-if="showForm" 
      @submit="handleAddStudent" 
      @cancel="showForm = false"
      class="mb-3"
    />

    <!-- Students List -->
    <div v-if="filteredStudents.length > 0" class="row g-2">
      <div v-for="student in filteredStudents" :key="student.id" class="col-md-6">
        <BaseCard>
          <div class="text-center">
            <span class="small">{{ student.name }}</span>
          </div>
        </BaseCard>
      </div>
    </div>

    <!-- Empty States -->
    <div v-if="!selectedClass && !showForm" class="text-center text-muted py-4">
      <p class="mb-0">Please select a class first to view and add students.</p>
    </div>

    <div v-else-if="selectedClass && filteredStudents.length === 0 && !showForm" class="text-center text-muted py-4">
      <p class="mb-0">No students in {{ selectedClass.name }} yet. Click "+Add Student" to get started.</p>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useClassData } from '../../composables/useClassData.js'
import StudentForm from '../forms/StudentForm.vue'
import BaseCard from '../ui/BaseCard.vue'

const props = defineProps({
  showAddForm: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['form-visibility-change'])

const { filteredStudents, selectedClass, addStudent } = useClassData()

const showForm = ref(props.showAddForm)

// Watch for prop changes
watch(() => props.showAddForm, (newVal) => {
  showForm.value = newVal
})

// Watch for form visibility changes and emit to parent
watch(showForm, (newVal) => {
  emit('form-visibility-change', newVal)
})

const handleAddStudent = async (studentName) => {
  if (!selectedClass.value) {
    alert('Please select a class first before adding students.')
    return
  }
  
  try {
    const result = await addStudent(studentName)
    if (result) {
      showForm.value = false
      return result // Return the result for the StudentForm component
    }
  } catch (error) {
    console.error('Error adding student:', error)
    return null
  }
}
</script>
