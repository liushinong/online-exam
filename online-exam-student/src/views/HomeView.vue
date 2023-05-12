<template>
    <div class="home">
        <h1>首页</h1>
    </div>
</template>

<script>
// @ is an alias to /src

import { getInfo } from '@/api/user'

export default {
    name: 'HomeView',
    components: {},
    created() {
        getInfo().then((res) => {
            console.log(res)
        })
    },
}
</script>
