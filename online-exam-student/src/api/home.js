import request from '@/utils/request'

export function getInfo (id) {
    return request({
        url: '/user/selectInformation',
        method: 'post',
        params: {
            studentId: id
        }
    })
}

export function pageList (data) {
    return request({
        url: '/user/selectStudentSubject',
        method: 'post',
        data
    })
}

export function joinSubject (data) {
    return request({
        url: '/student/education/subject/join',
        method: 'post',
        data
    })
}