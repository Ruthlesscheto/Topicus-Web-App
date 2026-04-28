<template>
  <div :class="cardClasses" @click="handleClick">
    <div v-if="$slots.header || title" class="card-header d-flex justify-content-between align-items-center bg-violet">
      <slot name="header">
        <h2 class="card-title h5 mb-0 text-white">{{ title }}</h2>
      </slot>
      <slot name="header-actions" />
    </div>
    <div class="card-body p-3">
      <slot />
    </div>
    <div v-if="$slots.footer" class="card-footer">
      <slot name="footer" />
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  title: {
    type: String,
    default: ''
  },
  variant: {
    type: String,
    default: 'default',
    validator: (value) => ['default', 'primary', 'secondary', 'success', 'danger', 'warning', 'info', 'light', 'dark'].includes(value)
  },
  clickable: {
    type: Boolean,
    default: false
  },
  selected: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['click'])

const cardClasses = computed(() => {
  const classes = ['card', 'h-100']

  if (props.clickable) {
    classes.push('card-clickable')
  }

  if (props.selected) {
    classes.push('border-primary', 'bg-primary', 'bg-opacity-10')
  }

  return classes.join(' ')
})

const handleClick = (event) => {
  if (props.clickable) {
    emit('click', event)
  }
}
</script>

<style scoped>
/* Custom violet background for headers */
.bg-violet {
  background-color: #E8436E !important;
}

.card-clickable {
  cursor: pointer;
  transition: transform 0.2s ease;
}

.card-clickable:hover {
  transform: translateY(-2px);
}
</style>
