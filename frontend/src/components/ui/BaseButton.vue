<template>
  <button
    :class="buttonClasses"
    :disabled="disabled"
    @click="$emit('click', $event)"
  >
    <i v-if="icon" :class="[`bi ${icon}`, { 'me-2': $slots.default }]" aria-hidden="true"></i>
    <slot />
  </button>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  variant: {
    type: String,
    default: 'primary',
    validator: (value) => [
      'primary', 'secondary', 'success', 'danger', 'warning', 'info', 'light', 'dark',
      'outline-primary', 'outline-secondary', 'outline-success', 'outline-danger',
      'outline-warning', 'outline-info', 'outline-light', 'outline-dark',
      'purple' // Custom purple variant
    ].includes(value)
  },
  size: {
       type: String,
    default: 'md',
    validator: (value) => ['sm', 'md', 'lg'].includes(value)
  },
  disabled: {
    type: Boolean,
    default: false
  },
  icon: {
    type: String,
    default: '',
    validator: (value) => !value || value.startsWith('bi-')
  }
})

defineEmits(['click'])

const buttonClasses = computed(() => {
  const classes = ['btn']

  // Handle custom purple variant
  if (props.variant === 'purple') {
    classes.push('btn-purple')
  } else {
    classes.push(`btn-${props.variant}`)
  }

  // Add size class if not default
  if (props.size !== 'md') {
    classes.push(`btn-${props.size}`)
  }

  return classes.join(' ')
})
</script>

<style scoped>
/* Custom purple button styling */
.btn-purple {
  background-color: #872650;
  border-color: #872650;
  color: white;
}

.btn-purple:hover {
  background-color: #6d1e40;
  border-color: #6d1e40;
  color: white;
}

.btn-purple:focus,
.btn-purple.focus {
  box-shadow: 0 0 0 0.2rem rgba(135, 38, 80, 0.5);
}

.btn-purple:disabled,
.btn-purple.disabled {
  background-color: #872650;
  border-color: #872650;
  opacity: 0.65;
}
</style>