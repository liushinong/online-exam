import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/admin/subject/page',
    method: 'post',
    data
  })
}