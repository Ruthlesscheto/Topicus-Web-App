<script setup>
import { ref } from 'vue'
import BaseCard from './ui/BaseCard.vue'
import BaseButton from './ui/BaseButton.vue'
import ClassesContent from './content/ClassesContent.vue'
import StudentsContent from './content/StudentsContent.vue'
import DocumentsContent from './content/DocumentsContent.vue'
import StatisticsContent from './content/StatisticsContent.vue'



// Define props for the component
const props = defineProps({
  title: {
    type: String,
    default: 'Quadrant Title'
  },
  addButtonText: {
    type: String,
    default: 'Add Item'
  },
  type: {
    type: String,
    default: 'default',
    validator: (value) => ['classes', 'students', 'documents', 'statistics', 'default'].includes(value)
  }
})

// Form visibility state
const showForm = ref(false)

// Handle add button clicks
const handleAddClick = () => {
  showForm.value = true
}

// Handle form visibility changes from child components
const handleFormVisibilityChange = (isVisible) => {
  showForm.value = isVisible
}
</script>

<template>
  <BaseCard :title="title">
    <template #header-actions>
      <BaseButton
        v-if="addButtonText && type !== 'statistics'"
        @click="handleAddClick"
        variant="purple"
        size="sm"
        icon="bi-plus-lg"
      >
        {{ addButtonText }}
      </BaseButton>
    </template>

    <!-- Dynamic Content Based on Type -->
    <ClassesContent
      v-if="type === 'classes'"
      :show-add-form="showForm"
      @form-visibility-change="handleFormVisibilityChange"
    />

    <StudentsContent
      v-else-if="type === 'students'"
      :show-add-form="showForm"
      @form-visibility-change="handleFormVisibilityChange"
    />

    <DocumentsContent
      v-else-if="type === 'documents'"
      :show-add-form="showForm"
      @form-visibility-change="handleFormVisibilityChange"
    />

    <StatisticsContent
      v-else-if="type === 'statistics'"
    />

    <!-- Default Content -->
    <div v-else class="default-content">
      <slot>
        <div class="placeholder-content">
          <p class="placeholder-text">Add your content here</p>
        </div>
      </slot>
    </div>
  </BaseCard>
</template>

<style scoped>
/* Placeholder styles for default content */
.placeholder-content {
  text-align: center;
  color: #6c757d;
  padding: 2rem;
}

.placeholder-text {
  margin: 0;
  font-style: italic;
}
</style>
