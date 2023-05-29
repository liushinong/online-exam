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