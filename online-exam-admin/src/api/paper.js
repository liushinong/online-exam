import request from '@/utils/request'

export function pageList (data) {
    return request({
        url: '/admin/exam/paper/page',
        method: 'post',
        data
    })
}

export function select (id) {
    return request({
        url: '/admin/exam/paper/select',
        method: 'post',
        params: {
            id: id
        }
    })
}

export function edit (data) {
    return request({
        url: '/admin/exam/paper/edit',
        method: 'post',
        data
    })
}

export function del (id) {
    return request({
        url: '/admin/exam/paper/delete',
        method: 'post',
        params: {
            id: id
        }
    })
}