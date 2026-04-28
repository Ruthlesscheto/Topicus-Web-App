<template>
  <BaseCard title="Add New Student">
    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <input
          v-model="studentName"
          placeholder="Student name (e.g., John Doe)"
          class="form-control"
          required
        />
      </div>
      <div v-if="error" class="alert alert-danger mb-3">
        {{ error }}
      </div>
      <div v-if="success" class="alert alert-success mb-3">
        {{ success }}
      </div>
      <div class="d-flex gap-2">
        <BaseButton type="submit" variant="purple" icon="bi-person-plus" :disabled="isSubmitting">
          {{ isSubmitting ? 'Adding...' : 'Add Student' }}
        </BaseButton>
        <BaseButton type="button" variant="secondary" icon="bi-x-lg" @click="$emit('cancel')">
          Cancel
        </BaseButton>
      </div>
    </form>
  </BaseCard>
</template>

<script setup>
import { ref } from 'vue'
import BaseCard from '../ui/BaseCard.vue'
import BaseButton from '../ui/BaseButton.vue'

const emit = defineEmits(['submit', 'cancel'])

const studentName = ref('')
const error = ref('')
const success = ref('')
const isSubmitting = ref(false)

const handleSubmit = async () => {
  if (studentName.value.trim()) {
    error.value = ''
    success.value = ''
    isSubmitting.value = true
    
    try {
      const result = await emit('submit', studentName.value.trim())
      if (result) {
        success.value = `Student ${studentName.value.trim()} added successfully!`
        studentName.value = ''
      }
    } catch (err) {
      error.value = `Error: ${err.message}`
    } finally {
      isSubmitting.value = false
    }
  }
}
</script>
