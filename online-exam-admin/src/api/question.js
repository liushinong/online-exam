import request from '@/utils/request'

export function pageList (data) {
  return request({
    url: '/question/page',
    method: 'post',
    data
  })
}

export function selectById (id) {
  return request({
    url: '/question/select',
    method: 'post',
    params: {
      id: id
    }
  })
}

export function edit (data) {
  return request({
    url: '/question/edit',
    method: 'post',
    data
  })
}

export function del (id) {
  return request({
    url: '/question/delete',
    method: 'post',
    params: {
      id: id
    }
  })
}