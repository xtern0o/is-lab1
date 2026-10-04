<script setup lang="ts">
import { storeToRefs } from 'pinia'

import EntityTable from '@/components/entities/EntityTable.vue'
import EntityTabs from '@/components/entities/EntityTabs.vue'
import EntityToolbar from '@/components/entities/EntityToolbar.vue'
import PaginationBar from '@/components/entities/PaginationBar.vue'
import RecordDialog from '@/components/entities/RecordDialog.vue'
import { useDatabaseStore } from '@/stores/database'
import SpecialOperationsView from './SpecialOperationsView.vue'

const store = useDatabaseStore()
const {
  collections,
  activeTab,
  query,
  databaseQuery,
  currentPage,
  sortDescending,
  selectedEntityType,
  selectedRecordId,
  activeDefinition,
  isOperations,
  windowTitle,
  windowStatus,
  databaseRows,
  visibleRows,
  pageCount,
} = storeToRefs(store)

const {
  selectTab,
  searchAll,
  clearSearch,
  changePage,
  toggleSort,
  openRecord,
  closeRecord,
  saveRecord,
  deleteRecord,
} = store
</script>

<template>
  <div class="database-view">
    <section class="window" aria-labelledby="window-title">
      <div class="window-titlebar">
        <div class="window-orb" aria-hidden="true"></div>
        <h1 id="window-title">{{ windowTitle }}</h1>
        <span>{{ windowStatus }}</span>
      </div>

      <EntityTabs :active-tab="activeTab" @select="selectTab" />

      <SpecialOperationsView v-if="isOperations" />
      <div v-else class="database-content">
        <EntityToolbar
          v-model:query="query"
          :searchable="activeDefinition.searchableKeys.length > 0"
          :sort-descending="sortDescending"
          @search-all="searchAll"
          @sort="toggleSort"
        />

        <div v-if="databaseQuery" class="search-results" role="status">
          <span>
            DATABASE RESULTS FOR: <strong>“{{ databaseQuery }}”</strong> ·
            {{ databaseRows.length }} FOUND
          </span>
          <button class="text-button" type="button" @click="clearSearch">CLEAR ×</button>
        </div>

        <EntityTable :columns="activeDefinition.columns" :rows="visibleRows" @open="openRecord" />
        <PaginationBar
          :page="currentPage"
          :page-count="pageCount"
          @previous="changePage(-1)"
          @next="changePage(1)"
        />
      </div>
    </section>

    <RecordDialog
      v-if="selectedEntityType"
      :entity-type="selectedEntityType"
      :record-id="selectedRecordId"
      :collections="collections"
      @close="closeRecord"
      @save="saveRecord"
      @delete="deleteRecord"
    />
  </div>
</template>

<style scoped>
.window {
  width: min(1320px, 92vw);
  min-height: 535px;
  margin: 0 auto;
  background: var(--paper);
  border: 2px solid var(--ink);
  box-shadow: 7px 7px 0 rgba(92, 25, 24, 0.42);
}

.window-titlebar {
  min-height: 36px;
  padding: 5px 7px;
  display: grid;
  grid-template-columns: 22px 1fr auto;
  align-items: center;
  gap: 9px;
  color: #ffedc8;
  background: var(--burgundy);
  border-bottom: 2px solid var(--ink);
}

.window-titlebar h1 {
  margin: 0;
  font:
    800 13px/1 'Courier New',
    monospace;
  letter-spacing: 0.08em;
}

.window-titlebar > span {
  font:
    700 10px/1 'Courier New',
    monospace;
  text-transform: uppercase;
}

.window-orb {
  width: 19px;
  height: 19px;
  border: 2px solid #2c1110;
  border-radius: 50%;
  background: radial-gradient(circle at 35% 35%, #fff2ab 0 18%, var(--orange) 19% 55%, #732019 56%);
}

.database-content {
  padding: 18px;
}

.search-results {
  margin: 0 0 11px;
  padding: 7px 9px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  color: #fff1ca;
  background: var(--brick);
  border: 2px solid var(--ink);
  font:
    700 10px/1.3 'Courier New',
    monospace;
  text-transform: uppercase;
}

@media (max-width: 760px) {
  .window {
    box-shadow: 6px 6px 0 rgba(92, 25, 24, 0.5);
  }

  .window-titlebar > span {
    display: none;
  }
}
</style>
