// initial state
const state = {
    exam: {
        question: {
            typeEnum: [{ key: 1, value: '单选题' }, { key: 2, value: '多选题' }, { key: 3, value: '判断题' }, { key: 4, value: '填空题' }, { key: 5, value: '简答题' }],
            editUrlEnum: [{ key: 1, value: '/edit/singleChoice', name: '单选题' },
            { key: 2, value: '/edit/multipleChoice', name: '多选题' },
            { key: 3, value: '/edit/trueFalse', name: '判断题' },
            { key: 4, value: '/edit/gapFilling', name: '填空题' },
            { key: 5, value: '/edit/shortAnswer', name: '简答题' }]
        },

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

const format = function (array, key) {
    for (let item of array) {
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
