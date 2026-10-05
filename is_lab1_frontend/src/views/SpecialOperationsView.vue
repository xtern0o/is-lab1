<script setup lang="ts">
import { ref } from 'vue'

import { showError } from '@/shared/errors'

const namePart = ref('')
const goldenPalmLimit = ref('')
const minimumLength = ref('')
const additionalOscars = ref('')

function requireParams(...params: Array<[string, string]>) {
  const missing = params.filter(([value]) => !value.trim()).map(([, label]) => label)
  if (missing.length > 0) {
    showError('не заполнены параметры', `введите: ${missing.join(', ')}`)
  }
}

function validateNameSearch() {
  requireParams([namePart.value, 'часть названия'])
}

function validateGoldenPalmSearch() {
  requireParams([goldenPalmLimit.value, 'количество golden_palm_count'])
}

function validateOscarAward() {
  requireParams(
    [minimumLength.value, 'минимальную длину'],
    [additionalOscars.value, 'количество Оскаров'],
  )
}
</script>

<template>
  <section class="operations-view">
    <div class="operations-list">
      <header class="operations-header">
        <strong>специальные функции по заданию там</strong>
      </header>

      <div class="operation-row">
        <p class="operation-name">
          <span>01</span>
          сгруппировать фильмы по количеству оскаров
        </p>
        <button class="xp-button" type="button">Выполнить</button>
      </div>

      <div class="operation-row">
        <p class="operation-name">
          <span>02</span>
          найти фильмы по части названия
        </p>
        <div class="operation-controls">
          <input
            v-model="namePart"
            type="search"
            placeholder="часть названия..."
            aria-label="Часть названия фильма"
          />
          <button class="xp-button" type="button" @click="validateNameSearch">Выполнить</button>
        </div>
      </div>

      <div class="operation-row">
        <p class="operation-name">
          <span>03</span>
          найти фильмы с меньшим количеством golden_palm_count
        </p>
        <div class="operation-controls">
          <input
            v-model="goldenPalmLimit"
            type="number"
            min="0"
            placeholder="количество..."
            aria-label="Количество Золотых пальм"
          />
          <button class="xp-button" type="button" @click="validateGoldenPalmSearch">
            Выполнить
          </button>
        </div>
      </div>

      <div class="operation-row">
        <p class="operation-name">
          <span>04</span>
          получить фильмы без оскара
        </p>
        <button class="xp-button" type="button">Выполнить</button>
      </div>

      <div class="operation-row operation-row--wide">
        <p class="operation-name">
          <span>05</span>
          наградить длинные фильмы дополнительными оскарами
        </p>
        <div class="operation-controls award-controls">
          <label>
            ДЛИНА БОЛЬШЕ
            <input v-model="minimumLength" type="number" min="1" placeholder="мин." />
          </label>
          <label>
            ДОБАВИТЬ
            <input v-model="additionalOscars" type="number" min="1" placeholder="оскаров" />
          </label>
          <button class="xp-button xp-button--primary" type="button" @click="validateOscarAward">
            Выполнить
          </button>
        </div>
      </div>
    </div>

    <section class="results-panel" aria-labelledby="results-title">
      <header class="results-header">
        <strong id="results-title">результат выполнения</strong>
        <span>ожидаем запрос...</span>
      </header>
      <div class="empty-results">
        <p>здесь будет таблица с результатом</p>
        <span>выбери действие выше</span>
      </div>
    </section>
  </section>
</template>

<style scoped>
.operations-view {
  padding: 18px;
}

.operations-list,
.results-panel {
  border: 2px solid var(--ink);
  background: #fff9e8;
}

.operations-header,
.results-header {
  min-height: 34px;
  padding: 8px 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  color: #ffedcb;
  background: var(--burgundy);
  font:
    800 10px/1.2 'Courier New',
    monospace;
}

.results-header span {
  color: #eebd78;
  font-size: 9px;
}

.operation-row {
  min-height: 58px;
  padding: 9px 11px;
  display: grid;
  grid-template-columns: minmax(280px, 1fr) auto;
  align-items: center;
  gap: 16px;
  border-bottom: 1px solid #a35237;
}

.operation-row:nth-child(odd) {
  background: #f6d9a4;
}

.operation-row:last-child {
  border-bottom: 0;
}

.operation-name {
  margin: 0;
  display: flex;
  align-items: center;
  gap: 11px;
  color: var(--ink);
  font:
    700 11px/1.35 'Courier New',
    monospace;
}

.operation-name span {
  min-width: 28px;
  padding: 5px 4px;
  color: #fff1ca;
  background: var(--brick);
  text-align: center;
  font-size: 10px;
}

.operation-controls {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 8px;
}

.operation-controls input {
  width: 210px;
  min-width: 0;
  padding: 8px 9px;
  border: 1px solid var(--ink);
  border-radius: 0;
  outline: none;
  color: var(--ink);
  background: #fffaf0;
  font:
    700 11px/1.2 'Courier New',
    monospace;
}

.operation-controls input:focus {
  box-shadow: 0 0 0 2px var(--yellow);
}

.operation-row > .xp-button,
.operation-controls > .xp-button {
  min-width: 106px;
}

.award-controls label {
  display: flex;
  align-items: center;
  gap: 6px;
  color: var(--brick);
  font:
    800 9px/1 'Courier New',
    monospace;
  white-space: nowrap;
}

.award-controls input {
  width: 90px;
}

.results-panel {
  margin-top: 16px;
}

.empty-results {
  min-height: 210px;
  display: grid;
  place-content: center;
  gap: 7px;
  text-align: center;
  background:
    repeating-linear-gradient(135deg, transparent 0 18px, rgba(183, 55, 37, 0.05) 18px 36px),
    #fff9e8;
}

.empty-results p,
.empty-results span {
  margin: 0;
  font-family: 'Courier New', monospace;
}

.empty-results p {
  color: var(--brick);
  font-size: 11px;
  font-weight: 800;
}

.empty-results span {
  color: #805a49;
  font-size: 10px;
}

@media (max-width: 900px) {
  .operation-row {
    grid-template-columns: 1fr;
    gap: 9px;
  }

  .operation-controls {
    justify-content: flex-start;
    flex-wrap: wrap;
  }
}

@media (max-width: 600px) {
  .operations-view {
    padding: 10px;
  }

  .operations-header span,
  .results-header span {
    display: none;
  }

  .operation-controls {
    align-items: stretch;
    flex-direction: column;
  }

  .operation-controls input,
  .operation-controls > .xp-button {
    width: 100%;
  }

  .award-controls label {
    justify-content: space-between;
  }

  .award-controls input {
    width: 55%;
  }
}
</style>
