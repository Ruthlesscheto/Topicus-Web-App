<template>
  <BaseCard title="Add New Class">
    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <input
          v-model="formData.name"
          placeholder="Class name (e.g., class 5)"
          class="form-control"
          required
        />
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <input
            v-model="formData.teacherFirstName"
            placeholder="Teacher first name"
            class="form-control"
            required
          />
        </div>
        <div class="col-md-6 mb-3">
          <input
            v-model="formData.teacherLastName"
            placeholder="Teacher last name"
            class="form-control"
            required
          />
        </div>
      </div>
      <div class="d-flex gap-2">
        <BaseButton type="submit" variant="purple" icon="bi-plus-lg">
          Add Class
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
  name: '',
  teacherFirstName: '',
  teacherLastName: ''
})

const handleSubmit = () => {
  if (formData.name && formData.teacherFirstName && formData.teacherLastName) {
    emit('submit', { ...formData })
    // Reset form
    formData.name = ''
    formData.teacherFirstName = ''
    formData.teacherLastName = ''
  }
}
</script>
