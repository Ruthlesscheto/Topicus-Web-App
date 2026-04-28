<template>
  <div class="documents-content">
    <!-- Add Document Form -->
    <DocumentForm 
      v-if="showForm" 
      @submit="handleAddDocument" 
      @cancel="showForm = false"
      class="mb-3"
    />

    <!-- Documents List -->
    <div v-if="filteredDocuments.length > 0" class="list-group">
      <div v-for="doc in filteredDocuments" :key="doc.id" class="list-group-item">
        <div class="d-flex w-100 justify-content-between">
          <h6 class="mb-1">{{ doc.title }}</h6>
          <small>{{ doc.lastEdited }}</small>
        </div>
        <small class="text-muted">Edited by {{ doc.editor }}</small>
      </div>
    </div>

    <!-- Empty States -->
    <div v-if="!selectedClass && !showForm" class="text-center text-muted py-4">
      <p class="mb-0">Please select a class first to view and add documents.</p>
    </div>

    <div v-else-if="selectedClass && filteredDocuments.length === 0 && !showForm" class="text-center text-muted py-4">
      <p class="mb-0">No documents for {{ selectedClass.name }} yet. Click "+New Document" to get started.</p>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useClassData } from '../../composables/useClassData.js'
import DocumentForm from '../forms/DocumentForm.vue'

const props = defineProps({
  showAddForm: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['form-visibility-change'])

const { filteredDocuments, selectedClass, addDocument } = useClassData()

const showForm = ref(props.showAddForm)

// Watch for prop changes
watch(() => props.showAddForm, (newVal) => {
  showForm.value = newVal
})

// Watch for form visibility changes and emit to parent
watch(showForm, (newVal) => {
  emit('form-visibility-change', newVal)
})

const handleAddDocument = (documentData) => {
  if (!selectedClass.value) {
    alert('Please select a class first before adding documents.')
    return
  }
  addDocument(documentData)
  showForm.value = false
}
</script>
