import { list } from '@/api/subject'

const state = {
    subjects: []
}

const getters = {
    subjectEnumFormat: (state) => (key) => {
        for (let item of state.subjects) {
            if (item.id === key) {
                return item.name
            }
        }
        return null
    }
}

// actions
const actions = {
    initSubject ({ commit }) {
        list().then(re => {
            commit('setSubjects', re.response)
        })
    }
}

// mutations
const mutations = {
    setSubjects: (state, subjects) => {
        state.subjects = subjects
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
