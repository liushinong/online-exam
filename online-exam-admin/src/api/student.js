import request from '@/utils/request'

export function findStudent(data) {
    return request({
        url: '/admin/user/findByPage',
        method: 'post',
        data
    })
}
export function findSubjectBT(data) {
    return request({
        url: '/admin/subject/page',
        method: 'post',
        data
    })
}