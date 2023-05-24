import request from '@/utils/request'

export function answerSubmit (data) {
    return request({
        url: '/student/exam/paper/answer/answerSubmit',
        method: 'post',
        data
    })
}