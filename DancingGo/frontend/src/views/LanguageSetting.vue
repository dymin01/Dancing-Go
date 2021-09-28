<template>
  <div id="header" class="d-flex justify-end m-3 pe-4" style="z-index:100;">
    <v-menu offset-y style="z-index:200;">
      <template v-slot:activator="{ on, attrs }">
        <i
        v-bind="attrs"
        v-on="on"
        id="icon" class="fas fa-language"></i>
      </template>
      <v-list>
        <v-list-item
          v-for="(lang, idx) in languages"
          :key="idx"
          link
          @click="setLanguage(lang.name)"
        >
          <v-list-item-title>{{ lang.name }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'LanguageSetting',
  data () {
    return {
      languages: [
        { name: '한국어' },
        { name: 'English' },
      ],
    }
  },
  methods: {
    ...mapActions(['changeLang']),
    setLanguage(lang) {
      this.changeLang(lang)
    }
  }
}
</script>

<style scoped>
#header {
  position: absolute;
  width: 100vw;
  max-width: 98%;
}
#icon {
  z-index: 100;
  color: white;
  font-size: 45pt;
  text-shadow: 0 0 10px purple;
}
#icon:hover {
  cursor: pointer;
}
</style>