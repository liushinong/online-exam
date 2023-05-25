import request from '@/utils/request'

export function list (id) {
  return request({
    url: '/student/education/subject/list',
    method: 'post',
    params: {
      id: id
    }
  })
}