import { computed, reactive, ref, watch } from 'vue'
import { defineStore } from 'pinia'

import { ENTITY_DEFINITIONS } from '@/domain/entitySchemas'
import { createMockCollections } from '@/domain/mockData'
import type { DataTabId, EntityRecord, RecordMutation, TabId } from '@/domain/types'

const PAGE_SIZE = 5

export const useDatabaseStore = defineStore('database', () => {
  const collections = reactive(createMockCollections())
  const activeTab = ref<TabId>('movies')
  const query = ref('')
  const databaseQuery = ref<string | null>(null)
  const currentPage = ref(0)
  const sortDescending = ref(false)
  const selectedEntityType = ref<DataTabId | null>(null)
  const selectedRecordId = ref('')

  const activeEntityType = computed<DataTabId>(() =>
    activeTab.value === 'operations' ? 'movies' : activeTab.value,
  )
  const activeDefinition = computed(() => ENTITY_DEFINITIONS[activeEntityType.value])
  const activeRecords = computed(() => collections[activeEntityType.value])
  const isOperations = computed(() => activeTab.value === 'operations')
  const windowTitle = computed(() =>
    isOperations.value ? 'SPECIAL OPERATIONS' : activeDefinition.value.title,
  )
  const windowStatus = computed(() => {
    if (isOperations.value) return '05 actions available'
    const count = String(activeRecords.value.length).padStart(2, '0')
    return `${count} ${activeDefinition.value.statusNoun} online`
  })

  function matchesSearch(record: EntityRecord, search: string) {
    return activeDefinition.value.searchableKeys.some((key) =>
      (record[key] ?? '').toLowerCase().includes(search),
    )
  }

  const databaseRows = computed(() => {
    const search = databaseQuery.value?.toLowerCase()
    return search
      ? activeRecords.value.filter((record) => matchesSearch(record, search))
      : activeRecords.value
  })
  const pageCount = computed(() => Math.max(1, Math.ceil(databaseRows.value.length / PAGE_SIZE)))
  const currentRows = computed(() => {
    const start = currentPage.value * PAGE_SIZE
    return databaseRows.value.slice(start, start + PAGE_SIZE)
  })
  const visibleRows = computed(() => {
    const search = query.value.trim().toLowerCase()
    return !search || search === databaseQuery.value?.toLowerCase()
      ? currentRows.value
      : currentRows.value.filter((record) => matchesSearch(record, search))
  })

  function clearSearch() {
    query.value = ''
    databaseQuery.value = null
    currentPage.value = 0
  }

  function selectTab(tab: TabId) {
    activeTab.value = tab
  }

  function searchAll() {
    const search = query.value.trim()
    if (!search) return
    databaseQuery.value = search
    currentPage.value = 0
  }

  function changePage(offset: number) {
    const nextPage = currentPage.value + offset
    if (nextPage >= 0 && nextPage < pageCount.value) currentPage.value = nextPage
  }

  function toggleSort() {
    sortDescending.value = !sortDescending.value
    const direction = sortDescending.value ? -1 : 1
    activeRecords.value.sort((left, right) => left.id.localeCompare(right.id) * direction)
    currentPage.value = 0
  }

  function openRecord(id: string) {
    selectedEntityType.value = activeEntityType.value
    selectedRecordId.value = id
  }

  function closeRecord() {
    selectedEntityType.value = null
    selectedRecordId.value = ''
  }

  function saveRecord({ entityType, id, record }: RecordMutation) {
    const current = collections[entityType].find((item) => item.id === id)
    if (current) Object.assign(current, record)
  }

  function deleteRecord(entityType: DataTabId, id: string) {
    const records = collections[entityType]
    const index = records.findIndex((record) => record.id === id)
    if (index >= 0) records.splice(index, 1)
    currentPage.value = Math.min(currentPage.value, pageCount.value - 1)
  }

  watch(activeTab, () => {
    clearSearch()
    sortDescending.value = false
  })

  return {
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
    selectTab,
    searchAll,
    clearSearch,
    changePage,
    toggleSort,
    openRecord,
    closeRecord,
    saveRecord,
    deleteRecord,
  }
})
