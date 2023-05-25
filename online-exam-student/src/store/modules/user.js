import Cookies from 'js-cookie'

const actions = {}


const mutations = {
    changeLogin: (state, user) => {
        state.token = user.token
        Cookies.set('token', user.token)
    }
}

const state = {
    token: Cookies.get('token') ? Cookies.get('token') : ''
}

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
