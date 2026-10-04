<script setup lang="ts">
const props = defineProps<{
  query: string
  searchable: boolean
  sortDescending: boolean
}>()

const emit = defineEmits<{
  'update:query': [value: string]
  searchAll: []
  sort: []
  create: []
}>()

function updateQuery(event: Event) {
  emit('update:query', (event.target as HTMLInputElement).value)
}
</script>

<template>
  <div class="toolbar">
    <form v-if="searchable" class="search-cluster" @submit.prevent="emit('searchAll')">
      <label class="search-box">
        <span>FIND:</span>
        <input
          :value="props.query"
          type="search"
          placeholder="filter this page..."
          @input="updateQuery"
        />
      </label>
      <button class="xp-button" type="submit" :disabled="!props.query.trim()">SEARCH ALL</button>
    </form>
    <p v-else class="search-unavailable">NO STRING FIELDS TO SEARCH</p>

    <div class="toolbar-actions">
      <button class="xp-button" type="button" @click="emit('sort')">
        SORT: ID {{ sortDescending ? '↓' : '↑' }}
      </button>
      <button class="xp-button xp-button--primary" type="button" @click="emit('create')">
        + NEW RECORD
      </button>
    </div>
  </div>
</template>

<style scoped>
.toolbar {
  display: flex;
  align-items: stretch;
  justify-content: space-between;
  gap: 18px;
  margin-bottom: 8px;
}

.search-cluster {
  min-width: 0;
  display: flex;
  gap: 8px;
}

.search-unavailable {
  align-self: center;
  margin: 0;
  color: var(--brick);
  font:
    700 10px/1.4 'Courier New',
    monospace;
  text-transform: uppercase;
}

.search-box {
  min-width: min(430px, 100%);
  display: flex;
  align-items: center;
  border: 2px solid var(--ink);
  background: white;
}

.search-box span {
  align-self: stretch;
  padding: 10px 12px;
  display: flex;
  align-items: center;
  color: #fff1ca;
  background: var(--brick);
  border-right: 2px solid var(--ink);
  font:
    800 11px/1 'Courier New',
    monospace;
}

.search-box input {
  min-width: 0;
  flex: 1;
  padding: 9px 11px;
  border: 0;
  outline: none;
  color: var(--ink);
  background: white;
  font:
    700 12px/1 'Courier New',
    monospace;
}

.search-box:focus-within {
  box-shadow: 0 0 0 3px var(--yellow);
}

.toolbar-actions {
  display: flex;
  gap: 8px;
}

@media (max-width: 760px) {
  .toolbar,
  .search-cluster {
    align-items: stretch;
    flex-direction: column;
  }

  .search-box {
    min-width: 0;
  }

  .toolbar-actions button {
    flex: 1;
  }
}
</style>
