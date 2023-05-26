import request from '@/utils/request'

export function pageList (data) {
    return request({
        url: '/admin/exam/paper/answer/pageList',
        method: 'post',
        data
    })
}

export function pageListComplete (data) {
    return request({
        url: '/admin/exam/paper/answer/pageList/complete',
        method: 'post',
        data
    })
}

export function edit (data) {
    return request({
        url: '/admin/exam/paper/answer/edit',
        method: 'post',
        data
    })
}

export function read (id) {
    return request({
        url: '/admin/exam/paper/answer/read',
        method: 'post',
        params: {
            id
        }
    })
}