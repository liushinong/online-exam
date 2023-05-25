/*
 * @Author: lz 2930507471@qq.com
 * @Date: 2023-05-16 08:15:12
 * @LastEditors: lz 2930507471@qq.com
 * @LastEditTime: 2023-05-19 08:24:07
 * @FilePath: \online-exam-admin\src\api\remote-search.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request'

export function searchUser(name) {
  return request({
    url: '/vue-element-admin/search/user',
    method: 'get',
    params: { name }
  })
}

export function transactionList(query) {
  return request({
    url: '/vue-element-admin/transaction/list',
    method: 'get',
    params: query
  })
}
