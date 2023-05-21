<template>
  <div style="line-height: 1.8">
    <div v-if="qType == 1" v-loading="qLoading">
      <div class="q-title" v-html="question.title"></div>
      <div class="q-content">
        <span
          :key="item.id"
          v-for="item in question.items"
          class="q-item-contain"
        >
          <span class="q-item-prefix">{{ item.prefix }}</span>
          <span v-html="item.content" class="q-item-content"></span>
        </span>
      </div>
    </div>
    <div v-else-if="qType == 2" v-loading="qLoading">
      <div class="q-title" v-html="question.title"></div>
      <div class="q-content">
        <span
          :key="item.id"
          v-for="item in question.items"
          class="q-item-contain"
        >
          <span class="q-item-prefix">{{ item.prefix }}</span>
          <span v-html="item.content" class="q-item-content"></span>
        </span>
      </div>
    </div>
    <div v-else-if="qType == 3" v-loading="qLoading">
      <div
        class="q-title"
        v-html="question.title"
        style="display: inline; margin-right: 10px"
      ></div>
      <span>（</span>
      <span :key="item.id" v-for="item in question.items">
        <span v-html="item.content" class="q-item-content"></span>
      </span>
      <span>）</span>
    </div>
    <div v-else-if="qType == 4" v-loading="qLoading">
      <div class="q-title" v-html="question.title"></div>
    </div>
    <div v-else-if="qType == 5" v-loading="qLoading">
      <div class="q-title" v-html="question.title"></div>
    </div>
  </div>
</template>
<script>
export default {
  name: "QuestionShow",
  props: {
    question: {
      type: Object,
      default: function () {
        return {};
      },
    },
    qLoading: {
      type: Boolean,
      default: false,
    },
    qType: {
      type: Number,
      default: 0,
    },
  },
};
</script>

<style scoped lang="scss">
.q-title {
  margin: 0px 5px;

  p {
    display: inline !important;
  }
}

.q-content {
  p {
    display: inline !important;
  }
}

.q-item-prefix {
  margin: 5px 10px;
}

.q-item-contain {
  display: inline-block;
}

.q-item-content {
  margin: 5px;
  white-space: normal;

  p {
    display: inline !important;
  }
}
</style>
