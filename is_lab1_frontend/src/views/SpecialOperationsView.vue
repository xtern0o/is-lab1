<script setup lang="ts">
import danger from '@/assets/img/danger-18465_256.gif'
</script>

<template>
  <div class="operations-placeholder">
    <div class="warning-badge"><img :src="danger" alt="" /></div>
    <p class="eyebrow">AUTHORIZED PERSONNEL ONLY</p>
    <h2>SPECIAL OPERATIONS</h2>
    <p>This section will contain grouped statistics, advanced searches and batch Oscar awards.</p>
    <button class="xp-button xp-button--primary" type="button">ENTER OPERATIONS →</button>
  </div>
</template>

<style scoped>
.operations-placeholder {
  min-height: 405px;
  padding: 60px 24px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  background:
    repeating-linear-gradient(135deg, transparent 0 18px, rgba(183, 55, 37, 0.07) 18px 36px),
    var(--paper);
}

.warning-badge {
  width: 82px;
  height: 82px;
  overflow: hidden;
  margin-bottom: 18px;
  border: 3px solid var(--ink);
  border-radius: 50%;
  background: var(--yellow);
  box-shadow: 5px 5px 0 var(--brick);
}

.warning-badge img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.eyebrow {
  margin: 0 0 7px;
  color: var(--brick);
  font:
    800 11px/1 'Courier New',
    monospace;
  letter-spacing: 0.16em;
}

h2 {
  margin: 0 0 8px;
  font:
    900 34px/1 Georgia,
    serif;
}

p:not(.eyebrow) {
  max-width: 550px;
  margin: 0 0 24px;
  font:
    700 12px/1.5 'Courier New',
    monospace;
}
</style>
