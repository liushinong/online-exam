import request from '@/utils/request'

export function pageList (data) {
    return request({
        url: '/student/exam/paper/pageList',
        method: 'post',
        data
    })
}

export function select(id) {
    return request({
        url: 'student/exam/paper/select',
        method: 'post',
        params: {
            id
        }
    })
}