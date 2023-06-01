// initial state
const state = {

  exam: {
    question: {
      typeEnum: [{ key: 1, value: '单选题' }, { key: 2, value: '多选题' }, { key: 3, value: '判断题' }, { key: 4, value: '填空题' }, { key: 5, value: '简答题' }],
      editUrlEnum: [{ key: 1, value: '/edit/singleChoice', name: '单选题' },
        { key: 2, value: '/edit/multipleChoice', name: '多选题' },
        { key: 3, value: '/edit/trueFalse', name: '判断题' },
        { key: 4, value: '/edit/gapFilling', name: '填空题' },
        { key: 5, value: '/edit/shortAnswer', name: '简答题' }],
      answer: {
        doRightTag: [{ key: true, value: 'success' }, { key: false, value: 'danger' }, { key: null, value: 'warning' }],
        doRightEnum: [{ key: true, value: '正确' }, { key: false, value: '错误' }, { key: null, value: '待批改' }],
        doCompletedTag: [{ key: false, value: 'info' }, { key: true, value: 'success' }]
      }
    },
    examPaper: {
      paperTypeEnum: [{ key: 1, value: '固定试卷' }, { key: 2, value: '时段试卷' }, { key: 3, value: '任务试卷' }]
    }
  }

}

// getters
const getters = {
  enumFormat: (state) => (array, key) => {
    return format(array, key)
  }
}

// actions
const actions = {}

// mutations
const mutations = {}

const format = function(array, key) {
  for (const item of array) {
    if (item.key === key) {
      return item.value
    }
  }
  return null
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
