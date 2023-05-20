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

const actions = {
    initSubject ({ commit }, action) {
        let data = {
            "pageNum": 1,
            "pageSize": 100,
            "params": {
                "teacherId": 2,
                "name": ""
            }
        }
        list(data).then(res => {
            commit('setSubjects', res.data.content)
            if (action !== undefined) {
                action()
            }
        })
    }
}

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