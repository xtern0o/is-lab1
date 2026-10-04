<script setup lang="ts">
import { ENTITY_TABS } from '@/domain/entitySchemas'
import type { TabId } from '@/domain/types'

defineProps<{ activeTab: TabId }>()
const emit = defineEmits<{ select: [tab: TabId] }>()
</script>

<template>
  <nav class="tabs" aria-label="Database sections">
    <button
      v-for="tab in ENTITY_TABS"
      :key="tab.id"
      type="button"
      :class="{ active: activeTab === tab.id }"
      @click="emit('select', tab.id)"
    >
      {{ tab.label }}
    </button>
  </nav>
</template>

<style scoped>
.tabs {
  display: flex;
  overflow-x: auto;
  background: #df8a53;
  border-bottom: 2px solid var(--ink);
}

.tabs button {
  min-width: 120px;
  padding: 10px 18px;
  border: 0;
  border-right: 1px solid var(--ink);
  color: var(--ink);
  background: linear-gradient(#f2cb91, #daa064);
  box-shadow:
    inset 1px 1px #fff0cc,
    inset -1px -1px #a76848;
  font:
    800 12px/1 'Courier New',
    monospace;
  text-transform: uppercase;
  cursor: pointer;
}

.tabs button:hover {
  background: linear-gradient(#f8dba7, #e2ac70);
}

.tabs button.active {
  color: #fff1cb;
  background: linear-gradient(#b9422e, #8f2824);
  box-shadow:
    inset 1px 1px #d97850,
    inset -1px -1px #641e22;
}
</style>
