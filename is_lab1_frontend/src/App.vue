<script setup lang="ts">
import AppFooter from '@/components/layout/AppFooter.vue'
import AppHeader from '@/components/layout/AppHeader.vue'
import ErrorStack from '@/components/layout/ErrorStack.vue'
import DatabaseView from '@/views/DatabaseView.vue'
</script>

<template>
  <div class="site-shell">
    <AppHeader />
    <main class="workspace"><DatabaseView /></main>
    <AppFooter />
    <ErrorStack />
  </div>
</template>
