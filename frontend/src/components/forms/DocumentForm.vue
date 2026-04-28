<template>
  <BaseCard title="Add New Document">
    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <input
          v-model="formData.title"
          placeholder="Document title (e.g., May 2025)"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <input
          v-model="formData.editor"
          placeholder="Editor name (e.g., Mr. Smith)"
          class="form-control"
          required
        />
      </div>
      <div class="d-flex gap-2">
        <BaseButton type="submit" variant="purple" icon="bi-file-plus">
          Add Document
        </BaseButton>
        <BaseButton type="button" variant="secondary" icon="bi-x-lg" @click="$emit('cancel')">
          Cancel
        </BaseButton>
      </div>
    </form>
  </BaseCard>
</template>

<script setup>
import { reactive } from 'vue'
import BaseCard from '../ui/BaseCard.vue'
import BaseButton from '../ui/BaseButton.vue'

const emit = defineEmits(['submit', 'cancel'])

const formData = reactive({
  title: '',
  editor: ''
})

const handleSubmit = () => {
  if (formData.title && formData.editor) {
    emit('submit', { ...formData })
    // Reset form
    formData.title = ''
    formData.editor = ''
  }
}
</script>
