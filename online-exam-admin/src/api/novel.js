import request from '@/utils/request'

export function getNovelList (query) {
    return request({
        url: '/novel/getNovelList',
        method: 'post',
        data: query
    })
}

export function crawlNovel () {
    return request({
        url: '/novel/crawlNovel',
        method: 'post',
    })
}