import request from '@/utils/request'

export function getTypeList (query) {
    return request({
        url: '/type/getAllType',
        method: 'post',
        data: query
    })
}

export function addType (query) {
    return request({
        url: '/type/addType',
        method: 'post',
        params: query
    })
}

export function delType (query) {
    return request({
        url: '/type/delType',
        method: 'post',
        params: query
    })
}