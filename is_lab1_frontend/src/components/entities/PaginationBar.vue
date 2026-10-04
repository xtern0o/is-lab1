<script setup lang="ts">
defineProps<{ page: number; pageCount: number }>()
const emit = defineEmits<{ previous: []; next: [] }>()
</script>

<template>
  <div class="pagination">
    <button class="text-button" type="button" :disabled="page === 0" @click="emit('previous')">
      ← prev
    </button>
    <span>
      Page: <strong>{{ String(page + 1).padStart(2, '0') }}</strong
      >/{{ String(pageCount).padStart(2, '0') }}
    </span>
    <button
      class="text-button"
      type="button"
      :disabled="page + 1 >= pageCount"
      @click="emit('next')"
    >
      next →
    </button>
  </div>
</template>

<style scoped>
.pagination {
  margin-top: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 17px;
  font:
    800 11px/1 'Courier New',
    monospace;
}

button:hover:not(:disabled) {
  color: var(--ink);
}

strong {
  padding: 2px 4px;
  color: #fff1ca;
  background: var(--brick);
  font-size: 14px;
}
</style>
